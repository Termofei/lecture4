package com.example.lecture4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lecture4.databinding.NameListItemBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NameFragment(private val countryName: String ): Fragment() {
    lateinit var binding: NameListItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NameListItemBinding.inflate(inflater, container, false)


        val retrofit2 = Retrofit.Builder().baseUrl("https://restcountries.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        val nameService = retrofit2.create(NameService::class.java)
        val nameRepository = NameRepository(nameService)
        nameRepository.getNames(countryName)?.enqueue(object : Callback<List<Name>> {
            override fun onResponse(call: Call<List<Name>>, response: Response<List<Name>>) {
                val names = response.body()?.get(0) ?: return
                Log.d("names", names.toString())
                binding.apply {
                    country = names.name
                    capital = names.capital
                    region = names.region
                    population = names.population.toString()
                    area = names.area.toString()


                    Glide
                        .with(this.root.context)
                        .load(names.flags.png)
                        .fitCenter()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(ivFlag2)

                }
                binding.btnBack.setOnClickListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
            override fun onFailure(call: Call<List<Name>>, t: Throwable) {
                Snackbar.make(binding.root, "Failed", Snackbar.LENGTH_LONG )
                    .show()

            }
        })

        return binding.root
    }

}