package exam.projects.sosu_final.repositories.dtos

data class FunctionAbilityItemDto(
    var currentLevel: Int,
    var expectedLevel: Int,
    var execution: Int,
    var meaningOfExecution: Int,
    var subjectWish: String,
)