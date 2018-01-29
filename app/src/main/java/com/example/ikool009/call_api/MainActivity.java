package com.example.ikool009.call_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private Button btn_login,btn_reset;
    private EditText user, pass;
    private String str_user, str_pass;
    private String url_api = "http://sourcework.co/bsru/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindwidget();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private  void bindwidget(){
        btn_login = (Button) findViewById(R.id.login);
        btn_reset = (Button) findViewById(R.id.reset);
        user = (EditText) findViewById(R.id.txt_user);
        pass = (EditText) findViewById(R.id.txt_pass);
    }

    private  void reset(){
        user.setText("");
        pass.setText("");
    }

    private void login (){
        str_user = user.getText().toString().trim();
        str_pass = pass.getText().toString().trim();

        if(str_user.equals("") || str_pass.equals("")){
            Toast.makeText(getApplicationContext(), "กรุณาป้อนให้ครบ", Toast.LENGTH_LONG).show();
        }else{
            Call_login call_login = new Call_login(str_user,str_pass);
            call_login.execute(url_api);
            try{
                final String reslut_server = call_login.get();

                Toast.makeText(getApplicationContext(),reslut_server,Toast.LENGTH_LONG).show();

                JSONObject jsonObject = new JSONObject(reslut_server);

                final boolean  status_login =  jsonObject.getBoolean("status");
                final String message = jsonObject.getString("message");
                if(status_login==true){
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "login ไม่สำเร็จ ", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){

            }
        }
    }




}
