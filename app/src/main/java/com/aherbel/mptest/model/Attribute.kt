package com.aherbel.mptest.model

import com.google.gson.annotations.SerializedName

data class Attribute (

	@SerializedName("name") val name : String? = null,
	@SerializedName("value_id") val valueId : String? = null,
	@SerializedName("value_name") val valueName : String? = null,
	@SerializedName("value_struct") val valueStruct : ValueStruct? = null,
	@SerializedName("attribute_group_id") val attributeGroupId : String? = null,
	@SerializedName("attribute_group_name") val attributeGroupName : String? = null,
	@SerializedName("source") val source : Long? = null,
	@SerializedName("id") val id : String? = null
)