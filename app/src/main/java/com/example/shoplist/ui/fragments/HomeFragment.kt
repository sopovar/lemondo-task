package com.example.shoplist.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoplist.R
import com.example.shoplist.adapter.ShopAdapter
import com.example.shoplist.databinding.FragmentHomeBinding
import com.example.shoplist.model.Resource
import com.example.shoplist.model.Shop
import com.example.shoplist.utils.Constants.Companion.TOKEN
import com.example.shoplist.utils.PrefHelper
import com.example.shoplist.viewmodel.ShopViewModel
import com.example.shoplist.viewmodel.ShopViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by sopovardidze
 */
class HomeFragment : Fragment(R.layout.fragment_home), KodeinAware {

    override val kodein by kodein()

    private val factory : ShopViewModelFactory by instance()

    private lateinit var binding : FragmentHomeBinding

    private lateinit var shopAdapter : ShopAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val viewModel : ShopViewModel by viewModels { factory }

        val token = PrefHelper.read(TOKEN, "")

        if(token == null || token == ""){
            viewModel.authToken.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Loading -> {
                        Toast.makeText(context, "Auth Loading", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        response.data.let {
                            PrefHelper.write(TOKEN, it?.access_token)
                        }
                        viewModel.getShopData()
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else {
            viewModel.getShopData()
        }

        viewModel.shopData.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Loading -> {
                    showProgressBar(true)
                }
                is Resource.Success -> {
                    initRecyclerview(response.data!!.shops)
                }
                is Resource.Error -> {
                    showProgressBar(false)
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecyclerview(items : List<Shop>){
        showProgressBar(false)
        shopAdapter = ShopAdapter(items, requireContext())

        binding.shopRecyclerView.apply {
            adapter = shopAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(value : Boolean) = if(value) {
        binding.progressBar.visibility = View.VISIBLE
    } else {
        binding.progressBar.visibility = View.GONE
    }
}