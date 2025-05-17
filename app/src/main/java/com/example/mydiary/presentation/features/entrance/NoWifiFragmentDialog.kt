package com.example.mydiary.presentation.features.entrance

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.mydiary.R
import com.example.mydiary.databinding.NoWifiDialogBinding

class NoWifiFragmentDialog : DialogFragment() {
    private var binding: NoWifiDialogBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoWifiDialogBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setGravity(Gravity.CENTER)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.confirmButtonNoWifif?.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(),R.color.no_wifi_transparent_grey)))
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}