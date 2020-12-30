package com.example.azkar

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView


class AzkarListAdapter(private var azkars: List<AzkarItem>) :
    RecyclerView.Adapter<AzkarListAdapter.AzkarViewHolder>() {
    private var originalList = azkars.toList()

    init {
        setHasStableIds(true)
    }

    class AzkarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var card: CardView = itemView.findViewById(R.id.azkar_card)
        private var background: ImageView = itemView.findViewById(R.id.background)
        private var title: TextView = itemView.findViewById(R.id.title)
        private var content: TextView = itemView.findViewById(R.id.content)

        fun bind(azkarItem: AzkarItem) {

            if (azkarItem.title == -1) {
                title.visibility = View.GONE
            } else {
                title.text = itemView.context.getString(azkarItem.title)
                card.setOnClickListener {
                    val intent = Intent(itemView.context, AzkarDetailsActivity::class.java)
                    intent.putExtra("AZKAR_TYPE", azkarItem.title)
                    itemView.context.startActivity(intent)
                }
            }
            if (azkarItem.content == -1) {
                content.visibility = View.GONE
            } else {
                content.text = itemView.context.getString(azkarItem.content)
            }
            if (azkarItem.background != -1) {
                Thread() {
                    val img =
                        BitmapFactory.decodeResource(
                            itemView.context.resources,
                            azkarItem.background
                        )
                    Palette.Builder(img).generate {
                        title.setTextColor(
                            Utils.getComplementaryColor(
                                it?.getLightVibrantColor(Color.WHITE) ?: Color.WHITE
                            )
                        )
                        content.setTextColor(
                            Utils.getComplementaryColor(
                                it?.getDominantColor(Color.WHITE) ?: Color.WHITE
                            )
                        )
                    }
                }.start()
                background.setImageResource(azkarItem.background)
            }
        }
    }

    override fun getItemId(position: Int) = azkars[position].id


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AzkarViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_azkar, parent, false)
    )

    override fun getItemCount(): Int = azkars.size

    override fun onBindViewHolder(holder: AzkarViewHolder, position: Int) {
        holder.bind(azkars[position])
        holder.setIsRecyclable(false);
    }

    fun filter(newText: String?, context: Context) {
        azkars = if (newText.isNullOrEmpty()) {
            originalList.toList()
        } else {
            azkars.filter {

                (it.content != -1 && context.getString(it.content).contains(newText.toRegex()))
            }
        }
        notifyDataSetChanged()
    }
}