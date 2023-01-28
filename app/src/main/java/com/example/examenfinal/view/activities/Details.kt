package com.example.examenfinal.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.examenfinal.R
import com.example.examenfinal.databinding.ActivityDetailsBinding
import com.example.examenfinal.model.Api
import com.example.examenfinal.model.Raza
import com.example.examenfinal.model.Raza_detail
import com.example.examenfinal.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "")

        val call = Constants.getRetrofit().create(Api::class.java).getRazaApiary(id)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object: Callback<Raza_detail> {
                override fun onResponse(call: Call<Raza_detail>, response: Response<Raza_detail>) {
                    binding.pbConexion.visibility = View.GONE

                    with(binding){

                        tvTitle.text = response.body()?.raza

                        Glide.with(this@Details)
                            .load(response.body()?.image)
                            .into(ivImage)

                        tvLongDesc.text = response.body()?.longDesc
                    }

                }


                override fun onFailure(call: Call<Raza_detail>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                    Toast.makeText(
                        this@Details,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }
    }
}


