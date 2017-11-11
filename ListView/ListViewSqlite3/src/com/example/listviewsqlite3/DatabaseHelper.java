package com.example.listviewsqlite3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Subclass of the {@link SQLiteOpenHelper} that sets up the database for the
 * demo.
 * 
 * @author Kah
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, "CursorDemo", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS names ("
				+ BaseColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, first VARCHAR, last VARCHAR)");
		db.execSQL("INSERT INTO names (first, last) VALUES ('John', 'Doe')");
		db.execSQL("INSERT INTO names (first, last) VALUES ('James', 'Kirk')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Steps to upgrade the database for the new version ...
	}
}
