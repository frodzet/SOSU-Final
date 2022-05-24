package exam.projects.sosu_final.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.SubjectAdapter
import exam.projects.sosu_final.databinding.ActivityDashboardBinding
import exam.projects.sosu_final.repositories.entities.Subject
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.AddressDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
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
            startSubjectActivity(subject)
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

        getAllSubjects()

    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {

            recyclerViewSubjects.adapter = subjectsAdapter
            recyclerViewSubjects.layoutManager = LinearLayoutManager(this@DashboardActivity)
        }
    }

    /* MENU */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menuItemAddSubject -> {
                addSubject()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startSubjectActivity(subject: Subject) {
        val intent = Intent(this@DashboardActivity, SubjectActivity::class.java)

        intent.putExtra("subjectId", subject.id)

        startActivity(intent)
    }

    /* HttpClient */
    private fun getAllSubjects() {
        subjectViewModel.getAll()
        subjectViewModel.getAllSubjectsResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                subjectsAdapter.allSubjects = response.body()!!
            } else {
                Toast.makeText(this, "Prøv igen", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun addSubject() {
        val subjectDto = SubjectDto("Mikkel", "Hansen", "Mikkel@hansen.dk", "50 50 50 50", AddressDto("Mikkel Castle Rock", 6800, "Mikkel Awesome Street"))
        subjectViewModel.addSubject(subjectDto)
        subjectViewModel.addSubjectResponse.observe(this@DashboardActivity, Observer { response ->
            if(response.isSuccessful) {
                Toast.makeText(this@DashboardActivity, "Borger tilføjet!", Toast.LENGTH_LONG).show()
                getAllSubjects()
            } else {
                Toast.makeText(this@DashboardActivity, "Prøv igen!", Toast.LENGTH_LONG).show()
            }
        })
    }
}