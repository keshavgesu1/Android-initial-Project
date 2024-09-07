package com.example.smarttask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smarttask.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private val initialViewModel by viewModel<InitialViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
