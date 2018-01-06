package com.example.hitboxtest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.jbox2d.common.Vec2;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    public MainActivity activity;
    Paint paint;
    DrawThread dt;
    float downX,downY;
    float upX,upY;

    public GameView(MainActivity activity){
        super(activity);

        this.activity = activity;
        this.getHolder().addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);

        dt = new DrawThread(this);
        Constant.DRAW_THREAD_FLAG = true;
        dt.start();
    }

    public void onDraw(Canvas canvas){
        if(canvas == null){
            return;
        }

//        canvas.save();
//        canvas.translate(ScreenScaleUtil.x, ScreenScaleUtil.y);
//        canvas.scale(ScreenScaleUtil.ratio2, ScreenScaleUtil.ratio1);
//        canvas.clipRect(0,0,1280,720);//标准分辨率大小

        canvas.drawARGB(255,255,255,255);//屏幕背景颜色

        for (MyBody body:activity.bl){
            body.drawSelf(canvas,paint);
        }
    }

    public void repaint(){
        SurfaceHolder holder = this.getHolder();
        Canvas canvas = holder.lockCanvas();
        try {
            synchronized (holder){
                onDraw(canvas);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(canvas != null){
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upX = event.getX();
                upY = event.getY();

                Vec2 vel = new Vec2(upX-downX,upY-downY);
                activity.ball.body.setLinearVelocity(vel);
        }

        //repaint();
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Constant.DRAW_THREAD_FLAG = false;
        activity.bl.clear();
        activity.tempbl.clear();
    }
}
