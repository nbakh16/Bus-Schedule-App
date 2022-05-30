package com.nbakh.busschedule.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nbakh.busschedule.BusSchedule
import com.nbakh.busschedule.R
import com.nbakh.busschedule.databinding.BusListRowBinding

class ScheduleAdapter (val menuClickCallback : (BusSchedule, RowAction) -> Unit)
    : ListAdapter<BusSchedule, ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffUtil()) {

    class ScheduleViewHolder(val binding: BusListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(busSchedule: BusSchedule) {
            binding.schedule = busSchedule
        }
    }

    class ScheduleDiffUtil : DiffUtil.ItemCallback<BusSchedule>() {
        override fun areItemsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = BusListRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = getItem(position)
        holder.bind(schedule)

        //more menu
        holder.binding.menuBtn.setOnClickListener {
            val popupMenu = PopupMenu(holder.binding.menuBtn.context, holder.binding.menuBtn)
            popupMenu.menuInflater.inflate(R.menu.row_menu, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {
                val action : RowAction = when(it.itemId) {
                    R.id.itemEdit -> RowAction.EDIT
                    R.id.itemDelete -> RowAction.DELETE
                    else -> RowAction.NONE
                }
                //row object, action
                menuClickCallback(schedule, action)
                true
            }
        }
    }
}

enum class RowAction{
    EDIT, DELETE, NONE
}