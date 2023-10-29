package uz.shahbozbek.namozvaqtlari.ui.fragments.mainPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.shahbozbek.namozvaqtlari.data.Repository
import uz.shahbozbek.namozvaqtlari.data.models.getDaily.GetDailyData
import javax.inject.Inject

private const val TAG = "MainPageViewModel"
@HiltViewModel
class MainPageViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _prayerTime = MutableLiveData<GetDailyData>()
    val prayerTime:LiveData<GetDailyData> get() = _prayerTime

    private var _error = MutableLiveData<String>()
    val error:LiveData<String> get() = _error

    fun getDailyData(region:String) = viewModelScope.launch {
        try {
            repository.getDaily(region).let {response ->
                if (response.isSuccessful){
                    response.body()?.let {
                        _prayerTime.value = it
                        Log.d(TAG, "getDailyData1: $it")
                    }
                } else {
                    _error.value = "Ma'lumotlar yetib kelmadi"
                }
                Log.d(TAG, "getDailyData2: ${response.body()}")
            }
        } catch (e:Exception){
            Log.e(TAG, "getDailyData3: ${e.message}")
        }
    }

}