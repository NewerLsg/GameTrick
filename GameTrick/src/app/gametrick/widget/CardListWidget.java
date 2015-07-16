package app.gametrick.widget;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import app.gametrick.R;

public class CardListWidget extends ScrollView  {
	final String Tag = "CardListWidget";
	private LinearLayout llayout;
	private String [] cardarray;
	private String [] cardarrayAmount;
	private List<CardItemWidget> cswList = new ArrayList<CardItemWidget>();

	public CardListWidget(Context context,String[] cardarray ,String[] cardarrayAmount) {
		super(context, null);
		// TODO Auto-generated constructor stub
		
		this.cardarray = cardarray;
		this.cardarrayAmount = cardarrayAmount;
		initList(context);		
		ButterKnife.inject(this, this);
	}
	
	public  CardListWidget(Context context) {
		super(context, null);	
		// TODO Auto-generated constructor stub
		ButterKnife.inject(this, this);
	}
	
	public  CardListWidget(Context context, AttributeSet attrs) {
		super(context, attrs);	
		// TODO Auto-generated constructor stub
		ButterKnife.inject(this, this);
	}
	
	private void initList(Context context) {
	
		llayout = (LinearLayout) LayoutInflater.from(context)
											   .inflate(R.layout.card_list, this)
											   .findViewById(R.id.ly);
		
		for(int i = 0;i < cardarray.length; i++ ) {
			Log.d(Tag,"i:" + i);
			CardItemWidget csw = new CardItemWidget(context,cardarray[i],Integer.valueOf(cardarrayAmount[i]));
			cswList.add(csw);
			llayout.addView(csw);
		}	
	}
	
	public void resetProgress() {
		for(CardItemWidget i:cswList) {
			i.reset();
		}
	}
}
