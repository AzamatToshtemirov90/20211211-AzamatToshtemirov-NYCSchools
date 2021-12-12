package com.azamat.nycschoolforartech.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.azamat.nycschoolforartech.R

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var binding: T
        private set

    val anim = navOptions {
        anim {
            enter = R.anim.bounce
            popEnter = R.anim.fade_in

        }
    }

    abstract fun layoutResId(): Int

    abstract fun viewDidLoad()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            layoutResId(),
            null,
            true
        )

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDidLoad()
    }

    fun getNav(view: View) = Navigation.findNavController(view)
}
