package io.github.nginth.anxietydiary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by nginther on 9/21/15.
 */
public class AnxietyLevelDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.anxiety_level_dialog, null);
        //set numberpicker attributes
        NumberPicker np = (NumberPicker) view.findViewById(R.id.anx_dialog_numpicker);
        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(true);

        builder.setView(view)
                .setPositiveButton(R.string.anx_dialog_save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.anx_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AnxietyLevelDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
