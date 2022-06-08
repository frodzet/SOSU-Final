package exam.projects.sosu_final.activities.functionability

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.functionability.FunctionAbilityAdapter
import exam.projects.sosu_final.databinding.ActivityFunctionAbilityBinding
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.entities.FunctionAbility
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class FunctionAbilityActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityFunctionAbilityBinding
    private lateinit var subjectAboutItemBinding: SubjectAboutItemBinding
    private lateinit var subjectId: String

    private val functionAbilityAdapter by lazy {
        FunctionAbilityAdapter(listener = {
//            lastClickedHealthCondition = it
            startActivitySingleAbility(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityFunctionAbilityBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!

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

        subjectViewModel.getAllFunctionAbilities(subjectId)
        subjectViewModel.getAllFunctionAbilitiesResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                functionAbilityAdapter.functionAbilities = response.body()!!
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewFunctionAbility.adapter = functionAbilityAdapter
            recyclerViewFunctionAbility.layoutManager = LinearLayoutManager(this@FunctionAbilityActivity)
        }
    }

    private fun startActivitySingleAbility(functionAbility: FunctionAbility) {
        val intent: Intent = Intent(this@FunctionAbilityActivity, SingleFunctionAbilityActivity::class.java)

        intent.putExtra("subjectId", subjectId)
        intent.putExtra("functionAbilityId", functionAbility.id)
        intent.putExtra("functionAbilityTitle", functionAbility.title)

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