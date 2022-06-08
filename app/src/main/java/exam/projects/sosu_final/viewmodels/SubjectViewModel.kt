package exam.projects.sosu_final.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.FunctionAbilityItemDto
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.HealthConditionItemDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.FunctionAbility
import exam.projects.sosu_final.repositories.entities.GeneralInformation
import exam.projects.sosu_final.repositories.entities.HealthCondition
import exam.projects.sosu_final.repositories.entities.Subject
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val TAG = "ViewModel"
const val IOException = "Server not responding!"
const val HttpException = "Bad request!"

class SubjectViewModel(private val subjectRepository: SubjectRepository) : ViewModel() {
    /* SUBJECT */
    val getAllSubjectsResponse: MutableLiveData<Response<List<Subject>>> = MutableLiveData()
    val getOneSubjectResponse: MutableLiveData<Response<Subject>> = MutableLiveData()

    /* GENERAL INFORMATION */
    val getAllGeneralInformationResponse: MutableLiveData<Response<List<GeneralInformation>>> =
        MutableLiveData()

    /* HEALTH CONDITIONS */
    val getAllHealthConditionsResponse: MutableLiveData<Response<List<HealthCondition>>> = MutableLiveData()
    val getOneHealthConditionResponse: MutableLiveData<Response<HealthCondition>> = MutableLiveData()

    /* FUNCTION ABILITIES */
    val getAllFunctionAbilitiesResponse: MutableLiveData<Response<List<FunctionAbility>>> = MutableLiveData()
    val getOneFunctionAbilityResponse: MutableLiveData<Response<FunctionAbility>> = MutableLiveData()

    fun getAllSubjects() {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getAllSubjects()
                getAllSubjectsResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "getAllSubjects: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllSubjects: $IOException")
                return@launch
            }
        }
    }

    fun getOneSubject(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOneSubject(subjectId)
                getOneSubjectResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "getOneSubject: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getOneSubject: $IOException")
                return@launch
            }
        }
    }

    fun addSubject(subjectDto: SubjectDto) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.addSubject(subjectDto)
            } catch (e: IOException) {
                Log.e(TAG, "addSubject: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "addSubject: $IOException")
                return@launch
            }
            getAllSubjects()
        }
    }

    fun getAllGeneralInformation(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getAllGeneralInformation(subjectId)
                getAllGeneralInformationResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "getAllGeneralInformation: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllGeneralInformation: $IOException")
                return@launch
            }
        }
    }

    fun updateGeneralInformation(
        subjectId: String,
        generalInformationId: String,
        generalInformationDto: GeneralInformationDto
    ) {
        viewModelScope.launch {
            try {
                subjectRepository.updateGeneralInformation(
                    subjectId,
                    generalInformationId,
                    generalInformationDto
                )
            } catch (e: IOException) {
                Log.e(TAG, "updateGeneralInformation: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "updateGeneralInformation: $IOException")
                return@launch
            }
        }
    }

    fun getAllHealthConditions(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getAllHealthConditions(subjectId)
                getAllHealthConditionsResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "getAllHealthConditions: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllHealthConditions: $IOException")
                return@launch
            }
        }
    }

    fun getOneHealthCondition(subjectId: String, healthConditionId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOneHealthCondition(subjectId, healthConditionId)
                getOneHealthConditionResponse.value = response!!
            } catch (e: IOException) {
                Log.e(TAG, "getAllHealthConditionItems: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllHealthConditionItems: $IOException")
                return@launch
            }
        }
    }

    fun updateHealthConditionItem(
        subjectId: String,
        healthConditionId: String,
        healthConditionItemId: String,
        healthConditionItemDto: HealthConditionItemDto
    ) {
        viewModelScope.launch {
            try {
                subjectRepository.updateHealthConditionItem(
                    subjectId,
                    healthConditionId,
                    healthConditionItemId,
                    healthConditionItemDto
                )
            } catch (e: IOException) {
                Log.e(TAG, "updateHealthConditionItem: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "updateHealthConditionItem: $IOException")
                return@launch
            }
        }
    }

    fun getAllFunctionAbilities(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getAllFunctionAbilities(subjectId)
                getAllFunctionAbilitiesResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "getAllFunctionAbilities: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllFunctionAbilities: $IOException")
                return@launch
            }
        }
    }

    fun getOneFunctionAbility(subjectId: String, functionAbilityId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOneFunctionAbility(subjectId, functionAbilityId)
                getOneFunctionAbilityResponse.value = response!!
            } catch (e: IOException) {
                Log.e(TAG, "getOneFunctionAbility: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getOneFunctionAbility: $IOException")
                return@launch
            }
        }
    }

    fun updateFunctionAbilityItem(
        subjectId: String,
        functionAbilityId: String,
        functionAbilityItemId: String,
        functionAbilityItemDto: FunctionAbilityItemDto
    ) {
        viewModelScope.launch {
            try {
                subjectRepository.updateFunctionAbilityItem(
                    subjectId,
                    functionAbilityId,
                    functionAbilityItemId,
                    functionAbilityItemDto
                )
            } catch (e: IOException) {
                Log.e(TAG, "updateHealthConditionItem: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "updateHealthConditionItem: $IOException")
                return@launch
            }
        }
    }
}