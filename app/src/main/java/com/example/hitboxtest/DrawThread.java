package com.example.hitboxtest;

import static com.example.hitboxtest.Constant.DRAW_THREAD_FLAG;
import static com.example.hitboxtest.Constant.ITERA;
import static com.example.hitboxtest.Constant.TIME_STEP;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class DrawThread extends Thread {
    GameView gv;

    public DrawThread(GameView gv){
        this.gv = gv;
    }

    @Override
    public void run() {
        while (DRAW_THREAD_FLAG){
            gv.activity.world.step(TIME_STEP,ITERA,ITERA);

            if(gv.activity.tempbl.size() > 0){//待删除列表不为控
                for(MyBody bb:gv.activity.tempbl){
                    gv.activity.world.destroyBody(bb.body);//销毁待删除列表中的物体
                    bb = null;
                }

                gv.activity.bl.removeAll(gv.activity.tempbl);//删除发生碰撞的砖块物体
                gv.activity.tempbl.clear();
            }

            gv.repaint();
        }
    }
}
