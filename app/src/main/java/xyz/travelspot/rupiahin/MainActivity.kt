package xyz.travelspot.rupiahin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import xyz.travelspot.rupiahin.R
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHitung: Button = findViewById(R.id.btn_hitung)
        val edtAmout:EditText = findViewById(R.id.edt_usd_value)
        val tvKeterangan: TextView = findViewById(R.id.tv_keterangan)
        val tvLabelHasil: TextView = findViewById(R.id.tv_label_hasil)
        val tvHasil: TextView = findViewById(R.id.tv_hasil)
        val rate: Int = 14500

        btnHitung.setOnClickListener {
            var hasil: String = edtAmout.text.toString()
            val queue = Volley.newRequestQueue(this)
            val url = "https://travelspot.xyz/api/moneyapp/usd-to-idr?amount=%s".format(hasil)

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        // Display the first 500 characters of the response string.
                        tvKeterangan.visibility = View.INVISIBLE
                        tvLabelHasil.visibility = View.VISIBLE
                        tvHasil.visibility = View.VISIBLE
                        val money = Gson().fromJson(response, Currency::class.java)
                        tvHasil.setText("Rp ${money.idr_value}")
                    },
                    Response.ErrorListener {
                        var hasil: Int = edtAmout.text.toString().toInt() * rate
                        val toast = Toast.makeText(this, "Network error, result may not be accurate", Toast.LENGTH_LONG)
                        toast.show()
                        tvKeterangan.visibility = View.INVISIBLE
                        tvLabelHasil.visibility = View.VISIBLE
                        tvHasil.visibility = View.VISIBLE
                        val money = Currency(hasil.toString())
                        tvHasil.setText("Rp ${money.idr_value}")
//                        tvHasil.setText("Rp ${money.getFormattedValue()}")
                    })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }
    }

}