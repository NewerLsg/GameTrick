package app.gametrick.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CardCheckBoxItemWidget extends LinearLayout{
	
	private Context context;
	private int cardId;
	private String cardName;
	
	public CardCheckBoxItemWidget(Context context) {
		super(context, null);
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	public CardCheckBoxItemWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public CardCheckBoxItemWidget(Context context, int cardId, String cardName) {
		super(context,null);
		this.context = context;
		this.cardId = cardId;
		this.cardName = cardName;
	}
	
	private void initView() {
		
	} 
}
