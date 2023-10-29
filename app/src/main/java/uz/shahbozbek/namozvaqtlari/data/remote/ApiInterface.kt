package uz.shahbozbek.namozvaqtlari.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.shahbozbek.namozvaqtlari.data.models.getDaily.GetDailyData
import uz.shahbozbek.namozvaqtlari.data.models.getMonthly.GetMonthlyData
import uz.shahbozbek.namozvaqtlari.data.models.getWeekly.GetWeeklyData

interface ApiInterface {

    @GET("present/day?region=region")
    suspend fun getDaily(@Query("region") region: String): Response<GetDailyData>

    @GET("present/week?region=region")
    suspend fun getWeekly(@Query("region") region: String): Response<GetWeeklyData>

    @GET("monthly?region=region&month=month")
    suspend fun getMonthly(
        @Query("region") region: String,
        @Query("month") month: Int
    ): Response<GetMonthlyData>

}