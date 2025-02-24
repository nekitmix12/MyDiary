package com.example.mydiary.presentation.adapters.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.mydiary.R
import com.example.mydiary.databinding.NotesExitBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.Item
import com.example.mydiary.presentation.adapters.holders.ExitHolder
import com.example.mydiary.presentation.models.ExitModel

class ExitDelegate(private val onClick: () -> Unit) : Delegate<NotesExitBinding, ExitModel> {

    override fun isRelativeItem(item: Item): Boolean = item is ExitModel

    override fun getLayoutId(): Int = R.layout.notes_exit

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<NotesExitBinding, ExitModel> =
        ExitHolder(NotesExitBinding.inflate(layoutInflater, parent, false), onClick)

    override fun getDiffUtil(): DiffUtil.ItemCallback<ExitModel> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ExitModel>() {
        override fun areItemsTheSame(oldItem: ExitModel, newItem: ExitModel) =
            true

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ExitModel, newItem: ExitModel) =
            true
    }

}