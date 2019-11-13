package com.mahmoudalyudeen.ambrosia.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudalyudeen.ambrosia.databinding.ItemEntryBinding
import com.mahmoudalyudeen.ambrosia.domain.Entry

class EntriesAdapter(
//    private val entryListener: EntryListener
) :
    ListAdapter<Entry, EntriesAdapter.EntryViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        return EntryViewHolder(ItemEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = getItem(position)
        holder.bind(
            entry
//            , entryListener
        )
    }

    class EntryViewHolder(private var binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            entry: Entry
//                 , entryListener: EntryListener
        ) {
            binding.entry = entry
//            binding.entryListener = entryListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Entry>() {
        override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem == newItem
        }
    }
}

class EntryListener(private val onClickListener: (entry: Entry) -> Unit) {
    fun onClick(entry: Entry) {
        onClickListener(entry)
    }
}