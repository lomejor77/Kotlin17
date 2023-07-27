package cl.awakelabs.kotlin17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import cl.awakelabs.kotlin17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val divisas = listOf<String>("USD","CLP","EUR")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.divisaActual.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.cambioDivisa.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        initListener()
        setContentView(binding.root)
    }

    private fun initListener() {
        binding.btnConvert.setOnClickListener {
            val monto = binding.textMonto.text.toString().toDouble()
            val actualDivisa = binding.divisaActual.selectedItem.toString()
            val cambioDivisa = binding.cambioDivisa.selectedItem.toString()
            val resultados = conversor(monto,actualDivisa,cambioDivisa).toString()
                binding.textResultado.text = resultados
        }
        binding.btnClean.setOnClickListener{
            limpiar()
        }
    }

    private fun limpiar() {
        binding.textConvert.text = ""
        binding.textMonto.setText("")
    }

    private fun conversor(monto: Double, actualDivisa: String, cambioDivisa: String): Double {
        var resultConversion = monto
        when (actualDivisa) {
            "USD" -> {
                if (cambioDivisa == "CLP") {
                    resultConversion = monto * 824.74
                } else if (cambioDivisa == "USD") {
                    resultConversion = monto * 1

                } else if (cambioDivisa == "EUR") {
                    resultConversion = monto * 0.90
                }
            }

            "CLP" -> {
                if (cambioDivisa == "CLP") {
                    resultConversion = monto * 1
                } else if (cambioDivisa == "USD") {
                    resultConversion = monto / 824.74

                } else if (cambioDivisa == "EUR") {
                    resultConversion = monto / 916.49
                }
            }

            "EUR" -> {
                if (cambioDivisa == "CLP") {
                    resultConversion = monto * 916.49
                } else if (cambioDivisa == "USD") {
                    resultConversion = monto * 0.90

                } else if (cambioDivisa == "EUR") {
                    resultConversion = monto * 1
                }

            }

        }
        return resultConversion
    }
}