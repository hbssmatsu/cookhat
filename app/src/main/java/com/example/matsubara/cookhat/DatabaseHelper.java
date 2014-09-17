package com.example.matsubara.cookhat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.util.Collections.copy;

/**
 * Created by matsubara on 2014/08/29.
 */
public class DatabaseHelper {

    private static final  String TAG = "DatabaseOpenHelper";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database file
    private static final String DATABASE_FILE_NAME = "CookBook.db";

    // default data
    private static final String DATABASE_DEFAULT_DATA = "CookBookOrignal.db";

    private DatabaseOpenHelper mDbOpenHelper;
    private SQLiteDatabase mDb;

    /*
     * constuct
     * @param context
     */
    public DatabaseHelper(Context context) {
        //Log.v("test", "read Class");
        mDbOpenHelper =  new DatabaseOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    /*
     *DB open
     */
    public void openWrite() {
        mDb = mDbOpenHelper.getWritableDatabase();
    }

    /*
     * DB close
     */
    public void close() {
        mDbOpenHelper.close();
    }

    /*
     *  DB open Helper
     */
    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        private boolean createDatabase = false;

        private Context context;
        private final File dbPath;



        /*
         * construct
         * @param context, dbName, factory, version
         */
        public DatabaseOpenHelper(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
            //Log.v("test", "DatabaseOpenHelperを読み込みします");
            this.context = context;
            this.dbPath = context.getDatabasePath(DATABASE_FILE_NAME);
        }

        /*
         * create database
         */
        @Override
        public synchronized SQLiteDatabase getReadableDatabase() {
            //Log.v("test", "test");
            SQLiteDatabase db = super.getReadableDatabase();
            if(createDatabase) {
                try {
                    db = copyDatabase(db);
                }catch (IOException e) {
                    Log.wtf(TAG, e);
                }
            }
            return db;
        }
        @Override
        public synchronized SQLiteDatabase getWritableDatabase() {
            //Log.v("test", "Writable test");
            SQLiteDatabase db = super.getWritableDatabase();
            if(createDatabase) {
                try {
                    db = copyDatabase(db);
                }catch (IOException e) {
                    Log.wtf(TAG,e);
                }
            }
            return db;
        }

        private SQLiteDatabase copyDatabase(SQLiteDatabase db) throws IOException {
            // db close
            db.close();
            //Log.v("test", "copyDatabase");

            // copy database
            InputStream input = context.getAssets().open(DATABASE_DEFAULT_DATA);
            OutputStream output = new FileOutputStream(this.dbPath);
            copy(input, output);

            createDatabase = false;

            // db open
            return super.getWritableDatabase();

        }

        private int copy(InputStream input, OutputStream output) throws IOException {
            byte[] buffer = new byte[1024*4];
            int count = 0;
            int n = 0;
            while (-1 != (n=input.read(buffer))) {
                output.write(buffer, 0, n);
                count += n;
            }
            return count;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            super.onOpen(db);

            // getWritableDatabase()時に起動するので初期が必要なことを通知
            this.createDatabase = true;

            // create the default table
            /*db.execSQL(
                    "CREATE TABLE table_recipeList ("
                            + "_id integer primary key autoincrement,"
                            + "name text not null, "
                            + "default_para integer"
                            + ")"
            );*/

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
