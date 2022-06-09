package exam.projects.sosu_final.adapters.generalinformation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import exam.projects.sosu_final.databinding.GeneralInformationItemBinding
import exam.projects.sosu_final.repositories.entities.GeneralInformation

class GeneralInformationAdapter(val listener: (GeneralInformation) -> Unit) :
    RecyclerView.Adapter<GeneralInformationAdapter.GeneralInformationViewHolder>() {

    private var allGeneralInformation: List<GeneralInformation> = listOf()

    inner class GeneralInformationViewHolder(val binding: GeneralInformationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneralInformationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GeneralInformationItemBinding.inflate(layoutInflater, parent, false)

        return GeneralInformationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneralInformationViewHolder, position: Int) {
        val generalInformation = allGeneralInformation[position]

        holder.binding.apply {
            buttonTitle.text = generalInformation.title
            buttonTitle.tooltipText = generalInformation.description


            buttonTitle.setOnClickListener {
                if (editTextComment.visibility == View.GONE) {
                    editTextComment.visibility = View.VISIBLE
                    buttonSave.visibility = View.VISIBLE
                    if (generalInformation.comment == null)
                        generalInformation.comment = ""
                    if (!editTextComment.text.toString().contains(generalInformation.comment)) {
                        editTextComment.text.append(generalInformation.comment)
                    }
                } else {
                    editTextComment.visibility = View.GONE
                    buttonSave.visibility = View.GONE
                }
            }
            buttonSave.setOnClickListener {
                generalInformation.comment = editTextComment.text.toString()
                listener(generalInformation)
                editTextComment.visibility = View.GONE
                buttonSave.visibility = View.GONE
            }
        }

    }

    override fun getItemCount(): Int {
        return this.allGeneralInformation.size
    }

    fun updateList(newList: List<GeneralInformation>) {
        allGeneralInformation = newList
        notifyDataSetChanged()
    }
}