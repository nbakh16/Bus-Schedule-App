package com.nbakh.busschedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbakh.busschedule.adapters.RowAction
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

        val adapter = ScheduleAdapter{schedule, action ->
            performRowAction(schedule, action)
        }
        binding.scheduleRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.scheduleRV.adapter = adapter

        viewModel.getAllSchedule().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        //adapter.submitList(viewModel.getAllSchedule())

        binding.addScheduleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleListFragment_to_newScheduleFragment)
        }
        return binding.root

    }

    private fun performRowAction(schedule: BusSchedule, action : RowAction) {
        when(action) {
            RowAction.EDIT -> {

            }
            RowAction.DELETE -> {
                viewModel.deleteSchedule(schedule)
            }
        }
    }

}