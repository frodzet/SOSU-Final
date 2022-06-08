package exam.projects.sosu_final.adapters.functionability

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.FunctionAbilityItemBinding
import exam.projects.sosu_final.repositories.entities.FunctionAbility

class FunctionAbilityAdapter(val listener: (FunctionAbility) -> Unit) :
    RecyclerView.Adapter<FunctionAbilityAdapter.FunctionAbilityViewHolder>() {
    inner class FunctionAbilityViewHolder(val binding: FunctionAbilityItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FunctionAbilityAdapter.FunctionAbilityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FunctionAbilityItemBinding.inflate(layoutInflater, parent, false)

        return FunctionAbilityViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FunctionAbilityAdapter.FunctionAbilityViewHolder,
        position: Int
    ) {
        val functionAbility = functionAbilities[position]

        holder.binding.apply {
            this.buttonTitle.text = functionAbility.title
            buttonTitle.setOnClickListener {
                listener(functionAbility)
            }
        }
    }

    override fun getItemCount(): Int {
        return functionAbilities.size
    }

    val diffCallback = object : DiffUtil.ItemCallback<FunctionAbility>() {
        override fun areItemsTheSame(oldItem: FunctionAbility, newItem: FunctionAbility): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FunctionAbility,
            newItem: FunctionAbility
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var functionAbilities: List<FunctionAbility>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

}