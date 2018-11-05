package cheng.com.bawie.initkong;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Custermer extends LinearLayout {


    private LinearLayout kj;
    private ImageView imageViewa;
    private TextView textViewa;
    //3.声明回调对象
    private CustormerClick leftclick;

    public Custermer(Context context) {
        this(context,null);
    }

    public Custermer(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Custermer(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化控件
        initView(context);
        //初始化属性
        initAttrs(context,attrs);
        //初始化点击事件
        initClick();

    }




    private void initView(Context context) {
        kj = (LinearLayout) View.inflate(context, R.layout.item, this);
        imageViewa = kj.findViewById(R.id.imagea);
        textViewa = kj.findViewById(R.id.textviewa);
    }

    //初始化属性
    private void initAttrs(Context context, AttributeSet attrs) {
        //加载属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custermer);
        //加载图片
        Drawable drawable = typedArray.getDrawable(R.styleable.Custermer_image);
        if(drawable!=null){
            //赋值
            imageViewa.setImageDrawable(drawable);
        }

        //加载文字
        CharSequence text = typedArray.getText(R.styleable.Custermer_text);
        if(!TextUtils.isEmpty(text)){
            //赋值
            textViewa.setText(text);
        }

        int color = typedArray.getColor(R.styleable.Custermer_text_color,-1);
        if(color!=-1){
            textViewa.setTextColor(color);
        }

        float dimension = typedArray.getDimension(R.styleable.Custermer_text_size, 0f);
        textViewa.setTextSize(dimension);

    }

    private void initClick() {
        //5.使用回调接口
        textViewa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leftclick!=null){
                    leftclick.onLeftClick(v);
                }
            }
        });
    }

    //4.设置回调对象的set方法


    public void setLeftclick(CustormerClick leftclick) {
        this.leftclick = leftclick;
    }

    //1 定义一个回调接口 写方法
   public  interface CustormerClick{
        void onLeftClick(View view);
    }


}
