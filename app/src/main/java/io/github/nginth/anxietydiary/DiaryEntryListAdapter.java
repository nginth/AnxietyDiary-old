package io.github.nginth.anxietydiary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nginther on 8/17/15.
 */
public class DiaryEntryListAdapter extends ArrayAdapter {
    private Context context;
    private boolean useList = true;

    public DiaryEntryListAdapter(Context context, List items){
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    // Holder for the list items
    // don't have to call findViewById each time
    private class ViewHolder{
        TextView titleText;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        DiaryEntryListItem item = (DiaryEntryListItem)getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            if(useList) {
                viewToUse = mInflater.inflate(R.layout.diaryentry_list_item, null);
            } else {
                viewToUse = mInflater.inflate(R.layout.diaryentry_grid_item, null);
            }

            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText(item.getItemTitle());
        return viewToUse;



    }


}
