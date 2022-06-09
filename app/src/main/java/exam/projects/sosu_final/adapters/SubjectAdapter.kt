package exam.projects.sosu_final.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.entities.Subject

class SubjectAdapter(val listener: (Subject, Int) -> Unit) :
    RecyclerView.Adapter<SubjectAdapter.SubjectsViewHolder>() {

    private var allSubjects: List<Subject> = listOf()

    inner class SubjectsViewHolder(val binding: SubjectAboutItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectAdapter.SubjectsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SubjectAboutItemBinding.inflate(layoutInflater, parent, false)

        return SubjectsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectAdapter.SubjectsViewHolder, position: Int) {
        val subject = allSubjects[position]
        holder.itemView.setOnClickListener {
            listener(subject, position)
        }

        holder.binding.apply {
            textViewName.text = "${subject.firstName} ${subject.lastName}"
            textViewPhone.text = "${subject.phone}"
            textViewEmail.text = "${subject.email}"
            textViewAddress.text =
                "${subject.address.street}, ${subject.address.postCode}, ${subject.address.city}"
        }
    }

    override fun getItemCount(): Int {
        return this.allSubjects.size
    }

    fun updateList(newList: List<Subject>) {
        allSubjects = newList
        notifyDataSetChanged()
    }

}