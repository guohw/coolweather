package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherHelper extends SQLiteOpenHelper {

	/*province 建表
	 * 
	 * ***/
	public static final String CREATE_PROVINCE="create table  Province("+" id integer primary key autoincrement," +
			"province_name text," +
			"province_code text)";
	/*City 建表
	 * */
	public static final String CTEATE_CITY="create table City("+"id integer primary key autoincrement," +
			"city_name text," +
			"city_code," +
			"province_id integer)";
	/*Country
	 * 建表
	 * */
	public static final	String CTEATE_COUNTRY="create table Country("+" id integer primary key autoincrement," +
			" country_name text," +
			"country_code text," +
			"city_id integer)";
	
	
			
	public CoolWeatherHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CTEATE_CITY);
		db.execSQL(CTEATE_COUNTRY);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
