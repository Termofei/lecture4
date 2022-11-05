package com.example.lecture4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.lecture4.databinding.NameListItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NameFragment: Fragment() {
    lateinit var binding: NameListItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnBack.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container1, FirstFragment())
            transaction.addToBackStack("return_to_first_transaction")
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE )
            transaction.commit()


        }

        val retrofit2 = Retrofit.Builder().baseUrl("https://restcountries.com/v2/%22")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(OkHttpClient())
            .build()

        val nameService = retrofit2.create(NameService::class.java)
        val nameRepository = NameRepository(nameService)
        nameRepository.getNames()?.enqueue(object : Callback<List<Name>> {
            override fun onResponse(call: Call<List<Name>>, response: Response<List<Name>>) {
                val names = response.body() ?: return
                Log.e("names", names.toString())
            }

            override fun onFailure(call: Call<List<Name>>, t: Throwable) {

            }

        })

        return binding.root
    }

}