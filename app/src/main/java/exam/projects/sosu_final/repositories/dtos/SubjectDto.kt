package exam.projects.sosu_final.repositories.dtos

data class SubjectDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val address: AddressDto,
)