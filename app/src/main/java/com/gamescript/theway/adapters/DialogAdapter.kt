package com.gamescript.theway.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamescript.theway.models.DialogItem
import com.gamescript.theway.R
import com.gamescript.theway.databinding.ItemDialogBinding

class DialogAdapter(
    private val dialog: ArrayList<DialogItem>,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog, parent, false)
        return DialogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.bind(dialog[position])

        holder.itemView.setOnClickListener { onClick() }
    }

    override fun getItemCount(): Int {
        return dialog.size
    }

    inner class DialogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDialogBinding.bind(view)

        fun bind(dialogItem: DialogItem) {
            binding.dialogImage.setImageResource(dialogItem.getImageId())
            binding.dialogText.text = dialogItem.getText()
        }
    }
}