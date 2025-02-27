package com.example.mydiary.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.mydiary.R
import com.example.mydiary.databinding.StatisticsFragmentBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.decorators.LineItemDecoration
import com.example.mydiary.presentation.adapters.decorators.MarginItemDecoration
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.EmotionByDayDelegate
import com.example.mydiary.presentation.adapters.delegates.EmotionCategoryDelegate
import com.example.mydiary.presentation.adapters.delegates.MoodyDuringDayDelegate
import com.example.mydiary.presentation.adapters.delegates.VerticalItemWithLabelDelegate
import com.example.mydiary.presentation.models.EmotionByDayModel
import com.example.mydiary.presentation.models.EmotionCategoryModel
import com.example.mydiary.presentation.models.Gap
import com.example.mydiary.presentation.models.MoodyDuringDayModel
import com.example.mydiary.presentation.models.VerticalItemWithLabelModel

class StatisticsFragment : Fragment() {
    private lateinit var binding: StatisticsFragmentBinding
    private var adapters = AdapterWithDelegates(
        listOf(
            EmotionCategoryDelegate(),
            MoodyDuringDayDelegate(),
            VerticalItemWithLabelDelegate(
                listOf(
                    EmotionByDayDelegate(),

                    )
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.statistics_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StatisticsFragmentBinding.bind(view)

        with(binding.statisticsRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.statisticsRecycler)

        with(binding) {
            tabLayout.addTab(tabLayout.newTab().setText("17–23 фев"))
            tabLayout.addTab(tabLayout.newTab().setText("10–16 фев"))
            tabLayout.addTab(tabLayout.newTab().setText("3–9 фев"))
            tabLayout.addTab(tabLayout.newTab().setText("27 янв – 2 фев"))
        }

        with(binding.statisticsRecycler) {
            addItemDecoration(
                MarginItemDecoration(
                    0,
                    70,
                    0,
                    0,
                    R.layout.statistics_emotion_by_category
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    24,
                    0,
                    24,
                    24,
                    R.layout.statistics_emotion_by_category,
                    R.layout.statistics_mood_during_day,
                    R.layout.statistics_emotions,
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    12,
                    12,
                    0,
                    0,
                    R.layout.emotion_logs
                )
            )

        }

        adapters.submitList(
            listOf(
                EmotionCategoryModel(
                    listOf(
                        Pair(0f, 0f),
                        Pair(2f, 0.55f),
                        Pair(1f, 0f),
                        Pair(3f, 0.75f),
                    ),
                    "5 записей"
                ),
                VerticalItemWithLabelModel(
                    listOf(
                        EmotionByDayModel(
                            "Понедельник\n17 фев",
                            "Спокойствие\nПродуктивность\nСчастье",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.test_emotion_icon
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.test_light
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.test_ellipse
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                            ),
                            40.dpToPx(requireContext())
                        ),
                        EmotionByDayModel(
                            "Вторник\n18 фев",
                            "Выгорание\nУсталость",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.summertime_sadness
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.test_emotion_shell
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),

                                ),
                            40.dpToPx(requireContext())
                        ),
                        EmotionByDayModel(
                            "Среда\n19 фев",
                            "",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.nothing_emotion
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                            ),
                            40.dpToPx(requireContext())
                        ),
                        EmotionByDayModel(
                            "Четверг\n20 фев",
                            "",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.nothing_emotion
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                            ),
                            40.dpToPx(requireContext())
                        ),
                        EmotionByDayModel(
                            "Пятница\n21 фев",
                            "",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.nothing_emotion
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                            ),
                            40.dpToPx(requireContext())
                        ),
                        EmotionByDayModel(
                            "Суббота\n22 фев",
                            "",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.nothing_emotion
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                            ),
                            40.dpToPx(requireContext())
                        ),
                        EmotionByDayModel(
                            "Воскресенье\n23 фев",
                            "",
                            listOf(
                                AppCompatResources.getDrawable(
                                    requireContext(),
                                    R.drawable.nothing_emotion
                                )
                                    ?: throw IllegalArgumentException("statistics fragment: can't load image"),
                            ),
                            40.dpToPx(requireContext())
                        ),

                        ),
                    requireContext().getString(R.string.emotions_by_day_of_the_week)
                ),
                MoodyDuringDayModel(
                    listOf(
                        listOf(1f, 0f, 0f, 0f),
                        listOf(0f, 0.5f, 0.5f, 0f),
                        listOf(0f, 1f, 0f, 0f),
                        listOf(0f, 0f, 0f, 1f),
                        listOf(0f, 0f, 0f, 0f),
                    ), listOf(1, 2, 1, 1, 0)
                )
            )
        )

    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}