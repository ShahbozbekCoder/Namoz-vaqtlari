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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import uz.shahbozbek.namozvaqtlari.databinding.FragmentMainPageBinding

@AndroidEntryPoint
class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val mainPageViewModel by viewModels<MainPageViewModel>()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

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

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.cardView.setOnClickListener {
            checkLocationPermission()
        }

    }

    private fun checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                Toast.makeText(requireContext(), "${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Empty ", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            checkLocationPermission()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
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