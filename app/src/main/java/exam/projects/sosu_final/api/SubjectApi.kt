package exam.projects.sosu_final.api

import exam.projects.sosu_final.entities.Subject
import retrofit2.Response
import retrofit2.http.GET

interface SubjectApi {
    @GET("subjects")
    suspend fun getAll(): Response<List<Subject>>
}