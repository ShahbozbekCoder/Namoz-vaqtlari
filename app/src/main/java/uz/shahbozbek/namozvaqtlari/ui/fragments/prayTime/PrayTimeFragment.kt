package uz.shahbozbek.namozvaqtlari.ui.fragments.prayTime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.shahbozbek.namozvaqtlari.R
import uz.shahbozbek.namozvaqtlari.databinding.FragmentSolatTimeBinding

@AndroidEntryPoint
class PrayTimeFragment : Fragment(R.layout.fragment_solat_time) {

    private lateinit var binding: FragmentSolatTimeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSolatTimeBinding.bind(view)
    }

}