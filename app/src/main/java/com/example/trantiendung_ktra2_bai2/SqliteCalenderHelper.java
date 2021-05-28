package com.example.trantiendung_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SqliteCalenderHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "LichThiDB.db";
    static final int DATABASE_VERSION = 1;

    public SqliteCalenderHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createSQL=" CREATE TABLE lichthi(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "ten TEXT," +
                "ngaythi TEXT," +
                "giothi TEXT," +
                "thiviet TEXT)";
        db.execSQL(createSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addOrder(LichThi lichthi)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten",lichthi.getTenmonhoc());
        contentValues.put("ngaythi",lichthi.getDate());
        contentValues.put("giothi",lichthi.getTime());
        contentValues.put("thiviet",lichthi.getType());
        SQLiteDatabase statement = getWritableDatabase();
        return statement.insert("lichthi",null,contentValues);
    }

    public List<LichThi> getAll()
    {
        List<LichThi> list = new ArrayList<>();

        SQLiteDatabase statement = getReadableDatabase();

        Cursor cursor = statement.query("lichthi",null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String ngaythi = cursor.getString(2);
            String giothi = cursor.getString(3);
            String thiviet = cursor.getString(4);
            LichThi lt = new LichThi(id,ten,ngaythi,giothi,thiviet);
            list.add(lt);
        }

        return list;
    }

    public List<LichThi> searchByName(String key)
    {
        List<LichThi> list = new ArrayList<>();

        String whereClause = "ten LIKE ?";
        String[] whereArgs= {"%"+key+"%"};

        SQLiteDatabase statement = getReadableDatabase();

        Cursor cursor = statement.query("lichthi",null,whereClause,whereArgs,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String ngaythi = cursor.getString(2);
            String giothi = cursor.getString(3);
            String thiviet = cursor.getString(4);
            LichThi lt = new LichThi(id,ten,ngaythi,giothi,thiviet);
            list.add(lt);
        }

        return list;
    }

    public int update(LichThi lichthi ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",lichthi.getId());
        contentValues.put("ten",lichthi.getTenmonhoc());
        contentValues.put("ngaythi",lichthi.getDate());
        contentValues.put("giothi",lichthi.getTime());
        contentValues.put("thiviet",lichthi.getType());
        SQLiteDatabase statement = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs={Integer.toString(lichthi.getId())};
        return  statement.update("lichthi",contentValues,whereClause,whereArgs);
    }
    public int delete(int id)
    {
        SQLiteDatabase statement = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        return statement.delete("lichthi",whereClause,whereArgs);
    }

}
