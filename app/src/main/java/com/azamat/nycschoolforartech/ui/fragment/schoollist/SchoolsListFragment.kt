package com.azamat.nycschoolforartech.ui.fragment.schoollist

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.os.bundleOf
import com.azamat.nycschoolforartech.R
import com.azamat.nycschoolforartech.base.BaseFragment
import com.azamat.nycschoolforartech.databinding.FragmentSchoolsListBinding
import com.azamat.nycschoolforartech.model.enums.ItemClick
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.ui.activity.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SchoolsListFragment : BaseFragment<FragmentSchoolsListBinding>() {

    private val viewModel: SchoolsListViewModel by viewModel()
    private val mainVM: MainViewModel by sharedViewModel()

    override fun layoutResId() = R.layout.fragment_schools_list

    override fun viewDidLoad() {
        initData()
        initRecyclerView()
        viewModel.getSchoolListFromRoom()
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
        binding.mainVM = mainVM
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvSchools.apply {
            adapter = SchoolListAdapter().apply {
                setOnItemClickListener { itemClick, school ->
                    when (itemClick) {
                        ItemClick.PARENT -> navigateToScoreScreen(school)
                        ItemClick.PHONE -> makePhoneCall(school.phoneNumber)
                        ItemClick.MAP -> openMap(school.latitude,  school.longitude)
                    }
                }
            }
        }
    }

    private fun navigateToScoreScreen(school: School) {
        getNav(binding.root).navigate(R.id.action_schoolsListFragment_to_scoreFragment, bundleOf("school" to school), anim)
    }

    private fun makePhoneCall(phoneNo: String?) {
        phoneNo?.let {
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${phoneNo.trim()}")
            })
        }
    }

    private fun openMap(latitude: String?, longitude: String?) {
        // Creates an Intent that will load a map
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?z=18")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(requireActivity().packageManager)?.let {
            startActivity(mapIntent)
        }
    }
}
