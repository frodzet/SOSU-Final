package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class FunctionAbilityItem(
    @SerializedName("_id")
    val id: String,
    val currentLevel: Int,
    val execution: String,
    val expectedLevel: Int,
    val meaningOfExecution: String,
    val note: String,
    val subTitle: String,
    val subjectWish: String,
)