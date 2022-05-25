package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class HealthConditionItem(
    @SerializedName("_id")
    val id: String,
    var comment: String,
    var reason: String,
    var relevant: Number,
    val subTitle: String
)