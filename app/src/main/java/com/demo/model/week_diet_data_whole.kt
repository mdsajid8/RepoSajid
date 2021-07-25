package com.demo.model

import com.google.gson.annotations.SerializedName

data class week_diet_data_whole(

        @field:SerializedName("dayOfWeek")
        var day: String? = null,

        @field:SerializedName("meal_time")
        var mealTime: String? = null,

        @field:SerializedName("food")
        var food: String? = null
)

