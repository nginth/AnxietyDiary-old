package io.github.nginth.anxietydiary;

import android.provider.BaseColumns;

/**
 * Created by nginther on 8/20/15.
 * From http://developer.android.com/training/basics/data-storage/databases.html
 */
public final class DiaryEntryContract {
    public DiaryEntryContract(){} //empty constructor: do not instantiate

    public static abstract class DiaryEntry implements BaseColumns{
        //implements BaseColumns to get access to _ID, for compatability across Android libraries
        public static final String TABLE_NAME = "Diary";
        public static final String COLUMN_NAME_ENTRY_ID = _ID;
        public static final String COLUMN_NAME_DIARY_ENTRY = "diaryentry";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_ANX_LEVEL = "anxlevel";
    }
}
