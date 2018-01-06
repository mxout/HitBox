package com.example.hitboxtest;

import org.jbox2d.dynamics.Body;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class BodySearchUtil {
    public static void doAction(Body body1, Body body2, ArrayList<MyBody> bl,ArrayList<MyBody> tempbl){
        for(MyBody mpi:bl){
            if(body1 == mpi.body || body2 == mpi.body){//从列表中找到发生碰撞的物体
                if(mpi instanceof MyRectColor){//碰撞的物体是矩形
                    MyRectColor mrc = (MyRectColor)mpi;
                    if(mrc.isBlock){//碰撞的物体是砖块
                        mrc.isLive = false;//则将物体设置为不可见
                        tempbl.add(mrc);//并将物体放入待删除列表中
                    }
                }

                if(mpi instanceof MyCircleColor){//碰撞的物体时圆球

                }
            }
        }
    }
}
