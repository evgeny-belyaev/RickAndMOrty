package com.ugene.rickandmorty

import androidx.fragment.app.Fragment

abstract class FragmentBase<T> : Fragment() {
    private var _binding: T? = null

    protected val binding get() = _binding!!

    protected fun setBinding(binding: T): T {
        _binding = binding
        return binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}