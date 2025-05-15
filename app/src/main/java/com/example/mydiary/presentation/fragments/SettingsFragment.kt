package com.example.mydiary.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingsFragmentBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.ButtonDelegate
import com.example.mydiary.presentation.adapters.delegates.LabelDelegate
import com.example.mydiary.presentation.adapters.delegates.ProfileDelegate
import com.example.mydiary.presentation.adapters.delegates.RemindDelegate
import com.example.mydiary.presentation.adapters.delegates.SettingParamDelegate
import com.example.mydiary.presentation.models.ButtonModel
import com.example.mydiary.presentation.models.LabelModel
import com.example.mydiary.presentation.models.ProfileModel
import com.example.mydiary.presentation.models.RemindModel
import com.example.mydiary.presentation.models.SettingParamModel
import com.example.mydiary.presentation.view_models.SettingsViewModel
import java.util.UUID

class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding
    private lateinit var viewModel: SettingsViewModel
    private lateinit var bottomSheetFragment: BottomSheetFragment

    private var adapters = AdapterWithDelegates(
        listOf(
            LabelDelegate(),
            ProfileDelegate(),
            SettingParamDelegate(::onSwitchClick),
            RemindDelegate(::onDelete),
            ButtonDelegate(::onAddClick)
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view)
        bottomSheetFragment = BottomSheetFragment()

        with(binding.settingsRecycler) {
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

        adapters.submitList(
            listOf(
                LabelModel(
                    requireContext().getString(R.string.settings)
                ), ProfileModel(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.mountain)
                        ?: throw IllegalArgumentException("profile image failed"), "Иван Иванов"
                ), SettingParamModel(
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.setting_remind
                    ) ?: throw IllegalArgumentException("profile image failed"),
                    "Присылать напоминания",
                    true
                ), RemindModel(
                    UUID.randomUUID().toString(), "20:00"
                ), ButtonModel(requireContext().getString(R.string.add_remind)), SettingParamModel(
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.settings_fingerprint
                    ) ?: throw IllegalArgumentException("profile image failed"),
                    "Вход по отпечатку пальца",
                    false
                )
            )
        )

        childFragmentManager.setFragmentResultListener(
            "requestKey",
            viewLifecycleOwner
        ) { key, bundle ->
            val firstString = bundle.getString("hour")
            val secondString = bundle.getString("minutes")
            Log.d("childFragmentManager", "firstString: $firstString, secondString: $secondString")
        }


    }


    private fun onSwitchClick() {
    }

    private fun onDelete(remindModel: RemindModel) {

    }

    private fun onAddClick() {
        Log.d("childFragmentManager","add")
        bottomSheetFragment.show(childFragmentManager, "tag")
    }

    private fun onAddAnswerButtonClick() {

    }
}