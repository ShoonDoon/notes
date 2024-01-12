package com.example.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.RecyclerNoteBinding

class Adapter : RecyclerView.Adapter<Adapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding: RecyclerNoteBinding): RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback = object : DiffUtil.ItemCallback<ModelNote>(){
        override fun areItemsTheSame(oldItem: ModelNote, newItem: ModelNote): Boolean {
            return  oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: ModelNote, newItem: ModelNote): Boolean {
            return oldItem  == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            RecyclerNoteBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        )
    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener{
            val direction = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

}