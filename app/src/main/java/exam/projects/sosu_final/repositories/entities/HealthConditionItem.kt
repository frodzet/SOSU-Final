package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class HealthConditionItem(
    @SerializedName("_id")
    val id: String,
    val comment: String,
    val reason: String,
    val relevant: String,
    val subTitle: String
)