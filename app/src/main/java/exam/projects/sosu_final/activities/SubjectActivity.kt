package exam.projects.sosu_final.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import exam.projects.sosu_final.R
import exam.projects.sosu_final.activities.functionability.FunctionAbilityActivity
import exam.projects.sosu_final.activities.generalinformation.GeneralInformationActivity
import exam.projects.sosu_final.activities.healthcondition.HealthConditionActivity
import exam.projects.sosu_final.databinding.ActivitySubjectBinding
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class SubjectActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivitySubjectBinding
    private lateinit var subjectAboutItemBinding: SubjectAboutItemBinding
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var subjectId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivitySubjectBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!

        createNavigationBar()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel =
            ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        val subjectAboutView: View = layoutInflater.inflate(R.layout.subject_about_item, null)
        activityBinding.linearLayoutSingleSubject.addView(subjectAboutView)
        subjectAboutItemBinding = SubjectAboutItemBinding.bind(subjectAboutView)

        subjectViewModel.getOne(subjectId)
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

        this.activityBinding.buttonGeneralInformation.setOnClickListener {
            val intent: Intent = Intent(this@SubjectActivity, GeneralInformationActivity::class.java)

            intent.putExtra("subjectId", subjectId)

            startActivity(intent)
        }

        this.activityBinding.buttonHealthCondition.setOnClickListener {
            val intent: Intent = Intent(this@SubjectActivity, HealthConditionActivity::class.java)

            intent.putExtra("subjectId", subjectId)

            startActivity(intent)
        }

        this.activityBinding.buttonFunctionAbility.setOnClickListener {
            val intent: Intent = Intent(this@SubjectActivity, FunctionAbilityActivity::class.java)

            intent.putExtra("subjectId", subjectId)

            startActivity(intent)
        }
    }

    private fun createNavigationBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}