package com.example.mydiary.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mydiary.R
import com.example.mydiary.databinding.AddRemindFragmentBinding
import com.example.mydiary.presentation.models.EmotionElementModel
import kotlinx.coroutines.launch

class AddEmotionFragment : Fragment(R.layout.add_remind_fragment) {
    private lateinit var binding: AddRemindFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddRemindFragmentBinding.bind(view)

        lifecycleScope.launch {
            binding.addLogsField.selectedItem.collect{
                if (it == null) {
                    binding.emotionToAdding.visibility = View.GONE
                    binding.descriptionEmotion.text = requireContext().getString(R.string.choose_emotion)
                } else {
                    binding.emotionToAdding.apply {
                        visibility = View.VISIBLE
                        text = it.emotion
                    }
                    binding.descriptionEmotion.text = it.description
                    binding.addButton.isActivated = true
                }
            }

        }




        binding.addLogsField.setEmotions(
            listOf(
                listOf(
                    EmotionElementModel(
                        "Ярость",
                        "ощущение, что необходимо отдохнуть",
                        Color.RED,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Зависть",
                        "ощущение, что необходимо отдохнуть",
                        Color.RED,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Выгорание",
                        "ощущение, что необходимо отдохнуть",
                        Color.BLUE,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Депрессия",
                        "ощущение, что необходимо отдохнуть",
                        Color.BLUE,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                listOf(
                    EmotionElementModel(
                        "Напряжение",
                        "ощущение, что необходимо отдохнуть",
                        Color.RED,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Беспокойство",
                        "ощущение, что необходимо отдохнуть",
                        Color.RED,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Усталость",
                        "ощущение, что необходимо отдохнуть",
                        Color.BLUE,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Апатия",
                        "ощущение, что необходимо отдохнуть",
                        Color.BLUE,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                listOf(
                    EmotionElementModel(
                        "Возбуждение",
                        "ощущение, что необходимо отдохнуть",
                        Color.YELLOW,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Уверенность",
                        "ощущение, что необходимо отдохнуть",
                        Color.YELLOW,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Спокойствие",
                        "ощущение, что необходимо отдохнуть",
                        Color.GREEN,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Благодарность",
                        "ощущение, что необходимо отдохнуть",
                        Color.GREEN,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                listOf(
                    EmotionElementModel(
                        "Восторг",
                        "ощущение, что необходимо отдохнуть",
                        Color.YELLOW,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Счастье",
                        "ощущение, что необходимо отдохнуть",
                        Color.YELLOW,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Удовлетворённость",
                        "ощущение, что необходимо отдохнуть",
                        Color.GREEN,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                    EmotionElementModel(
                        "Защищённость",
                        "ощущение, что необходимо отдохнуть",
                        Color.GREEN,
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                        AppCompatResources.getDrawable(requireContext(), R.drawable.test_ellipse)
                            ?: throw IllegalArgumentException("problem in Add logs"),
                    ),
                ),

                )
        )

    }
}