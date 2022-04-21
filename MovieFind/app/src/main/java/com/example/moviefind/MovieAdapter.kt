package com.example.moviefind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val mList:List<Movie>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var mListner: onItemClickListner
    interface onItemClickListner{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(listner: onItemClickListner){
        mListner =listner
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card,parent,false)
        return ViewHolder(view,mListner)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel=mList[position]
//        holder.moviePic=itemViewModel.
        holder.txtTitle.text=itemViewModel.title
        holder.txtYear.text=itemViewModel.year
        holder.txtRated.text=itemViewModel.rated
        holder.txtReleased.text=itemViewModel.released
        holder.txtRuntime.text=itemViewModel.runtime
        holder.txtGenre.text=itemViewModel.genre
        holder.txtDirector.text=itemViewModel.director
        holder.txtWriter.text=itemViewModel.writer
        holder.txtActors.text=itemViewModel.actors
        holder.txtPlot.text=itemViewModel.plot
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View, listner:onItemClickListner):RecyclerView.ViewHolder(itemView){
//        val moviePic : ImageView =itemView.findViewById<ImageView>(R.id.imageId)
        val txtTitle: TextView =itemView.findViewById<TextView>(R.id.titleId)
        val txtYear: TextView =itemView.findViewById<TextView>(R.id.yearId)
        val txtRated: TextView =itemView.findViewById<TextView>(R.id.ratedId)
        val txtReleased: TextView =itemView.findViewById<TextView>(R.id.releasedId)
        val txtRuntime: TextView =itemView.findViewById<TextView>(R.id.runtimeId)
        val txtGenre: TextView =itemView.findViewById<TextView>(R.id.genreId)
        val txtDirector: TextView =itemView.findViewById<TextView>(R.id.directorId)
        val txtWriter: TextView =itemView.findViewById<TextView>(R.id.writerId)
        val txtActors: TextView =itemView.findViewById<TextView>(R.id.actorsId)
        val txtPlot: TextView =itemView.findViewById<TextView>(R.id.plotId)
        init {
            itemView.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }
    }


}