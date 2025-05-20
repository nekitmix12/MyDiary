package com.example.mydiary.presentation.features.add_emotion

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mydiary.R
import com.example.mydiary.databinding.AddEmotionFragmentBinding
import com.example.mydiary.presentation.features.common.MainActivity
import com.example.mydiary.presentation.models.EmotionElementModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddEmotionFragment : Fragment(R.layout.add_emotion_fragment) {
    private lateinit var binding: AddEmotionFragmentBinding
    private var navController: NavController? = null

    @Inject
    lateinit var viewModel: AddEmotionViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddEmotionFragmentBinding.bind(view)
        navController = findNavController()

        (requireActivity() as MainActivity).mainActivityComponent.addEmotionsComponent()

        lifecycleScope.launch {
            launch {
                binding.addLogsField.selectedItem.collect {
                    if (it == null) {
                        binding.emotionToAdding.visibility = View.GONE
                        binding.descriptionEmotion.text =
                            requireContext().getString(R.string.choose_emotion)
                    } else {
                        binding.emotionToAdding.apply {
                            visibility = View.VISIBLE
                            text = it.emotion
                            setTextColor(it.color)
                        }
                        binding.descriptionEmotion.text = it.description
                        binding.addButton.isActivated = true
                        binding.addButton.setOnClickListener { _ ->
                            onContinue(it)
                        }

                    }

                }
            }
            launch {
                viewModel.getEmotions()
            }
            launch {
                viewModel.emotions.collect {
                    if (it != null)
                        binding.addLogsField.setEmotions(it)

                }
            }
        }

        binding.exitButton.setOnClickListener {
            onExit()
        }


    }

    private fun onContinue(emotionElementModel: EmotionElementModel) {
        Log.d(TAG, "emotionElementModel: $emotionElementModel")
        AddEmotionFragmentDirections.actionAddEmotionFragmentToNodesFragment(emotionElementModel.emotionEnum.toString())
    }

    private fun onExit() {
        navController?.popBackStack()
    }

    companion object {
        private const val TAG = "AddEmotionFragment"
    }
}