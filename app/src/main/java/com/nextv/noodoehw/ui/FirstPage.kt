package com.nextv.noodoehw.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nextv.noodoehw.databinding.FirstPageBinding
import com.nextv.noodoehw.di.DependencyProvider
import com.nextv.noodoehw.helper.toast
import com.nextv.noodoehw.viewmodel.FirstViewModel

/**
 * Created by TimHuang on 2021/1/25.
 */
class FirstPage:Fragment() {

    private lateinit var viewModel :FirstViewModel
    private lateinit var binding: FirstPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FirstPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,DependencyProvider.getFirstViewModelFactory()).get(FirstViewModel::class.java)
        setupListener()
        dataBinding()
    }

    private fun dataBinding() {
        viewModel.message.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let {message->
                toast(message)
            }
        })

        viewModel.emailError.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let {error->
                binding.emailInputEt.error = error
            }
        })

        viewModel.passwordError.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let {error->
                binding.emailInputEt.error = error
            }
        })
    }

    private fun setupListener() {
        binding.btSubmit.setOnClickListener {
            viewModel.login(
                binding.emailInputEt.text.toString(),
                binding.passwordInputEt.text.toString(),
            )
        }
    }
}