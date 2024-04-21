package uz.shahbozbek.namozvaqtlari.ui.fragments.tasbih

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import uz.shahbozbek.namozvaqtlari.R
import uz.shahbozbek.namozvaqtlari.databinding.FragmentTasbihBinding

@AndroidEntryPoint
class TasbihFragment : Fragment(R.layout.fragment_tasbih) {

    private lateinit var binding: FragmentTasbihBinding
    private var numberOfZeekr = 0
    private var numberOfSum = 0
    private var sumOfZeekr = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasbihBinding.bind(view)
        binding.button.setOnClickListener {
            numberOfZeekr++
            numberOfSum++
            if (numberOfZeekr <= 33) {
                binding.tasbihNumber.text = numberOfZeekr.toString()
                binding.zeekrSum.text = numberOfSum.toString()
                binding.button.text = numberOfZeekr.toString()
            } else {
                numberOfZeekr = 1
                binding.tasbihNumber.text = numberOfZeekr.toString()
                binding.zeekrSum.text = numberOfSum.toString()
                binding.button.text = numberOfZeekr.toString()
                if (sumOfZeekr < 2) {
                    sumOfZeekr++
                } else sumOfZeekr = 0
            }
            when (sumOfZeekr) {
                0 -> binding.zeekrName.setText(R.string.subhanalloh)
                1 -> binding.zeekrName.setText(R.string.alhamdulillah)
                2 -> binding.zeekrName.setText(R.string.allohu_akbar)
            }
        }
        binding.refreshBtn.setOnClickListener {
            numberOfZeekr = 0
            numberOfSum = 0
            sumOfZeekr = 0
            binding.zeekrName.setText(R.string.subhanalloh)
            binding.tasbihNumber.text = numberOfZeekr.toString()
            binding.zeekrSum.text = numberOfSum.toString()
            binding.button.text = numberOfZeekr.toString()
        }
    }
}