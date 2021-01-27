package com.nextv.noodoehw.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nextv.noodoehw.databinding.ItemTrafficBinding
import com.nextv.noodoehw.domain.TrafficUI

/**
 * Created by timhuang on 2021/1/26.
 **/

class TrafficAdapter :ListAdapter<TrafficUI,TrafficViewHolder>(TrafficDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrafficViewHolder {
        return TrafficViewHolder(
            ItemTrafficBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: TrafficViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class TrafficViewHolder(val binding:ItemTrafficBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(item :TrafficUI){
        binding.tvTitle.text = item.title
        binding.tvTime.text =item.time
        binding.tvUrl.text = item.url
    }
}

//only simple implementation, could be changed when behavior become more complex
object TrafficDiff:DiffUtil.ItemCallback<TrafficUI>(){
    override fun areItemsTheSame(oldItem: TrafficUI, newItem: TrafficUI): Boolean = oldItem.time == newItem.time

    override fun areContentsTheSame(oldItem: TrafficUI, newItem: TrafficUI): Boolean  = oldItem.time == newItem.time

}