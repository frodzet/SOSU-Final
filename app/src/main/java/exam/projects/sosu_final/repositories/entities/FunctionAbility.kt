package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class FunctionAbility(
    @SerializedName("_id")
    val id: String,
    val functionAbilityItems: List<FunctionAbilityItem>,
    val title: String
)