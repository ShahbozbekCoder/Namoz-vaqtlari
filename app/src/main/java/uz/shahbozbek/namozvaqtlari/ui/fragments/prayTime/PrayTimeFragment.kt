package uz.shahbozbek.namozvaqtlari.ui.fragments.prayTime

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.shahbozbek.namozvaqtlari.R
import uz.shahbozbek.namozvaqtlari.databinding.FragmentSolatTimeBinding

@AndroidEntryPoint
class PrayTimeFragment : Fragment(R.layout.fragment_solat_time) {

    private lateinit var binding: FragmentSolatTimeBinding
    private val prayerTimeViewModel by viewModels<PrayerTimeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSolatTimeBinding.bind(view)

        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 because Calendar.MONTH is zero-based
        val currentYear = calendar.get(Calendar.YEAR)

        Log.d("TAG", "onViewCreated: $currentMonth $currentYear")

        prayerTimeViewModel.getMonthlyData("Tashkent",currentMonth,currentYear)
        viewModelListener()
    }

    private fun viewModelListener() {
        prayerTimeViewModel.prayerTime.observe(viewLifecycleOwner) {result ->

        }
    }

}