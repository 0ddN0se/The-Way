package com.gamescript.theway

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.gamescript.theway.adapters.DialogAdapter
import com.gamescript.theway.databinding.ActivityDialogBinding
import com.gamescript.theway.models.DialogItem


class Dialog : AppCompatActivity() {
    private lateinit var binding: ActivityDialogBinding
    private lateinit var dialog: ArrayList<DialogItem>
    private lateinit var dialogAdapter: DialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener { onClick() }
        drawRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun drawRecyclerView() {
        binding.dialogRecyclerView.layoutManager = GridLayoutManager(this, 1)
        dialog = arrayListOf()
        dialogAdapter = DialogAdapter(dialog, ::onClick)
        binding.dialogRecyclerView.adapter = dialogAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun textClick() {
        val nextIntent = Intent(this, TextActivity::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        dialog.clear()
        dialogAdapter.notifyDataSetChanged()
        finish()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun choiceClick() {
        val nextIntent = Intent(this, Choice::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        dialog.clear()
        dialogAdapter.notifyDataSetChanged()
        finish()
    }
    private fun endClick() {
        val nextIntent = Intent(this, End::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onClick() {
        Settings.DialogClicks++

        when {
            listOf(4, 14, 17, 19, 21, 23, 25, 27, 33, 43, 45, 48, 50, 55,58,61,64,66,68,73,75,78,84,320,332,593,609,619,633,639,646,665,672,685,693,724,736,739,744,782,785).contains(Settings.DialogClicks) -> {
                textClick()
            }
            listOf(9, 37, 87,89,91,93,95,97,99,101,103,105,107,110,113,119,125,129,131,133,135,137,139,141,145,147,149,153,157,161,165,167,169,173,177,183,187,191,193,197,199,201,205,209,215,221,225,229,233,235,238,244,248,250,254,256,262,266,273,283,287,291,295,299,301,303,306,308,312,315,339,342,346,348,352,356,360,362,366,373,377,383,389,391,401,410,414,419,422,426,432,434,436,440,442,445,447,455,457,459,463,467,469,473,477,479,485,495,511,517,524,536,540,547,553,563,569,573,579,585,653,661,668,703,710,722).contains(Settings.DialogClicks) -> {
                choiceClick()
            }
            listOf(323).contains(Settings.DialogClicks) -> {
                Settings.ChoiceCount = 34;
                choiceClick()
            }

            else -> {
                val dialogItem = DialogItem(
                    resources,
                    packageName,
                    "scene1",
                    Settings.DialogClicks
                )

                dialog.add(dialogItem)
                dialogAdapter.notifyDataSetChanged()
            }


        }




    }
}


