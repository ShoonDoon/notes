package com.example.notes.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.MainActivity
import com.example.notes.db.ModelNote
import com.example.notes.R
import com.example.notes.databinding.FragmentUpdateBinding


class UpdateFragment :  Fragment(R.layout.fragment_update), MenuProvider {

    private var updateNoteBinding: FragmentUpdateBinding ? = null
    private val binding get() = updateNoteBinding!!

    private lateinit var notesViewModel : com.example.notes.viewmodel.ViewModel
    private lateinit var currentNote: ModelNote


    private val args: UpdateFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        updateNoteBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).viewmodel
        currentNote = args.modelnote!!


        val navigationBarColor = ContextCompat.getColor(requireContext(), R.color.violet)

        requireActivity().window.navigationBarColor = navigationBarColor

        val statusBarColor  = ContextCompat.getColor(requireContext(), R.color.violet)

        requireActivity().window.statusBarColor  = statusBarColor

        (activity as? MainActivity)?.updateActionBarColor(R.color.violet)


        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)

        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()

            if (noteTitle.isNotEmpty()){
                val note = ModelNote(currentNote.id , noteTitle, noteDesc)
                notesViewModel.updateNote(note)
                view.findNavController().popBackStack(R.id.mainFragment, false)
            } else {
                Toast.makeText(context, "Будь ласка введіть заголовок" , Toast.LENGTH_SHORT )
            }
        }
    }


    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setMessage("Ви впевнені що бажаєте видалити нотатку?")
            setPositiveButton("Так"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Нотатку Видалено", Toast.LENGTH_SHORT)
                view?.findNavController()?.popBackStack(R.id.mainFragment, false)
            }
            setNegativeButton("Ні" , null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteNote()
                true
            } else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        updateNoteBinding = null
    }
}