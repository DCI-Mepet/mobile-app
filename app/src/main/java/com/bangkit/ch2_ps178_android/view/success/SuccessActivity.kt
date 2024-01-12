package com.bangkit.ch2_ps178_android.view.success

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.api.MainApi
import com.bangkit.ch2_ps178_android.data.api.PaymentApi
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.dataclass.PassingData
import com.bangkit.ch2_ps178_android.data.dataclass.PaymentResponse
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.databinding.ActivitySuccessBinding
import com.bangkit.ch2_ps178_android.view.main.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ...

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        if (receivedIntent != null && receivedIntent.hasExtra(BaseModel.paramName)) {
            var data_obj = receivedIntent.getParcelableExtra<PassingData>(BaseModel.paramName)

            // Gunakan data yang diterima di sini
            if (data_obj != null) {
                var data_row: MainAdapterRow = data_obj.mainAdapterRow

            }



            var btn_back = findViewById<Button>(R.id.back_to_home)
            btn_back.setOnClickListener {
                direct_event_obj( "1" )
            }
        }
    }



    fun set_txtContent(obj_el : TextView, data :String  ){
        obj_el.text = data
    }

    fun direct_event_obj( id_transaksi : String ){

        //BaseModel.swal(requireContext(), "s")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id_transaksi", id_transaksi)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
