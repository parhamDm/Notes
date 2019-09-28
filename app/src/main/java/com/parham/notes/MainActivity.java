package com.parham.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.*;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //is used for intent and delivering massage
    private static int REQUEST_CODE;
    //the program has an arrayList of notes which user enters
    private ArrayList<Note> notes;
    //is the adapter of notes
    private NotesAdapter notesAdapter;
    //ListView of notes it shows how notes be shown
    private ListView lv;
    /**
     * when starting a new activity,it my refer to one of the listView Items,
     * so helpPosition show us which item in notes is going to change.
     * if user wants to add a new note its -1
     */
    int helpPosition;
    //if user wants to edit note it will be changed
    boolean action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = new ArrayList<Note>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        //setting action listener for fab
        //and adding new note
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //starting new activity for result
                Intent intent = new Intent(MainActivity.this ,EditNoteActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                helpPosition=-1;
                action=false;
            }
        });
        /*
         * creating a list view and setting onClickListener for it
         * if user clicks listView items a new activity will be shown
         * and user can edit selected note
         */
        notesAdapter = new NotesAdapter(this,notes);
        lv = (ListView)findViewById(R.id.myListView);
        lv.setAdapter(notesAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(position);
            }
        });
        registerForContextMenu(lv);
    }

    /**
     * open activity method opens  a new activity for result and user can edit it
     * @param position is position of listView which user clicked
     */
    private void openActivity(int position){
        Intent intent = new Intent(MainActivity.this ,EditNoteActivity.class);
        Note sender = notes.get(position);
        intent.putExtra("title",sender.getTitle());
        intent.putExtra("note",sender.getText());
        startActivityForResult(intent,REQUEST_CODE);
        helpPosition=position;
        action=true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.floating_menu , menu);
    }

    /**
     * after closing opened activity,this method will be called
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            //if two fields are empty
            if(data.getExtras().getString("title").equals("")&&
                    data.getExtras().getString("note").equals((""))) {
                if (helpPosition == -1) {
                    return;
                } else {

                    notes.remove(helpPosition);
                    lv.setAdapter(notesAdapter);
                    return;
                }
            }

            if(action) {
                notes.remove(helpPosition);
                action = false;
            }
            ArrayList<Note> help=new ArrayList<Note>();
            ;
            notes.add(0,new Note(data.getExtras().getString("title"),
                        data.getExtras().getString("note")));
            lv.setAdapter(notesAdapter);


        }
    }

    /**
     * when user longclicks to lv content menu will be shown
     * @param item is an item of content menu
     */
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.remove:
                notes.remove(info.position);
                lv.setAdapter(notesAdapter);
                return true;
            case R.id.edit:
                openActivity(info.position);
            default:
                return super.onContextItemSelected(item);
        }
    }

}
