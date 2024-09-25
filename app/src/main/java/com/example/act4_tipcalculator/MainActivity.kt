package com.example.act4_tipcalculator

import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act4_tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.calculateButton.setOnClickListener{calculateTip()}
    }

    private fun calculateTip() {
        val cost : Double = binding.costOfService.text.toString().toDouble()

        val selectedId: Int= binding.tipOptions.checkedRadioButtonId
        val tipPercentage:Double = when(selectedId){
            R.id.option_ten_percent ->0.1
            R.id.option_seven_percent -> 0.07
            R.id.option_five_percent ->0.05
            else -> 0.05
        }
        var tip : Double = cost*tipPercentage
        val roundUp : Boolean = binding.roundTip.isChecked
        if( roundUp ){
            tip = ceil(tip)
        }
        val currencyTip:String = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,currencyTip)
    }
 }