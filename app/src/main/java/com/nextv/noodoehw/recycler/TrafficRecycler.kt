package com.nextv.noodoehw.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nextv.noodoehw.databinding.ItemTrafficBinding
import com.nextv.noodoehw.domain.data.Info

/**
 * Created by timhuang on 2021/1/26.
 **/

class TrafficAdapter :ListAdapter<Info,TrafficViewHolder>(TrafficDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrafficViewHolder {
        return TrafficViewHolder(
            ItemTrafficBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: TrafficViewHolder, position: Int) {

    }

}

class TrafficViewHolder(val binding:ItemTrafficBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(item :Info){
        //TODO binding data
    }
}

//only simple implementation, could be changed when behavior become more complex
object TrafficDiff:DiffUtil.ItemCallback<Info>(){
    override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean = oldItem.updatetime == newItem.updatetime

    override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean  = oldItem.updatetime == newItem.updatetime

}