package com.ugene.rickandmorty.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ugene.rickandmorty.FragmentBase
import com.ugene.rickandmorty.databinding.FragmentDashboardBinding
import dagger.android.support.AndroidSupportInjection

class DashboardFragment : FragmentBase<FragmentDashboardBinding>() {
    private val dashboardViewModel by lazy {
        getViewModel<DashboardViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(FragmentDashboardBinding.inflate(inflater, container, false))
        val root: View = binding.root

        binding.refresh.setOnClickListener {
            dashboardViewModel.refresh()
        }

        dashboardViewModel.apis.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it.characters
        }

        return root
    }
}