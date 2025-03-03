package com.example.mydiary.presentation.adapters.holders

import com.example.mydiary.databinding.NotesExitBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.ExitModel

class ExitHolder(
    binding: NotesExitBinding,
    private val onClick: () -> Unit,
) :
    BaseViewHolder<NotesExitBinding, ExitModel>(binding) {
    override fun onBinding(item: ExitModel) = with(binding) {
        exitButton.setOnClickListener { onClick() }
    }
}