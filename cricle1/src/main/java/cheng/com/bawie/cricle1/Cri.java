package cheng.com.bawie.cricle1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Cri extends View {

    private int height;
    private int width;
    private int[] cicleColor=new int[]{Color.parseColor("#8EE5EE"), Color.parseColor("#FFD700"), Color.parseColor("#FFD39B"), Color.parseColor("#FF8247"), Color.parseColor("#FF34B3"), Color.parseColor("#F0E68C")};
    private String[] cicleText=new String[]{"香蕉","苹果","橘子","西瓜","桃子","梨子"};
    private Paint paint;
    private RectF rectF;


    public Cri(Context context) {
        this(context,null);
    }

    public Cri(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Cri(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //测量的方法

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽高
        setMeasuredDimension(300,300);
        //获得宽高
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        //设置宽高
        rectF = new RectF(0,0,width,height);
    }

    //绘画

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔对象
        paint = new Paint();
        //颜色
        paint.setColor(Color.RED);
        //设置空心边框的宽度
        paint.setStrokeWidth(2);
        //设置画笔锯齿效果
        paint.setAntiAlias(true);
        for (int i = 0; i <6; i++) {
            paint.setColor(cicleColor[i]);
            canvas.drawArc(rectF,i*60,60,true,paint);
        }
    }
}
