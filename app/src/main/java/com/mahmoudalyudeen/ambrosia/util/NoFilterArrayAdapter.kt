package com.mahmoudalyudeen.ambrosia.util

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class NoFilterArrayAdapter<T>(context: Context, textViewResourceId: Int, var items: List<T>) :
    ArrayAdapter<T>(context, textViewResourceId, items) {

    override fun getFilter(): Filter {
        return NoFilter()
    }

    private inner class NoFilter : Filter() {

        override fun performFiltering(arg0: CharSequence): FilterResults = FilterResults().apply {
            values = items
            count = items.size
        }

        override fun publishResults(arg0: CharSequence, arg1: FilterResults) = notifyDataSetChanged()
    }
}