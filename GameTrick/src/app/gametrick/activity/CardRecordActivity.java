package app.gametrick.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import app.gametrick.R;
import app.gametrick.data.CardListCarrier;
import app.gametrick.data.CardListFactory;
import app.gametrick.widget.CardListWidget;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CardRecordActivity extends Activity{
	
	public String Tag = "CardRecordActivity";
	
	@InjectView(R.id.cardpage)
	ViewPager  vp;
	
	@InjectView(R.id.cursor_text)
	TextView crTv;

	@InjectView(R.id.basecard_tv)
	TextView baseTv;
	
	@InjectView(R.id.stratagemcard_tv)
	TextView stratagemTv;
	
	@InjectView(R.id.equipmentcard_tv)
	TextView equipmentTv;

	private List<View> vList;
	private int crWidth;
	public final static int BASE = 0;
	public final static int STRATAGEM = 1;
	public final static int EQUIPMENT = 2;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(Tag,"come card record");
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.card_record);
		ButterKnife.inject(this);
		initPageView();
	}
		
	private void initPageView() {
		Log.d(Tag,"come in initPageView");
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();  
		display.getMetrics(dm);
		int screenWidth = dm.widthPixels;  

		crWidth = (int)((screenWidth / 3));
		
		vList = new ArrayList<View>();
	//	LayoutInflater lf = LayoutInflater.from(this);
		Intent intent = this.getIntent();
	
		String spn = intent.getStringExtra("schemeName");
		
		CardListCarrier cardList = new CardListFactory(this).getCardList(spn == null?"":spn);
		
		if(cardList == null ) {
			Log.d(Tag,"card list is null");
		}
		
		if( cardList != null) {
			Log.d(Tag,"card list is not null");
			vList.add(cardList.getBaseList());
			vList.add(cardList.getStratagemList());
			vList.add(cardList.getEquipmentList());
		}
		
		MainAdapter vpAdapter= new MainAdapter(vList);	
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(vpAdapter);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Reset").setIcon(android.R.drawable.ic_menu_save);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId())
		{
			case Menu.FIRST + 1:
				resetData();
				break;	
		}
		return true;	
	}
	
	private void resetData() {
		// TODO Auto-generated method stub		
		for(View i:vList) {
			((CardListWidget)i).resetProgress();
		}	
	}

	@OnClick(R.id.basecard_rl)
	public void onClickBaseCard() {
		vp.setCurrentItem(CardRecordActivity.BASE);
	}
	
	@OnClick(R.id.stratagemcard_rl)
	public void onClickStratagemCard() {
		vp.setCurrentItem(CardRecordActivity.STRATAGEM);
	}
	
	@OnClick(R.id.equipmentcard_rl)
	public void onClickEquipmentCard() {
		vp.setCurrentItem(CardRecordActivity.EQUIPMENT);
	}
	
	
	public class MainAdapter extends PagerAdapter implements OnPageChangeListener {
		
		private List<View> vList;
		private int pageSelected;
		
		MainAdapter(List<View> list) {
			vList = list;
			pageSelected = 0;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return vList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			// TODO Auto-generated method stub
			return view == obj;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(vList.get(position));
		}
		
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(vList.get(position), 0);
			return vList.get(position);
		}
		
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			
	
		}


		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub	
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			// TODO Auto-generated method stub			
			Log.d(Tag,"pageselected:"+ pageSelected + "position:" + position);
			//Log.d(Tag,"offset :" + positionOffset + "pix:" + positionOffsetPixels);					
			Animation animation;
			if(pageSelected == position){		
				if(positionOffset >= 0.5) {
					animation = new TranslateAnimation(crWidth * position, crWidth * (position + 1), 0, 0 );
					animation.setDuration(10);  
					animation.setFillAfter(true);
					crTv.startAnimation(animation);  
					pageSelected = position + 1;
					
				}
			}else if(pageSelected >= position){
				if(positionOffset <= 0.5){
					
					animation = new TranslateAnimation(crWidth * (position + 1), crWidth * position, 0, 0);
					animation.setDuration(10);  
					animation.setFillAfter(true);
					crTv.startAnimation(animation);  
					pageSelected = position ;
				}				
			}			
		}	
	}

}
