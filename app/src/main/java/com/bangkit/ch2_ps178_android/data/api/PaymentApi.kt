package com.bangkit.ch2_ps178_android.data.api

import com.bangkit.ch2_ps178_android.data.dataclass.MainAPIResponse
import com.bangkit.ch2_ps178_android.data.dataclass.MainAdapterRow
import com.bangkit.ch2_ps178_android.data.dataclass.PaymentResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PaymentApi {


    @POST("api/create-midtrans-payment")
    suspend fun create_payment(
        @Query("id") id: Int,
        @Query("productName") productName: String,
        @Query("price") price: Double,
        @Query("quantity") quantity: Int,

        ): Response<PaymentResponse>

}