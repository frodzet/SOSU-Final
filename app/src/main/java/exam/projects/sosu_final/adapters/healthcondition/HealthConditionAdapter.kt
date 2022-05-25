package exam.projects.sosu_final.adapters.healthcondition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.GeneralInformationItemBinding
import exam.projects.sosu_final.databinding.HealthConditionItemBinding
import exam.projects.sosu_final.repositories.entities.GeneralInformation
import exam.projects.sosu_final.repositories.entities.HealthCondition

class HealthConditionAdapter(val listener: (HealthCondition) -> Unit): RecyclerView.Adapter<HealthConditionAdapter.HealthConditionViewHolder>(){
    inner class HealthConditionViewHolder(val binding: HealthConditionItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HealthConditionAdapter.HealthConditionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HealthConditionItemBinding.inflate(layoutInflater, parent, false)

        return HealthConditionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HealthConditionAdapter.HealthConditionViewHolder,
        position: Int
    ) {
        val healthCondition = healthConditions[position]

        holder.binding.apply {
            buttonTitle.text = healthCondition.title
            buttonTitle.setOnClickListener {
                Toast.makeText(holder.binding.root.context, "${healthCondition.id}", Toast.LENGTH_SHORT).show()
                listener(healthCondition)
            }
        }
    }

    override fun getItemCount(): Int {
        return healthConditions.size
    }

    val diffCallback = object : DiffUtil.ItemCallback<HealthCondition>() {
        override fun areItemsTheSame(oldItem: HealthCondition, newItem: HealthCondition): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: HealthCondition, newItem: HealthCondition): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var healthConditions: List<HealthCondition>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}