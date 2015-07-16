package app.gametrick.activity;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import app.gametrick.R;
import app.gametrick.task.Scheme4ViewAsyncTask;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class MainActivity extends Activity{	
	
	public final String Tag = "MainActivity";
	public final static String SCHEME_NAME = "schemeName";
	
	public final static int SCHEME_CUSTOM = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainpage);
		ButterKnife.inject(this);
		refreshData();
		
		
	}
	
	private void refreshData() {
		new Scheme4ViewAsyncTask().execute(this); 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case MainActivity.SCHEME_CUSTOM:	
				
				if(resultCode == RESULT_OK) {
				
					String schemeName = data.getStringExtra("newSchemeName");
					Set<String> schemeList = null;		
					SharedPreferences sp = this.getSharedPreferences(Scheme4ViewAsyncTask.SP_SHARED, MODE_PRIVATE);			
					
					if(sp != null) {
						schemeList = sp.getStringSet(Scheme4ViewAsyncTask.SP_SHARED_INDEX, null);
						schemeList = schemeList == null?new HashSet<String>():schemeList;
					}				
					
					schemeList.add(schemeName);
	
					Editor ed = sp.edit();
					ed.clear().putStringSet(Scheme4ViewAsyncTask.SP_SHARED_INDEX, schemeList);
					ed.commit();			

					refreshData();
				}
				break;
			default:
				break;
		}
	}
	

	@OnClick(R.id.customscheme)
	void customscheme() {
		Log.d(Tag,"onclick add");
		Intent intent = new Intent(MainActivity.this, CustomCardScheme.class);
		startActivityForResult(intent, MainActivity.SCHEME_CUSTOM);
	}
	
	@OnItemClick(R.id.scheme_lv)
	void schemeLvOnItemClick(AdapterView<?> parent, View v, int position, long id) {
		String schmename = (String)parent.getItemAtPosition(position);
		Intent intent = new Intent(MainActivity.this, CardRecordActivity.class);
		intent.putExtra(MainActivity.SCHEME_NAME, schmename=="default"?"":schmename);
		startActivity(intent);
	}
	
	@OnItemLongClick(R.id.scheme_lv) 
	boolean onItemClick4AccountLv(AdapterView<?> parent, View view, int position, long id) {
		String data = (String) parent.getItemAtPosition(position);
		
		if(data == "default"){
			return true;
		}
		parent.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){
				@Override
				public void onCreateContextMenu(ContextMenu menu, View v,
						ContextMenuInfo menuInfo) {
					// TODO Auto-generated method stub		
					menu.add(0, 0, 0, "Edit");
					menu.add(0, 1, 0, "Delete");
				}			
		});
		return false;
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo itemInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		final String data = (String)((AdapterView<?>) findViewById(R.id.scheme_lv)).getItemAtPosition(itemInfo.position);
	
		switch (item.getItemId()) {
			case 0:
				Intent intent = new Intent(MainActivity.this, CustomCardScheme.class);	
				intent.putExtra(MainActivity.SCHEME_NAME, data);	
				startActivity(intent);
				break;
			case 1:
				AlertDialog.Builder dg =  new AlertDialog.Builder(this);
				dg.setTitle("Comfirm To Delete?");
                AlertDialog.Builder builder = dg.setPositiveButton("OK", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                SharedPreferences sp = MainActivity.this.getSharedPreferences(Scheme4ViewAsyncTask.SP_SHARED, MODE_PRIVATE);
                                Set<String> schemeList = sp.getStringSet(Scheme4ViewAsyncTask.SP_SHARED_INDEX, null);
                                try {
                                    schemeList.remove(data);
                                } catch (NullPointerException e) {
                                    sp.edit().clear().putStringSet(Scheme4ViewAsyncTask.SP_SHARED_INDEX, schemeList).apply();
                                    new Scheme4ViewAsyncTask().execute(MainActivity.this);
                                }
                            }
                        }
                    ).setNegativeButton("Cancel", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }

                );

                    dg.create();
                    dg.show();
                    break;
                    default:
                }

                return false;
	}
	
}