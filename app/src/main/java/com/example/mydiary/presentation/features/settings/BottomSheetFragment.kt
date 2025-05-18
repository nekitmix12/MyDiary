package com.example.mydiary.presentation.features.settings

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mydiary.R
import com.example.mydiary.databinding.SettingBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        private const val COLLAPSED_HEIGHT = 228
    }

    lateinit var binding: SettingBottomSheetBinding
    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SettingBottomSheetBinding.bind(
            inflater.inflate(
                R.layout.setting_bottom_sheet,
                container
            )
        )
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            R.style.CustomTimePicker,
            { _, selectedHour, selectedMinute ->
                binding.hourText.text = selectedHour.toString()
                binding.minuteText.text = selectedMinute.toString()
            },
            hour,
            minute,
            true
        )

        binding.timeLinearLayout.setOnClickListener{
            timePickerDialog.show()
        }


        binding.bottomSheetSaveButton.setOnClickListener{
            val bundle = Bundle().apply {
                putString("hour",binding.hourText.text.toString())
                putString("minutes",binding.minuteText.text.toString())
            }
            parentFragmentManager.setFragmentResult("requestKey",bundle)
            dismiss()
        }
        return binding.root
    }


}