package exam.projects.sosu_final.entities

data class HealthCondition(
    val __v: Int,
    val _id: String,
    val healthConditionItems: List<HealthConditionItem>,
    val title: String
)