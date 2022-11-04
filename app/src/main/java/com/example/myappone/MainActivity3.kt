package com.example.myappone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myappone.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var gv: VariaveisGlobais
    lateinit var binding: ActivityMain3Binding // view binding
    lateinit var oResultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gv = application as VariaveisGlobais
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        oResultado = binding.textView
        oResultado.setText("${gv.oResultado}")
    }
}