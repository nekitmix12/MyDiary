package com.example.mydiary.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mydiary.R
import com.example.mydiary.databinding.AddEmotionFragmentBinding
import com.example.mydiary.presentation.models.EmotionElementModel
import kotlinx.coroutines.launch

class AddEmotionFragment : Fragment(R.layout.add_emotion_fragment) {
    private lateinit var binding: AddEmotionFragmentBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddEmotionFragmentBinding.bind(view)
        navController = findNavController()

        lifecycleScope.launch {
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

        binding.exitButton.setOnClickListener {
            onExit()
        }


        binding.addLogsField.setEmotions(
            listOf(
                listOf(
                    EmotionElementModel(
                        "Ярость",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_red),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Зависть",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_red),


                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Выгорание",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_blue),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Депрессия",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_blue),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                listOf(
                    EmotionElementModel(
                        "Напряжение",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_red),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Беспокойство",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_red),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Усталость",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_blue),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Апатия",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_blue),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                listOf(
                    EmotionElementModel(
                        "Возбуждение",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_yellow),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Уверенность",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_yellow),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Спокойствие",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_green),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Благодарность",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_green),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                listOf(
                    EmotionElementModel(
                        "Восторг",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_yellow),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Счастье",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_yellow),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Удовлетворённость",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_green),

                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Защищённость",
                        "ощущение, что необходимо отдохнуть",
                        requireContext().getColor(R.color.card_green),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse221)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                )
        )

    }

    private fun onContinue(emotionElementModel: EmotionElementModel) {
        Log.d("emotionElementModel", "emotionElementModel: $emotionElementModel")
        navController.navigate(R.id.nodesFragment)
    }

    private fun onExit() {
        navController.popBackStack()
    }
}