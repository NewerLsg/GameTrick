package app.gametrick.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import app.gametrick.R;
import app.gametrick.widget.CardListWidget;

public class CardListFactory {
	
	private String Tag = "CardListFactory";
	
	//private  CardListFactory singleInstance;
	public final static int BASECARDLIST = 0;
	public final static int STRATAGEMCARDLIST = 1;
	public final static int EQUIPMENTCARDLIST = 2; 
	public final static String BASEINDEX = "basecard";
	public final static String STRATAGEMINDEX = "stratagemcard";
	public final static String EQUIPMENTINDEX = "equipmentcard";
	private Context context;
	private String schemeName;
	
	public  CardListFactory(Context context) {
		this.context = context;
	}
	
	public  CardListFactory(Context context,String schemeName) {
		this.context = context;
		this.schemeName = schemeName;
	}
	
	public CardListCarrier getCardList(String sharedPrefrenceName) {
		this.schemeName = sharedPrefrenceName;	
		CardListWidget baseList = getViewByCategory(CardListFactory.BASECARDLIST) ;
		CardListWidget stratagemList =  getViewByCategory(CardListFactory.STRATAGEMCARDLIST) ;
		CardListWidget equipmentList =  getViewByCategory(CardListFactory.EQUIPMENTCARDLIST) ;	
		return new CardListCarrier(baseList,stratagemList,equipmentList);
	}
	
	private CardListWidget getViewByCategory(int category)
	{
		String indexName = "";
		int nameArrayRID = 0 ,amountArrayRID = 0;
		
		switch(category){
			case CardListFactory.BASECARDLIST:
				indexName = CardListFactory.BASEINDEX;
				nameArrayRID = R.array.BaseCard;
				amountArrayRID = R.array.BaseCardAmout;
				break;
			case CardListFactory.STRATAGEMCARDLIST:
				indexName = CardListFactory.STRATAGEMINDEX;
				nameArrayRID = R.array.StratagemCard;
				amountArrayRID = R.array.StratagemCardAmount;
				break;
			case CardListFactory.EQUIPMENTCARDLIST:
				indexName = CardListFactory.EQUIPMENTINDEX;
				nameArrayRID = R.array.EquipmentCard;
				amountArrayRID = R.array.EquipmentCardAmount;
				break;
			default:
				break;
		}

		try {		
			
			String[] nameArray,amountArray,tempName,tempAmout; 				
			nameArray = this.context.getResources().getStringArray(nameArrayRID);
			amountArray = this.context.getResources().getStringArray(amountArrayRID);
			
			if(this.schemeName == null || this.schemeName.equals("")) {
				tempName = nameArray;
				tempAmout = amountArray;
			}else{
				SharedPreferences  sp = context.getSharedPreferences(this.schemeName, Context.MODE_PRIVATE);
				JSONArray jsArray = new JSONArray(sp.getString(indexName, ""));
			
				List<Integer> idList = new ArrayList<Integer>();
				
				for(int i = 0;i < jsArray.length(); i++) {
					if(jsArray.getBoolean(i)) {
						idList.add(Integer.valueOf(i));
					}
				}			
				tempName = new String[idList.size()]; 
				tempAmout = new String[idList.size()]; 					
				for(int i = 0;i < idList.size();i++) {
					tempName[i] = nameArray[idList.get(i)];
					tempAmout[i] = amountArray[idList.get(i)];
				}
				
			}
			Log.d(Tag,"Create list done");
			return  new CardListWidget(this.context,tempName,tempAmout);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;	
	}
}
