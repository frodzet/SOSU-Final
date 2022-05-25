package exam.projects.sosu_final.activities.healthcondition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.adapters.GeneralInformationAdapter
import exam.projects.sosu_final.adapters.healthcondition.HealthConditionAdapter
import exam.projects.sosu_final.databinding.ActivityGeneralInformationBinding
import exam.projects.sosu_final.databinding.ActivityHealthConditionBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.entities.GeneralInformation
import exam.projects.sosu_final.repositories.entities.HealthCondition
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class HealthConditionActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityHealthConditionBinding
    private lateinit var lastClickedHealthCondition: HealthCondition
    private lateinit var subjectId: String

    private val healthConditionAdapter by lazy {
        HealthConditionAdapter(listener = {
            lastClickedHealthCondition = it
            startActivtySingleHealthCondition(lastClickedHealthCondition)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityHealthConditionBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!

        setupRecyclerView()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel = ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        subjectViewModel.getAllHealthConditions(subjectId)
        subjectViewModel.getAllHealthConditionsResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                healthConditionAdapter.healthConditions = response.body()!!
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewHealthCondition.adapter = healthConditionAdapter
            recyclerViewHealthCondition.layoutManager = LinearLayoutManager(this@HealthConditionActivity)
        }
    }

    private fun startActivtySingleHealthCondition(healthCondition: HealthCondition) {
        val intent: Intent = Intent(this@HealthConditionActivity, SingleHealthConditionActivity::class.java)

        intent.putExtra("subjectId", subjectId)
        intent.putExtra("healthConditionId", lastClickedHealthCondition.id)
        intent.putExtra("healthConditionTitle", lastClickedHealthCondition.title)

        startActivity(intent)
    }
}