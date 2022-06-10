package com.nbakh.busschedule

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nbakh.busschedule.customdialogs.DatePickerFragment
import com.nbakh.busschedule.customdialogs.TimePickerFragment
import com.nbakh.busschedule.databinding.FragmentNewScheduleBinding
import com.nbakh.busschedule.viewmodels.ScheduleViewModel

class NewScheduleFragment : Fragment() {
    private val viewModel : ScheduleViewModel by activityViewModels()
    private lateinit var binding: FragmentNewScheduleBinding

    private var from = ""
    private var to = ""
    private var busType = busTypeEconomy
    private var id: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewScheduleBinding.inflate(inflater, container, false)

        initSpinner()
        initBusTypeRadioGroup()

        id = arguments?.getLong("id")
        viewModel.getAllScheduleByID(id!!).observe(viewLifecycleOwner){
            setDataForEditing(it)
        }


        binding.dateBtn.setOnClickListener {
            DatePickerFragment {
                binding.showDateTV.text = it
            }.show(childFragmentManager, null)
        }

        binding.timeBtn.setOnClickListener {
            TimePickerFragment {
                binding.showTimeTV.text = it
            }.show(childFragmentManager, null)
        }

        binding.saveBtn.setOnClickListener {
            saveBusInfo()
        }

        return binding.root
    }

    private fun setDataForEditing(it: BusSchedule) {
        binding.saveBtn.text = "UPDATE"

        binding.busNameInputET.setText(it.busName)
        binding.showDateTV.setText(it.departureDate)
        binding.showTimeTV.setText(it.departureTime)
        val fromIndex = cityList.indexOf(it.from)
        val toIndex = cityList.indexOf(it.to)
        binding.citySpinnerFrom.setSelection(fromIndex)
        binding.citySpinnerTo.setSelection(toIndex)
        if(it.busType == busTypeEconomy)
            binding.busTypeRG.check(R.id.economyRB)
        else
            binding.busTypeRG.check(R.id.businessRB)
    }

    private fun saveBusInfo() {
        val name = binding.busNameInputET.text.toString()
        val date = binding.showDateTV.text.toString()
        val time = binding.showTimeTV.text.toString()

        //Validating
        if(name.isEmpty()){
            Toast.makeText(requireActivity(), "Please enter bus name!", Toast.LENGTH_SHORT).show()
            return
        }
        if(from == to){
            Toast.makeText(requireActivity(), "Both destination cannot be same!", Toast.LENGTH_SHORT).show()
            return
        }
        if(date.isEmpty()){
            Toast.makeText(requireActivity(), "Please select departure date!", Toast.LENGTH_SHORT).show()
            return
        }
        if(time.isEmpty()){
            Toast.makeText(requireActivity(), "Please select departure time!", Toast.LENGTH_SHORT).show()
            return
        }

        val schedule = BusSchedule(
            id = System.currentTimeMillis(),
            busName =  name,
            from = from,
            to = to,
            departureDate = date,
            departureTime = time,
            busType = busType
        )

//        BusScheduleDB.getDB(requireActivity())
//            .getScheduleDao()
//            .addSchedule(schedule)

        if(id != null){
            schedule.id = id!!
            viewModel.updateSchedule(schedule)
        }
        else
            viewModel.addSchedule(schedule) //pass to viewmodel
        findNavController().navigate(R.id.action_newScheduleFragment_to_scheduleListFragment)
        Log.d("NewScheduleFragment", "saveBusInfo: $schedule")
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            cityList
        )
        binding.citySpinnerFrom.adapter = adapter
        binding.citySpinnerTo.adapter = adapter

        binding.citySpinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                from = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.citySpinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                to = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initBusTypeRadioGroup() {
        binding.busTypeRG.setOnCheckedChangeListener { radioGroup, checkedId ->
            val rb: RadioButton = radioGroup.findViewById(checkedId)
            busType = rb.text.toString()
        }
    }

}