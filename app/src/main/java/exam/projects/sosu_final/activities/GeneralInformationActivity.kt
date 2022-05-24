package exam.projects.sosu_final.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import exam.projects.sosu_final.databinding.ActivityGeneralInformationBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.AddressDto
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class GeneralInformationActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityGeneralInformationBinding
    private lateinit var subjectId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityGeneralInformationBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        subjectId = intent.getStringExtra("subjectId")!!

        activityBinding.textViewTest.text = subjectId;

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel = ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        activityBinding.textViewTest.setOnClickListener {
            updateGeneralInformationItem()
        }
    }

    fun updateGeneralInformationItem() {
        val generalInformationDto = GeneralInformationDto("Mikkel")
        subjectViewModel.updateGeneralInformation(subjectId, "628ccdbb210f2e1918b01b1e", generalInformationDto)
        subjectViewModel.updateGeneralInformationResponse.observe(this@GeneralInformationActivity, Observer { response ->
            if(response.isSuccessful) {
                Toast.makeText(this@GeneralInformationActivity, "General information: '628ccdbb210f2e1918b01b1e' opdateret", Toast.LENGTH_LONG).show()
//                getAllSubjects()
            } else {
                Toast.makeText(this@GeneralInformationActivity, "Prøv igen!", Toast.LENGTH_LONG).show()
            }
        })
    }
}