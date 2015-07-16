package app.gametrick.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import app.gametrick.R;
import app.gametrick.adapter.CardGirdViewAdapter4Scheme;
import app.gametrick.data.CardListFactory;
import app.gametrick.data.CardSchemeItemData;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class CustomCardScheme extends Activity{
	
	@InjectView(R.id.basecardgv)
	GridView basecardgv;
	
	@InjectView(R.id.stratagemcardgv)
	GridView stratagemcardgv;
	
	@InjectView(R.id.equipmentcardgv)
	GridView equipmentcardgv;
	
	private String schemeName;
	private final String Tag = "CustomCardScheme";
	
	private List<CardSchemeItemData> baseList;
	private List<CardSchemeItemData> stratatemList;
	private List<CardSchemeItemData> equipmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scheme_custom);		
		ButterKnife.inject(this);
		initData();
	}
	
	private void initData() {
		Log.d(Tag,"come initData");
		Intent intent = getIntent();
		this.schemeName = intent.getStringExtra(MainActivity.SCHEME_NAME);
		
		this.baseList = cardListInit(CardListFactory.BASECARDLIST);
		this.stratatemList = cardListInit(CardListFactory.STRATAGEMCARDLIST);
		this.equipmentList = cardListInit(CardListFactory.EQUIPMENTCARDLIST);
		
		basecardgv.setAdapter(new CardGirdViewAdapter4Scheme(this, (ArrayList<CardSchemeItemData>)this.baseList));
		stratagemcardgv.setAdapter(new CardGirdViewAdapter4Scheme(this, (ArrayList<CardSchemeItemData>)this.stratatemList));
		equipmentcardgv.setAdapter(new CardGirdViewAdapter4Scheme(this, (ArrayList<CardSchemeItemData>)this.equipmentList));				
	}
	
	private  List<CardSchemeItemData> cardListInit(int type) {
	
		Log.d(Tag,"come in cardListInit type:" + type);
		
		String indexName = "";
		int nameArrayRID = 0;
		
		switch(type){
			case CardListFactory.BASECARDLIST:
				indexName = CardListFactory.BASEINDEX;
				nameArrayRID = R.array.BaseCard;			
				break;
			case CardListFactory.STRATAGEMCARDLIST:
				indexName = CardListFactory.STRATAGEMINDEX;
				nameArrayRID = R.array.StratagemCard;	
				break;
			case CardListFactory.EQUIPMENTCARDLIST:
				indexName = CardListFactory.EQUIPMENTINDEX;
				nameArrayRID = R.array.EquipmentCard;
				break;
			default:
				break;
		}
		
		String[] resourceArray = this.getResources().getStringArray(nameArrayRID);
 		List<CardSchemeItemData> cardList =  new ArrayList<CardSchemeItemData>();

 		for(int i = 0 ;i < resourceArray.length; i++)  {			
 			CardSchemeItemData data = new CardSchemeItemData(i, resourceArray[i], true);
 			cardList.add(data);
 		}
 		
 		Log.d(Tag,"init all data");
 	
 		if(this.schemeName != null && !this.schemeName.equals("")){
 			try {	
				JSONArray jsArray = new JSONArray(this.getSharedPreferences(schemeName, Context.MODE_PRIVATE).getString(indexName, ""));		
				
				for(int i = 0; i<jsArray.length(); i++) {
					cardList.get(i).setIsCheck(jsArray.getBoolean(i));
				}		
 			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
 			}
 		}
 		
 		Log.d(Tag,"init complete!.");
 		return  cardList;
	}
	
	@OnClick(R.id.save_scheme)
	public void save_scheme_onClick() {
		
		final JSONArray base = new JSONArray();	
		for(int i = 0;i < this.baseList.size();i++){
			CardSchemeItemData data = (CardSchemeItemData)basecardgv.getItemAtPosition(i);
			base.put(data.getIsCheck());
		}

		final JSONArray stratagem = new JSONArray();
		for(int i = 0;i < this.stratatemList.size();i++){
			CardSchemeItemData data = (CardSchemeItemData)stratagemcardgv.getItemAtPosition(i);
			stratagem.put(data.getIsCheck());
		}

		final JSONArray equipment = new JSONArray();
		for(int i = 0;i < this.equipmentList.size();i++){
			CardSchemeItemData data = (CardSchemeItemData)equipmentcardgv.getItemAtPosition(i);
			equipment.put(data.getIsCheck());
		}
		
		if(CustomCardScheme.this.schemeName != null && !CustomCardScheme.this.schemeName.equals("")){
			Editor editor = CustomCardScheme.this.getSharedPreferences(CustomCardScheme.this.schemeName,
					Context.MODE_PRIVATE).edit();
			editor.clear();
			editor.putString(CardListFactory.BASEINDEX, base.toString());
			editor.putString(CardListFactory.STRATAGEMINDEX, stratagem.toString());
			editor.putString(CardListFactory.EQUIPMENTINDEX, equipment.toString());		
			editor.commit();	
			CustomCardScheme.this.finish();			
		}else{
			final EditText ed = new EditText(this);
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setTitle("new?");
			ad.setView(ed);
			ad.setPositiveButton("OK", new OnClickListener(){
	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Editor editor = CustomCardScheme.this.getSharedPreferences(ed.getText().toString(),
															Context.MODE_PRIVATE).edit();
					
					editor.putString(CardListFactory.BASEINDEX, base.toString());
					editor.putString(CardListFactory.STRATAGEMINDEX, stratagem.toString());
					editor.putString(CardListFactory.EQUIPMENTINDEX, equipment.toString());		
					editor.commit();	
					
					Intent intent = new Intent();
					intent.putExtra("newSchemeName", ed.getText().toString());
					CustomCardScheme.this.setResult(RESULT_OK, intent);
					CustomCardScheme.this.finish();
				}
			});
			
			ad.setNegativeButton("Cancel", new OnClickListener(){
	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			});	
			ad.create();
			ad.show();
		}
	}
	
	@OnClick(R.id.cancle_scheme)
	public void cancle_scheme_onclick(){
		this.finish();
	}
	
	@OnItemClick(R.id.basecardgv)
	public void basecardGvItemClick(AdapterView<?> parent, View v, int position, long id) {
		CardSchemeItemData data = (CardSchemeItemData)parent.getItemAtPosition(position);
		this.baseList.get(position).setIsCheck(data.getIsCheck());
		Log.d(Tag,"positon:" + data.getIsCheck() + " id :"+ id);
	}
}
