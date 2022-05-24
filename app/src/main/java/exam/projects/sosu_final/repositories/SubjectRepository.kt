package exam.projects.sosu_final.repositories

import exam.projects.sosu_final.api.RetrofitInstance
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.GeneralInformation
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

    suspend fun deleteSubject(subjectId: String): Response<Subject> {
        return RetrofitInstance.subjectApi.deleteSubject(subjectId)
    }

    suspend fun getAllGeneralInformation(subjectId: String): Response<List<GeneralInformation>> {
        return RetrofitInstance.subjectApi.getAllGeneralInformation(subjectId)
    }

    suspend fun getOneGeneralInformation(subjectId: String, generalInformationId: String): Response<GeneralInformation> {
        return RetrofitInstance.subjectApi.getOneGeneralInformation(subjectId, generalInformationId);
    }

    suspend fun updateGeneralInformation(subjectId: String, generalInformationId: String, generalInformationDto: GeneralInformationDto): Response<GeneralInformationDto> {
        return RetrofitInstance.subjectApi.updateGeneralInformation(subjectId, generalInformationId, generalInformationDto);
    }
}