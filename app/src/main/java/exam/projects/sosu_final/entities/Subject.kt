package exam.projects.sosu_final.entities

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("_id")
    val id: String,
    val address: Address,
    val email: String,
    val firstName: String,
    val functionAbilities: List<FunctionAbility>,
    val generalInformation: List<GeneralInformation>,
    val healthConditions: List<HealthCondition>,
    val lastName: String,
    val phone: String
)