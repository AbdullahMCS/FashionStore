package com.cyberwalker.fashionstore.data

import androidx.annotation.DrawableRes

data class ShopItem(
    val id: Int? = null,
    val name: String? = null,
    val price: String? = null,
    @DrawableRes val image: Int? = null
)