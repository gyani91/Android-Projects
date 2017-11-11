package com.example.listviewtest2;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewAndroidExample extends ListActivity  {

	TextView content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_android_example);
		
		
		content = (TextView)findViewById(R.id.output);
		
		//listView = (ListView) findViewById(R.id.list);
		String[] values = new String[] { "Android Example ListActivity", "Adapter implementation", "Simple List View With ListActivity",
		  "ListActivity Android", "Android Example", "ListActivity Source Code", "ListView ListActivity Array Adapter", "Android Example ListActivity" };

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, values);


		// Assign adapter to ListView
		setListAdapter(adapter); 
		
		
	}

	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);

		   // ListView Clicked item index
		   int itemPosition     = position;
		   
		   // ListView Clicked item value
		   String  itemValue    = (String) l.getItemAtPosition(position);
			  
		   content.setText("Click : \n  Position :"+itemPosition+"  \n  ListItem : " +itemValue);
		   
	}
	
	

}

