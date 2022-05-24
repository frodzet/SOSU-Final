package exam.projects.sosu_final.repositories.dtos

data class SubjectDto(
    var firstName: String,
    var lastName: String,
    var email: String,
    var phone: String,
    var address: AddressDto,
)