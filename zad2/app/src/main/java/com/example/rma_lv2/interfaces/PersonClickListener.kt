package com.example.rma_lv2.interfaces

interface PersonClickListener {
    fun onRemoveImageClicked(position: Int)
    fun onPersonImageClicked(position: Int)
    fun onEditImageClicked(id: Int)
}