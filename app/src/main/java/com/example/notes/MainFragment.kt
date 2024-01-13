package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) , SearchView.OnQueryTextListener,
    MenuProvider {

    private var homeBinding: FragmentMainBinding? = null
    private val binding get() = homeBinding!!


    private lateinit var notesViewModel : com.example.notes.viewmodel.ViewModel
    private lateinit var noteAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navigationBarColor = ContextCompat.getColor(requireContext(), R.color.green)

        requireActivity().window.navigationBarColor = navigationBarColor

        val statusBarColor  = ContextCompat.getColor(requireContext(), R.color.green)

        (activity as? MainActivity)?.updateActionBarColor(R.color.green)

        requireActivity().window.statusBarColor  = statusBarColor

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).viewmodel
        setupHomeRecycleView()

        binding.addNoteFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }

    private fun updateUI(note: List<ModelNote>?){
        if(note != null){
            if (note.isNotEmpty()) {
                binding.emptyNotesText.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
                binding.emptyNotesText.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }


    private fun setupHomeRecycleView() {
        noteAdapter = Adapter()
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext()) // Use vertical orientation
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity?.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            }
        }
    }




    override fun onQueryTextSubmit(query: String?): Boolean {
        return  false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

}