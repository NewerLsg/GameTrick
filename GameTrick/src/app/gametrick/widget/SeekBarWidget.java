package app.gametrick.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class SeekBarWidget extends SeekBar {

	public SeekBarWidget(Context context) {
		super(context, null);
		// TODO Auto-generated constructor stub
	}
	public SeekBarWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	  public boolean onTouchEvent(MotionEvent event) {
          // TODO Auto-generated method stub
          
          return false ;
  }
}
