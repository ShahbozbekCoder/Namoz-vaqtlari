package uz.shahbozbek.namozvaqtlari.data.models.getMonthly

data class GetMonthlyData(
    val code: Int,
    val `data`: List<Data>,
    val status: String
)