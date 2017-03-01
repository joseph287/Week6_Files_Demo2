package com.example.mymac.week6_files_demo2;

import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {



    private EditText titleNote;
    private EditText bodyNote;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleNote=(EditText)findViewById(R.id.input_title_note_ma);
        bodyNote=(EditText)findViewById(R.id.input_body_note_ma);
        tv=(TextView)findViewById(R.id.tv_ma);
    }



    public void saveNote(View view){
        try{
            FileOutputStream outputStream=openFileOutput
                    (titleNote.getText().toString().replace("",""),MODE_APPEND);
            outputStream.write(bodyNote.getText().toString().getBytes());
            outputStream.close();
            Snackbar.make(view,"File Saved", Snackbar.LENGTH_SHORT).show();



        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }

    public void loadNote(View view){
        String lstofFilesInMemory="";
                String tempStr="";

        try{
            File filesDir=getFilesDir();
            File [] files=filesDir.listFiles();
            for(File file:files)
            lstofFilesInMemory+=file.getName()+"\n";

            FileInputStream inputStream=
                    openFileInput(titleNote.getText().toString().replace("",""));
            int c;
            while ( (c=inputStream.read())!=1){
                tempStr+=Character.toString((char)c);



            }
            inputStream.close();

        }catch (Exception e){}
        tv.setText(tempStr +"\n Files in Memory are:"+
        lstofFilesInMemory);

    }
}
