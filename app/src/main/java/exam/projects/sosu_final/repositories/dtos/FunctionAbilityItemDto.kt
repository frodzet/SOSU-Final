package exam.projects.sosu_final.repositories.dtos

data class FunctionAbilityItemDto(
    var currentLevel: Number,
    var execution: String,
    var expectedLevel: Number,
    var meaningOfExecution: String,
    var note: String,
    var subjectWish: String,
)