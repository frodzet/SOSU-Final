package exam.projects.sosu_final.repositories.entities

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("_id")
    val id: String,
    val city: String,
    val postCode: Int,
    val street: String
)