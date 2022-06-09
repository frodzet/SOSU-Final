package exam.projects.sosu_final.adapters.healthcondition

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.HealthConditionItemBinding
import exam.projects.sosu_final.repositories.entities.HealthCondition

class HealthConditionAdapter(val listener: (HealthCondition) -> Unit) :
    RecyclerView.Adapter<HealthConditionAdapter.HealthConditionViewHolder>() {

    private var healthConditions: List<HealthCondition> = listOf()

    inner class HealthConditionViewHolder(val binding: HealthConditionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
                listener(healthCondition)
            }
        }
    }

    override fun getItemCount(): Int {
        return healthConditions.size
    }

    fun updateList(newList: List<HealthCondition>) {
        healthConditions = newList
        notifyDataSetChanged()
    }
}