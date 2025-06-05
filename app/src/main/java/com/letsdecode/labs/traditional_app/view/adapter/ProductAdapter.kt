package com.letsdecode.labs.traditional_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.letsdecode.labs.R
import com.letsdecode.labs.databinding.ItemProductBinding
import com.letsdecode.labs.traditional_app.model.Product

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                // Map product name
                productTitle.text = product.productName
                
                // Map price with proper formatting
                productPrice.text = product.price.let { 
                    if (it.startsWith("$")) it else "$$it" 
                }
                
                productCategory.text = "General"

                // Map rating (format to 1 decimal place)
                productRating.text = String.format("%.1f", product.reviewRating)
                
                // Load image using Glide
                Glide.with(productImage)
                    .load(product.productImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(productImage)
                
                // Show out of stock indicator if needed
                if (!product.inStock) {
                    productPrice.setTextColor(
                        itemView.context.getColor(android.R.color.holo_red_dark)
                    )
                } else {
                    productPrice.setTextColor(
                        itemView.context.getColor(android.R.color.holo_green_dark)
                    )
                }
            }
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        // Use productId for identity
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        // Compare all fields that affect the visual representation
        return oldItem.productName == newItem.productName &&
               oldItem.price == newItem.price &&
               oldItem.productImage == newItem.productImage &&
               oldItem.reviewRating == newItem.reviewRating &&
               oldItem.inStock == newItem.inStock &&
               oldItem.shortDescription == newItem.shortDescription
    }
}
