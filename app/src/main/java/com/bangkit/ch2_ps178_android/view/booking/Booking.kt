package com.bangkit.ch2_ps178_android.view.booking

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi

import androidx.appcompat.widget.Toolbar

import androidx.cardview.widget.CardView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.dataclass.InputModal
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.dataclass.PassingData
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.view.payment.PaymentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale


class Booking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)


        //=== Modal info pem
        var modalInfoPem = ModalInfoPem(this)
        var col_info_pem: LinearLayout = findViewById(R.id.col_info_pem)

        //Membuka modal
        col_info_pem.setOnClickListener {
            modalInfoPem.show()
        }
        //Mensubmit modal
        var btn_modalInfo = modalInfoPem.modal.findViewById<Button>(R.id.btn_save)
        btn_modalInfo.setOnClickListener {
            var modal = modalInfoPem.modal

            val nama_lengkap : TextInputEditText = modal.findViewById( R.id.edit_nama_lengkap )
            set_txtContent( findViewById( R.id.nama_pesan ), nama_lengkap.text.toString() )

            val email : TextInputEditText = modal.findViewById( R.id.edit_alamat_email )
            set_txtContent( findViewById( R.id.email_pesan ), email.text.toString() )

            val nomor : TextInputEditText = modal.findViewById( R.id.edit_nomor_ponsel )
            set_txtContent( findViewById( R.id.nomor_pesan ), nomor.text.toString() )

            modalInfoPem.hide()
        }






        //=== Modal catatan
        var modalCatatan = ModalCatatan(this)
        var layout_catatan: LinearLayout = findViewById(R.id.layout_catatan)
        layout_catatan.setOnClickListener {
            modalCatatan.show()
        }
        //Mensubmit modal
        var btn_modalCatatan = modalCatatan.dialog.findViewById<Button>(R.id.btn_save)
        btn_modalCatatan.setOnClickListener {
            var modal = modalCatatan.dialog

            val catatan : TextInputEditText = modal.findViewById( R.id.edit_catatan_pemesanan )
            set_txtContent( findViewById( R.id.catatan ), catatan.text.toString() )

            modalCatatan.hide()
        }

        //Isi dropdown di UI

        //===  Untuk CheckIn
        var list_jam_drskrg = list_jamDariSekarang().toTypedArray()
        val col_checkin: LinearLayout = findViewById(R.id.col_check_in)
        val jam_checkin : AutoCompleteTextView = findViewById(R.id.jam_checkin)
        jam_checkin.isEnabled = false
        set_dropdownCheck( col_checkin, jam_checkin, list_jam_drskrg )

        // Untuk Checkout
        val col_checkout: LinearLayout = findViewById(R.id.col_check_out)
        val jam_checkout : AutoCompleteTextView = findViewById(R.id.jam_checkout)
        jam_checkout.isEnabled = false
        set_dropdownCheck( col_checkout, jam_checkout, list_jam_drskrg )

        //implementasi data ke ui
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

                //untuk data informasi
                set_txtContent(findViewById(R.id.tv_nama_lapangan), data_row.name)
                set_txtContent(findViewById(R.id.tv_rating), data_row.rating)
                set_txtContent(findViewById(R.id.daerah), data_row.kecamatan)
                set_txtContent(findViewById(R.id.jarak),  data_row.lat)
                set_txtContent(findViewById(R.id.harga),  "Rp " + data_row.price)
                set_txtContent(findViewById(R.id.tanggal),  get_tanggal())


                //Event pesan
                //Modal pesan syarat
                var modalSyarat = ModalSyarat(this)

                var btn_pesan_now : Button = findViewById(R.id.btn_pesan_now)
                btn_pesan_now.setOnClickListener {

                    modalSyarat.show()
                    modalSyarat.btn_pesan.setOnClickListener {

                        //Ambil input dari modal
                        val nama_lengkap : TextInputEditText = modalInfoPem.dialog.findViewById( R.id.edit_nama_lengkap )
                        val data_nama : String = nama_lengkap.text.toString()

                        val email : TextInputEditText = modalInfoPem.dialog.findViewById( R.id.edit_alamat_email )
                        val data_email : String = email.text.toString()

                        val nomor : TextInputEditText = modalInfoPem.dialog.findViewById( R.id.edit_nomor_ponsel )
                        val data_nomor : String = nomor.text.toString()

                        val catatan : TextInputEditText = modalCatatan.dialog.findViewById( R.id.edit_catatan_pemesanan )
                        val data_catatan : String = catatan.text.toString()

                        //Ambil input tanggal check in dan checkout
                        var tanggal = findViewById<TextView>( R.id.tanggal )
                        var tanggalData = tanggal.text.toString()
                        var checkInData: String = jam_checkin.text.toString()
                        var checkOutData : String = jam_checkout.text.toString()

//                        BaseModel.swal(this, "tanggal " + tanggalData + " in " + checkInData + " out " + checkOutData)
                        var data_obj = PassingData(
                            data_row,
                            InputModal(  data_nama, data_email, data_nomor, data_catatan ),
                            tanggalData,
                            checkInData,
                            checkOutData
                        )

//                        BaseModel.swal(this, "in " + checkInData + " out " + checkOutData  )
                        direct_event_obj( data_obj )
                    }
                }
            }
        }
        setupActionBar()
    }

    private fun setupActionBar() {
        val toolbar: Toolbar = findViewById(R.id.tb_booking_back)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.orange700)))
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }



    //================  Untuk Generate jam dari saat ini untuk checkin
    fun list_jamDariSekarang(): ArrayList<String> {
        val jamList = ArrayList<String>()

        // Mendapatkan waktu saat ini
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val waktuSaatIni = sdf.format(calendar.time)

        val jam_saat_ini = waktuSaatIni.substringBefore(":").toInt()


        for ( jam in jam_saat_ini..23 ){
            var jam_txt:String = ""
            if ( jam < 10 ){
                jam_txt = "0" + jam.toString()
            }else{
                jam_txt = jam.toString()
            }
            jamList.add( jam_txt + ":00" )
        }


        // Mengembalikan ArrayList jamList
        return jamList
    }



    fun set_dropdownCheck( col_input: LinearLayout, el_input: AutoCompleteTextView, data_list : Array<String> ){
        // Daftar jam yang akan ditampilkan di dropdown
        val jamList = data_list

        // Buat ArrayAdapter untuk AutoCompleteTextView
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, jamList)
        el_input.setAdapter(adapter)

        // Buat dan konfigurasi PopupWindow
        val popupWindow = PopupWindow(this)
        val popupView: View = LayoutInflater.from(this).inflate(R.layout.dropdown_list, null)
        val popupListView: ListView = popupView.findViewById(R.id.popupListView)
        val popupListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, jamList)
        popupListView.adapter = popupListAdapter
        popupWindow.contentView = popupView
        popupWindow.isFocusable = true

        // Tampilkan dropdown saat ImageView diklik
        col_input.setOnClickListener {
            if (!popupWindow.isShowing) {
                popupWindow.showAsDropDown(it)
            }
        }
        // Atur item yang dipilih pada AutoCompleteTextView saat item dropdown dipilih
        popupListView.setOnItemClickListener { _, _, position, _ ->
            el_input.setText(jamList[position])
            popupWindow.dismiss()
        }
    }
    fun set_txtContent(obj_el : TextView, data :String  ){
        obj_el.text = data
    }



    fun direct_event_obj( data_row_obj : PassingData ){


        //BaseModel.swal(requireContext(), "s")
            val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(BaseModel.paramName, data_row_obj)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish()
    }


    fun get_tanggal() : String{
        // Inisialisasi ThreeTenABP
        val tanggalHariIni: Date = Date()

        // Menentukan format yang diinginkan
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id-ID"))

        // Memformat tanggal hari ini dengan format yang diinginkan
        val tanggalDalamFormat: String = formatter.format(tanggalHariIni)

        return tanggalDalamFormat
    }

}

open class ModalSetting{

    lateinit var dialog: Dialog
    lateinit var context: Context

    open fun setting_modal( dialog : Dialog ){

        var bgTransprant: ColorDrawable = ColorDrawable(Color.TRANSPARENT)

        //Atur warna backdropnya
        //window?.setBackgroundDrawable(bgTransprant)
        //Ini merupakan constrain layout di ui
        var container_modal = dialog.window

        val layoutParams = container_modal?.attributes
        layoutParams?.dimAmount = 0.1f // Atur nilai transparansi di sini (0.0f hingga 1.0f)
        container_modal?.attributes = layoutParams

        // Atur warna background modal dialog
        container_modal?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Atur animasi agar dialog muncul dari bawah
        container_modal?.attributes?.windowAnimations = R.style.DialogAnimation
        // Set contentPadding menjadi nol untuk menghilangkan padding
        container_modal?.decorView?.setPadding(0, 0, 0, 0)
        // Atur lebar dan tinggi dialog agar sesuai kebutuhan

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        container_modal?.setLayout(width, height)

    }

    fun show(){
        // Tampilkan dialog
        dialog.show()
    }

    fun hide(){
        dialog.hide()
    }
}



class ModalInfoPem( context: Context ) : ModalSetting() {

    var modal : Dialog

    init{
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_infopem_layout)

        modal = dialog
        setting_modal(dialog)

    }



}


class ModalSyarat( context: Context ) : ModalSetting() {


    var btn_pesan : Button
    init{
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_syarat_layout)

        btn_pesan = dialog.findViewById(R.id.btn_pesan)
        setting_modal(dialog)
    }


}

class ModalCatatan( context: Context ) : ModalSetting() {

    init{
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modal_catatan_layout)

        setting_modal(dialog)
    }

    fun get_inputCatatan() : String{

        val catatan : TextInputEditText = dialog.findViewById(R.id.edit_catatan_pemesanan)
        return catatan.text.toString()
    }

}





