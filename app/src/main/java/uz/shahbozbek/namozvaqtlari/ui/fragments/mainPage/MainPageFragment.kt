package uz.shahbozbek.namozvaqtlari.ui.fragments.mainPage

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import uz.shahbozbek.namozvaqtlari.R
import uz.shahbozbek.namozvaqtlari.databinding.FragmentMainPageBinding

@AndroidEntryPoint
class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val mainPageViewModel by viewModels<MainPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPageViewModel.getDailyData(region = "Tashkent")
        viewModelListener()

//        binding.cardView.setOnClickListener {
//            findNavController().navigate(R.id.action_mainPageFragment_to_prayTimeFragment)
//        }

    }

    private fun viewModelListener() {
        mainPageViewModel.prayerTime.observe(requireActivity()) {
            binding.prayerPlace.text = it.data.meta.timezone
            binding.prayerDate.text = it.data.date.readable
            binding.prayerWeekday.text = it.data.date.gregorian.weekday.en
            binding.sunriseTv.text = it.data.timings.Fajr
            binding.sunshineTv.text = it.data.timings.Sunrise
            binding.dhuhurTv.text = it.data.timings.Dhuhr
            binding.asrTv.text = it.data.timings.Asr
            binding.maghribTv.text = it.data.timings.Maghrib
            binding.ishaTv.text = it.data.timings.Isha
        }
        mainPageViewModel.error.observe(requireActivity()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}