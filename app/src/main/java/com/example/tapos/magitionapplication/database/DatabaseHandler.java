package com.example.tapos.magitionapplication.database;


import android.content.ContentValues;
import android.content.Context;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import com.example.tapos.magitionapplication.R;
import com.example.tapos.magitionapplication.models.Album;
import com.example.tapos.magitionapplication.models.Magic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tapos on 1/22/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DB_FILE_NAME = "magic_file";
    private String TABLE_CREATE = "CREATE TABLE if not EXISTS magic_file ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " title TEXT, " +
            " description TEXT," +
            " thumbnail INTEGER, "+
            " UNIQUE (id) ON CONFLICT REPLACE )";
    private static final String DATABASE_NAME = "magics.db";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_THUMBNAIL = "thumbnail";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);

        insertDummyData(db);
    }
    //Update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == oldVersion + 1) {
            //	db.execSQL("ALTER TABLE student_info ADD COLUMN country VARCHAR(30)");
            db.execSQL("DROP TABLE if EXISTS  magic_file");
            db.execSQL(TABLE_CREATE);
        }
    }

    private void insertDummyData(SQLiteDatabase db) {
        List<Magic> allMagics = this.GenerateData();

        for (Magic magic:allMagics) {
            ContentValues values  = new ContentValues();

            values.put(COLUMN_TITLE,magic.getTitle());
            values.put(COLUMN_DESCRIPTION, magic.getDescription());
            values.put(COLUMN_THUMBNAIL, magic.getThumbnail());
            db.insert(DB_FILE_NAME,null,values);
        }

    }

    private List<Magic> GenerateData() {
        List<Magic> magicList = new ArrayList<>();
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10
        };


        Magic a = new Magic("Turning coffee into coins","hello", covers[0]);
        magicList.add(a);
        a = new Magic("The magic sword",
                "ingoghjj jj h ", covers[1]);
        magicList.add(a);

        a = new Magic("Pulling cards out of thin air", "test ", covers[2]);
        magicList.add(a);

        a = new Magic("Bullet catch", "hojo ojo oj", covers[3]);
        magicList.add(a);

        a = new Magic("Balloon swallowing", "The key to success here is tiny holes at the top of the balloon. The magician puts a balloon in his mouth and presses it against his tongue so the air releases. This creates the illusion that the magician is eating the balloon. But he needs to hurry after he inflates the balloon if he doesn’t want to get caught", covers[4]);
        magicList.add(a);

        a = new Magic("Run over by a truck", "It’s entirely based on the laws of physics. A real truck runs over the unprotected magician but doesn’t harm him. The key to the successful trick is a set of counterweights on the far side of the truck, not visible to the audience. The weights shift the balance of the truck, allowing the wheels on the lighter side to roll over the top of the magician.", covers[5]);
        magicList.add(a);

        a = new Magic("Cut and restored rope","A cut rope is magically back in one piece...but the magician actually never cuts the rope. He has a short piece of rope concealed in his hand and cuts that instead. Using sleight of hand and pulling the short rope up, he creates the illusion of restoring the rope only by magic.", covers[6]);
        magicList.add(a);

        a = new Magic("Mind reading", "All mind reading is based on human psychology, and a magician usually just guesses the most probable answer. When a magician performs the \"Gray Elephant From Denmark\" trick, he asks the audience certain math questions, the answer to which will always be the same. Try to perform it at home, and tell us in the comments section if you managed to trick your friends!", covers[7]);
        magicList.add(a);

        a = new Magic("Levitating objects", "To perform this trick, you need an extremely thin rubber band and a pencil to hide in your sleeve. Depending on the object you are going to levitate, the technique may differ, but the main idea will stay the same: fix the support so no one can see it, and turn on your charm", covers[8]);
        magicList.add(a);

        a = new Magic("Water suspended in midair", "The magician takes a glass, fills it with water, places a piece of cardboard on the glass, and then turns the glass upside down. The cardboard holds the water because of the vacuum inside the glass. But why doesn’t the water pour out when the magician takes away the cardboard? On the bottom of the glass, there’s a small hole that allows him to create the vacuum and hold or release the water. A transparent lid also helps the water to stay put.", covers[9]);
        magicList.add(a);

        return magicList;






    }




}
