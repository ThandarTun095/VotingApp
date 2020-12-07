package com.myanmaritc.votingapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmaritc.votingapp.R
import com.myanmaritc.votingapp.model.KingQueenItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_king_queen.view.*

class KingQueenAdapter: RecyclerView.Adapter<KingQueenAdapter.KingQueenViewHolder>() {

    var kingQueenList: List<KingQueenItem> = ArrayList()

    var clickListener: OnClickListener? = null

    inner class KingQueenViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        lateinit var item: KingQueenItem

        fun bind(kingQueenItem: KingQueenItem){
            this.item = kingQueenItem   //get data

            Picasso.get()
                .load(kingQueenItem.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.imgKingQueen)

            itemView.name.text = kingQueenItem.name
            itemView.className.text = kingQueenItem.className

        }

        override fun onClick(view: View?) {
            clickListener?.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KingQueenViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_king_queen,parent,false)
        return KingQueenViewHolder(view)
    }

    override fun getItemCount(): Int = kingQueenList.size

    override fun onBindViewHolder(holder: KingQueenViewHolder, position: Int) {

        holder.bind(kingQueenList[position])
    }

    fun addKingQueen(kingQueenList: List<KingQueenItem>){
        this.kingQueenList = kingQueenList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(item: KingQueenItem)
    }

    fun setOnClickListener(clickListener: OnClickListener){
        this.clickListener = clickListener
    }

}