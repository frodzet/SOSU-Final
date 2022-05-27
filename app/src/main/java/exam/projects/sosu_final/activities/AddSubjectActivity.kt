package exam.projects.sosu_final.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.lifecycle.ViewModelProvider
import exam.projects.sosu_final.databinding.ActivityAddSubjectBinding
import exam.projects.sosu_final.repositories.SubjectRepository
import exam.projects.sosu_final.viewmodels.SubjectViewModel
import exam.projects.sosu_final.viewmodels.SubjectViewModelFactory

class AddSubjectActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityAddSubjectBinding
    private lateinit var subjectViewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityAddSubjectBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        createNavigationBar()

        val subjectRepository = SubjectRepository()
        val viewModelFactory = SubjectViewModelFactory(subjectRepository)
        subjectViewModel =
            ViewModelProvider(this, viewModelFactory).get(SubjectViewModel::class.java)

        activityBinding.apply {
            this.buttonSave.setOnClickListener {
                for (view in activityBinding.root.allViews) {
                    if (view is EditText && view.text.isNullOrEmpty()) {
                        if (view.text.isNullOrEmpty()) {
                            Toast.makeText(
                                this@AddSubjectActivity,
                                "${view.hint} mangler at blive udfyldt!",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@setOnClickListener
                        }
                    }
                }

                val intent = Intent()
                intent.putExtra("firstName", editTextFirstName.text.toString())
                intent.putExtra("lastName", editTextLastName.text.toString())
                intent.putExtra("email", editTextEmail.text.toString())
                intent.putExtra("phone", editTextPhone.text.toString())
                intent.putExtra("city", editTextCity.text.toString())
                intent.putExtra("street", editTextStreet.text.toString())
                intent.putExtra("postCode", editTextPostCode.text.toString().toIntOrNull() ?: 0)

                setResult(RESULT_OK, intent)
                finish()
            }
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