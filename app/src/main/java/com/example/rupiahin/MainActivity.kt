package com.example.rupiahin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHitung: Button = findViewById(R.id.btn_hitung)
        val edtAmout: EditText = findViewById(R.id.edt_usd_value)
        val tvKeterangan: TextView = findViewById(R.id.tv_keterangan)
        val tvLabelHasil: TextView = findViewById(R.id.tv_label_hasil)
        val tvHasil: TextView = findViewById(R.id.tv_hasil)
        val rate: Int = 14500

        btnHitung.setOnClickListener {
            val toast = Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT)
            var hasil: Int = edtAmout.text.toString().toInt() * rate
            toast.show()
            tvKeterangan.visibility = View.INVISIBLE
            tvLabelHasil.visibility = View.VISIBLE
            tvHasil.visibility = View.VISIBLE
            val money = Currency(hasil)
            tvHasil.setText("Rp ${money.getFormattedValue()}")
        }
    }
}