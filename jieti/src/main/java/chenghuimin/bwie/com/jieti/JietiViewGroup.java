package chenghuimin.bwie.com.jieti;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JietiViewGroup extends ViewGroup {
    public JietiViewGroup(Context context) {
        this(context, null);
    }

    public JietiViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public JietiViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        for (int i = 0; i <5 ; i++) {
            initTextChild();
        }
    }

    private TextView initTextChild() {
        //创建textview对象
        TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.RED);
        textView.setText("123456");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(20);
        //添加到view
        this.addView(textView);
        return textView;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if(mode==MeasureSpec.AT_MOST){
            int measuredWidth=0;
            int measuredHeight=0;
            
            int resultWidth=0;
            int resultHeight=0;

            //获得子布局的数量
            int childCount = getChildCount();
            //循环
            for (int i = 0; i <childCount ; i++) {
                //获得下标
                View childAt = getChildAt(i);
                //测量
                measureChild(childAt,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
                //获得宽
                int measuredWidth1 = childAt.getMeasuredWidth();
                int measuredHeight1 = childAt.getMeasuredHeight();

                resultWidth+=measuredWidth1;
                resultHeight+=measuredHeight1;
            }

            setMeasuredDimension(resultWidth,resultHeight);
            return;
        }

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画颜色
        canvas.drawColor(Color.GRAY);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
      //布局 获得子布局的数量
        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
           //获得下标
            View childAt = getChildAt(i);
            childAt.measure(0,0);
            measureChild(childAt,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());

            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();

            childAt.layout(measuredWidth*i,measuredHeight*i,measuredWidth*(i+1),measuredHeight*(i+1));
        }


    }
}
