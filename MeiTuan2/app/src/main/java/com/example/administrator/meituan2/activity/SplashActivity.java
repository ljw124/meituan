package com.example.administrator.meituan2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.example.administrator.meituan2.R;


/**
 * 闪屏页完成
 */
public class SplashActivity extends AppCompatActivity {

    private RelativeLayout rl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //给需要播放动画的根布局设置一个id，通过此id来开启动画
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1);
        alphaAnimation.setDuration(3000); //让渐变动画多执行1秒
        alphaAnimation.setFillAfter(true);

        //开启动画
        rl_root.startAnimation(alphaAnimation);

        //对动画设置监听，动画播放结束后跳转到新手引导页
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish(); //关闭此activity
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
