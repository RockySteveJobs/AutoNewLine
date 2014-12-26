package com.lisheng.wordwrap.view;

import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;



/**
 * @author Rocky
 * 
 * 适配于自动换行AutoNewLineLinearLayout的TextView 
 */
public class AutoNewLineTextView extends TextView implements OnClickListener {

	private onTextViewClickListener mClickListener;

	public static final int UNIQUE_FLAG = "AutoNewLineTextView".hashCode();

	public AutoNewLineTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		if (this.getTag() == null) {
			this.setTag(0);
		}
	}

	public void setonTextViewClickListener(onTextViewClickListener listener) {
		mClickListener = listener;
		this.setOnClickListener(AutoNewLineTextView.this);
	}

	public interface onTextViewClickListener {
		void onTextViewClick(int pos, String text);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mClickListener.onTextViewClick((Integer) v.getTag(), ((TextView) v)
				.getText().toString());
	}
}
