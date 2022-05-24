package exam.projects.sosu_final.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import exam.projects.sosu_final.adapters.GeneralInformationAdapter
import exam.projects.sosu_final.databinding.ActivityGeneralInformationBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.repositories.dtos.GeneralInformationDto
import exam.projects.sosu_final.repositories.entities.GeneralInformation
import exam.projects.sosu_final.repositories.entities.Subject
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory
import retrofit2.Response
import java.io.IOException

class GeneralInformationActivity : AppCompatActivity() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityBinding: ActivityGeneralInformationBinding
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
}