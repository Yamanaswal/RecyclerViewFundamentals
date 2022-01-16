package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter(val clickListener: SleepNightListener) :
    RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /**  Without DataBinding */
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_sleep_night, parent, false)
        val binding =
            ListItemSleepNightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.sleep = item
        holder.binding.executePendingBindings()
        holder.binding.clickListener = clickListener
//        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    class ViewHolder(val binding: ListItemSleepNightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val sleepLength: TextView = binding.sleepLength
        val quality: TextView = binding.qualityString
        val qualityImage: ImageView = binding.qualityImage
    }
}

private fun SleepNightAdapter.ViewHolder.bind(item: SleepNight) {
    /**  Without DataBinding */
//    val res = itemView.context.resources
//    sleepLength.text = convertDurationToFormatted(
//        item.startTimeMilli, item.endTimeMilli, res)
//    quality.text = convertNumericQualityToString(
//        item.sleepQuality, res)
//    qualityImage.setImageResource(when (item.sleepQuality) {
//        0 -> R.drawable.ic_sleep_0
//        1 -> R.drawable.ic_sleep_1
//        2 -> R.drawable.ic_sleep_2
//        3 -> R.drawable.ic_sleep_3
//        4 -> R.drawable.ic_sleep_4
//        5 -> R.drawable.ic_sleep_5
//        else -> R.drawable.ic_sleep_active
//    })
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {

    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }

}