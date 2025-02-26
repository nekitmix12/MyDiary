package com.example.mydiary.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.databinding.StatisticsFragmentBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.delegates.EmotionCategoryDelegate
import com.example.mydiary.presentation.adapters.delegates.MoodyDuringDayDelegate
import com.example.mydiary.presentation.models.EmotionCategoryModel
import com.example.mydiary.presentation.models.MoodyDuringDayModel

class StatisticsFragment : Fragment() {
    private lateinit var binding: StatisticsFragmentBinding
    private var adapters = AdapterWithDelegates(
        listOf(
            EmotionCategoryDelegate(),
            MoodyDuringDayDelegate()
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

        with(binding){
            tabLayout.addTab(tabLayout.newTab().setText("17–23 фев"))
            tabLayout.addTab(tabLayout.newTab().setText("10–16 фев"))
            tabLayout.addTab(tabLayout.newTab().setText("3–9 фев"))
            tabLayout.addTab(tabLayout.newTab().setText("27 янв – 2 фев"))
        }

        adapters.submitList(
            listOf(
                EmotionCategoryModel(
                    listOf(
                        Pair(0f, 0f),
                        Pair(2f, 0.55f),
                        Pair(1f, 0f),
                        Pair(3f, 0.75f),
                    )
                ),
                MoodyDuringDayModel(
                    listOf(
                        listOf(1f, 0f, 0f, 0f),
                        listOf(0f, 0.5f, 0.5f, 0f),
                        listOf(0f, 1f, 0f, 0f),
                        listOf(0f, 0f, 0f, 1f),
                        listOf(0f, 0f, 0f, 0f),
                    ),
                    listOf(1, 2, 1, 1, 0)
                )
            )
        )
    }
}