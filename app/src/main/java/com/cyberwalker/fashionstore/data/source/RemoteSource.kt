package com.cyberwalker.fashionstore.data.source

import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.data.ShopItem

object RemoteSource {
    val shopItems = arrayListOf<ShopItem>(
        ShopItem(
            id = 1,
            name = "Jhc T-Shirt",
            price = "₹989",
            image = R.drawable.img_1
        ),
        ShopItem(
            id = 2,
            name = "Lkm T-Shirt",
            price = "₹674",
            image = R.drawable.img_3
        ),
        ShopItem(
            id = 3,
            name = "Sbm T-Shirt",
            price = "₹1189",
            image = R.drawable.img_2
        ),
        ShopItem(
            id = 4,
            name = "Nkr T-Shirt",
            price = "₹1589",
            image = R.drawable.img_4
        )
    )
}