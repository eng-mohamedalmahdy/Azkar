package com.example.azkar.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.azkar.pojo.AzkarItem
import com.example.azkar.R
import com.example.azkar.util.Utils
import com.example.azkar.ui.AzkarListFragmentDirections


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
                    itemView.findNavController().navigate(
                        AzkarListFragmentDirections.actionAzkarListFragmentToAzkarDetailsFragment(
                            azkarItem.title
                        )
                    )
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


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_azkar, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.3).toInt()
        view.layoutParams = layoutParams
        return AzkarViewHolder(view)
    }


    override fun getItemCount(): Int = azkars.size

    override fun onBindViewHolder(holder: AzkarViewHolder, position: Int) {
        holder.bind(azkars[position])
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