package uz.shahbozbek.namozvaqtlari.data

import uz.shahbozbek.namozvaqtlari.data.remote.ApiInterface
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getDaily(region: String) = apiInterface.getDaily(region)

    suspend fun getWeekly(region: String) = apiInterface.getWeekly(region)

    suspend fun getMonthly(region: String, month: Int) = apiInterface.getMonthly(region, month)

}