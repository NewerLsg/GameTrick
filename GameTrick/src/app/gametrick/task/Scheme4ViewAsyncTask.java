package app.gametrick.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ListView;
import app.gametrick.R;
import app.gametrick.activity.MainActivity;
import app.gametrick.adapter.CardSchemeListAdapter;

public class Scheme4ViewAsyncTask extends AsyncTask<MainActivity ,Void, Void>{
	private MainActivity view;
	private List<String> schemeList; 
	public final static String SP_SHARED = "scheme";
	public final static String SP_SHARED_INDEX = "schemeList";
	
	@Override
	protected Void doInBackground(MainActivity... params) {
		// TODO Auto-generated method stub
		this.view =  params[0];
		schemeList = new ArrayList<String>();		
		schemeList.add("default");		
		SharedPreferences sp = this.view.getSharedPreferences(Scheme4ViewAsyncTask.SP_SHARED, Activity.MODE_PRIVATE);		
		Set<String> sl = sp.getStringSet(Scheme4ViewAsyncTask.SP_SHARED_INDEX, null);		
		if(sl != null){
			for(String name:sl) {
				schemeList.add(name);
			}
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result){		
		ListView lv= (ListView) this.view.findViewById(R.id.scheme_lv);
		lv.setAdapter(new CardSchemeListAdapter(this.view,this.schemeList));
	}
}
