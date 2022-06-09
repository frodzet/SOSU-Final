package exam.projects.sosu_final.activities.healthcondition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.healthcondition.HealthConditionAdapter
import exam.projects.sosu_final.databinding.ActivityHealthConditionBinding
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.entities.HealthCondition
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class HealthConditionActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityHealthConditionBinding
    private lateinit var subjectAboutItemBinding: SubjectAboutItemBinding
    private lateinit var subjectId: String

    private val healthConditionAdapter by lazy {
        HealthConditionAdapter(listener = { healthCondition: HealthCondition ->
            startActivitySingleHealthCondition(healthCondition)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityHealthConditionBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        createNavigationBar()
        subjectId = intent.getStringExtra("subjectId")!!

        setupViewModel()

        getSubject(subjectId)
        setupSubjectView()

        getHealthConditions()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel =
            ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)
    }

    private fun getSubject(subjectId: String) {
        subjectViewModel.getOneSubject(subjectId)
        subjectViewModel.getOneSubjectResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val subject = response.body()!!
                subjectAboutItemBinding.apply {
                    textViewName.text = "${subject.firstName} ${subject.lastName}"
                    textViewPhone.text = "${subject.phone}"
                    textViewEmail.text = "${subject.email}"
                    textViewAddress.text =
                        "${subject.address.street}, ${subject.address.postCode}, ${subject.address.street}"
                }
            } else {
                Toast.makeText(this, "Subject not found!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupSubjectView() {
        val subjectAboutView: View = layoutInflater.inflate(R.layout.subject_about_item, null)
        activityBinding.linearLayoutSingleSubject.addView(subjectAboutView)
        subjectAboutItemBinding = SubjectAboutItemBinding.bind(subjectAboutView)
    }

    private fun getHealthConditions() {
        subjectViewModel.getAllHealthConditions(subjectId)
        subjectViewModel.getAllHealthConditionsResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                healthConditionAdapter.updateList(response.body()!!)
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewHealthCondition.adapter = healthConditionAdapter
            recyclerViewHealthCondition.layoutManager =
                LinearLayoutManager(this@HealthConditionActivity)
        }
    }

    private fun startActivitySingleHealthCondition(healthCondition: HealthCondition) {
        val intent = Intent(this@HealthConditionActivity, SingleHealthConditionActivity::class.java)

        intent.putExtra("subjectId", subjectId)
        intent.putExtra("healthConditionId", healthCondition.id)
        intent.putExtra("healthConditionTitle", healthCondition.title)

        startActivity(intent)
    }

    private fun createNavigationBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }
}