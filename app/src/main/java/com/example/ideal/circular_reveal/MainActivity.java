package com.example.ideal.circular_reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button b1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.b1);
        t1=(TextView)findViewById(R.id.t1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b1.getText().toString().equals("show text")) {
                    if (Build.VERSION.SDK_INT >= 21) {
// get the center for the clipping circle
                        int cx = t1.getWidth() / 2;
                       // int cy = t1.getHeight() / 2;
                        int cy = 0;

// get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(t1, cx, cy, 0, finalRadius);

// make the view visible and start the animation
                        t1.setVisibility(View.VISIBLE);
                        anim.setDuration(100);
                        anim.start();
                        b1.setText("hide text");
                    } else {
                        t1.setText(View.VISIBLE);
                        b1.setText("hide text");
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= 21) {
// get the center for the clipping circle
                        int cx = t1.getWidth() / 2;
                       // int cy = t1.getHeight() / 2;
                        int cy = 0;

// get the initial radius for the clipping circle
                        float initialRadius = (float) Math.hypot(cx, cy);

// create the animation (the final radius is zero)
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(t1, cx, cy, initialRadius, 0);

// make the view invisible when the animation is done
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                t1.setVisibility(View.INVISIBLE);
                                b1.setText("show text");
                            }
                        });

// start the animation
                        anim.setDuration(1000);
                        anim.start();
                       
                    }
                    else {

                            t1.setText(View.VISIBLE);
                            b1.setText("show text");

                    }
                }
            }
        });
    }
}
