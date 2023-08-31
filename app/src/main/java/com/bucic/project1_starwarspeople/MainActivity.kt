package com.bucic.project1_starwarspeople

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter: PeopleAdapter by lazy { PeopleAdapter(this::onClickItem) }
    private val repository: StarWarsPeopleRepository by lazy { StarWarsPeopleRepository() }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        GlobalScope.launch(Dispatchers.Main) {
            repository.getPeople().collect { people ->
                adapter.peopleList = people
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun onClickItem(entity: StarWarsPersonEntity) {
//        Toast.makeText(this, entity.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("entity", entity)
        startActivity(intent)
    }
}