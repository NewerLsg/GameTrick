package app.gametrick.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import app.gametrick.R;
import app.gametrick.data.CardSchemeItemData;

public class CardGirdViewAdapter4Scheme extends BaseAdapter{

	private LayoutInflater lf;
	private List<CardSchemeItemData> cardList;
	
	public CardGirdViewAdapter4Scheme(Context context, ArrayList<CardSchemeItemData> cardList) {
			this.lf = LayoutInflater.from(context);
			this.cardList = cardList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.cardList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.cardList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return this.cardList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final CardSchemeItemData data = this.cardList.get(position);
		convertView = lf.inflate(R.layout.scheme_card_item, null);
		CheckBox cb =  (CheckBox) convertView.findViewById(R.id.card_scheme_id);
		cb.setChecked(data.getIsCheck());
		cb.setText(data.getName());
		cb.setTag(data);
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				data.setIsCheck(isChecked);
			}
			
		});
		return convertView;
	}

}
