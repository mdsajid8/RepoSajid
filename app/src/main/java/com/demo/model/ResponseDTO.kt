package com.demo.model

import com.google.gson.annotations.SerializedName


data class ResponseDTO(

	@field:SerializedName("diet_duration")
	val dietDuration: Int? = null,

	@field:SerializedName("week_diet_data")
	val weekDietData: WeekDietData? = null
)

data class WednesdayItem(

	@field:SerializedName("meal_time")
	val mealTime: String? = null,

	@field:SerializedName("food")
	val food: String? = null
)

data class MondayItem(

	@field:SerializedName("meal_time")
	val mealTime: String? = null,

	@field:SerializedName("food")
	val food: String? = null
)

data class ThursdayItem(

	@field:SerializedName("meal_time")
	val mealTime: String? = null,

	@field:SerializedName("food")
	val food: String? = null
)

data class WeekDietData(

	@field:SerializedName("wednesday")
	val wednesday: List<WednesdayItem?>? = null,

	@field:SerializedName("thursday")
	val thursday: List<ThursdayItem?>? = null,

	@field:SerializedName("monday")
	val monday: List<MondayItem?>? = null
)
