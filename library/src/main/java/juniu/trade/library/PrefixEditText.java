package juniu.trade.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author lyd
 * @date 18/8/7
 * @desription
 */
public class PrefixEditText extends AppCompatEditText {

    /**
     * 前缀文字
     */
    private String mPrefixText;

    public PrefixEditText(Context context) {
        super(context);
        init(null);
    }

    public PrefixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PrefixEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.PrefixEditText);
            String text = array.getString(R.styleable.PrefixEditText_prefix_text);
            setPrefixText(text);
            array.recycle();
        }
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setPrefixText(mPrefixText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setPrefixText(String prefixText) {
        if (mPrefixText == null) {
            mPrefixText = "";
        }
        this.mPrefixText = prefixText;
        drawPrefix();
    }

    /**
     * 设置文字（当内容为0的时候会置""）
     * @param str
     */
    public void setTextZero(String str){
        if(TextUtils.isEmpty(str)){
            setText("");
        }else {
            if("0".equals(str)){
                setText("");
            }
        }
    }

    /**
     * 绘制
     */
    private void drawPrefix() {
        int color;
        //获取hint文字颜色
        if (TextUtils.isEmpty(getText())) {
            color = getHintTextColors().getDefaultColor();
        }
        //获取字体颜色
        else {
            color = getTextColors().getDefaultColor();
        }
        TextDrawable drawable = new TextDrawable(color, getTextSize(), getTypeface(), mPrefixText);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        setCompoundDrawables(drawable, null, null, null);
    }
}
