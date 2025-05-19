package com.example.mydiary.presentation.features.settings

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingsFragmentBinding
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.ButtonDelegate
import com.example.mydiary.presentation.adapters.delegates.LabelDelegate
import com.example.mydiary.presentation.adapters.delegates.ProfileDelegate
import com.example.mydiary.presentation.adapters.delegates.RemindDelegate
import com.example.mydiary.presentation.adapters.delegates.SettingParamDelegate
import com.example.mydiary.presentation.features.common.MainActivity
import com.example.mydiary.presentation.models.ButtonModel
import com.example.mydiary.presentation.models.LabelModel
import com.example.mydiary.presentation.models.ProfileModel
import com.example.mydiary.presentation.models.RemindModel
import com.example.mydiary.presentation.models.SettingParamModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private var binding: SettingsFragmentBinding? = null

    private val pickImageLauncher = registerForActivityResult(PickVisualMedia()) { uri: Uri? ->
        uri?.let {
            saveImageToAppDirectory(it)
            viewModel.changeSettings(
                viewModel.settings.value?.copy(imageUrl = uri.toString())
                    ?: SettingsModel(imageUrl = uri.toString())
            )
        }
    }

    @Inject
    lateinit var viewModel: SettingViewModel

    private lateinit var bottomSheetFragment: BottomSheetFragment

    private var adapters = AdapterWithDelegates(
        listOf(
            LabelDelegate(),
            ProfileDelegate(::onProfileClick),
            SettingParamDelegate(::onSwitchClick),
            RemindDelegate(::onDelete),
            ButtonDelegate(::onAddClick)
        )
    )

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view)
        bottomSheetFragment = BottomSheetFragment()

        (requireActivity() as MainActivity).mainActivityComponent.settingsComponent().create()
            .inject(this)

        setRecycleLogic()
        viewModel.getSettings()
        viewModel.getAllReminds()

        lifecycleScope.launch {
            launch {
                combine(
                    viewModel.settings,
                    viewModel.reminds
                ) { settings, remind -> settings to remind }
                    .collect {
                        Log.d(TAG, it.first.toString() + "  -  " + it.second.toString())
                        if (it.first != null && it.second != null) createScreen(
                            it.first!!,
                            it.second!!
                        )
                    }
            }
            launch {
                viewModel.settings.collect {
                    Log.d(TAG, it.toString() + "   ")
                    //createScreen(it.copy())
                }
            }
        }


        childFragmentManager.setFragmentResultListener(
            "requestKey", viewLifecycleOwner
        ) { key, bundle ->
            val firstString = bundle.getString("hour")
            val secondString = bundle.getString("minutes")
            Log.d("childFragmentManager", "firstString: $firstString, secondString: $secondString")
        }


    }


    private fun setRecycleLogic() {
        if (binding != null) with(binding!!.settingsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
            addItemDecoration(
                PaddingItemDecoration(
                    24, 0, 24, 24, R.layout.logbook_label
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    32, 32, 24, 24, R.layout.settings_profile
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    0, 0, 24, 24, R.layout.settings_param
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    16, 16, 24, 24, R.layout.settings_remind
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    0, 24, 24, 24, R.layout.settings_button
                )
            )
        }

    }

    private fun onSwitchClick() {
    }

    private fun onDelete(remindModel: RemindModel) {

    }


    private fun onProfileClick() {
        pickImageLauncher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }

    private fun onAddClick() {
        Log.d("childFragmentManager", "add")
        bottomSheetFragment.show(childFragmentManager, "tag")
    }

    private fun loadImageFromAppDirectory(): Bitmap? {
        val file = File(requireContext().filesDir, "images/image_profile.jpg")
        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }

    private fun saveImageToAppDirectory(imageUri: Uri) {
        val inputStream = requireContext().contentResolver.openInputStream(imageUri) ?: return

        val directory = File(requireContext().filesDir, "images")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val fileName = "image_profile.jpg"
        val file = File(directory, fileName)

        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        inputStream.close()
        outputStream.close()

        Log.d(TAG, "Сохранено в ${file.absolutePath}")
    }

    private fun createScreen(settings: SettingsModel, remindModels: List<RemindModel>) {
        Log.d(TAG, "createScreen")
        val temp = mutableListOf<Item>()
        temp.add(
            LabelModel(
                requireContext().getString(R.string.settings)
            )
        )
        temp.add(
            ProfileModel(
                if (settings.imageUrl.isNotEmpty()) {
                    Log.d(TAG, settings.imageUrl.isNotEmpty().toString())
                    loadImageFromAppDirectory()?.toDrawable(
                        resources
                    ) ?: AppCompatResources.getDrawable(
                        requireContext(),
                        if (settings.sex == 0) R.drawable.profile_girl else R.drawable.profileboy
                    )
                    ?: throw IllegalArgumentException("profile image failed")
                } else {
                    AppCompatResources.getDrawable(
                        requireContext(),
                        if (settings.sex == 0) R.drawable.profile_girl else R.drawable.profileboy
                    ) ?: throw IllegalArgumentException("profile image failed")
                }, settings.name

            )
        )

        temp.add(
            SettingParamModel(
                AppCompatResources.getDrawable(
                    requireContext(), R.drawable.setting_remind
                ) ?: throw IllegalArgumentException("remind image failed"),
                getString(R.string.send_reminds),
                settings.isSendRemindOn
            )
        )
        temp.plus(remindModels)
        temp.add(ButtonModel(getString(R.string.add_remind)))
        temp.add(
            SettingParamModel(
                AppCompatResources.getDrawable(
                    requireContext(), R.drawable.settings_fingerprint
                ) ?: throw IllegalArgumentException("fingerprint image failed"),
                getString(R.string.sign_in_with_fingerprint),
                false
            )
        )
        adapters.submitList(
            temp
        )
        Log.d(TAG, temp.toString())
    }

    companion object {
        private const val TAG = "SettingsFragment"
    }

}