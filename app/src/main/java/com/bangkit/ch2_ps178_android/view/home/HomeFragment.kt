package com.bangkit.ch2_ps178_android.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.data.adapter.AdapterStatis
import com.bangkit.ch2_ps178_android.data.adapter.AdapterStatisEx
import com.bangkit.ch2_ps178_android.data.adapter.MainAdapter
import com.bangkit.ch2_ps178_android.data.api.MainApi
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRowStatis
import com.bangkit.ch2_ps178_android.data.model.BaseModel
import com.bangkit.ch2_ps178_android.data.model.MainModel
import com.bangkit.ch2_ps178_android.view.detail.DetailActivity
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var hasContentLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        loadContentList()
        loadContentList_non()

    }

    fun start_shimmer(){
        val shimmerLoad : ShimmerFrameLayout = requireView().findViewById(R.id.shimmer_view)
        shimmerLoad.visibility = View.VISIBLE
        shimmerLoad.startShimmer()
    }
    fun stop_shimmer(){
        val shimmerLoad : ShimmerFrameLayout = requireView().findViewById(R.id.shimmer_view)
        shimmerLoad.visibility = View.GONE
        shimmerLoad.stopShimmer()
    }




    private fun loadContentList_non(){
//        Ini data content yang gak pake paging
        val adapter = AdapterStatisEx { item, cardView ->

            direct_event( "1" )
            // Tangani item klik di sini
            // `item` adalah objek dari data class MainAdapterRowStatis
            // `cardView` adalah CardView yang diklik
            // Anda dapat menambahkan logika atau tindakan yang sesuai di sini
        }

        val recyclerView: RecyclerView = requireView().findViewById(R.id.data_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        // Data manual yang akan Anda tambahkan ke dalam adapter
        val manualDataList = listOf(
            MainAdapterRowStatis("kebun1", BaseModel.getImg(), "Judul 1", "Lorem"),
            MainAdapterRowStatis("kebun1",  BaseModel.getImg(), "Judul 2", "Lorem"),
            MainAdapterRowStatis("kebun1",  BaseModel.getImg(), "Judul 3", "Lorem"),
            MainAdapterRowStatis("kebun1",  BaseModel.getImg(), "Judul 4", "Lorem"),
            MainAdapterRowStatis("kebun1",  BaseModel.getImg(), "Judul 5", "Lorem"),
            MainAdapterRowStatis("kebun1",  BaseModel.getImg(), "Judul 6", "Lorem"),
            // Tambahkan item lain sesuai kebutuhan Anda
        )

        // Mengisi adapter dengan data manual
        adapter.submitList(manualDataList)

        stop_shimmer()

    }













    private fun loadContentList() {
//        Ini data content yang pake paging, cuman yaa gituh


        // Panggil loadContentList di sini untuk memuat data dan mengatur RecyclerView
        // Recycleview data storys

        val adapter = MainAdapter() { data_row, el_row ->
            // Event ketika item di RecyclerView di klik
            // Data click adalah data row pada list dari setiap index
            // el_story elemen card sesuai dengan index berbanding lurus dengan data click
            //BaseModel.toast(requireContext(), data_row.name)
            direct_event_obj( data_row )
        }


        //Memulai efek load shimmer
        start_shimmer()

        val recyclerView: RecyclerView = requireView().findViewById(R.id.data_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        //Memasukkan data dari view model API
        val main_viewModel = ViewModelProvider(this).get(MainModel::class.java)
        main_viewModel.set_data()


        // Observer untuk LiveData PagingData
        main_viewModel.Data_mainPaging.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
        }

        // Observe LoadState to show/hide ProgressBar
        adapter.addLoadStateListener { loadState ->
            val isRefreshing = loadState.refresh is LoadState.Loading
            val isListEmpty = adapter.itemCount == 0

            // Show ProgressBar only if it's refreshing and the list is empty
            if (isRefreshing && isListEmpty){
                start_shimmer()
            }else{
                stop_shimmer()
            }
        }


    }


    fun direct_event( param : String ){
        //Ini ngirim data dengan anggapan data ke detail diambil dari link by id
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("param", param)
        startActivity(intent)
    }

    fun direct_event_obj( data_row_obj : MainAdapterRow ){


        //BaseModel.swal(requireContext(), "s")
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("data_paramObj", data_row_obj)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }



    override fun onDestroyView() {
        super.onDestroyView()
        hasContentLoaded = false
    }
}