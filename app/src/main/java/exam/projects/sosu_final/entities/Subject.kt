package exam.projects.sosu_final.entities

data class Subject(
    val __v: Int,
    val _id: String,
    val address: Address,
    val email: String,
    val firstName: String,
    val functionAbilities: List<FunctionAbility>,
    val generalInformation: List<GeneralInformation>,
    val healthConditions: List<HealthCondition>,
    val lastName: String,
    val phone: String
)