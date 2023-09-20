package com.gamescript.theway

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView;
import android.widget.TextView
import com.gamescript.theway.databinding.ActivityMainBinding
import com.gamescript.theway.databinding.ActivityTextBinding


class MainActivity : AppCompatActivity() {
    private var mAnimationDrawable: AnimationDrawable? = null
    private var mFadeInAnimation: Animation? = null
    private var mFadeOutAnimation: Animation? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        binding.headView.startAnimation(mFadeInAnimation)
        binding.newGameButton.startAnimation(mFadeInAnimation)



        binding.newGameButton.setOnClickListener { nextClick() }

        binding.trainImage.setBackgroundResource(R.drawable.train_animation)
        mAnimationDrawable =  binding.trainImage.getBackground() as AnimationDrawable
        mAnimationDrawable!!.start()


    }

    override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun nextClick() {
        val nextIntent = Intent(this, TextActivity::class.java)
        val  CHOOSE_THIEF =0;
        startActivityForResult(nextIntent, CHOOSE_THIEF)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        finish()
    }
}