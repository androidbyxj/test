package cn.ireader.ti.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import javax.security.auth.login.LoginException;

/**
 * Created by Xj on 2017/6/8.
 */

public class TextViewWithoutPadding extends TextView {

    private Rect mBounds;
    private TextPaint mPaint;
    private String mText;
    private int mLength,mLeft,mTop,mRight,mBottom;

    public TextViewWithoutPadding(Context context) {
        super(context);
        init();
    }

    public TextViewWithoutPadding(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewWithoutPadding(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
    }
    Drawable drawable;
    private void init() {
        mText = getText().toString();
        mBounds = new Rect();
        mPaint = getPaint();
        drawable = ContextCompat.getDrawable(getContext(),R.drawable.test);
        mPaint.setTextSize(getTextSize());
//        mPaint.setColor(0xFF007DFF);
        mLength = mText.length();
        adJustTextWidthAndHeight();
        Log.e(TAG, "init: " + mBounds.toString());
    }

    public static final int MENU_UNENABLE_COLOR = 0x33000000;


    protected ColorStateList createColorStateList(int color) {
        int normal = color;
        int focused = changeColorByLight(color, 0.8f);//颜色加黑20%
        int unable = MENU_UNENABLE_COLOR;
        int pressed = focused;
        return createColorStateList(normal, focused, unable, pressed);
    }

    public static int changeColorByLight(int color,float lightPercent){
        int resultColor = color;
        if(lightPercent > 0){
            float[] hsl = new float[3];
            rgb2hsl(color, hsl);
            resultColor = hsl2rgb(hsl[0], hsl[1], hsl[2]*lightPercent);
        }
        return resultColor;
    }

    public static final void rgb2hsl(int rgb, float[] hsl) {
        float r = Color.red(rgb) / 255f;
        float g = Color.green(rgb) / 255f;
        float b = Color.blue(rgb) / 255f;
        float max, min, diff, r_dist, g_dist, b_dist;
        max = Math.max(Math.max(r, g), b);
        min = Math.min(Math.min(r, g), b);

        diff = max - min;
        hsl[2] = (max + min) / 2;
        if (diff == 0.f) {
            //--This is a gray, no chroma
            hsl[0] = 0.f;
            hsl[1] = 0.f;
        } else {
            if (hsl[2] < 0.5) {
                hsl[1] = diff / (max + min);
            } else {
                hsl[1] = diff / (2 - max - min);
            }
            r_dist = ((max - r)/6.0f + diff/2.0f) / diff;
            g_dist = ((max - g)/6.0f + diff/2.0f) / diff;
            b_dist = ((max - b)/6.0f + diff/2.0f) / diff;
            if (r == max) {
                hsl[0] = b_dist - g_dist;
            } else if (g == max) {
                hsl[0] = (1.0f/3.0f) + r_dist - b_dist;
            } else if (b == max) {
                hsl[0] = (2.0f/3.0f) + g_dist - r_dist;
            }
            if (hsl[0] < 0) hsl[0] += 1;
            if (hsl[0] > 1) hsl[0] -= 1;
        }
    }

    public static final int hsl2rgb(double h, double s, double l) {
        double r, g, b;
        double var_1, var_2;
        if (s == 0) {
            r = l * 255.0;
            g = l * 255.0;
            b = l * 255.0;
        } else {
            if (l < 0.5) {
                var_2 = l * (1 + s);
            } else {
                var_2 = (l + s) - (s * l);
            }
            var_1 = 2.0 * l - var_2;
            r = 255.0 * hue2rgb(var_1, var_2, h + (1.0 / 3.0));
            g = 255.0 * hue2rgb(var_1, var_2, h);
            b = 255.0 * hue2rgb(var_1, var_2, h - (1.0 / 3.0));
        }
        return Color.rgb((int) r, (int) g, (int) b);
    }

    private static double hue2rgb(double v1, double v2, double vH) {
        if (vH < 0)
            vH += 1;
        if (vH > 1)
            vH -= 1;
        if (6.0 * vH < 1)
            return v1 + (v2 - v1) * 6.0 * vH;
        if (2.0 * vH < 1)
            return v2;
        if (3.0 * vH < 2)
            return v1 + (v2 - v1) * ((2.0 / 3.0) - vH) * 6.0;
        return (v1);
    }

    public static ColorStateList createColorStateList(int normal, int focused, int unable, int pressed) {
        int[] colors = new int[] { pressed, focused, unable, normal };
        int[][] states = new int[4][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { -android.R.attr.state_enabled };
        states[3] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    public static final String TAG = "TextViewWithoutPadding";
    @Override
    protected void onDraw(@NonNull Canvas canvas) {

        //        adJustTextWidthAndHeight();
//        final int left = mBounds.left;
//        final int bottom = mBounds.bottom;
        Log.e(TAG, "onDraw: " + " start offset :　" + mBounds.toString());
        mBounds.offset(-mLeft, -mTop);
        Log.e(TAG, "onDraw: " + "end offset : " + mBounds.toString());
        canvas.drawText(mText, -mLeft, mBounds.bottom - mBottom, mPaint);
        drawable.draw(canvas);
        //        super.draw(canvas);
        Log.e(TAG, "onDraw: " + "canvas over ： " + mBounds.toString());

        reset();
    }

    private void reset() {
        mBounds.offset(mLeft,mTop);
        Log.e(TAG, "reset: " + "重置后位：" + toString());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: " + "");
        setMeasuredDimension(mBounds.width(), -mTop + mBottom);
    }

    private void adJustTextWidthAndHeight() {
        mPaint.getTextBounds(mText, 0, mLength, mBounds);
        if (mLength == 0) {
            mBounds.right = mBounds.left;
        }
        mLeft = mBounds.left;
        mTop = mBounds.top;
        mRight = mBounds.right;
        mBottom = mBounds.bottom;
        Log.e(TAG, "adJustTextWidthAndHeight: " + "打印 -- " + toString());
    }

    @Override
    public String toString() {
        return "TextViewWithoutPadding{" + "mLeft=" + mLeft + ", mTop=" + mTop + ", mRight=" + mRight + ", mBottom=" + mBottom + '}';
    }
}
