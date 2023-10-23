package com.ehappy.exactivity01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);        
       
        Button btnHome=(Button)findViewById(R.id.btnHome);  
        // 設定 button 的 myListener
        btnHome.setOnClickListener(myListner); 
        Toast.makeText(getApplicationContext(),"onCreate(2)",Toast.LENGTH_SHORT).show();
    }
    
	@Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(getApplicationContext(),"onStart(2)",Toast.LENGTH_SHORT).show();		
	} 
	
	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(getApplicationContext(),"onResume(2)",Toast.LENGTH_SHORT).show();		
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Toast.makeText(getApplicationContext(),"onRestart(2)",Toast.LENGTH_SHORT).show();		
	}

	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(getApplicationContext(),"onPause(2)",Toast.LENGTH_SHORT).show();		
	} 
	
	@Override
	protected void onStop() {
		super.onStop();
		Toast.makeText(getApplicationContext(),"onStop(2)",Toast.LENGTH_SHORT).show();		
	}
	
    @Override
	protected void onDestroy() {
    	super.onDestroy();
    	Toast.makeText(getApplicationContext(),"onDestroy(2)",Toast.LENGTH_SHORT).show();		
	}
    
    private Button.OnClickListener myListner=new Button.OnClickListener(){
    	public void onClick(View v){
    		finish();
    	}
    };    
}