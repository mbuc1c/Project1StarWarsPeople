package com.bucic.project1_starwarspeople

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailsActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvHeight: TextView
    private lateinit var fabBack: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        connectViews()
        applyDetails()
    }

    private fun applyDetails() {
        val entity = intent.getSerializableExtra("entity") as? StarWarsPersonEntity

        entity?.let {
            tvName.text = it.name
            tvGender.text = it.gender
            tvHeight.text = it.height
        }

        fabBack.setOnClickListener { finish() }
    }

    private fun connectViews() {
        tvName = findViewById(R.id.name_tv)
        tvGender = findViewById(R.id.gender_tv)
        tvHeight = findViewById(R.id.height_tv)
        fabBack = findViewById(R.id.back_fab)
    }


}