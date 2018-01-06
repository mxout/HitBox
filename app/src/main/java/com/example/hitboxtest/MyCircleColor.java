package com.example.hitboxtest;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.jbox2d.dynamics.Body;

import static com.example.hitboxtest.Constant.RATE;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class MyCircleColor extends MyBody {
    float radius;

    public MyCircleColor(Body body,float radius,int color){
        this.body = body;
        this.radius = radius;
        this.color = color;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        paint.setColor(color&0x8cffffff);

        float x = body.getPosition().x * RATE;
        float y = body.getPosition().y * RATE;

        canvas.drawCircle(x,y,radius,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(color);

        canvas.drawCircle(x,y,radius,paint);

        paint.reset();
    }
}
