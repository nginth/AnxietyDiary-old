package io.github.nginth.anxietydiary;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class DiaryEntryFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    private List diaryEntryListItemList;

    // TODO: Rename and change types of parameters
    public static DiaryEntryFragment newInstance(String param1, String param2) {
        DiaryEntryFragment fragment = new DiaryEntryFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DiaryEntryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Cursor updateEntries() throws InterruptedException, ExecutionException {
        DiaryEntryDbHelper db = new DiaryEntryDbHelper(getActivity().getApplicationContext());
        FetchDB getDB = new FetchDB();

        String[] projection = {
                DiaryEntryContract.DiaryEntry.COLUMN_NAME_ENTRY_ID,
                DiaryEntryContract.DiaryEntry.COLUMN_NAME_DATE,
                DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY,
                DiaryEntryContract.DiaryEntry.COLUMN_NAME_ANX_LEVEL
        };

        final String ROW_LIMIT = "1000";
        Cursor c = getDB.execute(db).get().rawQuery("SELECT  * FROM " + DiaryEntryContract.DiaryEntry.TABLE_NAME, null);

        c.moveToFirst();
        return c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diaryentry, container, false);
        diaryEntryListItemList = new ArrayList<DiaryEntryListItem>();

        try {
            Cursor cursor = updateEntries();
            mAdapter =
                    new DiaryEntryListAdapter(
                            getActivity(),
                            cursor,
                            0);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error fetching entries from databae");
            e.printStackTrace();
        }


        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);


        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DiaryEntryListItem item = (DiaryEntryListItem) this.diaryEntryListItemList.get(position);
        Toast.makeText(getActivity(), item.getItemTitle() + " clicked!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DetailActivity.class)
                .putExtra(Intent.EXTRA_TEXT, item.getItemTitle());
        startActivity(intent);
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

    //Async because getWritableDatabase can take a while on first creation
    private class FetchDB extends AsyncTask<DiaryEntryDbHelper, Void, SQLiteDatabase> {
        String LOG_TAG = FetchDB.class.getSimpleName();

        protected SQLiteDatabase doInBackground(DiaryEntryDbHelper... dbs) {
            return dbs[0].getWritableDatabase();
        }

        protected void onPostExecute(SQLiteDatabase result) {
            if (result != null) {

                ContentValues values = new ContentValues();
                values.put(DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY, "I feel blessed I feel blessed I feel blessed I feel blessed I feel blessed I feel blessed");
                values.put(DiaryEntryContract.DiaryEntry.COLUMN_NAME_ANX_LEVEL, 1);

                long id;
                id = result.insert(DiaryEntryContract.DiaryEntry.TABLE_NAME, "null", values);
            }
        }
    }

}
