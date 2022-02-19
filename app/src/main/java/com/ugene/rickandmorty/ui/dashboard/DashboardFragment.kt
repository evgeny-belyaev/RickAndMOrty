package com.ugene.rickandmorty.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ugene.rickandmorty.databinding.FragmentDashboardBinding
import com.ugene.rickandmorty.FragmentBase
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DashboardFragment : FragmentBase<FragmentDashboardBinding>() {
    @Inject
    lateinit var dashboardViewModel: DashboardViewModel

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

        binding.textDashboard.text = dashboardViewModel.getI().toString()

        return root
    }
}