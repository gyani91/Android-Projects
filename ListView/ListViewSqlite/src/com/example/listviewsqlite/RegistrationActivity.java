package com.example.listviewsqlite;

 
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
 
public class RegistrationActivity extends Activity {
    RegistrationAdapter adapter;
    RegistrationOpenHelper helper;
    EditText fnameEdit, lnameEdit;
    Button submitBtn, resetBtn;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fnameEdit = (EditText) findViewById(R.id.et_fname);
        lnameEdit = (EditText) findViewById(R.id.et_lname);
        submitBtn = (Button) findViewById(R.id.btn_submit);
        resetBtn = (Button) findViewById(R.id.btn_reset);
        adapter = new RegistrationAdapter(this);
 
        submitBtn.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String fnameValue = fnameEdit.getText().toString();
                String lnameValue = lnameEdit.getText().toString();
                long val = adapter.insertDetails(fnameValue, lnameValue);
                // Toast.makeText(getApplicationContext(), Long.toString(val),
                // 300).show();
                finish();
            }
        });
        resetBtn.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                fnameEdit.setText("");
                lnameEdit.setText("");
            }
        });
 
    }
}