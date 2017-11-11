package com.example.listviewsqlite4;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

	private DBhelper dbhelper;
	private Context ourcontext;
	private SQLiteDatabase database;

	public SQLController(Context c) {
		ourcontext = c;
	}

	public SQLController open() throws SQLException {
		dbhelper = new DBhelper(ourcontext);
		database = dbhelper.getWritableDatabase();
		return this;

	}

	public void close() {
		dbhelper.close();
	}

	public void insertData(String name) {
		ContentValues cv = new ContentValues();
		cv.put(DBhelper.MEMBER_NAME, name);
		database.insert(DBhelper.TABLE_MEMBER, null, cv);
	}

	public Cursor readData() {
		String[] allColumns = new String[] { DBhelper.MEMBER_ID,
				DBhelper.MEMBER_NAME };
		Cursor c = database.query(DBhelper.TABLE_MEMBER, allColumns, null,
				null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public int updateData(long memberID, String memberName) {
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(DBhelper.MEMBER_NAME, memberName);
		int i = database.update(DBhelper.TABLE_MEMBER, cvUpdate,
				DBhelper.MEMBER_ID + " = " + memberID, null);
		return i;
	}

	public void deleteData(long memberID) {
		database.delete(DBhelper.TABLE_MEMBER, DBhelper.MEMBER_ID + "="
				+ memberID, null);
	}

}// outer class end
