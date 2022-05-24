package exam.projects.sosu_final.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.SubjectAdapter
import exam.projects.sosu_final.databinding.ActivityDashboardBinding
import exam.projects.sosu_final.entities.Subject
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class DashboardActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityDashboardBinding
    private lateinit var lastClickedSubject: Subject
    private var lastClickedSubjectIndex: Int = 0

    private val subjectsAdapter by lazy {
        SubjectAdapter(listener = { subject: Subject, position: Int ->
            lastClickedSubject = subject
            lastClickedSubjectIndex = position
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        setupRecyclerView()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel = ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        subjectViewModel.getAll()
        subjectViewModel.getAllSubjectsResponse.observe(this, Observer {response ->
            if(response.isSuccessful) {
                subjectsAdapter.allSubjects = response.body()!!
            } else {
                Toast.makeText(this, "HELLO", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewSubjects.adapter = subjectsAdapter
            recyclerViewSubjects.layoutManager = LinearLayoutManager(this@DashboardActivity)
        }
    }
}