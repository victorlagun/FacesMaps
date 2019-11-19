package com.example.victor.facesmaps.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.victor.facesmaps.R
import com.example.victor.facesmaps.repository.impl.RepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RepositoryImpl.getAll(1)
        RepositoryImpl.getUser(2).observe(this, Observer { it?.let { text.text = it.email } })
    }
}
