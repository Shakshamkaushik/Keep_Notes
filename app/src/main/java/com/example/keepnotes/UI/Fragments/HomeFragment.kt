package com.example.keepnotes.UI.Fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.keepnotes.Model.NotesEntity
import com.example.keepnotes.R
import com.example.keepnotes.UI.Adapter.NotesAdapter
import com.example.keepnotes.ViewModel.NotesVM
import com.example.keepnotes.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesVM by viewModels()
    var oldMineNotes = arrayListOf<NotesEntity>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)


        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->

            oldMineNotes = notesList as ArrayList<NotesEntity>
            binding.rvAllNotes.layoutManager = GridLayoutManager(context, 2)
            adapter = NotesAdapter(context, notesList)
            binding.rvAllNotes.adapter = adapter

        }

        binding.fabAddNote.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        binding.tvFilterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->

                oldMineNotes = notesList as ArrayList<NotesEntity>
                binding.rvAllNotes.layoutManager = GridLayoutManager(context, 2)
                adapter = NotesAdapter(context, notesList)
                binding.rvAllNotes.adapter = adapter

            }
        }

        binding.tvFilterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->

                oldMineNotes = notesList as ArrayList<NotesEntity>
                binding.rvAllNotes.layoutManager = GridLayoutManager(context, 2)
                adapter = NotesAdapter(context, notesList)
                binding.rvAllNotes.adapter = adapter

            }
        }

//        binding.tvFilterHigh.setOnClickListener {
//            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
//
//                oldMineNotes = notesList as ArrayList<NotesEntity>
//                binding.rvAllNotes.layoutManager = GridLayoutManager(context, 2)
//                adapter = NotesAdapter(context, notesList)
//                binding.rvAllNotes.adapter = adapter
//
//            }
//        }

        binding.ivAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->

                oldMineNotes = notesList as ArrayList<NotesEntity>
                binding.rvAllNotes.layoutManager = GridLayoutManager(context, 2)
                adapter = NotesAdapter(context, notesList)
                binding.rvAllNotes.adapter = adapter

            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)

        val searchView = item.actionView as SearchView

        searchView.queryHint = "Enter Notes here.."
        searchView.maxWidth = 730

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            searchView.transitionAlpha = 102f
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(newText: String?) {

        val newFilteredList = arrayListOf<NotesEntity>()

        for (i in oldMineNotes) {
            if (i.title!!.contains(newText!!) || i.subTitle.contains(newText)) {
                newFilteredList.add(i)
            }
        }
        if (newFilteredList.isEmpty()) {
            binding.tvNoDataFoundEmptyList.visibility = View.VISIBLE
            newFilteredList.clear()
            adapter.filtering(newFilteredList)
        } else {
            binding.tvNoDataFoundEmptyList.visibility = View.GONE
            adapter.filtering(newFilteredList)
        }
    }
}