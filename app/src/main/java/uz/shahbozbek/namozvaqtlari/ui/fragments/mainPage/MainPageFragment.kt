package uz.shahbozbek.namozvaqtlari.ui.fragments.mainPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
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
        _binding = FragmentMainPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPageViewModel.getDailyData(region = "Toshkent")
        viewModelListener()

    }

    private fun viewModelListener() {
        mainPageViewModel.prayerTime.observe(requireActivity()){
            binding.prayerPlace.text = it.region
            binding.prayerDate.text = it.date
            binding.prayerWeekday.text = it.weekday
            binding.sunriseTv.text = it.times.tong_saharlik
            binding.sunshineTv.text = it.times.quyosh
            binding.dhuhurTv.text = it.times.peshin
            binding.asrTv.text = it.times.asr
            binding.maghribTv.text = it.times.shom_iftor
            binding.ishaTv.text = it.times.hufton
        }
        mainPageViewModel.error.observe(requireActivity()){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}