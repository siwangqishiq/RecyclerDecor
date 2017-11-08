package com.xinlan.recyclerdecor;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by panyi on 2017/10/13.
 */

public class LinearLayoutColorDivider extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private final int mSize;
    private final int mOrientation;

    public LinearLayoutColorDivider(Resources resources, int color, int size, int orientation) {
        mDivider = new ColorDrawable(resources.getColor(color));
        mSize = size;
        mOrientation = orientation;
    }

    /**
     * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
     * Any content drawn by this method will be drawn before the item views are drawn,
     * and will thus appear underneath the views.
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView
     */
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left;
        int right;
        int top;
        int bottom;
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                left = child.getRight() + params.rightMargin;
                right = left + mSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        } else {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mSize;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }


    /**
     * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
     * Any content drawn by this method will be drawn after the item views are drawn
     * and will thus appear over the views.
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView.
     */
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Rect r = new Rect(0, 0, 200, 200);
        Paint p = new Paint(Color.RED);

        int top;
        final int childCount = parent.getChildCount();
        System.out.println("count = "+childCount);
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            //top = child.getBottom() + params.bottomMargin;
            r.set(0, child.getBottom(), 200, child.getBottom() + 20);
            c.drawRect(r, p);
        }
//        int left;
//        int right;
//        int top;
//        int bottom;
//        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
//            top = parent.getPaddingTop();
//            bottom = parent.getHeight() - parent.getPaddingBottom();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount - 1; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                left = child.getRight() + params.rightMargin;
//                right = left + mSize;
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        } else {
//            left = parent.getPaddingLeft();
//            right = parent.getWidth() - parent.getPaddingRight();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount - 1; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                top = child.getBottom() + params.bottomMargin;
//                bottom = top + mSize;
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
    }


    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, mSize, 0);
        } else {
            outRect.set(0, 0, 0, mSize);
        }
    }
}//end class
