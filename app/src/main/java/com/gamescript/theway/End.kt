package com.gamescript.theway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.gamescript.theway.databinding.ActivityEndBinding
import com.gamescript.theway.databinding.ActivityMainBinding

class End : AppCompatActivity() {
    private var mFadeInAnimation: Animation? = null
    private lateinit var binding: ActivityEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.headView2.startAnimation(mFadeInAnimation)
        binding.backButton.startAnimation(mFadeInAnimation)

        binding.backButton.setOnClickListener {
            Settings.TextClick=0;
            Settings.DialogClicks=0;
            Settings.ChoiceCount=0;
            mainClick() }

    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }
    private fun mainClick() {
        val nextIntent = Intent(this, MainActivity::class.java)
        val CHOOSE_THIEF = 0
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

}