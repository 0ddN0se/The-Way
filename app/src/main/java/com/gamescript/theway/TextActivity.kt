package com.gamescript.theway

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gamescript.theway.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textActivity.setOnClickListener { onClick() }
        binding.textFrame.setOnClickListener { onClick() }
    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun onClick() {
        Settings.TextClick++
        when {
            listOf(3,5,12,14,19,21,23,25,27,29,32,36,38,41,45,47,51,54,60,62,75,77,79,88,93,95,97)
                .contains(Settings.TextClick) -> {
                dialogClick()
            }
            listOf(7,43,66)
                .contains(Settings.TextClick) -> {
                choiceClick()
            }
            listOf(9,105,108,111)
                .contains(Settings.TextClick) -> {
                endClick()
            }
            listOf(100)
                .contains(Settings.TextClick) -> {
                Settings.ChoiceCount = 53;
                choiceClick()
            }
            listOf(102)
                .contains(Settings.TextClick) -> {
                Settings.ChoiceCount = 60;
                choiceClick()
            }

            else -> {
                var idRoom = "t${Settings.TextClick}"
                var resourceViewID = resources.getIdentifier(idRoom, "string", packageName)
                binding.textFrame.setText(resourceViewID)
            }
        }

        if ( listOf(34,48,55).contains(Settings.TextClick)) {
            Settings.TextClick = 55
        }
        if ( listOf(59).contains(Settings.TextClick)) {
            Settings.DialogClicks = 75
        }
        if ( listOf(65).contains(Settings.TextClick)) {
            Settings.ChoiceCount = 4;
        }

    }

    private fun dialogClick() {
        val nextIntent = Intent(this, Dialog::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun choiceClick() {
        val nextIntent = Intent(this, Choice::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    private fun endClick() {
        val nextIntent = Intent(this, End::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}