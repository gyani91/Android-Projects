package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	//Handle presses on the action bar menu
    	switch (item.getItemId())
    	{
    		case R.id.action_search:
    			//openSearch();
    			// Show the Up button in the action bar.
    			TextView textView = new TextView(this);
    			textView.setTextSize(40);
    			textView.setText("SearchTest");
    			return true;
    		case R.id.action_settings:
    			//openSettings();
    			// Show the Up button in the action bar.
    			TextView textView2 = new TextView(this);
    			textView2.setTextSize(40);
    			textView2.setText("SettingsTest");
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    public void sendMessage(View view)
    {
    	//System.out.println("Hey !!");
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}
