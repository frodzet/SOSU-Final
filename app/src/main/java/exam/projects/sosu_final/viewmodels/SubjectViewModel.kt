package exam.projects.sosu_final.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.HealthConditionItemDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.repositories.entities.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
const val TAG = "ViewModel"
const val IOException = "Server not responding!"
const val HttpException = "Bad request!"
class SubjectViewModel(private val subjectRepository: SubjectRepository): ViewModel() {
    /* SUBJECT */
    val getOneSubjectResponse: MutableLiveData<Response<Subject>> = MutableLiveData()
    val getAllSubjectsResponse: MutableLiveData<Response<List<Subject>>> = MutableLiveData()
    val addSubjectResponse: MutableLiveData<Response<SubjectDto>> = MutableLiveData()

    /* GENERAL INFORMATION */
    val getAllGeneralInformationResponse: MutableLiveData<Response<List<GeneralInformation>>> = MutableLiveData()
    val getOneGeneralInformationResponse: MutableLiveData<Response<GeneralInformation>> = MutableLiveData()
    val updateGeneralInformationResponse: MutableLiveData<Response<GeneralInformationDto>> = MutableLiveData()

    /* HEALTH CONDITIONS */
    val getAllHealthConditionsResponse: MutableLiveData<Response<List<HealthCondition>>> = MutableLiveData()
    val getOneHealthConditionResponse: MutableLiveData<Response<HealthCondition>> = MutableLiveData()
    val updateHealthConditionResponse: MutableLiveData<Response<HealthConditionItemDto>> = MutableLiveData()

    /* FUNCTION ABILITIES */
    val getAllFunctionAbilitiesResponse: MutableLiveData<Response<List<FunctionAbility>>> = MutableLiveData()
    val getOneFunctionAbilityResponse: MutableLiveData<Response<FunctionAbility>> = MutableLiveData()

    fun getOne(subjectId: String) {
        viewModelScope.launch {
            val response = try {
                subjectRepository.getOne(subjectId)
            } catch (e: IOException) {
                Log.e(TAG,"getOne: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getOne: $HttpException")
                return@launch
            }

            if (response.isSuccessful) {
                getOneSubjectResponse.value = response;
            } else {
                Log.e(TAG, "getOne: ${response.code()}")
            }
        }
    }

    fun getAll() {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getAll()
                getAllSubjectsResponse.value = response;
            } catch (e: IOException) {
                Log.e(TAG, "getAll: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAll: $HttpException")
                return@launch
            }
        }
    }

    fun addSubject(subjectDto: SubjectDto) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.addSubject(subjectDto)
                addSubjectResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "addSubject: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "addSubject: $IOException")
                return@launch
            }
        }
    }

    fun deleteSubject(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.deleteSubject(subjectId)
                getOneSubjectResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "deleteSubject: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "deleteSubject: $IOException")
                return@launch
            }
        }
    }

    fun getAllGeneralInformation(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getAllGeneralInformation(subjectId)
                getAllGeneralInformationResponse.value = response;
            } catch (e: IOException) {
                Log.e(TAG, "getAllGeneralInformation: $IOException", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllGeneralInformation: $IOException")
                return@launch
            }
        }
    }

    fun getOneGeneralInformation(subjectId: String, generalInformationId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOneGeneralInformation(subjectId, generalInformationId)
                getOneGeneralInformationResponse.value = response;
            } catch (e: IOException) {
                Log.e(TAG, "getOneGeneralInformation: $IOException", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getOneGeneralInformation: $IOException")
                return@launch
            }
        }
    }

    fun updateGeneralInformation(subjectId: String, generalInformationId: String, generalInformationDto: GeneralInformationDto) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.updateGeneralInformation(subjectId, generalInformationId, generalInformationDto)
                updateGeneralInformationResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "updateGeneralInformation: $IOException", )
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
                Log.e(TAG, "getAllHealthConditions: $IOException", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllHealthConditions: $IOException")
                return@launch
            }
        }
    }

    fun getAllHealthConditionItems(subjectId: String, healthConditionId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOneHealthCondition(subjectId, healthConditionId)
                getOneHealthConditionResponse.value = response!!
            } catch (e: IOException) {
                Log.e(TAG, "getAllHealthConditionItems: $IOException", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllHealthConditionItems: $IOException")
                return@launch
            }
        }
    }

    fun updateHealthConditionItem(subjectId: String, healthConditionId: String, healthConditionItemId: String, healthConditionItemDto: HealthConditionItemDto) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.updateHealthConditionItem(subjectId, healthConditionId, healthConditionItemId, healthConditionItemDto)
                updateHealthConditionResponse.value = response
            } catch (e: IOException) {
                Log.e(TAG, "updateHealthConditionItem: $IOException", )
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
                Log.e(TAG, "getAllFunctionAbilities: $IOException", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAllFunctionAbilities: $IOException")
                return@launch
            }
        }
    }

    fun getAllFunctionAbilityItems(subjectId: String, functionAbilityId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOneFunctionAbility(subjectId, functionAbilityId)
                getOneFunctionAbilityResponse.value = response!!
            } catch (e: IOException) {
                Log.e(TAG, "getOneFunctionAbility: $IOException", )
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getOneFunctionAbility: $IOException")
                return@launch
            }
        }
    }
}