package com.example.mastermind.connectservicephpexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mastermind on 20/3/2018.
 */

public class LoginActivity extends Activity {

    private EditText txtUsername;
    private EditText txtPassword;
    private AccessServiceAPI m_ServiceAccess;
    private ProgressDialog m_ProgressDialog;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txt_username_login);
        txtPassword = findViewById(R.id.txt_pwd_login);
        m_ServiceAccess = new AccessServiceAPI();
    }

    public void btnLoginClicked(View view){
        if("".equals(txtUsername.getText().toString())){
            txtUsername.setError("Username is required");
            return;
        }
        if("".equals(txtPassword.getText().toString())){
            txtPassword.setError("Password is required");
            return;
        }
        new TaskLogin().execute(txtUsername.getText().toString(), txtPassword.getText().toString());
    }

    public void btnGoRegisterClicked(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public class TaskLogin extends AsyncTask<String,Void,Integer>{
        private int id;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(LoginActivity.this,"Please wait...","Processing..",true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> param = new HashMap<>();
            param.put("action", "login");
            param.put("username", params[0]);
            param.put("password", params[1]);
            System.out.println(params[0] + params[1]);

            JSONObject jObjResult;
            try {
                jObjResult = m_ServiceAccess.convertJSONString2Obj(m_ServiceAccess.getJSONStringWithParam_POST(Common.SERVICE_API_URL1,param));
                id = (jObjResult.getInt("id"));

                return jObjResult.getInt("result");

            }catch (Exception e){
                return Common.RESULT_ERROR;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            m_ProgressDialog.dismiss();
            if(Common.RESULT_SUCCESS==integer){
                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),WelcomeActivity.class);
                i.putExtra("id", id);
                i.putExtra("username",txtUsername.getText().toString());
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(),"Login Fail",Toast.LENGTH_LONG).show();
            }
        }


    }
}


