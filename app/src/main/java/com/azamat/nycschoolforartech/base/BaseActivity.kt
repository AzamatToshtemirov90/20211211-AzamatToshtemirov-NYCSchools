package com.azamat.nycschoolforartech.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>() : AppCompatActivity() {

    protected lateinit var binding: T
        private set

    abstract fun layoutResId(): Int

    abstract fun viewDidLoad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId())
        binding.lifecycleOwner = this
        viewDidLoad()
    }
}
