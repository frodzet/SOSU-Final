package exam.projects.sosu_final.api

import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.Subject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SubjectApi {
    @GET("subjects")
    suspend fun getAll(): Response<List<Subject>>

    @GET("subjects/{subjectId}")
    suspend fun getOne(@Path("subjectId") subjectId: String): Response<Subject>

    @POST("subjects")
    suspend fun addSubject(@Body subjectDto: SubjectDto): Response<SubjectDto>
}