package com.example.yummy;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yummy.databinding.ActivityLotteBinding;

public class LotteActivity extends AppCompatActivity {


    private ActivityLotteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLotteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LottieAnimationView lottieAnimationView = findViewById(R.id.mylotte);
        lottieAnimationView.setAnimation(R.raw.annimation);
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.playAnimation();

        Log.d("LottieActivity", "Animation Started"); // Add this log

        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("LottieActivity", "Animation Ended"); // Add this log to confirm it reaches this point
                Intent toHomeIntent = new Intent(LotteActivity.this, MainActivity.class);
                startActivity(toHomeIntent);
                finish();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("LottieActivity", "Animation Start"); // Add this log
            }

            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        });


    }
}