package com.nextv.noodoehw.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nextv.noodoehw.databinding.SecondPageBinding
import com.nextv.noodoehw.di.DependencyProvider
import com.nextv.noodoehw.helper.toast
import com.nextv.noodoehw.recycler.TrafficAdapter
import com.nextv.noodoehw.viewmodel.SecondViewModel

/**
 * Created by timhuang on 2021/1/26.
 **/

class SecondPage:Fragment() {

    private lateinit var binding: SecondPageBinding
    private val trafficAdapter = TrafficAdapter()
    private lateinit var viewModel:SecondViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = SecondPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, DependencyProvider.getSecondViewModelFactory()).get(
            SecondViewModel::class.java)

        setup()
        dataBinding()
    }

    private fun setup() {
        viewModel.getData()
    }

    private fun dataBinding() {

        viewModel.message.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let {message->
                toast(message)
            }
        })

        viewModel.data.observe(viewLifecycleOwner,{
            Log.e("SecondPage","get data:$it")
            trafficAdapter.submitList(it)
        })

    }
}