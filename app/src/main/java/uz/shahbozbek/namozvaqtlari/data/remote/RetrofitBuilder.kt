package uz.shahbozbek.namozvaqtlari.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "https://islomapi.uz/api/"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // Adjust to a suitable value
        .readTimeout(30, TimeUnit.SECONDS) // Adjust to a suitable value
        .writeTimeout(30, TimeUnit.SECONDS) // Adjust to a suitable value
        .build()

    fun apiInterfaceBuilder(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

}