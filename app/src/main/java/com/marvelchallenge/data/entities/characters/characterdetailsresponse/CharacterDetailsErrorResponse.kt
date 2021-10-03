package com.marvelchallenge.data.entities.characters.characterdetailsresponse

import com.google.gson.annotations.SerializedName

data class CharacterDetailsErrorResponse(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
