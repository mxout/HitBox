package com.example.hitboxtest;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;

import static com.example.hitboxtest.ScreenScaleUtil.SCREEN_HEIGHT;
import static com.example.hitboxtest.ScreenScaleUtil.SCREEN_WIDTH;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<MyBody> bl = new ArrayList<>();
    public static ArrayList<MyBody> tempbl = new ArrayList<>();
    public World world;
    public static MyCircleColor ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        SCREEN_WIDTH = displayMetrics.widthPixels;
        SCREEN_HEIGHT = displayMetrics.heightPixels;

        //ScreenScaleUtil.ScreenScaleWithNotEqualRatio();

        Vec2 gravity = new Vec2(0.0f,9.8f);
        world = new World(gravity);

        //创建4边
        final int kd=20;//宽度或高度
        MyRectColor mrc=Box2DUtil.createBox(kd/2, SCREEN_HEIGHT/2, kd/2, SCREEN_HEIGHT/2-kd/2, true,world, Color.YELLOW,false);
        bl.add(mrc);
        mrc=Box2DUtil.createBox(SCREEN_WIDTH-kd/2, SCREEN_HEIGHT/2, kd/2, SCREEN_HEIGHT/2-kd/2, true,world,Color.YELLOW,false);
        bl.add(mrc);
        mrc=Box2DUtil.createBox(SCREEN_WIDTH/2, kd/2, SCREEN_WIDTH/2, kd/2, true,world,Color.YELLOW,false);
        bl.add(mrc);
        mrc=Box2DUtil.createBox(SCREEN_WIDTH/2, SCREEN_HEIGHT-kd/2, SCREEN_WIDTH/2, kd/2, true,world,Color.YELLOW,false);
        bl.add(mrc);

        //创建砖块
        final int bs=20;//砖块间距
        final int bw=(int)((SCREEN_WIDTH-2*kd-5*bs)/4);
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                mrc=Box2DUtil.createBox
                        (
                                kd+bs+bw/2+j*(bw+bs),
                                kd+bs+kd/2+i*(kd+bs),
                                bw/2, kd/2,
                                true,
                                world,
                                Color.RED,
                                true
                        );
                bl.add(mrc);
            }
        }

        //创建球
        ball=Box2DUtil.createCircle(SCREEN_WIDTH/2, SCREEN_HEIGHT-2*kd, kd, world,Color.MAGENTA);
        bl.add(ball);
        //ball.body.setLinearVelocity(new Vec2(10,-60));

        MyContactListener listener = new MyContactListener();
        world.setContactListener(listener);

        GameView gameView = new GameView(this);
        setContentView(gameView);
    }
}
