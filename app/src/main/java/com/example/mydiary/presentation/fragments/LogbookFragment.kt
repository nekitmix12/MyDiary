package com.example.mydiary.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookFragmentBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.EmotionDelegate
import com.example.mydiary.presentation.adapters.delegates.LabelDelegate
import com.example.mydiary.presentation.adapters.delegates.LogbookCircleButtonDelegate
import com.example.mydiary.presentation.adapters.delegates.LogbookTopBarDelegate
import com.example.mydiary.presentation.models.CircleButtonModel
import com.example.mydiary.presentation.models.EmotionCardModel
import com.example.mydiary.presentation.models.LabelModel
import com.example.mydiary.presentation.models.LogbookTopBarModel

class LogbookFragment : Fragment(R.layout.logbook_fragment) {
    private lateinit var binding: LogbookFragmentBinding

    private var adapters = AdapterWithDelegates(
        listOf(
            LogbookTopBarDelegate(),
            LabelDelegate(),
            LogbookCircleButtonDelegate(::onAddClick),
            EmotionDelegate(::onEmotionCardClick),

            )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LogbookFragmentBinding.bind(view)
        with(binding.logbookRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
            addItemDecoration(
                PaddingItemDecoration(
                    16, 0, 24, 24, R.layout.logbook_top_bar
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    32, 0, 24, 24, R.layout.logbook_label
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    32, 32, 24, 24, R.layout.logbook_cycrle_button
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    8, 0, 24, 24, R.layout.logbook_emotion_card
                )
            )
        }
        adapters.submitList(
            listOf(
                LogbookTopBarModel(
                    "4 записи", "в день:" to "2 записи", "серия" to "0 дней"
                ),
                LabelModel(
                    requireContext().getString(R.string.what_are_you_listening_now)
                ),
                CircleButtonModel(
                    listOf(
                        Pair(
                            Pair(
                                ContextCompat.getColor(
                                    requireContext(), R.color.transparent

                                ), ContextCompat.getColor(
                                    requireContext(), R.color.nodes_selected_grey
                                )
                            ), 0.35f
                        )
                    ), true
                ),
                EmotionCardModel(
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.tools_card_background_blue
                    ) ?: throw IllegalArgumentException("Not found Drawable"),
                    "сегодня",
                    "14:36",
                    "усталость",
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.test_emotion_shell
                    ) ?: throw IllegalArgumentException("Not found Drawable")
                ),
                EmotionCardModel(
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.tools_card_background_green
                    ) ?: throw IllegalArgumentException("Not found Drawable"),
                    "вчера",
                    "14:08",
                    "спокойствие",
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.test_emotion_icon
                    ) ?: throw IllegalArgumentException("Not found Drawable")
                ),
                EmotionCardModel(
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.tools_card_background_yellow
                    ) ?: throw IllegalArgumentException("Not found Drawable"),
                    "воскресенье",
                    "16:12",
                    "продуктивность",
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.test_light
                    ) ?: throw IllegalArgumentException("Not found Drawable")
                ),
                EmotionCardModel(
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.tools_card_background_red
                    ) ?: throw IllegalArgumentException("Not found Drawable"),
                    "воскресенье",
                    "03:59",
                    "беспокойство",
                    AppCompatResources.getDrawable(
                        requireContext(), R.drawable.test_soft_flower
                    ) ?: throw IllegalArgumentException("Not found Drawable")
                ),
            )
        )

    }

    private fun onAddClick() {

    }

    private fun onEmotionCardClick(emotionCardModel: EmotionCardModel) {

    }

}