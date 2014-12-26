package com.lisheng.wordwrap.view;

import java.util.Hashtable;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Rocky
 *
 * �Զ����е� LinearLayout
 */
public class AutoNewLineLinearLayout extends LinearLayout {
	private int mLeft, mRight, mTop, mBottom;
	private Hashtable map = new Hashtable();

	public AutoNewLineLinearLayout(Context context) {
		super(context);
	}

	public AutoNewLineLinearLayout(Context context, int horizontalSpacing,
			int verticalSpacing) {
		super(context);
	}

	public AutoNewLineLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int mWidth = MeasureSpec.getSize(widthMeasureSpec);
		int mCount = getChildCount();
		int mX = 0;
		int mY = 0;
		mLeft = 0;
		mRight = 0;
		mTop = 5;
		mBottom = 0;

		int j = 0;

		View lastview = null;
		for (int i = 0; i < mCount; i++) {
			final View child = getChildAt(i);

			child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			// 此处增加onlayout中的换行判断，用于计算所�?��高度
			int childw = child.getMeasuredWidth();
			int childh = child.getMeasuredHeight();
			mX += childw; // 将每次子控件宽度进行统计叠加，如果大于设定的高度则需要换行，高度即Top坐标也需重新设置

			Position position = new Position();
			mLeft = getPosition(i - j, i);
			mRight = mLeft + child.getMeasuredWidth();
			if (mX >= mWidth) {
				mX = childw;
				mY += childh;
				j = i;
				mLeft = 0;
				mRight = mLeft + child.getMeasuredWidth();
				mTop = mY + 5;
				// PS：如果发现高度还是有问题就得自己再细调了
			}
			mBottom = mTop + child.getMeasuredHeight();
			mY = mTop; // 每次的高度必须记�?否则控件会叠加到�?��
			position.left = mLeft;
			position.top = mTop + 3;
			position.right = mRight;
			position.bottom = mBottom;
			map.put(child, position);
		}
		setMeasuredDimension(mWidth, mBottom);
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(1, 1); // default of 1px spacing
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			Position pos = (Position)map.get(child);
			if (pos != null) {
				child.layout(pos.left, pos.top, pos.right, pos.bottom);
			} else {
				Log.i("MyLayout", "error");
			}
		}
	}

	private class Position {
		int left, top, right, bottom;
	}

	public int getPosition(int IndexInRow, int childIndex) {
		if (IndexInRow > 0) {
			return getPosition(IndexInRow - 1, childIndex - 1)
					+ getChildAt(childIndex - 1).getMeasuredWidth() + 8;
		}
		return getPaddingLeft();
	}
}