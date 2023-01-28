package com.example.examenfinal.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenfinal.databinding.ActivityMainBinding
import com.example.examenfinal.model.Api
import com.example.examenfinal.model.Raza
import com.example.examenfinal.model.Raza_detail
import com.example.examenfinal.util.Constants
import com.example.examenfinal.view.adapters.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch{
            val call = Constants.getRetrofit().create(Api::class.java).getRazaApiary("examenfinal/dogs_list")

            call.enqueue(object: Callback<ArrayList<Raza>> {
                override fun onResponse(
                    call: Call<ArrayList<Raza>>,
                    response: Response<ArrayList<Raza>>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")


                    binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMenu.adapter = Adapter(this@MainActivity, response.body()!!)

                    binding.pbConexion.visibility = View.GONE
                }

                override fun onFailure(call: Call<ArrayList<Raza>>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun selectedRaza(raza: Raza) {
        val parametros = Bundle()

        parametros.apply{
            putString("id", raza.id)
        }

        val intent = Intent(this@MainActivity, Raza_detail::class.java)

        intent.putExtras(parametros)

        startActivity(intent)
    }

}
