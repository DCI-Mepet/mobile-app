// MainAdapter.kt

package com.bangkit.ch2_ps178_android.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView

class MainAdapter(private val onItem_click: (MainAdapterRow, CardView) -> Unit) :
    PagingDataAdapter<MainAdapterRow, MainAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.main_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MainAdapterRow) {


            //Ambil datanya
            val img_data = "https://disbun.kaltimprov.go.id/upload/artikel/medium_07-10-2019-01-04-12-8067.jpg"
            val judul_data = item.name

            //Menetapkan aturan UI, dan di terapkan pada object Holder di method onBindViewHolder
            val img: ShapeableImageView = itemView.findViewById(R.id.gambar)
            val judul: TextView = itemView.findViewById(R.id.judul)
            val konten: TextView = itemView.findViewById(R.id.konten)

            Glide.with(itemView)
                .load(img_data)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Atur ke DiskCacheStrategy.NONE jika Anda tidak ingin menyimpan cache
                .into(img)
            judul.text = judul_data
            konten.text = "saya adalah petani singkong real estet"

            //event setiap item ketika di klik dengan melakukan call back
            val card_story = itemView.findViewById<CardView>(R.id.card_story)
            itemView.setOnClickListener {
                //item adalah datanya dari elemen yang di klik
                //card story adalah elemen item card yang diklik dari data
                onItem_click(item, card_story)
            }
        }
    }

    companion object {
        public val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MainAdapterRow>() {
            override fun areItemsTheSame(
                oldItem: MainAdapterRow,
                newItem: MainAdapterRow
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MainAdapterRow,
                newItem: MainAdapterRow
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
