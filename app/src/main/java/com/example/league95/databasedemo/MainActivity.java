package com.example.league95.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * A database demo app that demonstrates how to work with simple
 * databases. An introductory app.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //We surround everything with try catch.
        try {
            //Create the database object
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            //Create the table
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            /**
             * We can also delete users
             * Limit with delete doesn't work, but it works with SELECT
             */
            //sqLiteDatabase.execSQL("DELETE FROM users WHERE name IN" +
                    //"(SELECT name from users where name = 'Nisha' LIMIT 1)");
            /**
             * Let's say we want to update some of these values
             * change the age of someone etc..
             */
            //sqLiteDatabase.execSQL("UPDATE users SET age = 2 WHERE name = 'Rob' ");
            /**
             * Delete a certain user
             */
            //sqLiteDatabase.execSQL("DELETE FROM users WHERE age = '2' ");
            /**
             * Create a new Table to test with a primary KEY
             */
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR(26), age INT(3)," +
                    "id INTEGER PRIMARY KEY)");
            /**
             * Insert 4 values into the table now
             */
            //sqLiteDatabase.execSQL("INSERT INTO newUsers VALUES ('Rob', 17, 1)");
            //sqLiteDatabase.execSQL("INsERT INTO newUsers VALUES ('Franky', 22, 2)");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers VALUES ('Nisha', 17, 3)");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers VALUES ('rame', 13, 4)");
            /**
             * Delete a certain user with ID
             */
            sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE id = 1 ");


            //Create a cursor to fetch the values
            Cursor c = sqLiteDatabase.rawQuery("SELECT * from newUsers", null);
            //indices
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int id = c.getColumnIndex("id");
            //Get the very first value
            c.moveToFirst();
            while (c != null)
            {
                Log.i("Username: ", c.getString(nameIndex));
                Log.i("Age: ", String.valueOf(c.getInt(ageIndex)));
                Log.i("User ID: ", String.valueOf(c.getInt(id)));
                c.moveToNext();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
