package io.github.nginth.anxietydiary;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String LOG_TAG = DiaryEntryFragment.class.getSimpleName();

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

    private Cursor cursor;

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

    public void onStart() {
        super.onStart();
        try {
            updateEntries();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Database read failed.", e);
        }

    }

    public void updateEntries() throws InterruptedException, ExecutionException {
//        String[] projection = {
//                DiaryEntryContract.DiaryEntry.COLUMN_NAME_ENTRY_ID,
//                DiaryEntryContract.DiaryEntry.COLUMN_NAME_DATE,
//                DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY,
//                DiaryEntryContract.DiaryEntry.COLUMN_NAME_ANX_LEVEL
//        };
        DiaryEntryDbHelper db = new DiaryEntryDbHelper(getActivity().getApplicationContext());

        cursor = db.getDB(getActivity().getApplicationContext()).rawQuery("SELECT * FROM " + DiaryEntryContract.DiaryEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        //if adapter already exists, swap old data with new data
        if (mAdapter != null) {
            ((CursorAdapter) mAdapter).swapCursor(cursor);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diaryentry, container, false);

        try {
            updateEntries();
            mAdapter =
                    new DiaryEntryListAdapter(
                            getActivity(),
                            cursor,
                            0);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Database read failed", e);
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
        cursor.moveToPosition(position);
        String diaryEntry = cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntryContract.DiaryEntry.COLUMN_NAME_DIARY_ENTRY));
        String diaryID = cursor.getString(cursor.getColumnIndexOrThrow(DiaryEntryContract.DiaryEntry.COLUMN_NAME_ENTRY_ID));
        Toast.makeText(getActivity(), position + " clicked!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DetailActivity.class)
                .putExtra("diaryEntry", diaryEntry).putExtra("diaryID", diaryID);
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

}
