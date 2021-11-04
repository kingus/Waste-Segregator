package com.example.waste_segregator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.journeyapps.barcodescanner.ScanOptions
import android.widget.Toast
import com.journeyapps.barcodescanner.ScanContract
import com.example.waste_segregator.databinding.FragmentScanBarcodeBinding
import com.journeyapps.barcodescanner.ScanIntentResult


class ScanBarcodeFragment : Fragment() {
    private lateinit var binding: FragmentScanBarcodeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBarcodeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val options = ScanOptions()
//            options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
//            options.setPrompt(getString(R.string.scan_product_label))
            options.setOrientationLocked(false)
            options.setBeepEnabled(false)
            options.setBarcodeImageEnabled(true)
            barcodeLauncher.launch(options)

        }
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                .show()
        }
    }
}