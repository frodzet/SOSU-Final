package exam.projects.sosu_final.activities.generalinformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.R
import exam.projects.sosu_final.adapters.GeneralInformationAdapter
import exam.projects.sosu_final.databinding.ActivityGeneralInformationBinding
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.entities.GeneralInformation
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class GeneralInformationActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityGeneralInformationBinding
    private lateinit var subjectAboutItemBinding: SubjectAboutItemBinding
    private lateinit var subjectId: String
    private lateinit var lastClickedGeneralInformation: GeneralInformation

    private val generalInformationAdapter by lazy {
        GeneralInformationAdapter(listener = {
                lastClickedGeneralInformation = it
                updateGeneralInformationItem(lastClickedGeneralInformation)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityGeneralInformationBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!

        setupRecyclerView()
        createNavigationBar()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel = ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)


        subjectViewModel.getAllGeneralInformation(subjectId)
        subjectViewModel.getAllGeneralInformationResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                generalInformationAdapter.allGeneralInformation = response.body()!!
            } else {
                Toast.makeText(this, "No response!", Toast.LENGTH_LONG).show()
            }
        })

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

//        activityBinding.apply {
//            subjectViewModel.getOne(subjectId)
//            subjectViewModel.getOneSubjectResponse.observe(this@GeneralInformationActivity, Observer { response ->
//                if(response.isSuccessful) {
//                    textViewName.text = "${response.body()!!.firstName} ${response.body()!!.lastName}"
//                } else {
//                    textViewName.text = response.code().toString()
//                }
//            })
//        }
    }

    private fun setupRecyclerView() {
        this.activityBinding.apply {
            recyclerViewGeneralInformationItems.adapter = generalInformationAdapter
            recyclerViewGeneralInformationItems.layoutManager = LinearLayoutManager(this@GeneralInformationActivity)
        }
    }

    private fun updateGeneralInformationItem(generalInformation: GeneralInformation) {
        subjectViewModel.updateGeneralInformation(
            subjectId,
            generalInformation.id,
            GeneralInformationDto(generalInformation.comment)
        )
        Toast.makeText(this, "${generalInformation.title} er blevet opdateret!", Toast.LENGTH_SHORT).show()
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