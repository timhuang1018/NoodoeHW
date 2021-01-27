package com.nextv.noodoehw.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nextv.noodoehw.databinding.ThirdPageBinding
import com.nextv.noodoehw.di.DependencyProvider
import com.nextv.noodoehw.helper.toast
import com.nextv.noodoehw.viewmodel.ThirdViewModel

/**
 * Created by timhuang on 2021/1/27.
 **/

class ThirdPage:Fragment() {

    private lateinit var binding:ThirdPageBinding
    private lateinit var viewModel: ThirdViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ThirdPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, DependencyProvider.getThirdViewModelFactory(requireContext())).get(ThirdViewModel::class.java)

        viewModel.getData()
        setListener()
        dataBinding()
        setupAutoCompleteView()
    }

    private fun dataBinding() {
        viewModel.user.observe(viewLifecycleOwner,{ user->
            binding.tvEmail.text = user.username
            binding.tvTimeZone.setText(user.timezone.toString(),false)
        })

        viewModel.message.observe(viewLifecycleOwner,{
            it.getContentIfNotHandled()?.let {message->
                toast(message)
            }
        })


        viewModel.loading.observe(viewLifecycleOwner,{
            binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    private fun setListener() {
        binding.ivBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setupAutoCompleteView() {
        val timezoneList=viewModel.getTimeZoneList()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(), R.layout.simple_spinner_item,
                timezoneList)
        binding.tvTimeZone.setAdapter(adapter)
        binding.tvTimeZone.onItemClickListener =
                AdapterView.OnItemClickListener { parent, arg1, position, id ->
                    viewModel.changeTimezone(position)
                }
    }
}