package com.example.shaadi.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shaadi.R
import com.example.shaadi.models.Results

class MatchesListAdapter(
    private val context: Context,
    private val listener: OnStateChangeListener,
    private val matchList: ArrayList<Results>
) : RecyclerView.Adapter<MatchesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shaadi_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "${matchList[position].name.first} ${matchList[position].name.last}"
        holder.age.text = "${matchList[position].dob.age} Years Old"
        holder.email.text = matchList[position].email
        holder.location.text = "${matchList[position].location.city}, ${matchList[position].location.country}"
        holder.phone.text = matchList[position].phone.replace("-","")
        Glide.with(context).load(matchList[position].picture.thumbnail).into(holder.image)

        if(matchList[position].hasAccepted == true) {
            holder.accepted.visibility = View.VISIBLE
            holder.accept.visibility = View.GONE
            holder.decline.visibility = View.GONE
        } else if(matchList[position].hasAccepted == false) {
            holder.declined.visibility = View.VISIBLE
            holder.accept.visibility = View.GONE
            holder.decline.visibility = View.GONE
        }

        holder.accept.setOnClickListener {
            holder.accepted.visibility = View.VISIBLE
            holder.accept.visibility = View.GONE
            holder.decline.visibility = View.GONE
            listener.onChange(position, State.ACCEPTED)
        }

        holder.decline.setOnClickListener {
            holder.declined.visibility = View.VISIBLE
            holder.accept.visibility = View.GONE
            holder.decline.visibility = View.GONE
            listener.onChange(position,State.DECLINED)
        }
    }

    override fun getItemCount(): Int {
        return matchList.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.userImage)
        val name: TextView = view.findViewById(R.id.name)
        val accept: Button = view.findViewById(R.id.accept)
        val decline: Button = view.findViewById(R.id.decline)
        val accepted: TextView = view.findViewById(R.id.accepted)
        val declined: TextView = view.findViewById(R.id.declined)
        val email: TextView = view.findViewById(R.id.email)
        val age: TextView = view.findViewById(R.id.age)
        val location: TextView = view.findViewById(R.id.location)
        val phone: TextView = view.findViewById(R.id.phone)
    }

    interface OnStateChangeListener{
        fun onChange(position : Int, state : State)
    }
}