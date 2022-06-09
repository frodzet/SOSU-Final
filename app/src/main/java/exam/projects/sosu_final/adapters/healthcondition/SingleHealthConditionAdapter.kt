package exam.projects.sosu_final.adapters.healthcondition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.SingleHealthConditionItemBinding
import exam.projects.sosu_final.repositories.entities.HealthConditionItem

class SingleHealthConditionAdapter(val listener: (HealthConditionItem) -> Unit) :
    RecyclerView.Adapter<SingleHealthConditionAdapter.SingleHealthConditionViewHolder>() {

    private var healthConditionItems: List<HealthConditionItem> = listOf()

    inner class SingleHealthConditionViewHolder(val binding: SingleHealthConditionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleHealthConditionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SingleHealthConditionItemBinding.inflate(layoutInflater, parent, false)

        return SingleHealthConditionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleHealthConditionViewHolder, position: Int) {
        val healthConditionItem = healthConditionItems[position]
        holder.binding.apply {
            buttonHealthConditionItem.text = healthConditionItem.subTitle
            if (!editTextComment.text.toString().contains(healthConditionItem.comment))
                editTextComment.text.append(healthConditionItem.comment)
            if (!editTextReason.text.toString().contains(healthConditionItem.reason))
                editTextReason.text.append(healthConditionItem.reason)

            radioButtonNotRelevant.isChecked = healthConditionItem.relevant == 0
            radioButtonPotential.isChecked = healthConditionItem.relevant == 1
            radioButtonRelevant.isChecked = healthConditionItem.relevant == 2

            buttonHealthConditionItem.setOnClickListener {
                if (editTextComment.visibility == View.GONE) {
                    editTextComment.visibility = View.VISIBLE
                    editTextReason.visibility = View.VISIBLE
                    radioGroupHealthCondition.visibility = View.VISIBLE
                    buttonSave.visibility = View.VISIBLE
                } else {
                    editTextComment.visibility = View.GONE
                    editTextReason.visibility = View.GONE
                    radioGroupHealthCondition.visibility = View.GONE
                    buttonSave.visibility = View.GONE
                }
            }
            buttonSave.setOnClickListener {
                healthConditionItem.comment = editTextComment.text.toString()
                healthConditionItem.reason = editTextReason.text.toString()
                if (radioButtonNotRelevant.isChecked) {
                    healthConditionItem.relevant = 0
                } else if (radioButtonPotential.isChecked) {
                    healthConditionItem.relevant = 1
                } else if (radioButtonRelevant.isChecked) {
                    healthConditionItem.relevant = 2
                }
                editTextComment.visibility = View.GONE
                editTextReason.visibility = View.GONE
                radioGroupHealthCondition.visibility = View.GONE
                buttonSave.visibility = View.GONE
                listener(healthConditionItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.healthConditionItems.size
    }

    fun updateList(newList: List<HealthConditionItem>) {
        healthConditionItems = newList
        notifyDataSetChanged()
    }
}