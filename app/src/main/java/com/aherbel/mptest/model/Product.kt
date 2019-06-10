package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Product (

	@SerializedName("id") val id : String? = null,
	@SerializedName("site_id") val siteId : String? = null,
	@SerializedName("title") val title : String? = null,
	@SerializedName("seller") val seller : Seller? = null,
	@SerializedName("price") val price : Float? = null,
	@SerializedName("currency_id") val currencyId : String? = null,
	@SerializedName("available_quantity") val availableQuantity : Int? = null,
	@SerializedName("sold_quantity") val soldQuantity : Int? = null,
	@SerializedName("buying_mode") val buyingMode : String? = null,
	@SerializedName("listing_type_id") val listingTypeId : String? = null,
	@SerializedName("stop_time") val stopTime : String? = null,
	@SerializedName("condition") val condition : String? = null,
	@SerializedName("pictures") val pictures: ArrayList<Picture>? = null,
	@SerializedName("permalink") val permalink : String? = null,
	@SerializedName("thumbnail") val thumbnail : String? = null,
	@SerializedName("accepts_mercadopago") val acceptsMercadopago : Boolean? = null,
	@SerializedName("installments") val installments : Installments? = null,
	@SerializedName("address") val address : Address? = null,
	@SerializedName("shipping") val shipping : Shipping? = null,
	@SerializedName("seller_address") val sellerAddress : SellerAddress? = null,
	@SerializedName("attributes") val attributes : ArrayList<Attribute>? = null,
	@SerializedName("original_price") val originalPrice : Float? = null,
	@SerializedName("category_id") val categoryId : String? = null,
	@SerializedName("official_store_id") val officialStoreId : String? = null,
	@SerializedName("catalog_product_id") val catalogProductId : String? = null,
	@SerializedName("reviews") val reviews : Reviews? = null,
	@SerializedName("warranty") val warranty: String? = null,
	@SerializedName("tags") val tags : ArrayList<String>? = null
): Serializable {

	fun getDiscount(): Float {
		val originalPrice = originalPrice ?: 0f
		return if (originalPrice != 0f) {
			val price = price ?: 0f
			100 - (price * 100 / originalPrice)
		} else {
			0f
		}
	}
}