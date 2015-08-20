package io.github.nginth.anxietydiary;

/**
 * Created by nginther on 8/17/15.
 */
public class DiaryEntryListItem {
    private String itemTitle;

    public DiaryEntryListItem(String title){
        itemTitle = title;
    }

    public String getItemTitle(){
        return itemTitle;
    }

    public void setItemTitle(String title){
        itemTitle = title;
    }

}
