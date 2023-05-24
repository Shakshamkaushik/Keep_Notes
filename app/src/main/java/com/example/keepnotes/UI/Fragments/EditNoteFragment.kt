package com.example.keepnotes.UI.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.keepnotes.Model.NotesEntity
import com.example.keepnotes.R
import com.example.keepnotes.ViewModel.NotesVM
import com.example.keepnotes.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNoteFragment : Fragment() {

    val notes by navArgs<EditNoteFragmentArgs>()
    var priority: String = "2"
    val viewModel: NotesVM by viewModels()

    lateinit var binding: FragmentEditNoteBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        binding.etTitle.setText(notes.dataa.title)
        binding.etSubtitle.setText(notes.dataa.subTitle)
        binding.etNote.setText(notes.dataa.note)

        when (notes.dataa.priority) {
            "2" -> {
                priority = "2"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
//                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
//            "3" -> {
//                priority = "3"
//                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
//                binding.pGreen.setImageResource(0)
//                binding.pYellow.setImageResource(0)
//            }
        }
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

        binding.fabEditSaveNote.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subTitile = binding.etSubtitle.text.toString()
        val nottes = binding.etNote.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM,d,yyyy", d.time)

        val data = NotesEntity(
            notes.dataa.id,
            title = title,
            subTitle = subTitile,
            note = nottes,
            date = date.toString(),
            priority
        )
        viewModel.updateNOtes(data)

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            val bottonSheetDialog: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottonSheetDialog.setContentView(R.layout.dialog_delete)

            val tvYes = bottonSheetDialog.findViewById<TextView>(R.id.tvDialogYes)
            val tvNo = bottonSheetDialog.findViewById<TextView>(R.id.tvDialogNo)


            tvYes?.setOnClickListener {
                viewModel.deleteNotes(notes.dataa.id!!)
                bottonSheetDialog.dismiss()
                findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
            }

            tvNo?.setOnClickListener {
                bottonSheetDialog.dismiss()
            }
            bottonSheetDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }

}