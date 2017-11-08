package com.xinlan.recyclerdecor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mListView;

    static final int OUT_SET_LEFT = 0;
    static final int OUT_SET_TOP = 0;
    static final int OUT_SET_RIGHT = 0;
    static final int OUT_SET_BOTTOM = 25;

    private static final String[] MSGS =
            new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven"};
    private static final Random RANDOM = new Random();

    private static String randomText() {
        StringBuilder builder = new StringBuilder();
        int words = RANDOM.nextInt(15) + 10;
        for (int i = 0; i < words; i++) {
            builder.append(MSGS[RANDOM.nextInt(7)]).append(" ");
        }
        return builder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (RecyclerView) findViewById(R.id.list);

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add(randomText());
        }

        mListView.setAdapter(new Adapter(list));
        mListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mListView.setItemAnimator(new DefaultItemAnimator());
//        mListView.addItemDecoration(new LinearLayoutColorDivider(this.getResources() , R.color.colorPrimaryDark ,
//                64,LinearLayoutManager.VERTICAL));
        mListView.addItemDecoration(new NewDecoration());
    }

    public static class NewDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint;
        private int mDecorSize = 100;
        private Paint mTextPaint;

        public NewDecoration() {
            mPaint = new Paint();
            mPaint.setColor(Color.BLUE);

            mTextPaint = new Paint();
            mTextPaint.setTextAlign(Paint.Align.LEFT);
            mTextPaint.setColor(Color.BLACK);
            mTextPaint.setTextSize(40);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, mDecorSize, 0, 0);
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = parent.getChildAt(i);
                int left = childView.getLeft();
                int top = childView.getTop() - mDecorSize;
                drawContent(canvas, left, top, childView.getWidth());
            }//end for i
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            final int childCount = parent.getChildCount();

            if (childCount > 2) {
                View headView = parent.getChildAt(0);

                View childView = parent.getChildAt(1);
                int left = childView.getLeft();
                int top = childView.getTop() - mDecorSize;
                if (top <= mDecorSize) {
                    //drawContent(canvas, left, headView.getTop() - mDecorSize + headView.getHeight(), childView.getWidth());
                    drawContent(canvas, left, top - mDecorSize, childView.getWidth());
                    drawContent(canvas, left, top, childView.getWidth());
                } else {
                    drawContent(canvas, left, 0, childView.getWidth());
                }
            }


            System.out.println("on draw over!");
            //canvas.drawText("你好 世界" , 0 , 100 , mTextPaint);
        }

        private void drawContent(Canvas canvas, int left, int top, int width) {
            canvas.drawRect(left, top, left + width, top + mDecorSize, mPaint);
            canvas.drawText("你好 世界", left, top + 40, mTextPaint);
        }
    }//end class

}//end class
