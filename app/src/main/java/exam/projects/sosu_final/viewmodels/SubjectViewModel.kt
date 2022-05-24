package exam.projects.sosu_final.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import exam.projects.sosu_final.repositories.entities.Subject
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
const val TAG = "ViewModel"
const val IOException = "Server not responding!"
const val HttpException = "Bad request!"
class SubjectViewModel(private val subjectRepository: SubjectRepository): ViewModel() {
    val getOneSubjectResponse: MutableLiveData<Response<Subject>> = MutableLiveData()
    val getAllSubjectsResponse: MutableLiveData<Response<List<Subject>>> = MutableLiveData()
    val addSubjectResponse: MutableLiveData<Response<SubjectDto>> = MutableLiveData()

    fun getOne(subjectId: String) {
        viewModelScope.launch {
            try {
                val response = subjectRepository.getOne(subjectId)
                getOneSubjectResponse.value = response;
            } catch (e: IOException) {
                Log.e(TAG, "getAll: $IOException")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getAll: $HttpException")
                return@launch
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
}