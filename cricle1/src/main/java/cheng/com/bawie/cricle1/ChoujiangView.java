package cheng.com.bawie.cricle1;

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
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class ChoujiangView extends View implements View.OnClickListener{

    private int screenwidth;
    private int screenheight;
    //获取屏幕宽高信息
    private int centerX;
    private int centerY;
    private int[] colors;
    private Paint paint;
    private String[] desc = new String[]{"性感", "丰满", "知性", "聪明", "贤惠", "优秀"};
    private boolean isRote;

    public ChoujiangView(Context context) {
        this(context,null);
    }

    public ChoujiangView(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ChoujiangView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
      //获取屏幕宽高信息
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenwidth = displayMetrics.widthPixels;
        screenheight = displayMetrics.heightPixels;
        //获取屏幕中心坐标
        centerX=screenwidth/2;
        centerY=screenheight/2;

        //初始化画笔
        initPaint();

        //色彩
        colors = new int[]{Color.RED, Color.GRAY, Color.YELLOW, Color.BLUE, Color.GREEN, Color.DKGRAY, Color.WHITE};
        
        //初始化旋转动画
        initAnimation();
        //给自己添加点击事件
        this.setOnClickListener(this);

    }

    //测量大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(100,100);
    }

    //绘画
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //移动画布的坐标原点
        canvas.translate(centerX,centerY);


        //绘制6个圆弧
        RectF rectF=new RectF(-300,-300,300,300);
        float start=60;
        for (int i = 0; i <6 ; i++) {
            paint.setColor(colors[i]);
            canvas.drawArc(rectF,start*i,60,true,paint);
        }

        //绘制中心的圆
        paint.setColor(Color.RED);
        canvas.drawCircle(0,0,100,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(40);

        //获取文字宽度和高度
        Rect rectText = new Rect();
        paint.getTextBounds("start",0,5,rectText);
        int width = rectText.width();
        int height = rectText.height();
        canvas.drawText("start",-width/2,height/2,paint);


        //绘制描述信息
        RectF rectF1 = new RectF(-200, -200, 200, 200);
        for (int i = 0; i < 6; i++) {
           paint.setColor(Color.WHITE);
           Path path=new Path();
           path.addArc(rectF1,start*i+15,60);
           canvas.drawTextOnPath(desc[i],path,0,0,paint);
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

    }

    private void initAnimation() {
    }

    private void startAnima() {
        //是否在旋转状态
        isRote = true;
        double random = Math.random();
        RotateAnimation rotateAnimation = new RotateAnimation(0, (float) (720 * random),centerX,centerY);
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
                isRote=false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        startAnimation(rotateAnimation);

    }




    @Override
    public void onClick(View v) {
       if(!isRote){
           startAnima();
       }
    }

    //给一个随机的抽奖结果
    private void setRoundDom(){
        double random = Math.random();
        RotateAnimation rotateAnimation2=new RotateAnimation(0, (float) (360*random),centerX,centerY);
        rotateAnimation2.setDuration(100);
        rotateAnimation2.setFillAfter(true);
        startAnimation(rotateAnimation2);
    }
}
