package io.github.nginth.anxietydiary;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        String diaryEntry = getArguments().getString("diaryEntry");
        EditText editText = (EditText) view.findViewById(R.id.detail_EditText);
        editText.setText(diaryEntry);
        return view;
    }

}
