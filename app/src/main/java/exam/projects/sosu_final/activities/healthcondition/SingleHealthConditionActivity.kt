package exam.projects.sosu_final.activities.healthcondition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.adapters.healthcondition.HealthConditionAdapter
import exam.projects.sosu_final.adapters.healthcondition.SingleHealthConditionAdapter
import exam.projects.sosu_final.databinding.ActivityHealthConditionBinding
import exam.projects.sosu_final.databinding.ActivitySingleHealthConditionBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.entities.HealthCondition
import exam.projects.sosu_final.repositories.entities.HealthConditionItem
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class SingleHealthConditionActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivitySingleHealthConditionBinding
    private lateinit var subjectId: String
    private lateinit var healthConditionId: String
    private lateinit var healthConditionTitle: String
    private lateinit var lastClickedHealthConditionItem: HealthConditionItem

    private val singleHealthConditionAdapter by lazy {
        SingleHealthConditionAdapter(listener = {
            lastClickedHealthConditionItem = it
            Toast.makeText(this@SingleHealthConditionActivity, lastClickedHealthConditionItem.id, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivitySingleHealthConditionBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!
        healthConditionId = intent.getStringExtra("healthConditionId")!!
        healthConditionTitle = intent.getStringExtra("healthConditionTitle")!!

        setupRecyclerView()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel = ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        subjectViewModel.getOneHealthCondition(subjectId, healthConditionId)
        subjectViewModel.getOneHealthConditionResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                singleHealthConditionAdapter.healthConditionItems = response.body()?.healthConditionItems!!
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })

        activityBinding.apply {
            textViewHealthConditionTitle.text = healthConditionTitle
        }

        Toast.makeText(this@SingleHealthConditionActivity, "${subjectId} ${healthConditionId}", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewSingleHealthCondition.adapter = singleHealthConditionAdapter
            recyclerViewSingleHealthCondition.layoutManager = LinearLayoutManager(this@SingleHealthConditionActivity)
        }
    }
}