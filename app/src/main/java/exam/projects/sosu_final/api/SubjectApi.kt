package exam.projects.sosu_final.api

import exam.projects.sosu_final.repositories.dtos.FunctionAbilityItemDto
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.HealthConditionItemDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.*
import retrofit2.Response
import retrofit2.http.*

interface SubjectApi {
    @GET("subjects")
    suspend fun getAll(): Response<List<Subject>>

    @GET("subjects/{subjectId}")
    suspend fun getOne(@Path("subjectId") subjectId: String): Response<Subject>

    @POST("subjects")
    suspend fun addSubject(@Body subjectDto: SubjectDto): Response<Subject>

    @DELETE("subjects/{subjectId}")
    suspend fun deleteSubject(@Path("subjectId") subjectId: String): Response<Subject>

    @GET("subjects/{subjectId}/general-information")
    suspend fun getAllGeneralInformation(@Path("subjectId") subjectId: String): Response<List<GeneralInformation>>

    @GET("subjects/{subjectId}/general-information/{generalInformationId}")
    suspend fun getOneGeneralInformation(@Path("subjectId") subjectId: String, @Path("generalInformationId") generalInformationId: String): Response<GeneralInformation>

    @PATCH("subjects/{subjectId}/general-information/{generalInformationId}")
    suspend fun updateGeneralInformation(@Path("subjectId") subjectId: String, @Path("generalInformationId") generalInformationId: String, @Body generalInformationDto: GeneralInformationDto): Response<GeneralInformationDto>

    @GET("subjects/{subjectId}/health-conditions")
    suspend fun getAllHealthCondition(@Path("subjectId") subjectId: String): Response<List<HealthCondition>>

    @GET("subjects/{subjectId}/health-conditions/{healthConditionId}")
    suspend fun getOneHealthCondition(@Path("subjectId") subjectId: String, @Path("healthConditionId") healthConditionId: String): Response<HealthCondition>

    @PATCH("subjects/{subjectId}/health-conditions/{healthConditionId}/{healthConditionItemId}")
    suspend fun updateHealthConditionItem(@Path("subjectId") subjectId: String, @Path("healthConditionId") healthConditionId: String, @Path("healthConditionItemId") healthConditionItemId: String, @Body healthConditionItemDto: HealthConditionItemDto): Response<HealthConditionItemDto>

    @GET("subjects/{subjectId}/function-abilities")
    suspend fun getAllFunctionAbilities(@Path("subjectId") subjectId: String): Response<List<FunctionAbility>>

    @GET("subjects/{subjectId}/function-abilities/{functionAbilityId}")
    suspend fun getOneFunctionAbility(@Path("subjectId") subjectId: String, @Path("functionAbilityId") functionAbilityId: String): Response<FunctionAbility>

    @PATCH("subjects/{subjectId}/function-abilities/{functionAbilityId}/{functionAbilityItemId}")
    suspend fun updateFunctionAbilityItem(@Path("subjectId") subjectId: String, @Path("functionAbilityId") functionAbilityId: String, @Path("functionAbilityItemId") functionAbilityItemId: String, @Body functionAbilityItemDto: FunctionAbilityItemDto) : Response<FunctionAbilityItemDto>
}