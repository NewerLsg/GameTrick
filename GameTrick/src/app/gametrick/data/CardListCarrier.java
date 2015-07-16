package app.gametrick.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import app.gametrick.widget.CardListWidget;

public class CardListCarrier implements Parcelable{

	private  String Tag = "CardListCarrier";
	//List<CardListWidget> val;
	CardListWidget baseList;
	CardListWidget stratagemList;
	CardListWidget equipmentList;
	
	CardListCarrier(CardListWidget baseList, CardListWidget stratagemList ,CardListWidget equipmentList)
	{
		this.baseList = baseList;
		this.stratagemList = stratagemList;
		this.equipmentList = equipmentList;
	}
	
	CardListCarrier(Parcel source) {
		Log.d(Tag,"construct 1");
		this.baseList = (CardListWidget) source.readValue(CardListWidget.class.getClassLoader());
		Log.d(Tag,"construct 2");
		this.stratagemList =  (CardListWidget) source.readValue(CardListWidget.class.getClassLoader());
		this.equipmentList =  (CardListWidget) source.readValue(CardListWidget.class.getClassLoader());
	}
	
	public CardListWidget getBaseList() {	
		return this.baseList;
	}
	
	public CardListWidget getStratagemList() {
		return this.stratagemList;
	}
	
	public CardListWidget getEquipmentList() {
		return this.equipmentList;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		Log.d(Tag,"Error here 1");
		if(this.baseList == null) {
			Log.d(Tag,"basecard none.");
		}
		dest.writeValue(this.baseList);
		Log.d(Tag,"Error here 2");
		dest.writeValue(this.stratagemList);
		Log.d(Tag,"Error here 3");
		dest.writeValue(this.equipmentList);
	}
	
	public final static Parcelable.Creator<CardListCarrier> CREATOR = new Parcelable.Creator<CardListCarrier>() {
		 
	        @Override
	        public CardListCarrier createFromParcel(Parcel source) {
	            // TODO Auto-generated method stub
	            return new CardListCarrier(source);
	        }
	 
	        @Override
	        public CardListCarrier[] newArray(int size) {
	            // TODO Auto-generated method stub
	            return new CardListCarrier[size];
	        }
	 };
}
