package com.azamat.nycschoolforartech.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.ui.fragment.schoollist.SchoolListAdapter
import com.azamat.nycschoolforartech.util.extensions.hideKeyboard
import com.azamat.nycschoolforartech.util.extensions.showKeyboard

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<School>?) {
    val adapter = recyclerView.adapter as SchoolListAdapter
    data?.let {
        adapter.setData(data.toMutableList())
    }
}

@BindingAdapter("filterByKeyword")
fun filterByKeyword(recyclerView: RecyclerView, keyword: String?) {
    val adapter = recyclerView.adapter as SchoolListAdapter
    keyword?.let {
        adapter.filter.filter(it)
    }
}

@BindingAdapter("hideKeyboard")
fun hideKeyboardOnclick(view: View, isHide: Boolean) {
    if (isHide)
        view.hideKeyboard()
}

@BindingAdapter("showKeyboard")
fun showKeyboardOnclick(view: View, isShow: Boolean) {
    if (isShow)
        view.showKeyboard()
}
