package com.gamescript.theway.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamescript.theway.R
import com.gamescript.theway.databinding.ItemChoiceBinding
import com.gamescript.theway.models.ChoiceItem

class ChoiceAdapter(
    private val choiceList: ArrayList<ChoiceItem>
) : RecyclerView.Adapter<ChoiceAdapter.ChoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_choice, parent, false)
        return ChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
        holder.bind(choiceList[position])
    }

    override fun getItemCount(): Int {
        return choiceList.size
    }

    inner class ChoiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemChoiceBinding.bind(view)

        fun bind(choiceItem: ChoiceItem) {
            binding.root.setOnClickListener { choiceItem.action() }
            binding.choiceText.text = choiceItem.text
        }
    }


}