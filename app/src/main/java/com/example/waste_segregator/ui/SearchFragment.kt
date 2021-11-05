package com.example.waste_segregator.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waste_segregator.ui.adapter.ProductAdapter
import com.example.waste_segregator.databinding.FragmentSearchBinding
import com.example.waste_segregator.models.Product
import com.example.waste_segregator.ui.viewmodel.WastesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), LifecycleOwner {

    private val TAG = SearchFragment::class.qualifiedName

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: WastesViewModel by viewModels()


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
        val productsList = mutableListOf<Product>()

        viewModel.getWastes(1000)

        viewModel.products.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                Log.d(TAG, "Products have been loaded")
                response.body()?.result?.records?.forEach {
                    val synonyms = it.Synonim?.split(", '")
                    val product = Product(it.Nazwa!!, it.Typ!!, synonyms)
                    productsList.add(product)
                }
            } else {
                Log.d(TAG, "Can't retrieve products info.")
            }
        })

        binding.enteredProductEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }

            override fun afterTextChanged(s: Editable?) {

                val filteredProductList = productsList.filter { product ->
                    product.synonyms!!.any { synonym ->
                        s.toString() != "" &&
                                synonym.uppercase().contains(s.toString().uppercase())
                                || s.toString() != "" && product.product.uppercase()
                            .contains(s.toString().uppercase())
                    }
                }

                adapter.setData(filteredProductList)
                Log.d("Filtered products", filteredProductList.toString())
            }
        })

    }

    private fun setupRecyclerView() {
        binding.productsRecyclerView.adapter = adapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}