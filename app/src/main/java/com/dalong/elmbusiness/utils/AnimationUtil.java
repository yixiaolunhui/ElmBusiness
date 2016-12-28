package com.dalong.elmbusiness.utils;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * popupWindow 弹入弹出动画
 * Created by dalong on 2016/12/22.
 */

public class AnimationUtil {

    //弹入动画时间
    public final static int ANIMATION_IN_TIME=400;
    //弹出动画时间
    public final static int ANIMATION_OUT_TIME=300;

    /**
     * 创建弹入动画集合
     * @param context
     * @param fromYDelta
     * @return
     */
    public static Animation createInAnimation(Context context, int fromYDelta){
        //创建一个动画集合
        AnimationSet set=new AnimationSet(context,null);
        //设置动画结束之后是否保持动画的目标状态
        set.setFillAfter(true);
        //创建一个位移动画
        TranslateAnimation animation=new TranslateAnimation(0,0,fromYDelta,0);
        //设置过度时间
        animation.setDuration(ANIMATION_IN_TIME);
        //添加动画集合
        set.addAnimation(animation);
        //创建一个透明度动画效果  这里都是1到1
        AlphaAnimation alphaAnimation=new AlphaAnimation(1,1);
        //设置过度时间
        alphaAnimation.setDuration(ANIMATION_IN_TIME);
        //添加动画集合
        set.addAnimation(alphaAnimation);
        return set;
    }

    /**
     * 创建退出动画
     * @param context
     * @param toYDelta
     * @return
     */
    public static Animation createOutAnimation(Context context, int toYDelta){
        //创建一个动画集合
        AnimationSet set=new AnimationSet(context,null);
        //设置动画结束之后是否保持动画的目标状态
        set.setFillAfter(true);
        //创建一个位移动画
        TranslateAnimation animation=new TranslateAnimation(0,0,0,toYDelta);
        //设置过度时间
        animation.setDuration(ANIMATION_OUT_TIME);
        //添加到动画集合
        set.addAnimation(animation);
        //创建一个透明度动画效果  这里都是1到1
        AlphaAnimation alphaAnimation=new AlphaAnimation(1,1);
        //设置过度时间
        alphaAnimation.setDuration(ANIMATION_OUT_TIME);
        //添加动画集合
        set.addAnimation(alphaAnimation);
        return set;
    }
}
