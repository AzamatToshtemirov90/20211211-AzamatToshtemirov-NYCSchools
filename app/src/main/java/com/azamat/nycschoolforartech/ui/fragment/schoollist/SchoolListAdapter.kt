package com.azamat.nycschoolforartech.ui.fragment.schoollist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.azamat.nycschoolforartech.databinding.ItemSchoolBinding
import com.azamat.nycschoolforartech.model.enums.ItemClick
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.util.extensions.hideKeyboard
import java.util.*
import kotlin.collections.ArrayList

class SchoolListAdapter : RecyclerView.Adapter<SchoolListAdapter.ViewHolder>(), Filterable {

    val items: ArrayList<School> = arrayListOf()

    lateinit var binding: ItemSchoolBinding

    private var onItemClickListener: ((ItemClick, School) -> Unit)? = null

    var filteredList: List<School>

    init {
        filteredList = items
    }

    fun setData(items: List<School>?) {
        items?.let {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun setOnItemClickListener(onItemClickListener: ((ItemClick, School) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position], onItemClickListener)
    }


    class ViewHolder(private val binding: ItemSchoolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            school: School,
            onItemClickListener: ((ItemClick, School) -> Unit)?,
        ) {
            binding.school = school
            binding.root.setOnClickListener {
                binding.root.hideKeyboard()
                onItemClickListener?.invoke(ItemClick.PARENT, school)
            }
            binding.btnPhoneNumber.setOnClickListener {
                binding.root.hideKeyboard()
                onItemClickListener?.invoke(ItemClick.PHONE, school)
            }
            binding.btnMap.setOnClickListener {
                binding.root.hideKeyboard()
                onItemClickListener?.invoke(ItemClick.MAP, school)
            }
            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredList = items
                } else {
                    val resultList = ArrayList<School>()
                    for (item in items) {
                        if (item.schoolName?.lowercase(Locale.ROOT)
                                ?.contains(charSearch.lowercase(Locale.ROOT))!!
                        ) {
                            resultList.add(item)
                        }
                    }
                    filteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<School>
                notifyDataSetChanged()
            }

        }
    }
}
