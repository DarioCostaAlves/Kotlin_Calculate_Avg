package com.example.myappone

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myappone.databinding.ActivityMainBinding // view binding

public class VariaveisGlobais: Application() {
    var oMin: Int = -1
    var oMinInit = false

    var oMax: Int = -1
    var oMaxInit = false

    var oResultado: Double = 0.0

    var possoAtivarBotao = false
}
class MainActivity: AppCompatActivity() {
    lateinit var gv: VariaveisGlobais
    lateinit var oBotaoCalc: Button
    lateinit var oBotaoMaxMin: Button
    lateinit var aListaNumeros: EditText
    lateinit var minMax: TextView
    lateinit var binding: ActivityMainBinding // view binding
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        gv = application as VariaveisGlobais
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Toast.makeText(this, "onCreate1()", Toast.LENGTH_SHORT).show()
        // setContentView(R.layout.activity_main)
        oBotaoCalc = binding.botaoCalcular
        aListaNumeros = binding.campoListaNumeros
        minMax = binding.textViewMaxMin
        oBotaoMaxMin = binding.botaoMaxMin
        oBotaoCalc.setOnClickListener {
            var s: String
            s = aListaNumeros.text.toString()
            calcularMediaDentroDeMaxMin(s)
        }
        oBotaoMaxMin.setOnClickListener {
            execOutraActivity(MainActivity2::class.java)
        }
        initMinMax()
        oBotaoCalc.isEnabled = false
    }
    private fun execOutraActivity(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }
    fun calcularMediaDentroDeMaxMin(s: String): Unit {
        var arrayNumeros = s.trim().split("\\s+".toRegex()).toTypedArray()
        var aSoma  = 0.0
        var n = 0
        for (k in 0 .. arrayNumeros.size-1) {
            var x: Float
            try {
                x = arrayNumeros[k].toFloat()
                if (x <= gv.oMax)
                    if (x >= gv.oMin) {
                        aSoma += x
                        ++n
                    }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Aviso: valor inválido na lista foi ignorado", Toast.LENGTH_SHORT).show()
            }

        }
        if (n != 0)
            gv.oResultado = aSoma / n
        execOutraActivity(MainActivity3::class.java)
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause1()", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop1()", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart1()", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart1()", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume1()", Toast.LENGTH_SHORT).show()
        initMinMax()
        if (gv.possoAtivarBotao)
            oBotaoCalc.isEnabled = true
    }
    private fun initMinMax() {
        if (gv.oMaxInit && gv.oMinInit)
            minMax.setText("Min: ${gv.oMin}, Max: ${gv.oMax}")
        if (gv.oMaxInit && gv.oMinInit == false)
            minMax.setText("Min: ?, Max: ${gv.oMax}")
        if (gv.oMinInit && gv.oMaxInit == false)
            minMax.setText("Min: ${gv.oMin}, Max: ?")
    }
}