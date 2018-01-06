package com.example.hitboxtest;

/**
 * Created by Administrator on 2017/12/24 0024.
 */
public class ScreenScaleUtil {
    public static final int LANDSCAPE = 0;//横屏
    public static final int PORTRAIT = 1;//竖屏

    public static int screenOrient;
    public static float SCREEN_WIDTH = 0;//屏幕宽度（需根据屏幕实际宽度重新赋值）
    public static float SCREEN_HEIGHT = 0;//屏幕高度（需根据屏幕实际高度重新赋值）

    public static final float sHpWidth = 1280;//预期横屏（画布）宽度
    public static final float sHpHeight = 720;//预期横屏（画布）高度
    public static final float whHpRatio = sHpWidth/sHpHeight;//预期横屏宽高比

    public static final float sSpWidth = 720;//预期竖屏（画布）宽度
    public static final float sSpHeight = 1280;//预期竖屏（画布）高度
    public static final float whSpRatio = sSpWidth/sSpHeight;//预期竖屏宽高比

    public static int x = 0;//画布左上角起始坐标x
    public static int y = 0;//画布左上角起始坐标y
    public static float ratio1 = 1.0f;//等比例缩放时的高度缩放比例
    public static float ratio2 = 1.0f;//等比例缩放时的宽度缩放比例
    public static float ratio = 1.0f;//非等比例缩放时的整体缩放比

    public static void decideScreenOrientation(){
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {//横屏
            screenOrient = LANDSCAPE;
        }else{//竖屏
            screenOrient = PORTRAIT;
        }
    }

    public static void ScreenScaleWithNotEqualRatio(){//非等比例缩放
                                                    //直接计算宽高比
        decideScreenOrientation();

        if(screenOrient == LANDSCAPE){//横屏时
            ratio1 = SCREEN_HEIGHT / sHpHeight;//横屏高度缩放比
            ratio2 = SCREEN_WIDTH / sHpWidth;//横屏宽度缩放比

            x = 0;
            y = 0;
        }

        if(screenOrient == PORTRAIT){//竖屏时
            ratio1 = SCREEN_HEIGHT / sSpHeight;//竖屏高度缩放比
            ratio2 = SCREEN_WIDTH / sSpWidth;//竖屏宽度缩放比

            x = 0;
            y = 0;
        }
        return;
    }

    public static void ScreenScaleWithEqualRatioAndCrop(){//等比例缩放并裁剪
                                                        //需计算左上角x、y坐标用于移动画布，实现“裁剪”
        decideScreenOrientation();

        if(screenOrient == LANDSCAPE){//横屏时
            ratio = SCREEN_HEIGHT/sHpHeight;
            float width = sHpWidth*ratio;

            if(width > SCREEN_WIDTH){
                x = (int)(SCREEN_WIDTH - width);//裁剪宽度（画布向左移动，使左边部分移出屏幕）
            }
        }

        if(screenOrient == PORTRAIT){//竖屏时
            ratio = SCREEN_WIDTH/sSpWidth;
            float height = sSpHeight*ratio;

            if(height > SCREEN_HEIGHT){
                y = (int)(SCREEN_HEIGHT - height);//裁剪高度（画布向上移动，使上面部分移出屏幕）
            }
        }

        return;
    }

    public static void ScreenScaleWithEqualRatioAndBlank() {//等比例缩放并留白
                                                            //计算左上角x、y坐标，实现画布左右或上下留白
        decideScreenOrientation();

        if(screenOrient == LANDSCAPE){//横屏时
            float screenRatio = SCREEN_WIDTH/SCREEN_HEIGHT;

            if(screenRatio > whHpRatio){//实际（屏幕）宽高比大于预期（画布）宽高比，画布等比例缩放时则左右留白
                ratio = SCREEN_HEIGHT/sHpHeight;
                float width = sHpWidth*ratio;
                x = (int)((SCREEN_WIDTH-width)/2.0f);
                y = 0;
            }else {//实际（屏幕）宽高比小于预期（画布）宽高比，画布等比例缩放时则上下留白
                ratio = SCREEN_WIDTH/sSpWidth;
                float height = sSpHeight*ratio;
                x = 0;
                y = (int)((SCREEN_HEIGHT-height)/2.0f);
            }
        }

        if(screenOrient == PORTRAIT){//竖屏时
            float screenRatio = SCREEN_WIDTH/SCREEN_HEIGHT;

            if(screenRatio > whSpRatio){
                ratio = SCREEN_HEIGHT/sSpHeight;
                float width = sSpWidth*ratio;
                x = (int)((SCREEN_WIDTH-width)/2.0);
                y = 0;
            }else {
                ratio = SCREEN_WIDTH/sSpWidth;
                float height = sSpHeight*ratio;
                x = 0;
                y = (int)((SCREEN_HEIGHT-height)/2.0f);
            }
        }
        return;
    }
}
