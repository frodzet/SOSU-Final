package exam.projects.sosu_final.adapters.healthcondition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.SingleHealthConditionItemBinding
import exam.projects.sosu_final.repositories.entities.HealthConditionItem

class SingleHealthConditionAdapter: RecyclerView.Adapter<SingleHealthConditionAdapter.SingleHealthConditionViewHolder>() {
    inner class SingleHealthConditionViewHolder(val binding: SingleHealthConditionItemBinding): RecyclerView.ViewHolder(binding.root)

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
            buttonHealthConditionItemSubTitle.text = healthConditionItem.subTitle
            buttonHealthConditionItemSubTitle.setOnClickListener {
                if(editTextComment.visibility == View.GONE) {
                    editTextComment.visibility = View.VISIBLE
                    editTextReason.visibility = View.VISIBLE
                    radioGroup.visibility = View.VISIBLE
                    buttonSave.visibility = View.VISIBLE
                } else {
                    editTextComment.visibility = View.GONE
                    editTextReason.visibility = View.GONE
                    radioGroup.visibility = View.GONE
                    buttonSave.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return this.healthConditionItems.size
    }

    val diffCallback = object : DiffUtil.ItemCallback<HealthConditionItem>() {
        override fun areItemsTheSame(oldItem: HealthConditionItem, newItem: HealthConditionItem): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: HealthConditionItem, newItem: HealthConditionItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var healthConditionItems: List<HealthConditionItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}