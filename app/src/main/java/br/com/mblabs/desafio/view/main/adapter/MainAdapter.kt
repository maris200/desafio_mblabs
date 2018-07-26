package br.com.mblabs.desafio.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.mblabs.desafio.R
import br.com.mblabs.desafio.data.local.room.entity.ListItensEntity
import br.com.mblabs.desafio.view.main.MainActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class MainAdapter(private val item: List<ListItensEntity>,  mainActivity: MainActivity) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {

        val item =  item[position]

        Picasso.with(holder.itemView.context)
                .load(item.url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(80, 80)
                .into(holder.cover_image)

        holder.name_text.text = item.name
        holder.description_text.text = item.description
        holder.price_text.text = "R$" + item.currentPrice
        holder.old_price_text.text


        holder.itemView.setOnClickListener {

            holder.mainActivity!!.openItemDetails(
                    item.url,
                    item.currentPrice,
                    item.oldPrice,
                    item.name,
                    item.id
            )

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {

        return MainAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_main, parent, false))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mainActivity: MainActivity? = null
        var cover_image = itemView.findViewById<ImageView>(R.id.cover_image)
        var name_text = itemView.findViewById<TextView>(R.id.name_home_text)
        var description_text = itemView.findViewById<TextView>(R.id.description_home_text)
        var price_text = itemView.findViewById<TextView>(R.id.price_home_text)
        var old_price_text = itemView.findViewById<TextView>(R.id.old_price_home_text)

    }

}