package exam.projects.sosu_final.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import exam.projects.sosu_final.repositories.SubjectRepository

class SubjectViewModelFactory(private val subjectRepository: SubjectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SubjectViewModel(subjectRepository) as T
    }
}