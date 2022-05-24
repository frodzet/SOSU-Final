package exam.projects.sosu_final.repositories.dtos

data class FunctionAbilityItemDto(
    var currentLevel: String,
    var execution: String,
    var expectedLevel: String,
    var meaningOfExecution: String,
    var note: String,
    var subjectWish: String,
)