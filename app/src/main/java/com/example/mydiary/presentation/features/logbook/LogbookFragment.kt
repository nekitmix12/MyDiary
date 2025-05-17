package com.example.mydiary.presentation.features.logbook

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookFragmentBinding
import com.example.mydiary.domain.model.Result
import com.example.mydiary.presentation.MainActivity
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.EmotionDelegate
import com.example.mydiary.presentation.adapters.delegates.LabelDelegate
import com.example.mydiary.presentation.adapters.delegates.LogbookCircleButtonDelegate
import com.example.mydiary.presentation.adapters.delegates.LogbookTopBarDelegate
import com.example.mydiary.presentation.models.EmotionCardModel
import com.example.mydiary.presentation.models.LabelModel
import com.example.mydiary.presentation.models.LogbookTopBarModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogbookFragment : Fragment(R.layout.logbook_fragment) {
    private lateinit var binding: LogbookFragmentBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var viewModel: LogbookViewModel

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
        navController = findNavController()
        Log.d(TAG, "onViewCreated")
        viewModel.loadScreen(requireContext())
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
        lifecycleScope.launch {
            viewModel.screenModel.collect {
                when (it) {
                    is Result.Loading -> {
                        Log.d(TAG, "Loading")
                    }

                    is Result.Success -> {
                        Log.d(TAG, it.data.toString())
                        val tempList = mutableListOf<Item>()
                        tempList.add(
                            LogbookTopBarModel(
                                logs = "${it.data.logsCount} " + resources.getQuantityString(
                                    R.plurals.record,
                                    it.data.logsCount
                                ),
                                logsInTime = getString(R.string.in_day) + ": " to "${it.data.logInDay} " + resources.getQuantityString(
                                    R.plurals.record,
                                    it.data.logInDay
                                ),
                                streak = getString(R.string.streak) + ": " to "${it.data.logsStreak}  ${
                                    resources.getQuantityString(
                                        R.plurals.days,
                                        it.data.logsStreak
                                    )
                                }"
                            )
                        )
                        tempList.add(
                            LabelModel(
                                requireContext().getString(R.string.what_are_you_listening_now)
                            )
                        )
                        tempList.add(
                            it.data.loadingEmotions
                        )
                        tempList.plus(it.data.emotions)
                        adapters.submitList(tempList)
                    }

                    is Result.Error -> {
                        Log.d(TAG, it.exception)

                    }
                }

            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity)
            .mainActivityComponent
            .logBookComponent()
            .create()
            .inject(this)
    }

    private fun onAddClick() {
        navController.navigate(R.id.addEmotionFragment)
    }

    private fun onEmotionCardClick(emotionCardModel: EmotionCardModel) {
        Log.d("emotionCardModel", "emotionCardModel: $emotionCardModel")
        val action =
            LogbookFragmentDirections.actionLogbookFragmentToNodesFragment(emotionCardModel.id)
        navController.navigate(action)
    }

    companion object {
        const val TAG = "LogbookFragment"
    }
}

/*

adapters.submitList(
listOf(
LogbookTopBarModel(
"4 записи", "в день: " to "2 записи", "серия: " to "0 дней"
),
LabelModel(
requireContext().getString(R.string.what_are_you_listening_now)
),
CircleButton(
listOf(
Pair(
Pair(
ContextCompat.getColor(
requireContext(), R.color.logbook_gradient_green_2

), ContextCompat.getColor(
requireContext(), R.color.logbook_gradient_green_1
)
), 0.25f
),
Pair(
Pair(
ContextCompat.getColor(
requireContext(), R.color.transparent

), ContextCompat.getColor(
requireContext(), R.color.nodes_selected_grey
)
), 0.50f
),
Pair(
Pair(
ContextCompat.getColor(
requireContext(), R.color.logbook_gradient_yellow_1

), ContextCompat.getColor(
requireContext(), R.color.logbook_gradient_yellow_2
)
), 0.50f
),
), true
),
EmotionCardModel(
id = "1",
AppCompatResources.getDrawable(
requireContext(), R.drawable.tools_card_background_blue
) ?: throw IllegalArgumentException("Not found Drawable"),
"сегодня",
"14:36",
"усталость",
requireContext().getColor(R.color.card_blue),
AppCompatResources.getDrawable(
requireContext(), R.drawable.test_emotion_shell
) ?: throw IllegalArgumentException("Not found Drawable")
),
EmotionCardModel(
id = "1",
AppCompatResources.getDrawable(
requireContext(), R.drawable.tools_card_background_green
) ?: throw IllegalArgumentException("Not found Drawable"),
"вчера",
"14:08",
"спокойствие",
requireContext().getColor(R.color.card_green),
AppCompatResources.getDrawable(
requireContext(), R.drawable.test_emotion_icon
) ?: throw IllegalArgumentException("Not found Drawable")
),
EmotionCardModel(
id = "1",
AppCompatResources.getDrawable(
requireContext(), R.drawable.tools_card_background_yellow
) ?: throw IllegalArgumentException("Not found Drawable"),
"воскресенье",
"16:12",
"продуктивность",
requireContext().getColor(R.color.card_yellow),
AppCompatResources.getDrawable(
requireContext(), R.drawable.test_light
) ?: throw IllegalArgumentException("Not found Drawable")
),
EmotionCardModel(
id = "1",
AppCompatResources.getDrawable(
requireContext(), R.drawable.tools_card_background_red
) ?: throw IllegalArgumentException("Not found Drawable"),
"воскресенье",
"03:59",
"беспокойство",
requireContext().getColor(R.color.card_red),
AppCompatResources.getDrawable(
requireContext(), R.drawable.test_soft_flower
) ?: throw IllegalArgumentException("Not found Drawable")
),
)
)*/
