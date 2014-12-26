package com.lisheng.wordwrap.Activity;

import com.lisheng.wordwrap.view.AutoNewLineLinearLayout;
import com.lisheng.wordwrap.view.AutoNewLineTextView;
import com.lisheng.wordwrap.view.AutoNewLineTextView.onTextViewClickListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;

/**
 * @author Rocky
 * 
 *         主界面
 */
public class MainActivity extends Activity implements onTextViewClickListener{

	private AutoNewLineLinearLayout linel_TestNewLine;

	private  EditText edit_Text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		linel_TestNewLine = (AutoNewLineLinearLayout) findViewById(R.id.linel_TestNewLine);
		edit_Text=(EditText)findViewById(R.id.edit_Text);
		
		for (int i = 0; i < 200; i++) {
			AutoNewLineTextView textView = new AutoNewLineTextView(
					MainActivity.this);
			textView.setTag(i);
			textView.setBackgroundResource(R.drawable.common_txtv_newline_selector);
			textView.setonTextViewClickListener(this);
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			textView.setPadding(15, 0, 17, 5);
			textView.setId(textView.hashCode());
			if (i % 5 == 0) {
				textView.setText("android开发工程师");
			} else if (i % 5 == 1) {
				textView.setText("挑水工");
			} else if (i % 5 == 2) {
				textView.setText("产品经理");
			} else if (i % 5 == 3) {
				textView.setText("送水");
			} else if (i % 5 == 4) {
				textView.setText("ios开发工程师");
			}
			linel_TestNewLine.addView(textView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTextViewClick(int pos, String text) {
		// TODO Auto-generated method stub
		edit_Text.setText(text);
	}

}
