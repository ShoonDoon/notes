package com.example.notes

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notes.db.DatabaseBuild
import com.example.notes.viewmodel.ViewModel
import com.example.notes.viewmodel.ViewModelInstance

class MainActivity : AppCompatActivity() {


    lateinit var viewmodel: ViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.elevation = 0f
        setupViewModel()

    }


    fun updateActionBarColor(colorResId: Int) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, colorResId)))
    }


    private fun setupViewModel()
    {
        val repository = Repository(DatabaseBuild(this))
        val viewModelProviderFactory = ViewModelInstance(application, repository)
        viewmodel = ViewModelProvider(this , viewModelProviderFactory)[ViewModel::class.java]
    }

}