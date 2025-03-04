package com.example.mydiary.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.NotesQuestionsBlockBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.QuestionsBlockHolder
import com.example.mydiary.presentation.models.AnswerModel
import com.example.mydiary.presentation.models.QuestionBlockModel

class QuestionBlockDelegate(
    private val onAnswerClick: (AnswerModel) -> Unit,
    private val onAddAnswerButtonClick: (QuestionBlockModel) -> Unit,
) : Delegate<NotesQuestionsBlockBinding, QuestionBlockModel> {

    override fun isRelativeItem(item: Item): Boolean = item is QuestionBlockModel

    override fun getLayoutId(): Int = R.layout.notes_questions_block

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<NotesQuestionsBlockBinding, QuestionBlockModel> =
        QuestionsBlockHolder(
            NotesQuestionsBlockBinding.inflate(layoutInflater, parent, false),
            onAnswerClick,
            onAddAnswerButtonClick
        )

    override fun getDiffUtil(): DiffUtil.ItemCallback<QuestionBlockModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<QuestionBlockModel>() {
        override fun areItemsTheSame(oldItem: QuestionBlockModel, newItem: QuestionBlockModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: QuestionBlockModel, newItem: QuestionBlockModel) =
            oldItem == newItem
    }

}