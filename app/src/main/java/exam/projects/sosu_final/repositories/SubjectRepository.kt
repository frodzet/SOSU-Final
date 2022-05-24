package exam.projects.sosu_final.repositories

import exam.projects.sosu_final.api.RetrofitInstance
import exam.projects.sosu_final.entities.Subject
import retrofit2.Response

class SubjectRepository {
    suspend fun getAll(): Response<List<Subject>> {
        return RetrofitInstance.subjectApi.getAll()
    }
}