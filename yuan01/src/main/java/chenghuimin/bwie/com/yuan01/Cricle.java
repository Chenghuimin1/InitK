package chenghuimin.bwie.com.yuan01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class Cricle extends View implements View.OnClickListener {

    private int centerX;
    private int centerY;
    private int screenwidth;
    private int screenheight;
    private Paint paint;
    private int[] colors = new int[]{Color.CYAN, Color.WHITE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.RED};
    private String[] desc = new String[]{"苹果", "橙子", "栗子", "葡萄", "桃子", "西瓜"};
    private boolean isRote;
    private int color1;
    private int color2;
    private int color3;
    private int color4;
    private int color5;
    private int color6;


    public Cricle(Context context) {
        this(context, null);
    }

    public Cricle(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Cricle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取屏幕的宽高信息
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenwidth = displayMetrics.widthPixels;


        screenheight = displayMetrics.heightPixels;

        //获取屏幕的中心
        centerX = screenwidth / 2;
        centerY = screenheight / 2;


        //找布局
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Cricle);
        //获取颜色

        color1 = typedArray.getColor(R.styleable.Cricle_mainColor1, 0);
        color2 = typedArray.getColor(R.styleable.Cricle_mainColor2, 0);
        color3 = typedArray.getColor(R.styleable.Cricle_mainColor3, 0);
        color4 = typedArray.getColor(R.styleable.Cricle_mainColor4, 0);
        color5 = typedArray.getColor(R.styleable.Cricle_mainColor5, 0);
        color6 = typedArray.getColor(R.styleable.Cricle_mainColor6, 0);


        initPaint();
        this.setOnClickListener(this);


    }

    private void initPaint() {
        //创建画笔对象
        paint = new Paint();
        //设置画笔的颜色
        paint.setColor(Color.RED);
        //设置画笔的宽度
        paint.setStrokeWidth(20);
        //设置画笔的样式
        paint.setStyle(Paint.Style.FILL);
        //设置抗锯齿
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //把画布移动到坐标的原点
        canvas.translate(centerX, centerY);

        //绘制6个圆弧 rectf是矩形
        RectF rectF = new RectF(-300, -300, 300, 300);


        if (color1 != -1) {
            paint.setColor(color1);
            canvas.drawArc(rectF, 60 * 0, 60, true, paint);
        }
        if (color2 != -1) {
            paint.setColor(color2);
            canvas.drawArc(rectF, 60 * 1, 60, true, paint);
        }
        if (color3 != -1) {
            paint.setColor(color3);
            canvas.drawArc(rectF, 60 * 2, 60, true, paint);
        }
        if (color4 != -1) {
            paint.setColor(color4);
            canvas.drawArc(rectF, 60 * 3, 60, true, paint);
        }
        if (color5 != -1) {
            paint.setColor(color5);
            canvas.drawArc(rectF, 60 * 4, 60, true, paint);
        }
        if (color6 != -1) {
            paint.setColor(color6);
            canvas.drawArc(rectF, 60 * 5, 60, true, paint);
        }

       /* //开始角度
        float start=60;
       //循环
        for (int i = 0; i < 6; i++) {
            //画颜色
            paint.setColor(colors[i]);
            //画布 drawArc圆弧孤框线
            canvas.drawArc(rectF,start*i,60,true,paint);
        }*/

        //00000000000000000

        //绘制中心的圆
        //1.颜色
        paint.setColor(Color.RED);
        //2.画圆
        canvas.drawCircle(0, 0, 100, paint);

        //设置中心圆的文字
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        //获取文字的宽度和高度 矩形
        Rect rectText = new Rect();
        //设置中心圆的文字
        paint.getTextBounds("start", 0, 5, rectText);
        //获取宽高
        int width = rectText.width();
        int height = rectText.height();
        //画布将文字画上去
        canvas.drawText("start", -width / 2, height / 2, paint);


        //绘制文字信息
        RectF rectF1 = new RectF(-200, -200, 200, 200);
        //循环
        for (int i = 0; i < 6; i++) {
            //设置画笔颜色
            paint.setColor(Color.BLACK);
            Path path = new Path();
            //添加狐
            path.addArc(rectF1, 60 * i + 15, 60);
            canvas.drawTextOnPath(desc[i], path, 0, 0, paint);
        }
    }

    private void startAnimal() {
        //是否在旋转状态
        isRote = true;
        //随机数
        double random = Math.random();
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, (float) ((720) * random), centerX, centerY);
        //设置时间
        rotateAnimation.setDuration(800);
        rotateAnimation.setFillAfter(true);

        //设置重复次数
        rotateAnimation.setInterpolator(new LinearInterpolator());
        //设置重复模式
        rotateAnimation.setRepeatMode(Animation.RESTART);

        //给动画添加监听
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isRote = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        startAnimation(rotateAnimation);

    }

    @Override
    public void onClick(View v) {
        if (!isRote) {
            startAnimal();
        }
    }

    //封装接口属性
    private getName xn;

    //接口传值
    public interface getName{
        Void Name(View view);
    }
    //写方法
    public void HttpDong(getName hua){
        this.xn=hua;
    }
}
