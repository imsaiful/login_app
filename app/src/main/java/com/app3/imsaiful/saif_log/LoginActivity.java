package com.app3.imsaiful.saif_log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText uname;
    EditText upas;
    Button btn;
    TextView att;
    int attempt=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=(EditText)findViewById(R.id.uname);
        upas=(EditText)findViewById(R.id.upas);
        btn=(Button)findViewById(R.id.ulog);
        att=(TextView)findViewById(R.id.attempt);
        att.setText("Number of attempt: "+Integer.toString(attempt));
    }
    public void onLogin(View view)
    {

        String username=uname.getText().toString();
        String upass=upas.getText().toString();
        String type="login";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,username,upass);



    }
}
