package exam.projects.sosu_final.repositories.dtos

data class FunctionAbilityItemDto(
    val currentLevel: String,
    val execution: String,
    val expectedLevel: String,
    val meaningOfExecution: String,
    val note: String,
    val subjectWish: String,
)