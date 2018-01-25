package com.example.tapos.magitionapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tapos.magitionapplication.models.Magic;

import java.util.ArrayList;
import java.util.List;



public class MagicOperation {
    private static final String[] allColumns = {
            DatabaseHandler.COLUMN_ID,
            DatabaseHandler.COLUMN_TITLE,
            DatabaseHandler.COLUMN_DESCRIPTION,
            DatabaseHandler.COLUMN_THUMBNAIL,

    };

    public static final String LOGTAG = "EMP_MNGMNT_SYS";
    private static String DB_FILE_NAME = "magic_file";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    public MagicOperation(Context context) {
        this.dbhandler = new DatabaseHandler(context);
    }
    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }

    public Magic addMagic(Magic magic){
        ContentValues values  = new ContentValues();

        values.put(DatabaseHandler.COLUMN_TITLE,magic.getTitle());
        values.put(DatabaseHandler.COLUMN_DESCRIPTION, magic.getDescription());
        values.put(DatabaseHandler.COLUMN_THUMBNAIL, magic.getThumbnail());

        long insertid = database.insert(DB_FILE_NAME,null,values);
        magic.setId(insertid);
        return magic;

    }

    // Getting single Magic
    public Magic getEmployee(long id) {

        Cursor cursor = database.query(DB_FILE_NAME,allColumns,DatabaseHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Magic e = new Magic(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)));
        // return Employee
        return e;
    }

    //// Getting All Magic
    public List<Magic> getAllEmployees() {

        Cursor cursor = database.query(DB_FILE_NAME,allColumns,null,null,null, null, null);

        List<Magic> magics = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Magic magic = new Magic();
                magic.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHandler.COLUMN_ID)));
                magic.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHandler.COLUMN_TITLE)));
                magic.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHandler.COLUMN_DESCRIPTION)));
                magic.setThumbnail(cursor.getInt(cursor.getColumnIndex(DatabaseHandler.COLUMN_THUMBNAIL)));

                magics.add(magic);
            }
        }
        // return All Employees
        return magics;
    }




    // Updating Magic
    public int updateEmployee(Magic magic) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.COLUMN_TITLE, magic.getTitle());
        values.put(DatabaseHandler.COLUMN_DESCRIPTION, magic.getDescription());
        values.put(DatabaseHandler.COLUMN_THUMBNAIL, magic.getThumbnail());


        // updating row
        return database.update(DB_FILE_NAME, values,
                DatabaseHandler.COLUMN_ID + "=?",new String[] { String.valueOf(magic.getId())});
    }

    // Deleting Magic
    public void removeEmployee(Magic magic) {

        database.delete(DB_FILE_NAME, DatabaseHandler.COLUMN_ID + "=" + magic.getId(), null);
    }
}
