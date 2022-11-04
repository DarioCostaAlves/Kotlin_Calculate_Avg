package com.example.myappone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.myappone.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var gv: VariaveisGlobais
    private lateinit var binding: ActivityMain2Binding
    lateinit var oMin: EditText
    lateinit var oMax: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gv = application as VariaveisGlobais
        this.binding = ActivityMain2Binding.inflate(layoutInflater) // encher o layout
        val view = binding.root
        setContentView(view)
        oMin = binding.campoMin
        oMax = binding.campoMax
        if (gv.oMaxInit)
            oMax.setText("${gv.oMax}")
        if (gv.oMinInit)
            oMin.setText("${gv.oMin}")
    }
    override fun onPause() {
        super.onPause()
        var textMin = oMin.text.toString()
        var textMax = oMax.text.toString()
        if (textMin.length != 0) {
            gv.oMin = textMin.toInt()
            gv.oMinInit = true
        }
        if (textMax.length != 0) {
            gv.oMax = oMax.text.toString().toInt()
            gv.oMaxInit = true
        }
        if (textMin.length != 0 && textMax.length != 0)
            gv.possoAtivarBotao = true
        // Toast.makeText(this, "onPause2()", Toast.LENGTH_SHORT).show()
        // finish()
    }

    override fun onStop() {
        super.onStop()

        //Toast.makeText(this, "onStop2()", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        //Toast.makeText(this, "onDestroy2()", Toast.LENGTH_SHORT).show()
    }
}