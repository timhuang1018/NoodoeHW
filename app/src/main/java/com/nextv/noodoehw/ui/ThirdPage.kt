package com.nextv.noodoehw.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nextv.noodoehw.databinding.ThirdPageBinding
import com.nextv.noodoehw.di.DependencyProvider
import com.nextv.noodoehw.viewmodel.FirstViewModel
import com.nextv.noodoehw.viewmodel.ThirdViewModel

/**
 * Created by timhuang on 2021/1/27.
 **/

class ThirdPage:Fragment() {

    private lateinit var binding:ThirdPageBinding
    private lateinit var viewModel: ThirdViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ThirdPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, DependencyProvider.getThirdViewModelFactory(requireContext())).get(ThirdViewModel::class.java)

        viewModel.getData()
        setListener()
        dataBinding()
    }

    private fun dataBinding() {
        viewModel.email.observe(viewLifecycleOwner,{
            binding.tvEmail.text = it
        })
    }

    private fun setListener() {
        binding.ivBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}