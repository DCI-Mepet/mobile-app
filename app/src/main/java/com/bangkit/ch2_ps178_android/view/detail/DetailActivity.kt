package com.bangkit.ch2_ps178_android.view.detail

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivityDetailBinding
import com.bangkit.ch2_ps178_android.view.booking.Booking
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra(BaseModel.paramName)) {
            val data_row = receivedIntent.getParcelableExtra<MainAdapterRow>(BaseModel.paramName)

            // Gunakan data yang diterima di sini
            if (data_row != null) {


                // Implementasikan ke UI dari data yang diterima
                //untuk gambar
                var img_el : ImageView = findViewById(R.id.iv_lapangan)
                var img_url = BaseModel.getImg()
                Glide.with(this)
                    .load(img_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Atur ke DiskCacheStrategy.NONE jika Anda tidak ingin menyimpan cache
                    .into(img_el)

                //untuk implementasi data informasi ke elemen
//                set_txtContent(findViewById(R.id.tv_nama_lapangan), data_row.name)


                //Event pesan
                var btn_pesan : Button = findViewById(R.id.btn_pesan)
                btn_pesan.setOnClickListener {
                    direct_event_obj( data_row )
                }

            }
        }

        setupActionBar()
    }

    fun set_txtContent( obj_el : TextView, data :String  ){
        obj_el.text = data
    }

    fun set_icon( obj_el : CardView, data : String ){
        if ( data == "1" ){
            // Mengatur CardView menjadi terlihat
            obj_el.visibility = View.VISIBLE
        }else{
            // Mengatur CardView menjadi hilang
            obj_el.visibility = View.GONE
        }

    }


    fun direct_event_obj( data_row_obj : MainAdapterRow ){


        //BaseModel.swal(requireContext(), "s")
        val intent = Intent(this, Booking::class.java)
        intent.putExtra(BaseModel.paramName, data_row_obj)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private fun setupActionBar() {
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tb_detail_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.orange700)))
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}