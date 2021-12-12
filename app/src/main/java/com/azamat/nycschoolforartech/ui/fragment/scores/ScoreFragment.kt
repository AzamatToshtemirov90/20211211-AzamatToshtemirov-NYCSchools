package com.azamat.nycschoolforartech.ui.fragment.scores

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.azamat.nycschoolforartech.R
import com.azamat.nycschoolforartech.base.BaseFragment
import com.azamat.nycschoolforartech.databinding.FragmentScoreBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ScoreFragment : BaseFragment<FragmentScoreBinding>() {

    private val viewModel: ScoreViewModel by viewModel()
    private val args: ScoreFragmentArgs by navArgs()

    override fun layoutResId() = R.layout.fragment_score

    companion object {
        fun newInstance() = ScoreFragment()
    }

    override fun viewDidLoad() {
        initData()
        getExtraDataFromLogin()
        observeData()
    }

    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner, {
            it?.let {
                if (it.contains("2147483647")) {
                    Toast.makeText(requireContext(),  "NO INTERNET CONNECTION\nConnect with Internet to get the updated data!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initData() {
        binding.vm = viewModel
    }

    private fun getExtraDataFromLogin() {
        args.school?.let {
            viewModel.school.set(it)
            viewModel.getScoreByDbnFromRoom(it.dbn)
        }
    }

}
