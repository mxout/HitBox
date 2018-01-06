package com.example.hitboxtest;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {//开始碰撞时被回调
        BodySearchUtil.doAction(contact.m_fixtureA.getBody(),contact.m_fixtureB.getBody(),MainActivity.bl,MainActivity.tempbl);
    }

    @Override
    public void endContact(Contact contact) {//结束碰撞时被回调

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {//当碰撞更新后，在求解前被回调

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {//当碰撞被求解后回调，可以用于检查冲量情况

    }
}
