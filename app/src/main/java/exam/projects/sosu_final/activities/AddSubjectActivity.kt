package exam.projects.sosu_final.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import exam.projects.sosu_final.databinding.ActivityAddSubjectBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.AddressDto
import exam.projects.sosu_final.repositories.dtos.SubjectDto
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class AddSubjectActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityAddSubjectBinding
    private lateinit var subjectViewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel =
            ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        activityBinding.apply {
            buttonSave.setOnClickListener {
                addSubject(
                    editTextFirstName.text.toString(),
                    editTextLastName.text.toString(),
                    editTextEmail.text.toString(),
                    editTextPhone.text.toString(),
                    editTextCity.text.toString(),
                    editTextStreet.text.toString(),
                    editTextPostCode.text.toString().toInt()
                )
                setResult(RESULT_OK)
                finish()
            }
        }

    }

    private fun addSubject(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        city: String,
        street: String,
        postCode: Int
    ) {
        val subjectDto = SubjectDto(
            firstName,
            lastName,
            email,
            phone,
            AddressDto(city, postCode, street)
        )
        subjectViewModel.addSubject(subjectDto)
        subjectViewModel.addSubjectResponse.observe(this@AddSubjectActivity, Observer { response ->
            if (response.isSuccessful) {
                Toast.makeText(this@AddSubjectActivity, "Borger tilføjet!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@AddSubjectActivity, "Prøv igen!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}