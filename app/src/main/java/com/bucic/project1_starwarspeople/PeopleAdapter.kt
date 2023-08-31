package com.bucic.project1_starwarspeople

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeopleAdapter(
    private val action: (entity: StarWarsPersonEntity) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>() {

    var peopleList: List<StarWarsPersonEntity> = mutableListOf()

    class PersonViewHolder(
        private val itemView: View,
        private val action: (entity: StarWarsPersonEntity) -> Unit
        ) : RecyclerView.ViewHolder(itemView) {

        fun bind(entity: StarWarsPersonEntity) {
            val nameTextView = itemView.findViewById<TextView>(R.id.name)
            nameTextView.text = entity.name
            itemView.setOnClickListener { action(entity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_view_item, parent, false)
        return PersonViewHolder(view, action)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = peopleList[position]
        holder.bind(person)
    }
}