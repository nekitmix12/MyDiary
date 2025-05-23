package com.example.mydiary.presentation.features.notes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.NotesFragmentBinding
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.decorators.PaddingItemDecoration
import com.example.mydiary.presentation.adapters.delegates.EmotionDelegate
import com.example.mydiary.presentation.adapters.delegates.ExitDelegate
import com.example.mydiary.presentation.adapters.delegates.QuestionBlockDelegate
import com.example.mydiary.presentation.features.common.MainActivity
import com.example.mydiary.presentation.models.EmotionCardModel
import com.example.mydiary.presentation.models.ExitModel
import com.example.mydiary.presentation.models.QuestionBlockModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment), AddVariantDialogFragment.OnInputListener {
    private lateinit var binding: NotesFragmentBinding

    @Inject
    lateinit var viewModel: NotesViewModel
    private lateinit var navController: NavController

    private var adapters = AdapterWithDelegates(
        listOf(
            EmotionDelegate(::onEmotionCardClick),
            ExitDelegate(::onExitClick),
            QuestionBlockDelegate(::onAnswerClick, ::onAddAnswerButtonClick)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).mainActivityComponent.notesComponent().create()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NotesFragmentBinding.bind(view)
        navController = findNavController()

        val args: NotesFragmentArgs by navArgs()
        setRecycleSettings()

        viewModel.getEmotion(args.emotion, args.nodeId, requireContext())
        viewModel.createQuestionBlocks(args.nodeId)

        lifecycleScope.launch {
            launch {
                viewModel.emotion.collect {
                    Log.i(TAG, it.toString() + "--<<")
                }
            }
            launch {
                combine(viewModel.questionBlocks, viewModel.emotion) { first, second ->
                    first to second
                }.collect {
                    if (it.first != null && it.second != null) {
                        createList(it.second!!, it.first!!)
                    }
                }
            }
            launch {
                viewModel.questionBlocks.collect {
                    Log.i(TAG, it.toString() + "<<--")
                }
            }
        }


        binding.button111.setOnClickListener {
            onSave()
        }


    }


    private fun createList(emotion: EmotionCardModel, questionBlocks: List<QuestionBlockModel>) {
        val list = mutableListOf(
            ExitModel,
            emotion,
        )
        list.addAll(questionBlocks)
        Log.d(TAG, "createList: $list")
        adapters.submitList(
            list
        )
    }

    private fun onSave() {
        viewModel.onCreateClick {
            navController.navigate(R.id.logbookFragment)
        }

    }

    private fun onEmotionCardClick(emotionCardModel: EmotionCardModel) {}

    private fun onExitClick() {
        navController.popBackStack()
    }

    private fun onAnswerClick(answer: AnswerModel) {
        viewModel.onAnswerClick(answer)
        Log.d("answer", "answer: $answer")
    }

    private fun onAddAnswerButtonClick(questionBlockModel: QuestionBlockModel) {
        AddVariantDialogFragment().apply {
            arguments = bundleOf("questionId" to questionBlockModel.questionId)
        }.show(childFragmentManager, "AddVariant")
    }

    override fun onInputReceived(input: String, questionId: String) {
        viewModel.onAddButtonClick(questionId, input)
    }

    private fun setRecycleSettings() {
        with(binding.nodesRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapters
            animation = null
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

    companion object {
        const val TAG = "NotesFragment"
    }
}