package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class FunctionAbilityItem(
    @SerializedName("_id")
    val id: String,
    val subTitle: String,
    var currentLevel: Int,
    var expectedLevel: Int,
    var execution: Int,
    var meaningOfExecution: Int,
    val note: String,
    var subjectWish: String,
)