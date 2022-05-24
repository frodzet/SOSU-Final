package exam.projects.sosu_final.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import exam.projects.sosu_final.entities.Subject
import exam.projects.sosu_final.repositories.SubjectRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SubjectViewModel(private val subjectRepository: SubjectRepository): ViewModel() {
    val getAllSubjectsResponse: MutableLiveData<Response<List<Subject>>> = MutableLiveData()

    fun getAll() {
        viewModelScope.launch {
            val response = subjectRepository.getAll()
            getAllSubjectsResponse.value = response;
        }
    }
}