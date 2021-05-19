package com.finalproject.queue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context?, val antrianList: ArrayList<Antrian>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.name)
        val textViewDesc = itemView.findViewById<TextView>(R.id.description)
        val textViewProcess = itemView.findViewById<TextView>(R.id.processing)
        val textViewTotal = itemView.findViewById<TextView>(R.id.total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val antrian = antrianList[position]

        holder.textViewName.text = "Nama : " + antrian!!.nama
        holder.textViewDesc.text = "Deskripsi : " + antrian.deskripsi
        holder.textViewProcess.text = "Sedang Diproses : " + antrian.nomor.toString()
        holder.textViewTotal.text = "Antrian Terakhir : " + antrian.jumlah.toString()
    }

    override fun getItemCount(): Int {
        return antrianList.size
    }
}