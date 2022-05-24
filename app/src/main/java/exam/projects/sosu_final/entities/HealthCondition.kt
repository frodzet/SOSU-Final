package exam.projects.sosu_final.entities

import com.google.gson.annotations.SerializedName

data class HealthCondition(
    @SerializedName("_id")
    val id: String,
    val healthConditionItems: List<HealthConditionItem>,
    val title: String
)