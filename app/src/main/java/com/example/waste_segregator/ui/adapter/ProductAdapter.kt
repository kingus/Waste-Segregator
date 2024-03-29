package com.example.waste_segregator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waste_segregator.R
import com.example.waste_segregator.databinding.ProductItemBinding
import com.example.waste_segregator.models.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.PostsViewHolder>() {

    private var products = emptyList<Product>()

    inner class PostsViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.binding.productTextView.text = products[position].product
        holder.binding.wasteTypeTextView.text = products[position].wasteType

        when (products[position].wasteType) {
            WasteType.PAPER.typeName -> holder.binding.binImageView.setImageResource(R.drawable.recycling_bin_blue)
            WasteType.METAL_PLASTIC.typeName -> holder.binding.binImageView.setImageResource(R.drawable.recycling_bin_yellow)
            WasteType.MIXED.typeName -> holder.binding.binImageView.setImageResource(R.drawable.recycling_bin_black)
            WasteType.BIO.typeName -> holder.binding.binImageView.setImageResource(R.drawable.recycling_bin_brown)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(productsList: List<Product>) {
        products = productsList
        notifyDataSetChanged()
    }

}