package com.homelysoft.thenewboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";

	public static final String DATABASE_NAME = "HotOrNotdb";
	public static final String DATABASE_TABLE = "peopleTable";
	public static final int DATABASE_VERSION = 1;

	private DBHelper helper;
	private final Context myContext;
	private SQLiteDatabase database;

	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_HOTNESS + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);

		}

	}

	public HotOrNot(Context c) {
		myContext = c;
	}

	public HotOrNot open() throws SQLException {
		helper = new DBHelper(myContext);
		database = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		helper.close();
	}

	public long createEntry(String name, String hotness) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return database.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		Cursor c = database.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result += c.getString(iRow) + " " + c.getString(iName) + " "
					+ c.getString(iHotness) + "\n";

		}
		return result;

	}

	public String getName(long rowId) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		Cursor c = database.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + rowId, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			return c.getString(1); 
		}
		else
			return null;
	}

	public String getHotness(long rowId) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		Cursor c = database.query(DATABASE_TABLE, columns,
				KEY_ROWID + "=" + rowId, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			return c.getString(2); 
		}
		else
			return null;
	}

	public void updateEntry(long row, String name, String hotness) {
		ContentValues cv=new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		database.update(DATABASE_TABLE, cv, KEY_ROWID+"="+row, null);
		
	}

	public void deleteEntry(long row) {
		database.delete(DATABASE_TABLE, KEY_ROWID+"="+row, null);
		
	}

}
