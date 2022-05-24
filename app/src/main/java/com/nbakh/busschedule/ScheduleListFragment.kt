package com.nbakh.busschedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbakh.busschedule.adapters.ScheduleAdapter
import com.nbakh.busschedule.databinding.FragmentScheduleListBinding
import com.nbakh.busschedule.viewmodels.ScheduleViewModel

class ScheduleListFragment : Fragment() {
    private val viewModel : ScheduleViewModel by activityViewModels()
    private lateinit var binding: FragmentScheduleListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleListBinding.inflate(inflater, container, false)

        val adapter = ScheduleAdapter()
        binding.scheduleRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.scheduleRV.adapter = adapter
        adapter.submitList(viewModel.getAllSchedule())

        binding.addScheduleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleListFragment_to_newScheduleFragment)
        }
        return binding.root

    }

}