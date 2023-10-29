package uz.shahbozbek.namozvaqtlari.data.models.getWeekly

data class GetWeeklyDataItem(
    val date: String,
    val region: String,
    val times: Times,
    val weekday: String
)