package io.github.nginth.anxietydiary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class DetailActivity extends Activity {
    private String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null) {
            DetailActivityFragment details = new DetailActivityFragment();
            details.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.container, details)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveDiary(View view) {
        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(view.getContext());
        try {
            SQLiteDatabase db = dbHelper.getDB(view.getContext());
            //new value for diary entry column
            ContentValues values = new ContentValues();
            values.put(DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY, getIntent().getStringExtra("diaryEntry"));
            //Which row to update based on ID
            String selection = DiaryEntryContract.DiaryEntry.COLUMN_NAME_ENTRY_ID + " = ?";
            String[] selectionArgs = {getIntent().getStringExtra("diaryID")};

            db.update(
                    DiaryEntryContract.DiaryEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
            );

        } catch (Exception e) {
            Log.e(LOG_TAG, "Database read failed.", e);
        }
        Toast.makeText(view.getContext(), "Saved!", Toast.LENGTH_SHORT).show();
    }
}
