package app.gametrick.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.gametrick.R;

public class CardSchemeListAdapter extends BaseAdapter{

	private List<String> schemeList;
	private LayoutInflater lf;
	
	private int[] bg={R.drawable.scheme_item_bg_zhaoyun,
				R.drawable.scheme_item_bg_diaochan};
	
	public CardSchemeListAdapter(Context context, List<String> schemeList) {
		this.lf = LayoutInflater.from(context);
		this.schemeList = schemeList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.schemeList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.schemeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		String  schemeName = this.schemeList.get(position);	
		convertView = lf.inflate(R.layout.scheme_list_item,null);
		LinearLayout ly = (LinearLayout) convertView.findViewById(R.id.scheme_main);
		ly.setBackgroundResource(bg[position%2]);
		TextView tx = (TextView)convertView.findViewById(R.id.scheme_name);	
		tx.setText(schemeName);	
		return convertView;
	}
}
