package info.androidhive.customlistviewvolley;



import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private String[] jsonResponse;
	// Movies json url
	private static final String url = "http://mcafee.0x10.info/api/app?type=json";
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;
	ArrayList<HashMap<String, String>> arraylist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));
		arraylist = new ArrayList<HashMap<String, String>>();
		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					
			@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
		//				int j=0;
						for (int i = 0; i < response.length(); i++) {
							try {
								HashMap<String, String> map = new HashMap<String, String>();
								JSONObject obj = (JSONObject) response.get(i);
								Movie movie = new Movie();
								movie.setTitle(obj.getString("name"));
								movie.setThumbnailUrl(obj.getString("imagee"));
								movie.setRating((obj.getString("rating")));
								movie.setYear(obj.getString("last_update"));
								movie.setGenre(obj.getString("type"));

								// adding movie to movies array
								movieList.add(movie);
								
								String name = obj.getString("name");
								String image= obj.getString("imagee");
								String type = obj.getString("type");
								String price = obj.getString("price");
								String rating = obj.getString("rating");
								String users = obj.getString("users");
								String lastupdate = obj.getString("last_update");
								String descrip = obj.getString("description");
//								jsonResponse[j] = "Name: " +name+" Type: " + type+" Price: " + price +" Rating: " + rating +" Users: " + users +" LastUpdate: " + lastupdate + " Description: " + descrip + "\n";
								
								/*map.put("name",name);
								map.put("type",type);
								map.put("image",image);
								map.put("price",price);
								map.put("rating",rating);
								map.put("users",users);
								map.put("lastupdate",lastupdate);
								map.put("descrip",descrip);
								arraylist.add(map);*/
								//jsonResponse[i] += "Name: " +name+" Type: " + type+" Price: " + price +" Rating: " + rating +" Users: " + users +" LastUpdate: " + lastupdate + " Description: " + descrip + "\n";
	//							j++;

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		
		
		//secondre chanfe here
		 listView.setOnItemClickListener(new OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position,
	                    long id) {
	         
	            	String s =(String) ((TextView) view.findViewById(R.id.title)).getText();
	            	String s1 =(String) ((TextView) view.findViewById(R.id.rating)).getText();
	            	String s2 =(String) ((TextView) view.findViewById(R.id.releaseYear)).getText();
	            	String s3 =(String) ((TextView) view.findViewById(R.id.genre)).getText();
	            	//String s4 =(String) ((TextView) view.findViewById(R.id.list)).getText();
	           // 		for (HashMap<String, String> hashMap : arraylist) {
	    		//			   String val=(String)hashMap.get("name");
	    			//		   if(val.equals(s))
	    				//	  {
	    					//	   System.out.println(hashMap.keySet());
	    						//    for (String key : hashMap.keySet()) { 
	    						  //  System.out.println(hashMap.get(key));
	    						   // }
	    	//					}
	          //  		}

	 String st ="App_Name: "+s+" "+s1+" Last update: "+s2+" Type: "+s3;System.out.println(st);
	 Intent intent = new Intent(MainActivity.this, description.class);
	 intent.putExtra("message", st);
	 startActivity(intent);
	            }
	        });

		
		/*
		listView.setOnItemClickListener(new OnItemClickListener() {
			   public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				   Object item = adapter.getItemAtPosition(position);
				   System.out.println((String)item);
				  // View parentRow = (View) view.getParent();
				  //listView= (ListView) parentRow.getParent();
				   // position = listView.getPositionForView(parentRow);
				    //System.out.println(position);

				 //  System.out.println("Name: "+adapter.get(position).getName());
				   //String s =(String) ((TextView) view.findViewById(R.id.title)).getText();
				//   Toast toast = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
				  // for (HashMap<String, String> hashMap : arraylist) {
				//	   String val=(String)hashMap.get("name");
					//   if(val.equals(s))
					//   {
//						   Intent intent = new Intent(MainActivity.this, description.class);
						//   intent.putParcelableArrayListExtra("key", ArrayList<T extends Parcelable> arraylist);
					   //		hashMap.put("key", "value");
//					   intent.putExtra("map", "jsonResponse[position]");
					  // 	intent.putExtra("mylist", arraylist);   
//					   startActivity(intent);
						  // 
						 //Set<String> Message=hashMap.keySet();
						   //intent.putExtra(message, Message);
						   //startActivity(intent);   
					  // }
					    //System.out.println(hashMap.keySet());
					    //for (String key : hashMap.keySet()) { 
					    //System.out.println(hashMap.get(key));
				//	    }
					//}
				   
				  // System.out.println(s);
				   
			   }
	   });
		//change for click over*/
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
		
		//change for onclick listner
			
}
		
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
