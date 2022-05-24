package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class GeneralInformation(
    @SerializedName("_id")
    val id: String,
    val comment: String,
    val description: String,
    val title: String
)