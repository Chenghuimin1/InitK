package cheng.com.bawie.lx02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class Choujinag extends View {

    private String[] desc = new String[]{"性感", "丰满", "知性", "聪明", "贤惠", "优秀"};
    private int[] colors = new int[]{Color.RED, Color.GRAY, Color.YELLOW, Color.BLUE, Color.GREEN, Color.DKGRAY, Color.WHITE};
    private int screenwidth;
    private int screenheight;
    private int centerX;
    private int centerY;
    private Paint paint;

    public Choujinag(Context context) {
        this(context,null);
    }

    public Choujinag(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Choujinag(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //1. 获取屏幕的宽高信息
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenwidth = displayMetrics.widthPixels;
        screenheight = displayMetrics.heightPixels;

        //2. 获取屏幕的中心位置
        centerX = screenwidth / 2;
        centerY = screenheight / 2;

        //4初始化画笔
        initPaint();
    }



    //3.测量宽高信息
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(100,100);
    }

    // 4 初始化画笔
    private void initPaint() {
        //[1]创建画笔对象
        paint = new Paint();
        //[2]设置画笔的颜色
        paint.setColor(Color.RED);
        //[3]设置画笔的宽度
        paint.setStrokeWidth(20);
        //[4]设置画笔的样式
        paint.setStyle(Paint.Style.FILL);
        //[5]设置画笔的锯齿效果
        paint.setAntiAlias(true);
    }

    //5开始绘画

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas是画布

        //[1] 移动画布的坐标原点
        canvas.translate(centerX,centerY);
        //[2]圆角矩形 矩形的大小 
        RectF rectF=new RectF(-300,-300,300,300);
        //[3]设置圆弧的开始弧度
        float start=60;
        //[4]循环 开始绘画
        for (int i = 0; i <6 ; i++) {
            //画笔设置颜色 画出不同的色彩
            paint.setColor(colors[i]);
            //画布设置不同的弧度 drawArc:圆弧
            canvas.drawArc(rectF,start*i,60,true,paint);
        }

        //[5]绘制中心的圆
        paint.setColor(Color.RED);
        canvas.drawCircle(0,0,100,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(40);

        //[6]获取文字的宽度和高度
        Rect rectText = new Rect();
        paint.getTextBounds("start",0,5,rectText);
        int width = rectText.width();
        int height = rectText.height();
        canvas.drawText("start",-width/2,height/2,paint);

        //绘制描述信息
        RectF rectF1 = new RectF(-200, -200, 200, 200);
        for (int i = 0; i <6; i++) {
            paint.setColor(Color.WHITE);
            Path path = new Path();
            path.addArc(rectF1,start*i+15,60);
           canvas.drawTextOnPath(desc[i],path,0,0,paint);
        }
    }
}
