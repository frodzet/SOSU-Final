package exam.projects.sosu_final.adapters.functionability

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.SingleFunctionAbilityItemBinding
import exam.projects.sosu_final.repositories.entities.FunctionAbilityItem

class SingleFunctionAbilityAdapter(val listener: (FunctionAbilityItem) -> Unit) :
    RecyclerView.Adapter<SingleFunctionAbilityAdapter.SingleFunctionAbilityViewHolder>() {
    inner class SingleFunctionAbilityViewHolder(val binding: SingleFunctionAbilityItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            buttonFunctionAbilityItem.text = functionAbilityItem.subTitle
            radioButtonCurrentLevel0.isChecked = functionAbilityItem.currentLevel == 0
            radioButtonCurrentLevel1.isChecked = functionAbilityItem.currentLevel == 1
            radioButtonCurrentLevel2.isChecked = functionAbilityItem.currentLevel == 2
            radioButtonCurrentLevel3.isChecked = functionAbilityItem.currentLevel == 3
            radioButtonCurrentLevel4.isChecked = functionAbilityItem.currentLevel == 4
            radioButtonExpectedLevel0.isChecked = functionAbilityItem.expectedLevel == 0
            radioButtonExpectedLevel1.isChecked = functionAbilityItem.expectedLevel == 1
            radioButtonExpectedLevel2.isChecked = functionAbilityItem.expectedLevel == 2
            radioButtonExpectedLevel3.isChecked = functionAbilityItem.expectedLevel == 3
            radioButtonExpectedLevel4.isChecked = functionAbilityItem.expectedLevel == 4
            radioButtonExecution0.isChecked = functionAbilityItem.execution == 0
            radioButtonExecution1.isChecked = functionAbilityItem.execution == 1
            radioButtonExecution2.isChecked = functionAbilityItem.execution == 2
            radioButtonExecution3.isChecked = functionAbilityItem.execution == 3
            radioButtonMeaningOfExecution0.isChecked = functionAbilityItem.meaningOfExecution == 0
            radioButtonMeaningOfExecution1.isChecked = functionAbilityItem.meaningOfExecution == 1

            if(!editTextSubjectWish.text.toString().contains(functionAbilityItem.subjectWish))
                editTextSubjectWish.append(functionAbilityItem.subjectWish)

            this.buttonFunctionAbilityItem.setOnClickListener {
                if (textViewCurrentLevel.visibility == View.GONE) {
                    showItems()
                } else {
                    hideItems()
                }
            }

            this.buttonSave.setOnClickListener {
                val radioButtonCurrentLevelId = radioGroupCurrentLevel.checkedRadioButtonId
                val radioButtonCurrentLevel = radioGroupCurrentLevel.findViewById<RadioButton>(radioButtonCurrentLevelId)
                val radioButtonCurrentLevelIndex: Int = radioGroupCurrentLevel.indexOfChild(radioButtonCurrentLevel)
                functionAbilityItem.currentLevel = radioButtonCurrentLevelIndex;

                val radioButtonExpectedLevelId = radioGroupExpectedLevel.checkedRadioButtonId
                val radioButtonExpectedLevel = radioGroupExpectedLevel.findViewById<RadioButton>(radioButtonExpectedLevelId)
                val radioButtonExpectedLevelIndex = radioGroupExpectedLevel.indexOfChild(radioButtonExpectedLevel)
                functionAbilityItem.expectedLevel = radioButtonExpectedLevelIndex

                val radioButtonExecutionId = radioGroupExecution.checkedRadioButtonId
                val radioButtonExecution = radioGroupExecution.findViewById<RadioButton>(radioButtonExecutionId)
                val radioButtonExecutionIndex = radioGroupExecution.indexOfChild(radioButtonExecution)
                functionAbilityItem.execution = radioButtonExecutionIndex

                val radioButtonMeaningOfExecutionId = radioGroupMeaningOfExecution.checkedRadioButtonId
                val radioButtonMeaningOfExecution = radioGroupMeaningOfExecution.findViewById<RadioButton>(radioButtonMeaningOfExecutionId)
                val radioButtonMeaningOfExecutionIndex = radioGroupMeaningOfExecution.indexOfChild(radioButtonMeaningOfExecution)
                functionAbilityItem.meaningOfExecution = radioButtonMeaningOfExecutionIndex

                functionAbilityItem.subjectWish = editTextSubjectWish.text.toString()
                hideItems()
                listener(functionAbilityItem)

            }

        }
    }

    private fun SingleFunctionAbilityItemBinding.showItems() {
        textViewCurrentLevel.visibility = View.VISIBLE
        textViewExpectedLevel.visibility = View.VISIBLE

        radioGroupCurrentLevel.visibility = View.VISIBLE
        radioGroupExpectedLevel.visibility = View.VISIBLE

        buttonSubjectEvaluation.visibility = View.VISIBLE

        textViewExecution.visibility = View.VISIBLE
        radioGroupExecution.visibility = View.VISIBLE

        radioGroupMeaningOfExecution.visibility = View.VISIBLE
        textViewMeaningOfExecution.visibility = View.VISIBLE

        editTextSubjectWish.visibility = View.VISIBLE
        buttonSave.visibility = View.VISIBLE
    }

    private fun SingleFunctionAbilityItemBinding.hideItems() {
        textViewCurrentLevel.visibility = View.GONE
        radioGroupCurrentLevel.visibility = View.GONE

        textViewExpectedLevel.visibility = View.GONE
        radioGroupExpectedLevel.visibility = View.GONE

        textViewExecution.visibility = View.GONE
        radioGroupExecution.visibility = View.GONE

        radioGroupMeaningOfExecution.visibility = View.GONE
        textViewMeaningOfExecution.visibility = View.GONE

        editTextSubjectWish.visibility = View.GONE
        buttonSave.visibility = View.GONE

        buttonSubjectEvaluation.visibility = View.GONE
        buttonSave.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return this.functionAbilityItems.size
    }

    val diffCallback = object : DiffUtil.ItemCallback<FunctionAbilityItem>() {
        override fun areItemsTheSame(
            oldItem: FunctionAbilityItem,
            newItem: FunctionAbilityItem
        ): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FunctionAbilityItem,
            newItem: FunctionAbilityItem
        ): Boolean {
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