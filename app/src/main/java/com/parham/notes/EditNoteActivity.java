package com.parham.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_note);
        Button ok= (Button) findViewById(R.id.okButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendNote();
            }
        });

        Intent data=getIntent();
        String t = data.getStringExtra("title");
        String n =data.getStringExtra("note");
        if(n!=null&&t!=null){
            TextView title= (TextView) findViewById(R.id.title);
            TextView note =(TextView) findViewById(R.id.note);
            note.setText(n);
            title.setText(t);
        }
    }
    private void sendNote(){
        TextView title= (TextView) findViewById(R.id.title);
        TextView note =(TextView) findViewById(R.id.note);
        myNote=new Note(title.getText().toString(),note.getText().toString());

        Intent data=getIntent();
        data.putExtra("title",myNote.getTitle());
        data.putExtra("note",myNote.getText());
        setResult(RESULT_OK, data);
        finish();
    }


    Note myNote;

    public void finish(){

        super.finish();

    }

}
