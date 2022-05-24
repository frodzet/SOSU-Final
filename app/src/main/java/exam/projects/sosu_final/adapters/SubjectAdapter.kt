package exam.projects.sosu_final.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.SubjectAboutItemBinding
import exam.projects.sosu_final.repositories.entities.Subject

class SubjectAdapter(val listener: (Subject, Int) -> Unit):  RecyclerView.Adapter<SubjectAdapter.SubjectsViewHolder>(){

    inner class SubjectsViewHolder(val binding: SubjectAboutItemBinding): RecyclerView.ViewHolder(binding.root)

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
            textViewAddress.text = "${subject.address.street}, ${subject.address.postCode}, ${subject.address.city}"
        }

    }

    override fun getItemCount(): Int {
        return this.allSubjects.size
    }

    val diffCallback = object : DiffUtil.ItemCallback<Subject>() {
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    var allSubjects: List<Subject>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    fun updateList(searchedSubjects: List<Subject>) {
        allSubjects = searchedSubjects
        notifyDataSetChanged()
    }

}