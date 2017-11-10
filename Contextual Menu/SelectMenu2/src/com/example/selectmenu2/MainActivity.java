package com.example.selectmenu2;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements MultiChoiceModeListener{

	 private String[] myfriendname=null;
	 private String[] myfriendnickname=null;
	 private int[] photo=null;
	 ListView listView=null;
	 Context contex=null;
	 MyListAdapter adapter=null;
	 private List<MyFriendsSDetails> list=new ArrayList<MyFriendsSDetails>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contex=this;
		listView = (ListView) findViewById(R.id.listview);
		
		myfriendname = new String[] { "Sunil Gupta", "Abhishek Tripathi","Sandeep Pal", "Amit Verma" };
        myfriendnickname = new String[] { "sunil android", "Abhi cool","Sandy duffer", "Budhiya jokar"};
        photo = new int[] { R.drawable.sunil, R.drawable.abhi, R.drawable.sandy, R.drawable.amit};
	    
        for(int index=0; index< myfriendname.length; index++){
        	MyFriendsSDetails details=new MyFriendsSDetails(myfriendname[index], myfriendnickname[index], photo[index]);
        	list.add(details);
        }
        
        adapter=new MyListAdapter(contex, list);
        listView.setAdapter(adapter);
        listView.setMultiChoiceModeListener(this);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE_MODAL);
	
	}
	@Override
	public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
		switch (arg1.getItemId()) {
		case R.id.delete:
			SparseBooleanArray selected = adapter.getSelectedIds();
			for (int i = (selected.size() - 1); i >= 0; i--) {
				if (selected.valueAt(i)) {
					MyFriendsSDetails selecteditem = adapter.getItem(selected.keyAt(i));
					adapter.remove(selecteditem);
				}
			}
			// Close CAB
			arg0.finish();
			return true;
			default:
				return false;
		}	
	  }
	@Override
	public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
		arg0.getMenuInflater().inflate(R.menu.main, arg1);
		return true;
		
	}
	@Override
	public void onDestroyActionMode(ActionMode arg0) {
		adapter.removeSelection();
	}
	@Override
	public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
		return false;
	}
	@Override
	public void onItemCheckedStateChanged(ActionMode arg0, int arg1, long arg2, boolean arg3) {
		
		final int checkedCount = listView.getCheckedItemCount();
		arg0.setTitle(checkedCount + " Selected");
		adapter.toggleSelection(arg1);
	}

}
