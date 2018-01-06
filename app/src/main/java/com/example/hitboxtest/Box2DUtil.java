package com.example.hitboxtest;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import static com.example.hitboxtest.Constant.RATE;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class Box2DUtil {
    public static MyRectColor createBox(float x,
                                        float y,
                                        float halfWidth,
                                        float halfHeight,
                                        boolean isStatic,
                                        World world,
                                        int color,
                                        boolean isBlock){
        BodyDef bd = new BodyDef();//定义刚体的基础描述
        if(isStatic){
            bd.type = BodyType.STATIC;
        }else {
            bd.type = BodyType.DYNAMIC;
        }
        bd.position.set(x/RATE,y/RATE);

        Body bodyTemp = world.createBody(bd);//创建刚体

        PolygonShape shape = new PolygonShape();//定义刚体的形状
        shape.setAsBox(halfWidth/RATE,halfHeight/RATE);

        FixtureDef fd = new FixtureDef();//定义刚体的物理特性（用于动态物体）
        fd.density = 1.0f;
        fd.friction = 0.05f;
        fd.restitution = 0.6f;
        fd.shape = shape;
        if(!isStatic){
            bodyTemp.createFixture(fd);//赋予动态物体以物理特性
        }else {
            bodyTemp.createFixture(shape,0);//赋予静态物体以形状
        }

        return new MyRectColor(bodyTemp,halfWidth,halfHeight,color,isBlock);
    }

    public static MyCircleColor createCircle(float x,
                                          float y,
                                          float radius,
                                          World world,
                                          int color){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(x/RATE,y/RATE);

        Body bodyTemp = world.createBody(bd);

        CircleShape shape = new CircleShape();
        shape.m_radius = radius/RATE;

        FixtureDef fd = new FixtureDef();
        fd.density = 2.0f;
        fd.friction = 0.05f;
        fd.restitution = 0.8f;
        fd.shape = shape;

        bodyTemp.createFixture(fd);

        return new MyCircleColor(bodyTemp,radius,color);
    }
}
