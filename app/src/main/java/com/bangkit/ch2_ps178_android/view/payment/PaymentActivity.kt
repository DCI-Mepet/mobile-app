package com.bangkit.ch2_ps178_android.view.payment

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.dataclass.PassingData
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivityPaymentBinding
import com.bangkit.ch2_ps178_android.view.booking.Booking
import com.bangkit.ch2_ps178_android.view.booking.ModalSyarat
import com.bangkit.ch2_ps178_android.view.success.SuccessActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textfield.TextInputEditText

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra(BaseModel.paramName)) {
            var data_obj = receivedIntent.getParcelableExtra<PassingData>(BaseModel.paramName)

            // Gunakan data yang diterima di sini
            if (data_obj != null) {

                var data_row : MainAdapterRow = data_obj.mainAdapterRow


                var img_el : ImageView = findViewById(R.id.gambar)
                var img_url = BaseModel.getImg()
                Glide.with(this)
                    .load(img_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Atur ke DiskCacheStrategy.NONE jika Anda tidak ingin menyimpan cache
                    .into(img_el)
                
                set_txtContent(findViewById(R.id.tv_nama_lapangan), data_row.name)
                set_txtContent(findViewById(R.id.lokasi), data_row.kecamatan)
                set_txtContent(findViewById(R.id.jam_check_in), data_obj.checkIn)
                set_txtContent(findViewById(R.id.jam_check_out), data_obj.checkOut)
                set_txtContent(findViewById(R.id.tanggal_booking), data_obj.tanggal)
                set_txtContent(findViewById(R.id.biaya_lapangan), "Rp " + data_row.price)

                var total_harga = data_row.price.toInt() + 4000
                set_txtContent(findViewById(R.id.total_pembayaran),  "Rp " + total_harga.toString())


                var btn_bayar = findViewById<Button>(R.id.button_bayar)
                btn_bayar.setOnClickListener {
                    data_obj.total_pembayaran = total_harga.toString()
                    direct_event_obj( data_obj )
                }

            }
        }

    }


    fun direct_event_obj( data_row_obj : PassingData ){

        //BaseModel.swal(requireContext(), "s")
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(BaseModel.paramName, data_row_obj)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
    private fun setupActionBar() {
        val toolbar: Toolbar = findViewById(R.id.tb_payment_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.orange700)))
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun set_txtContent(obj_el : TextView, data :String  ){
        obj_el.text = data
    }
}