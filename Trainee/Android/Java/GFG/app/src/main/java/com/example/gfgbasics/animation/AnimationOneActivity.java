package com.example.gfgbasics.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.captaindroid.lineanimation.Animator;
import com.captaindroid.lineanimation.utils.OnPathListener;
import com.example.gfgbasics.MainActivity;
import com.example.gfgbasics.R;
import com.google.android.material.snackbar.Snackbar;
import com.sarnava.textwriter.TextWriter;

import me.wangyuwei.particleview.ParticleView;
import xyz.hanks.library.bang.SmallBangView;

public class AnimationOneActivity extends AppCompatActivity implements OnPathListener {

    Animator lineAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_one);

        allAnimation();

        lottiAnimation();

        textWriter();

        startAnimationArrow();

        smallBangView();

        particularView();

        btnBounceAnimation();

        interpolatorAnimation();

        shineEffectOnButton();

        //Just check its worked or not? look like splash screen
        Thread thread = new Thread(){
            public void run(){
                try{
                    Thread.sleep(3000);

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private void shineEffectOnButton() {
        View shine = findViewById(R.id.viewShine);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_right);
        shine.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                shine.startAnimation(anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void interpolatorAnimation() {
        // 2-second animation duration
        int ANIMATION_DURATION = 4000;

        Button linear = findViewById(R.id.linear);
        Button accelerate = findViewById(R.id.accelerate);
        Button decelerate = findViewById(R.id.decelerate);
        Button bounce = findViewById(R.id.bounce);
        Button overshoot = findViewById(R.id.overshoot);
        Button anticipate = findViewById(R.id.anticipate);
        Button cycle = findViewById(R.id.cycle);
        Button accelerateDecelerate = findViewById(R.id.accelerateDecelerate);
        Button anticipateOvershoot = findViewById(R.id.anticipateOvershoot);

        // Linear
        linear.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(linear, "translationX", 200f);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Accelerate
        accelerate.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(accelerate, "translationX", 200f);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Decelerate
        decelerate.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(decelerate, "translationX", 200f);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Bounce
        bounce.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(bounce, "translationX", 200f);
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Overshoot
        overshoot.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(overshoot, "translationX", 200f);
            animator.setInterpolator(new OvershootInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Anticipate
        anticipate.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(anticipate, "translationX", 200f);
            animator.setInterpolator(new AnticipateInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Cycle
        cycle.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(cycle, "translationX", 200f);
            animator.setInterpolator(new CycleInterpolator(2));
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Accelerate Decelerate
        accelerateDecelerate.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(accelerateDecelerate, "translationX", 200f);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });

        // Anticipate Overshoot
        anticipateOvershoot.setOnClickListener(clickedView -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(anticipateOvershoot, "translationX", 200f);
            animator.setInterpolator(new AnticipateOvershootInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        });
    }

    private void btnBounceAnimation() {
        // loading Animation from
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // getting the Button from activity_main.xml file
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            // start the animation
            button.startAnimation(animation);
        });
    }

    private void particularView() {
        ParticleView particleView = findViewById(R.id.particleView);
        particleView.startAnim();

        // this listner will get invoked automatically
        // when animaion ends.
        particleView.setOnParticleAnimListener(() -> Toast.makeText(getApplicationContext(), "Animation is End!!", Toast.LENGTH_SHORT).show());
    }

    private void smallBangView() {
        SmallBangView textView = findViewById(R.id.textViewAnimation);
        SmallBangView imageView = findViewById(R.id.imageViewAnimation);

        textView.setOnClickListener(
                v -> {
                    if (textView.isSelected()) {
                        textView.setSelected(false);
                    } else {
                        // if not selected only
                        // then show animation.
                        textView.setSelected(true);
                        textView.likeAnimation();
                    }
                });

        imageView.setOnClickListener(
                v -> {
                    if (imageView.isSelected()) {
                        imageView.setSelected(false);
                    } else {
                        // if not selected only
                        // then show animation.
                        imageView.setSelected(true);
                        imageView.likeAnimation();
                    }
                });
    }

    private void startAnimationArrow() {
        lineAnimator = findViewById(R.id.lineAnimator);
        lineAnimator.startAnimateArrow();
    }

    private void textWriter() {
        TextWriter textWriter = findViewById(R.id.textWriter);
        textWriter.setWidth(9)
                .setText("Welcome here")
                .setColor(R.color.color17)
                //Here change INTERMEDIATE to another property
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setDelay(10)
                .setSizeFactor(30f)
                .setListener(() -> {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar snackbar = Snackbar.make(parentLayout, "Eat 5 star do nothing", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Close", v -> Toast.makeText(AnimationOneActivity.this, "Click Close", Toast.LENGTH_SHORT).show()).show();
                }).startAnimation();
    }

    private void lottiAnimation() {

        // Declaring the animation view
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.addAnimatorUpdateListener((animation) -> {});
        animationView.playAnimation();

        animationView.isAnimating();// Do something.

        // Custom animation speed or duration.
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> animationView.setProgress((Float) animation.getAnimatedValue()));
        animator.start();
    }

    private void allAnimation() {
        ImageView imageView;
        Button blinkBTN, rotateBTN, fadeBTN, moveBTN, slideBTN, zoomBTN, stopBTN, bounceBTN;

        imageView = findViewById(R.id.imageview);
        blinkBTN = findViewById(R.id.BTNblink);
        rotateBTN = findViewById(R.id.BTNrotate);
        fadeBTN = findViewById(R.id.BTNfade);
        moveBTN = findViewById(R.id.BTNmove);
        slideBTN = findViewById(R.id.BTNslide);
        zoomBTN = findViewById(R.id.BTNzoom);
        stopBTN = findViewById(R.id.BTNstop);
        bounceBTN = findViewById(R.id.BTNbounce);

        blinkBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_blink);
            imageView.startAnimation(animation);
        });

        rotateBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_rotate);
            imageView.startAnimation(animation);
        });

        fadeBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fade);
            imageView.startAnimation(animation);
        });

        moveBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_move);
            imageView.startAnimation(animation);
        });

        slideBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_slide);
            imageView.startAnimation(animation);
        });

        zoomBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_zoom);
            imageView.startAnimation(animation);
        });

        bounceBTN.setOnClickListener(v->{
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_bounce);
            imageView.startAnimation(animation);
        });

        stopBTN.setOnClickListener(v-> imageView.clearAnimation());
    }

    @Override
    public Path setOnPathUpdateListener(int bitmapPositionX, int bitmapPositionY) {
        Path path = new Path();
        path.moveTo((float) lineAnimator.getWidth() /2, 0);

        path.cubicTo(0, (float) lineAnimator.getHeight() / 2, lineAnimator.getWidth(),
                (float) lineAnimator.getHeight() / 2,
                (float) lineAnimator.getWidth() / 2, lineAnimator.getHeight());
        return path;
    }

    @Override
    public void setOnAnimationCompleteListener() {

    }
}