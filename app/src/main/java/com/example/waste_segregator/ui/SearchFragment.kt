package com.example.waste_segregator.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waste_segregator.ui.adapter.ProductAdapter
import com.example.waste_segregator.databinding.FragmentSearchBinding
import com.example.waste_segregator.models.Product
import com.example.waste_segregator.repository.WastesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var repository: WastesRepository

    private val adapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        repository = WastesRepository()
        val productsList = mutableListOf<Product>()

        GlobalScope.launch {
            val response = repository.getWastes(1000)
            response.body()?.result?.records?.forEach {
                val product = Product(it.Nazwa!!, it.Typ!!)
                productsList.add(product)
            }
        }


        binding.enteredProductEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("Entered product", s.toString())
                val filteredProductList = productsList.filter { prod ->
                    s.toString() != "" && prod.product.uppercase()
                        .contains(s.toString().uppercase())
                }
                Log.d("Filtered products", filteredProductList.toString())
                adapter.setData(filteredProductList)
            }
        })

    }

    fun setupRecyclerView() {
        binding.productsRecyclerView.adapter = adapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}