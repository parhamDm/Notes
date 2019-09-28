package com.parham.notes;

/**
 * Created by Parham on 25/06/2016.
 */
public class Note {

    public Note(String title,String text){
        this.title = title;
        this.text = text;
    }

    public String text;
    public String title;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
