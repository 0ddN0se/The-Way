package com.gamescript.theway

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.gamescript.theway.adapters.ChoiceAdapter
import com.gamescript.theway.adapters.DialogAdapter
import com.gamescript.theway.databinding.ActivityChoiceBinding
import com.gamescript.theway.databinding.ActivityDialogBinding
import com.gamescript.theway.databinding.ActivityTextBinding
import com.gamescript.theway.models.ChoiceItem
import com.gamescript.theway.models.DialogItem

class Choice : AppCompatActivity() {

    private lateinit var binding: ActivityChoiceBinding
    private lateinit var choiceList: ArrayList<ChoiceItem>
    private lateinit var choiceAdapter: ChoiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun drawRecyclerView() {
        binding.choiceRecyclerView.layoutManager = GridLayoutManager(this, 1)
        choiceList = arrayListOf()
        choiceAdapter = ChoiceAdapter(choiceList)
        binding.choiceRecyclerView.adapter = choiceAdapter

        Settings.ChoiceCount++

        parseChoiceString()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun parseChoiceString() {
        val idChoice = resources.getIdentifier("c${Settings.ChoiceCount}", "string", packageName)
        val textList = resources.getString(idChoice).split("|")

        textList.forEach {
            val (string, params) = it.split('[', ']')
            choiceList.add(ChoiceItem(string, getUpdateSettingActivity(params)))
        }

        choiceAdapter.notifyDataSetChanged()
    }

    private fun getUpdateSettingActivity(params: String): () -> Unit {
        val settings = params.split(",")

        var nextActivity: () -> Unit = {}

        var textClick = Settings.TextClick
        var dialogClicks = Settings.DialogClicks
        var choiceCount = Settings.ChoiceCount

        var countEmpathy = 0
        var countObservation = 0
        var countRationality = 0

        settings.forEach {
            when {
                it[0] == 'c' -> {
                    choiceCount = it.split(':')[1].toInt()
                }
                it[0] == 't' -> {
                    nextActivity = this::textClick
                    textClick = it.split(':')[1].toInt()
                }
                it[0] == 'd' -> {
                    nextActivity = this::dialogClick
                    dialogClicks = it.split(':')[1].toInt()
                }
                it[0] == 'E' -> {
                    countEmpathy = it.split(':')[1].toInt()
                }
                it[0] == 'O' -> {
                    countObservation = it.split(':')[1].toInt()
                }
                it[0] == 'R' -> {
                    countRationality = it.split(':')[1].toInt()
                }
            }
        }

        return {
            Settings.TextClick = textClick
            Settings.DialogClicks = dialogClicks
            Settings.ChoiceCount = choiceCount

            Settings.Empathy += countEmpathy
            Settings.Observation += countObservation
            Settings.Rationality += countRationality

            nextActivity()
        }
    }

    private fun dialogClick() {
        val nextIntent = Intent(this, Dialog::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun textClick() {
        val nextIntent = Intent(this, TextActivity::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}