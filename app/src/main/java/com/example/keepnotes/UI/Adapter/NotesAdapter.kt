package com.example.keepnotes.UI.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnotes.Model.NotesEntity
import com.example.keepnotes.R
import com.example.keepnotes.UI.Fragments.HomeFragment
import com.example.keepnotes.UI.Fragments.HomeFragmentDirections
import com.example.keepnotes.databinding.FragmentHomeBinding
import com.example.keepnotes.databinding.ItemNtesBinding

@SuppressLint("StaticFieldLeak")
class NotesAdapter(val context: Context?, var notesList: List<NotesEntity>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<NotesEntity>) {


        notesList = newFilteredList
        notifyDataSetChanged()


    }

    class notesViewHolder(val binding: ItemNtesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNtesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.tvTitle.text = data.title.toString()
        holder.binding.tvSubTitle.text = data.subTitle.toString()
        holder.binding.tvDate.text = data.date.toString()

        when (data.priority) {
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.ic_baseline_star_24)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.starticon)
            }
//            "3" -> {
//                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
//            }
        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}