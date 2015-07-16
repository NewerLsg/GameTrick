package app.gametrick.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import app.gametrick.R;


public class SplashScreen extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);

		Handler hd = new Handler();			
		hd.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashScreen.this,MainActivity.class);
				startActivity(intent);
				SplashScreen.this.finish();
			}			
		}, 1500);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		Intent intent = new Intent(SplashScreen.this,MainActivity.class);
		startActivity(intent);
		return super.onTouchEvent(event);
	}
}
