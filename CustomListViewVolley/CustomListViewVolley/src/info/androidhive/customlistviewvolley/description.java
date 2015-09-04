package info.androidhive.customlistviewvolley;

import java.util.ArrayList;




import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class description extends Activity {
	Button sendBtn;
	   EditText txtphoneNo;
	   
	private TextView txtResponse,t1,t2,t3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		 
		Bundle bundle = getIntent().getExtras();
		 String message = bundle.getString("message");
		 
		 
		 
		 
		//	ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
		txtResponse = (TextView) findViewById(R.id.textView1);
		t1 = (TextView) findViewById(R.id.textView2);
		t2 = (TextView) findViewById(R.id.textView3);
		t3 = (TextView) findViewById(R.id.textView4);
		 sendBtn = (Button) findViewById(R.id.button1);
	      txtphoneNo = (EditText) findViewById(R.id.editText1);
	      
		txtResponse.setText(message);
		
		  //  HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("map");
		   // Log.v("HashMapTest", hashMap.get("key"));
		 //   txtResponse.setText(hashMap.get("key"));
		sendBtn.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View view) {
	        	 String phoneNo = txtphoneNo.getText().toString();
	             String message = txtResponse.getText().toString();

	             try {
	                SmsManager smsManager = SmsManager.getDefault();
	                smsManager.sendTextMessage(phoneNo, null, message, null, null);
	                Toast.makeText(getApplicationContext(), "SMS sent.",
	                Toast.LENGTH_LONG).show();
	             } catch (Exception e) {
	                Toast.makeText(getApplicationContext(),
	                "SMS faild, please try again.",
	                Toast.LENGTH_LONG).show();
	                e.printStackTrace();
	             }
	         }
	      });
	
	}
	
	

}
