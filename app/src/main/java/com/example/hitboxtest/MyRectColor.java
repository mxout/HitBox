package com.example.hitboxtest;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import org.jbox2d.dynamics.Body;

import static com.example.hitboxtest.Constant.RATE;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class MyRectColor extends MyBody {
    float halfWidth;
    float halfHeight;
    boolean isLive = true;
    boolean isBlock;

    public MyRectColor(Body body,float halfWidth,float halfHeight,int color,boolean isBlock){
        this.body = body;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
        this.color = color;
        this.isBlock = isBlock;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        if(!isLive){
            return;
        }

        paint.setColor(color&0x8cffffff);

        float x = body.getPosition().x * RATE;
        float y = body.getPosition().y * RATE;
        float angle = body.getAngle();

        canvas.save();

        Matrix matrix = new Matrix();
        matrix.setRotate((float)Math.toDegrees(angle),x,y);
        canvas.setMatrix(matrix);

        canvas.drawRect(x-halfWidth,y-halfHeight,x+halfWidth,y+halfHeight,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(color);

        canvas.drawRect(x-halfWidth,y-halfHeight,x+halfWidth,y+halfHeight,paint);

        paint.reset();
        canvas.restore();
    }
}
