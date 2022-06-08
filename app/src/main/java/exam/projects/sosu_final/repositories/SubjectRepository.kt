package exam.projects.sosu_final.repositories

import exam.projects.sosu_final.api.RetrofitInstance
import exam.projects.sosu_final.repositories.dtos.FunctionAbilityItemDto
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.HealthConditionItemDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.*
import exam.projects.sosu_final.viewmodels.IOException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class SubjectRepository {
    suspend fun getAll(): Response<List<Subject>> {
        return RetrofitInstance.subjectApi.getAll()
    }

    suspend fun getOne(subjectId: String): Response<Subject> {
        return RetrofitInstance.subjectApi.getOne(subjectId)
    }

    suspend fun addSubject(subjectDto: SubjectDto): Response<Subject> {
        return RetrofitInstance.subjectApi.addSubject(subjectDto)
    }

    suspend fun getAllGeneralInformation(subjectId: String): Response<List<GeneralInformation>> {
        return RetrofitInstance.subjectApi.getAllGeneralInformation(subjectId)
    }

    suspend fun updateGeneralInformation(subjectId: String, generalInformationId: String, generalInformationDto: GeneralInformationDto): Response<GeneralInformationDto> {
        return RetrofitInstance.subjectApi.updateGeneralInformation(subjectId, generalInformationId, generalInformationDto);
    }

    suspend fun getAllHealthConditions(subjectId: String): Response<List<HealthCondition>> {
        return RetrofitInstance.subjectApi.getAllHealthCondition(subjectId)
    }

    suspend fun getOneHealthCondition(subjectId: String, healthConditionId: String): Response<HealthCondition>? {
        return RetrofitInstance.subjectApi.getOneHealthCondition(subjectId, healthConditionId);
    }

    suspend fun updateHealthConditionItem(subjectId: String, healthConditionId: String, healthConditionItemId: String, healthConditionItemDto: HealthConditionItemDto): Response<HealthConditionItemDto> {
        return RetrofitInstance.subjectApi.updateHealthConditionItem(subjectId, healthConditionId, healthConditionItemId, healthConditionItemDto);
    }

    suspend fun getAllFunctionAbilities(subjectId: String): Response<List<FunctionAbility>> {
        return RetrofitInstance.subjectApi.getAllFunctionAbilities(subjectId)
    }

    suspend fun getOneFunctionAbility(subjectId: String, functionAbilityId: String): Response<FunctionAbility>? {
        return RetrofitInstance.subjectApi.getOneFunctionAbility(subjectId, functionAbilityId)
    }

    suspend fun updateFunctionAbilityItem(subjectId: String, functionAbilityId: String, functionAbilityItemId: String, functionAbilityItemDto: FunctionAbilityItemDto): Response<FunctionAbilityItemDto> {
        return RetrofitInstance.subjectApi.updateFunctionAbilityItem(subjectId, functionAbilityId, functionAbilityItemId, functionAbilityItemDto)
    }
}