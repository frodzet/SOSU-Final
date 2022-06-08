package exam.projects.sosu_final.activities.functionability

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.functionability.SingleFunctionAbilityAdapter
import exam.projects.sosu_final.databinding.ActivitySingleFunctionAbilityBinding
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.FunctionAbilityItemDto
import exam.projects.sosu_final.repositories.entities.FunctionAbilityItem
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class SingleFunctionAbilityActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivitySingleFunctionAbilityBinding
    private lateinit var subjectAboutItemBinding: SubjectAboutItemBinding
    private lateinit var subjectId: String
    private lateinit var functionAbilityId: String
    private lateinit var functionAbilityTitle: String
    private lateinit var lastClickedFunctionAbility: FunctionAbilityItem

    private val singleFunctionAbilityAdapter by lazy {
        SingleFunctionAbilityAdapter(listener = {
            lastClickedFunctionAbility = it
            updateFunctionAbilityItem(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivitySingleFunctionAbilityBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!
        functionAbilityId = intent.getStringExtra("functionAbilityId")!!
        functionAbilityTitle = intent.getStringExtra("functionAbilityTitle")!!

        setupRecyclerView()
        createNavigationBar()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel = ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        val subjectAboutView: View = layoutInflater.inflate(R.layout.subject_about_item, null)
        activityBinding.linearLayoutSingleSubject.addView(subjectAboutView)
        subjectAboutItemBinding = SubjectAboutItemBinding.bind(subjectAboutView)

        subjectViewModel.getOneSubject(subjectId)
        subjectViewModel.getOneSubjectResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val subject = response.body()!!
                subjectAboutItemBinding.apply {
                    textViewName.text = "${subject.firstName} ${subject.lastName}"
                    textViewPhone.text = "${subject.phone}"
                    textViewEmail.text = "${subject.email}"
                    textViewAddress.text = "${subject.address.street}, ${subject.address.postCode}, ${subject.address.street}"
                }
            } else {
                Toast.makeText(this, "Subject not found!", Toast.LENGTH_LONG).show()
            }
        })

        subjectViewModel.getOneFunctionAbility(subjectId, functionAbilityId)
        subjectViewModel.getOneFunctionAbilityResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                singleFunctionAbilityAdapter.functionAbilityItems = response.body()!!.functionAbilityItems
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })

        activityBinding.apply {
            textViewTitle.text = functionAbilityTitle
        }
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewSingleFunctionAbility.adapter = singleFunctionAbilityAdapter
            recyclerViewSingleFunctionAbility.layoutManager = LinearLayoutManager(this@SingleFunctionAbilityActivity)
        }
    }

    private fun updateFunctionAbilityItem(functionAbilityItem: FunctionAbilityItem) {
        subjectViewModel.updateFunctionAbilityItem(
            subjectId,
            functionAbilityId,
            functionAbilityItem.id,
            FunctionAbilityItemDto(
                functionAbilityItem.currentLevel,
                functionAbilityItem.expectedLevel,
                functionAbilityItem.execution,
                functionAbilityItem.meaningOfExecution,
                functionAbilityItem.subjectWish)
        )

        Toast.makeText(this@SingleFunctionAbilityActivity, "${functionAbilityItem.subTitle} er blevet opdateret!", Toast.LENGTH_SHORT).show()
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