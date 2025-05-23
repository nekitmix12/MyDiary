package com.example.mydiary.presentation.features.notes

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mydiary.R
import com.example.mydiary.databinding.DialogFragmentBinding

class AddVariantDialogFragment : DialogFragment() {

    private var binding: DialogFragmentBinding? = null
    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        binding = DialogFragmentBinding.inflate(layoutInflater)
        val questionId = requireArguments().getString("questionId")!!
        if (binding != null) {
            builder.setView(binding!!.root)
                .setPositiveButton(requireContext().getText(R.string.ok)) { _, _ ->
                    val inputText = binding!!.dialogEditText.text.toString()
                    (parentFragment as? OnInputListener)?.onInputReceived(inputText, questionId)
                }
                .setNegativeButton(requireContext().getText(R.string.cansel)) { _, _ -> }
        }
        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    interface OnInputListener {
        fun onInputReceived(input: String, questionId: String)
    }
}