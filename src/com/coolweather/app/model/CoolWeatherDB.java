package com.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	/*DATABASE NAME
	 * 
	 */
	public static final String DB_NAME="cool_weather";
	/*���ݿ�汾
	 * 
	 */
	public static final int VSESION=1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	/*
	 * ���췽��˽�л�
	 */
	private CoolWeatherDB(Context context){
		CoolWeatherHelper dbHelper=new CoolWeatherHelper(context, DB_NAME, null, VSESION);
		db=dbHelper.getWritableDatabase();
	}
	/*
	 * ��ȡCoolWeather��ʵ��
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
		
	}
	/*
	 * ��province���ݴ洢�����ݿ���
	 */
	public void saveProvince(Province province){
		if(province!=null){
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Provice", null, values);
		}
	}
	/*
	 * �����ݿ��ж�ȡȫ��ʡ����Ϣ
	 */
	public List<Province> loadProvinces(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
		
	}
	/*
	 * ��city���ݴ洢�����ݿ���
	 */
	public void savaCity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null,values);
		}
	}
	/*
	 * �����ݿ��ж�ȡcity��Ϣ
	 */
	public List<City> loadCities(int provinceId)
	{
		List<City> list=new ArrayList<City>();
		Cursor cursor=db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
		
	}
	/*
	 * ��country���ݴ洢�����ݿ�
	 */
	public void saveCountry(Country country){
		if(country!=null){
			ContentValues values=new ContentValues();
			values.put("country_name", country.getCountryName());
			values.put("country_code", country.getCountryCode());
			values.put("city_id", country.getCityId());
			db.insert("Country", null, values);
		}
	}
	/*
	 *  ��ȡcountry������
	 */
	public List<Country> loadCountry(int CityId){
		List<Country> list=new ArrayList<Country>();
		Cursor cursor=db.query("Country", null, "city_id", new String[]{String.valueOf("CityId")}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Country country=new Country();
				country.setCityId(CityId);
				country.setId(cursor.getInt(cursor.getColumnIndex("id")));
				country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
				country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
				list.add(country);
			}while(cursor.moveToNext());
		}
		return list;
	}
}

