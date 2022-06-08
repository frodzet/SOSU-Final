package exam.projects.sosu_final.activities.healthcondition

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.healthcondition.SingleHealthConditionAdapter
import exam.projects.sosu_final.databinding.ActivitySingleHealthConditionBinding
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.HealthConditionItemDto
import exam.projects.sosu_final.repositories.entities.HealthConditionItem
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class SingleHealthConditionActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivitySingleHealthConditionBinding
    private lateinit var subjectAboutItemBinding: SubjectAboutItemBinding
    private lateinit var subjectId: String
    private lateinit var healthConditionId: String
    private lateinit var healthConditionTitle: String
    private lateinit var lastClickedHealthConditionItem: HealthConditionItem

    private val singleHealthConditionAdapter by lazy {
        SingleHealthConditionAdapter(listener = { healthConditionItem: HealthConditionItem ->
            updateHealthConditionItem(healthConditionItem)
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivitySingleHealthConditionBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        createNavigationBar()
        subjectId = intent.getStringExtra("subjectId")!!
        healthConditionId = intent.getStringExtra("healthConditionId")!!
        healthConditionTitle = intent.getStringExtra("healthConditionTitle")!!

        setupViewModel()

        getSubject(subjectId)
        setupSubjectView()

        getHealthCondition()
        setupRecyclerView()

        activityBinding.apply {
            textViewHealthConditionTitle.text = healthConditionTitle
        }
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

    private fun getHealthCondition() {
        subjectViewModel.getOneHealthCondition(subjectId, healthConditionId)
        subjectViewModel.getOneHealthConditionResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                singleHealthConditionAdapter.healthConditionItems =
                    response.body()?.healthConditionItems!!
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewSingleHealthCondition.adapter = singleHealthConditionAdapter
            recyclerViewSingleHealthCondition.layoutManager =
                LinearLayoutManager(this@SingleHealthConditionActivity)
        }
    }

    private fun updateHealthConditionItem(healthConditionItem: HealthConditionItem) {
        subjectViewModel.updateHealthConditionItem(
            subjectId,
            healthConditionId,
            healthConditionItem.id,
            HealthConditionItemDto(
                healthConditionItem.comment,
                healthConditionItem.reason,
                healthConditionItem.relevant
            )
        )

        Toast.makeText(
            this@SingleHealthConditionActivity,
            "${healthConditionItem.subTitle} er blevet opdateret!",
            Toast.LENGTH_SHORT
        ).show()
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