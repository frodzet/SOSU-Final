package exam.projects.sosu_final.repositories

import exam.projects.sosu_final.api.RetrofitInstance
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.Subject
import retrofit2.Response

class SubjectRepository {
    suspend fun getAll(): Response<List<Subject>> {
        return RetrofitInstance.subjectApi.getAll()
    }

    suspend fun getOne(subjectId: String): Response<Subject> {
        return RetrofitInstance.subjectApi.getOne(subjectId)
    }

    suspend fun addSubject(subjectDto: SubjectDto): Response<SubjectDto> {
        return RetrofitInstance.subjectApi.addSubject(subjectDto);
    }
}