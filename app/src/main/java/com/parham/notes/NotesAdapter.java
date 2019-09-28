package com.parham.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Parham on 25/06/2016.
 */
public class NotesAdapter extends ArrayAdapter<Note> {

    public NotesAdapter(Context context, ArrayList<Note> notes){
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get data for showing to user
        Note n = getItem(position);

        //current view must not been used
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_linear_layout, parent, false);
        }

        // get data for view
        TextView noteTitle = (TextView)convertView.findViewById(R.id.noteTitle);
        TextView noteText = (TextView)convertView.findViewById(R.id.noteText);
        //and set data and limitations
        noteTitle.setMaxLines(1);
        noteText.setMaxLines(1);
        noteTitle.setText(n.title);
        if(n.getTitle().equals(""))
            noteTitle.setText("<بدون عنوان>");
        noteText.setText(n.text);
        // Return the completed view to screen
        return convertView;
    }

}
