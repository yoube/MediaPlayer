package com.example.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;


public class MainActivity extends Activity {

	private ListView mListView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,VideoActivity.class));
        finish();
        initView();
        //…Ë÷√∫·∆¡
        Log.i("wwww","onCreate");
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    	
    	Log.i("wwww","onConfigurationChanged");
    }

	private void initView() {
		View v = findViewById(R.id.textBut);
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(getResources().getConfiguration().orientation== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}else{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
			}
		});
//		mListView.setAdapter(adapter);
	}

}
