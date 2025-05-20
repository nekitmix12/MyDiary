package com.example.mydiary.presentation.features.notes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.NotesFragmentBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.EmotionDelegate
import com.example.mydiary.presentation.adapters.delegates.ExitDelegate
import com.example.mydiary.presentation.adapters.delegates.QuestionBlockDelegate
import com.example.mydiary.presentation.features.AddVariantDialogFragment
import com.example.mydiary.presentation.features.settings.SettingViewModel
import com.example.mydiary.presentation.models.AnswerModel
import com.example.mydiary.presentation.models.EmotionCardModel
import com.example.mydiary.presentation.models.ExitModel
import com.example.mydiary.presentation.models.QuestionBlockModel
import java.util.UUID
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment), AddVariantDialogFragment.OnInputListener {
    private lateinit var binding: NotesFragmentBinding

    @Inject
    lateinit var viewModel: SettingViewModel
    private lateinit var navController: NavController

    private var adapters = AdapterWithDelegates(
        listOf(
            EmotionDelegate(::onEmotionCardClick),
            ExitDelegate(::onExitClick),
            QuestionBlockDelegate(::onAnswerClick, ::onAddAnswerButtonClick)
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NotesFragmentBinding.bind(view)
        navController = findNavController()
        setRecycleSettings()


        adapters.submitList(
            listOf(
                ExitModel,
                EmotionCardModel(
                    "",
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
                QuestionBlockModel(
                    "Чем вы занимались?", listOf(
                        AnswerModel(UUID.randomUUID(), "Приём пищи", false),
                        AnswerModel(UUID.randomUUID(), "Встреча с друзьями", true),
                        AnswerModel(UUID.randomUUID(), "Тренировка", false),
                        AnswerModel(UUID.randomUUID(), "Хобби", false),
                        AnswerModel(UUID.randomUUID(), "Отдых", false),
                        AnswerModel(UUID.randomUUID(), "Поездка", false),
                    )
                ),
                QuestionBlockModel(
                    "С кем вы были?", listOf(
                        AnswerModel(UUID.randomUUID(), "Один", false),
                        AnswerModel(UUID.randomUUID(), "Друзья", true),
                        AnswerModel(UUID.randomUUID(), "Семья", true),
                        AnswerModel(UUID.randomUUID(), "Коллеги", false),
                        AnswerModel(UUID.randomUUID(), "Партнёр", false),
                        AnswerModel(UUID.randomUUID(), "Питомцы", false),
                    )
                ),
                QuestionBlockModel(
                    "Где вы были?", listOf(
                        AnswerModel(UUID.randomUUID(), "Дом", true),
                        AnswerModel(UUID.randomUUID(), "Работа", false),
                        AnswerModel(UUID.randomUUID(), "Школа", false),
                        AnswerModel(UUID.randomUUID(), "Транспорт", false),
                        AnswerModel(UUID.randomUUID(), "Улица", false),
                    )
                ),
            )
        )
        binding.button111.setOnClickListener {
            onSave()
        }


    }

    private fun onSave() {
        navController.navigate(R.id.logbookFragment)

    }

    private fun onEmotionCardClick(emotionCardModel: EmotionCardModel) {}

    private fun onExitClick() {
        navController.popBackStack()
    }

    private fun onAnswerClick(answer: AnswerModel) {
        Log.d("answer", "answer: $answer")
    }

    private fun onAddAnswerButtonClick(questionBlockModel:QuestionBlockModel) {
        AddVariantDialogFragment().show(childFragmentManager, "AddVariant")
    }

    override fun onInputReceived(input: String) {
        Toast.makeText(requireContext(), "Введено: $input", Toast.LENGTH_SHORT).show()
    }

    private fun setRecycleSettings(){
        with(binding.nodesRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
            addItemDecoration(
                PaddingItemDecoration(
                    14, 0, 24, 24, R.layout.notes_exit
                )
            )
            addItemDecoration(
                PaddingItemDecoration(
                    24, 0, 24, 24, R.layout.logbook_emotion_card, R.layout.notes_questions_block
                )
            )
        }
    }

}
