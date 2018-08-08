package juniu.trade.library;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author lyd
 * @date 18/8/7
 * @desription
 */
public class TextDrawable extends Drawable {

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 需要绘制的文字内容
     */
    private String mText;

    public TextDrawable(@ColorInt int color, float textSize, Typeface typeface, String text) {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(typeface);
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        mPaint.setTextAlign(Paint.Align.CENTER);
        this.mText = text;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float baseX = getBounds().top;
        float baseY = getBounds().bottom;
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        float newY = baseY + offY;
        float textWidth = mPaint.measureText(mText);
        canvas.drawText(mText, -textWidth / 2, newY, mPaint);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
