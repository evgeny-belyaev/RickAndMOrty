package com.ugene.rickandmorty

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ugene.rickandmorty.di.ViewModelFactory
import javax.inject.Inject

abstract class FragmentBase<T> : Fragment() {
    private var _binding: T? = null

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    protected val binding get() = _binding!!

    protected inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProvider(this, viewModelFactory).get(
            T::class.java
        )
    }

    protected fun setBinding(binding: T): T {
        _binding = binding
        return binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}