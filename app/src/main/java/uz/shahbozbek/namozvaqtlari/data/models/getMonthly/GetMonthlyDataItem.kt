package uz.shahbozbek.namozvaqtlari.data.models.getMonthly

data class GetMonthlyDataItem(
    val date: String,
    val day: Int,
    val month: Int,
    val region: String,
    val times: Times,
    val weekday: String
)