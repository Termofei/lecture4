package com.example.lecture4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lecture4.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment: Fragment()  {
    lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container,false)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val countryService = retrofit.create(CountryService::class.java)
        val countryRepository = CountryRepository(countryService)

        countryRepository.getCountries()?.enqueue(object: Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                val countries = response.body() ?: return
                val adapter = CountryAdapter(countries)
                binding.countriesList.adapter = adapter
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {

            }

        })
        return binding.root
    }

}