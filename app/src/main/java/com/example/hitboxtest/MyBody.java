package com.example.hitboxtest;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.jbox2d.dynamics.Body;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public abstract class MyBody {
    Body body;
    int color;
    public abstract void drawSelf(Canvas canvas, Paint paint);
}
