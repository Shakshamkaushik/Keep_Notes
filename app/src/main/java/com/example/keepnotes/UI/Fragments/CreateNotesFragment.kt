package com.example.keepnotes.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.keepnotes.Model.NotesEntity
import com.example.keepnotes.R
import com.example.keepnotes.ViewModel.NotesVM
import com.example.keepnotes.databinding.FragmentCreateNotesBinding
import com.example.keepnotes.databinding.FragmentHomeBinding
import java.util.Date


class CreateNotesFragment : Fragment() {


    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "2"
    val viewModel: NotesVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
//        binding.pRed.setOnClickListener {
//            priority = "3"
//            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pYellow.setImageResource(0)
//        }


        binding.fabSaveNote.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subTitile = binding.etSubtitle.text.toString()
        val nottes = binding.etNote.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM,d,yyyy", d.time)

        val data = NotesEntity(
            null,
            title = title,
            subTitle = subTitile,
            note = nottes,
            date = date.toString(),
            priority
        )
        viewModel.addNotes(data)

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }


}