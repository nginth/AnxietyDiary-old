package io.github.nginth.anxietydiary;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by nginther on 8/17/15.
 */
public class DiaryEntryListAdapter extends CursorAdapter {
    private Context context;
    private boolean useList = true;

    public DiaryEntryListAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
        this.context = context;
    }
//    // Holder for the list items
//    // don't have to call findViewById each time
//    private class ViewHolder{
//        TextView titleText;
//    }

    //Used to inflate a new view and return it. Doesn't bind any data at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.diaryentry_list_item, parent, false);
    }

    //Binds all data to the given TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Find the fields to populate
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvSummary = (TextView) view.findViewById(R.id.tvSummary);
        //Extract data from cursor
        String date = cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntryContract.DiaryEntry.COLUMN_NAME_DATE));
        String summary = cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY));
        //Populate fields with data from cursor
        //TODO: format date
        tvDate.setText(date);
        tvSummary.setText(summary);
    }
}
