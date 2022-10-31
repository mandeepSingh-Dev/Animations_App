package com.mandeep.animationsapp

import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.LevelListDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintsChangedListener
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mandeep.animationsapp.databinding.ActivityMainBinding
import java.util.Objects.toString

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val width1 = binding.layout1.layoutParams.width.toFloat()
        val width2 = binding.layout2.layoutParams.width.toFloat()

        Log.d("ogrfbf","$width1  $width2")


        /**Moving view using Object Animator*/
        binding.layout2.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(binding.animatedLayout,"translationX",binding.layout1.width.toFloat()+30f)
            objectAnimator.duration = 300
            objectAnimator.start()
        }
        binding.layout1.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(binding.animatedLayout,"translationX",(binding.layout2.width.toFloat().minus(binding.layout1.width.toFloat())))
            objectAnimator.duration = 300
            objectAnimator.start()
        }
        /** --------------------------------- */

      /**Animate two similar consraint Layout using Constraint Set*/
        val constraintSet1 = ConstraintSet()
        val constraintSet2 = ConstraintSet()
        val resetConstraintLayout = ConstraintSet()

        constraintSet1.clone(binding.root)
        constraintSet2.clone(this,R.layout.activity_main_duplicate)
        resetConstraintLayout.clone(binding.root)


        var set = false
        binding.root.setOnClickListener {
            /**Transition between two layouts*/
         /*  if(set) {
               TransitionManager.beginDelayedTransition(binding.root)
               constraintSet2.applyTo(binding.root)
           }
               else {
               TransitionManager.beginDelayedTransition(binding.root)
               constraintSet1.applyTo(binding.root)
           }*/
          /** ------------------------------------------------------ */

            /**Transitions of just constraint , margins etc of same layout */
            if(!set) {
                TransitionManager.beginDelayedTransition(binding.root)
                constraintSet1.connect(R.id.imageVIew, ConstraintSet.START, R.id.root, ConstraintSet.START)
                constraintSet1.connect(R.id.imageVIew, ConstraintSet.RIGHT, R.id.root, ConstraintSet.RIGHT)
                constraintSet1.connect(R.id.imageVIew, ConstraintSet.BOTTOM, R.id.root, ConstraintSet.BOTTOM)
                constraintSet1.connect(R.id.imageVIew, ConstraintSet.TOP, R.id.root, ConstraintSet.TOP)
                constraintSet1.setMargin(R.id.imageVIew, ConstraintSet.BOTTOM, 600)
                constraintSet1.constrainHeight(R.id.imageVIew,350)
                constraintSet1.constrainHeight(R.id.bottomNavigationView,300)
                constraintSet1.applyTo(binding.root)
            }else {
                TransitionManager.beginDelayedTransition(binding.root)
                resetConstraintLayout.applyTo(binding.root)
            }
            /** ------------------------------------------------------ */
            set = !set
        }






    }

}