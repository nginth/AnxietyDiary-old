package io.github.nginth.anxietydiary;

/**
 * Created by nginther on 8/17/15.
 */
public class DiaryEntryListItem {
    private String itemTitle;
    private String itemDate;
    private int itemId;
    private String itemAnxietyLevel;

    public DiaryEntryListItem(int id, String title, String date, int anxietyLevel) {
        itemTitle = title;
    }

    public String getItemTitle(){
        return itemTitle;
    }

    public String getItemDate() {
        return itemDate;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemLevel() {
        return itemAnxietyLevel;
    }

    public void setItemTitle(String title){
        itemTitle = title;
    }

}
