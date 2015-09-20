package io.github.nginth.anxietydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nginther on 8/20/15.
 * From http://developer.android.com/training/basics/data-storage/databases.html
 */
public class DiaryEntryDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DiaryEntryDbHelper.class.getSimpleName();
    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "Diary.db";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + DiaryEntryContract.DiaryEntry.TABLE_NAME + " ("
            + DiaryEntryContract.DiaryEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DiaryEntryContract.DiaryEntry.COLUMN_NAME_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY + " TEXT, "
            + DiaryEntryContract.DiaryEntry.COLUMN_NAME_ANX_LEVEL + " INTEGER);";
    private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DiaryEntryContract.DiaryEntry.TABLE_NAME;

    public DiaryEntryDbHelper(Context context){
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(LOG_TAG, "SQL Create expression: " + SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }
}
