package com.example.hitboxtest;

import android.app.Activity;

import org.jbox2d.callbacks.ContactFilter;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class MyContactFilter extends ContactFilter {
    public MyContactFilter(Activity activity){}

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();

        if((Integer)(bodyA.getUserData()) == -1 || (Integer)(bodyB.getUserData()) == -1){
            return true;
        }else {
            return false;
        }
    }
}
