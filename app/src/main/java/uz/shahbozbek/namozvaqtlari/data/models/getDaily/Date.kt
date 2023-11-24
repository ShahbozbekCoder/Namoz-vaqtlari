package uz.shahbozbek.namozvaqtlari.data.models.getDaily

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)