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

    private var functionAbilities: List<FunctionAbility> = listOf()

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

    fun updateList(newList: List<FunctionAbility>) {
        functionAbilities = newList
        notifyDataSetChanged()
    }

}