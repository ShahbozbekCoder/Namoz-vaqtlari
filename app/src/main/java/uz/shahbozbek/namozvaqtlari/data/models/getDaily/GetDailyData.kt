package uz.shahbozbek.namozvaqtlari.data.models.getDaily

data class GetDailyData(
    val date: String,
    val region: String,
    val times: Times,
    val weekday: String
)