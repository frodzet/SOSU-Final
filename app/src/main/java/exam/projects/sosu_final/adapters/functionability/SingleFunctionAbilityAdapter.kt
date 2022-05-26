package exam.projects.sosu_final.adapters.functionability

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.R
import exam.projects.sosu_final.databinding.SingleFunctionAbilityItemBinding
import exam.projects.sosu_final.repositories.entities.FunctionAbilityItem

class SingleFunctionAbilityAdapter(val listener: (FunctionAbilityItem) -> Unit): RecyclerView.Adapter<SingleFunctionAbilityAdapter.SingleFunctionAbilityViewHolder>() {
    inner class SingleFunctionAbilityViewHolder(val binding: SingleFunctionAbilityItemBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleFunctionAbilityAdapter.SingleFunctionAbilityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SingleFunctionAbilityItemBinding.inflate(layoutInflater, parent, false)

        return SingleFunctionAbilityViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SingleFunctionAbilityAdapter.SingleFunctionAbilityViewHolder,
        position: Int
    ) {
        val functionAbilityItem = functionAbilityItems[position]

        holder.binding.apply {
            buttonSubTitle.text = functionAbilityItem.subTitle
            //Toast.makeText(holder.binding.root.context, functionAbilityItem.currentLevel.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(this.root.context, spinnerCurrentLevel.selectedItem.toString(), Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        return this.functionAbilityItems.size
    }

    val diffCallback = object : DiffUtil.ItemCallback<FunctionAbilityItem>() {
        override fun areItemsTheSame(oldItem: FunctionAbilityItem, newItem: FunctionAbilityItem): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: FunctionAbilityItem, newItem: FunctionAbilityItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var functionAbilityItems: List<FunctionAbilityItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}