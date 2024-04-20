package uz.shahbozbek.namozvaqtlari.ui.fragments.prayTime

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.shahbozbek.namozvaqtlari.data.Repository
import uz.shahbozbek.namozvaqtlari.data.models.getMonthly.GetMonthlyData
import javax.inject.Inject

private const val TAG = "PrayerTimeViewModel"

@HiltViewModel
class PrayerTimeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _prayerTime = MutableLiveData<GetMonthlyData>()
    val prayerTime: LiveData<GetMonthlyData> get() = _prayerTime

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getMonthlyData(region: String, month: Int, year: Int) = viewModelScope.launch {
        try {
            repository.getMonthly(region, month = month, year = year).let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        _prayerTime.value = it
                        Log.d(TAG, "getMonthlyData1: $it")
                    }
                } else {
                    _error.value = "Ma'lumotlar yetib kelmadi"
                }
                Log.d(TAG, "getMonthlyData2: ${response.body()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getMonthlyData3: ${e.message}")
        }
    }
}