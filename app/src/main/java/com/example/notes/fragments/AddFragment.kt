package com.example.notes.fragments

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
import com.example.notes.MainActivity
import com.example.notes.db.ModelNote
import com.example.notes.R
import com.example.notes.databinding.FragmentAddBinding
import com.example.notes.viewmodel.ViewModel

class AddFragment : Fragment(R.layout.fragment_add), MenuProvider {

    private var addNoteBinding: FragmentAddBinding? = null
    private val binding get() = addNoteBinding!!

    private lateinit var notesViewModel : ViewModel
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteBinding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).viewmodel
        addNoteView = view




        val navigationBarColor = ContextCompat.getColor(requireContext(), R.color.violet)

        requireActivity().window.navigationBarColor = navigationBarColor

        val statusBarColor  = ContextCompat.getColor(requireContext(), R.color.violet)

        requireActivity().window.statusBarColor  = statusBarColor


        (activity as? MainActivity)?.updateActionBarColor(R.color.violet)


    }

    private fun saveNote(view: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if( noteTitle.isNotEmpty()){
            val note = ModelNote(0 , noteTitle, noteDesc)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context,  "Нотатку Збережено" , Toast. LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.mainFragment, false)
        } else {
            Toast.makeText(addNoteView.context,  "Будь ласка введіть загаловок нотатку" , Toast. LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }
            else -> false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }


}