package app.gametrick.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import app.gametrick.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CardItemWidget extends LinearLayout{
	@InjectView(R.id.name)
	TextView cardnameTv;
	
	@InjectView(R.id.seekBar)
	SeekBar cardSkb;
	
	@InjectView(R.id.surplus)
	TextView cardsurplusTv;
	
	private int amount;
	private int surplus;
	
	public CardItemWidget(Context context) {
		super(context,null);
		LayoutInflater.from(context).inflate(R.layout.card_item, this);
		ButterKnife.inject(this,this);  
	}
	
	public CardItemWidget(Context context, AttributeSet attrs) {
		super(context,attrs);
		LayoutInflater.from(context).inflate(R.layout.card_item, this);
		ButterKnife.inject(this,this);  
		initAttrs(context,attrs);
	}
	
	public CardItemWidget(Context context,String name,int max) {
		super(context,null);
		LayoutInflater.from(context).inflate(R.layout.card_item, this);
		ButterKnife.inject(this,this);  
		initAttrs(name,max);
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		 TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.cardattrs);
		 String cardname = a.getString(R.styleable.cardattrs_cardname);
		 cardnameTv.setText(cardname);
		 amount = surplus = a.getInt(R.styleable.cardattrs_maxamount, 0);	 
		 cardSkb.setMax(amount);
		 cardSkb.setProgress(0);
		 cardsurplusTv.setText(String.valueOf(this.surplus));	 
	}
	
	private void initAttrs(String name,int max) {
		cardnameTv.setText(name);
	    amount = surplus = max;
	    cardSkb.setMax(amount);
		cardSkb.setProgress(0);
	    cardsurplusTv.setText(String.valueOf(this.surplus));	
	}

	public void reset () {
		surplus = amount;
		cardSkb.setProgress(0);
		cardsurplusTv.setText(String.valueOf(this.surplus));
	}
	
	@OnClick(R.id.used)
	public void usedCard() {
		if(this.surplus <= 0) return ;
		surplus--;
		cardSkb.setProgress(amount - surplus);
		cardsurplusTv.setText(String.valueOf(this.surplus));	 
	}
	
	@OnClick(R.id.unused)
	public void unusedCard() {
		if(this.surplus >= amount) return ;
		surplus++;
		cardSkb.setProgress(amount - surplus);
		cardsurplusTv.setText(String.valueOf(this.surplus));	 
	}
}
