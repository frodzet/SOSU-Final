package exam.projects.sosu_final.api

import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.GeneralInformation
import exam.projects.sosu_final.repositories.entities.Subject
import retrofit2.Response
import retrofit2.http.*

interface SubjectApi {
    @GET("subjects")
    suspend fun getAll(): Response<List<Subject>>

    @GET("subjects/{subjectId}")
    suspend fun getOne(@Path("subjectId") subjectId: String): Response<Subject>

    @POST("subjects")
    suspend fun addSubject(@Body subjectDto: SubjectDto): Response<SubjectDto>

    @PATCH("subjects/{subjectId}/general-information/{generalInformationId}")
    suspend fun updateGeneralInformation(@Path("subjectId") subjectId: String, @Path("generalInformationId") generalInformationId: String, @Body generalInformationDto: GeneralInformationDto): Response<GeneralInformationDto>
}