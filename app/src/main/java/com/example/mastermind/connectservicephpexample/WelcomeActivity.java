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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mastermind on 20/3/2018.
 */

public class WelcomeActivity extends Activity{
    TextView tvWelcome;
    private EditText txt_name;
    private EditText txt_description;
    private EditText txt_category;
    private EditText txt_image;
    private EditText txt_startdate;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvWelcome = findViewById(R.id.tv_welcome);
        tvWelcome.setText("Welcome: " +  getIntent().getStringExtra("username"));
        txt_name = findViewById(R.id.txt_name);
        txt_description = findViewById(R.id.txt_description);
        txt_category = findViewById(R.id.txt_category);
        txt_image = findViewById(R.id.txt_image);
        txt_startdate = findViewById(R.id.txt_startdate);
        m_AccessServiceAPI = new AccessServiceAPI();

    }

    public void btnAddSeminarClicked(View view){
        if("".equals(txt_name.getText().toString())){
            txt_name.setError("Name is required");
            return;
        }
        if("".equals(txt_description.getText().toString())){
            txt_description.setError("Description is required");
            return;
        }
        if("".equals(txt_category.getText().toString())){
            txt_category.setError("Category is required");
            return;
        }
        if("".equals(txt_image.getText().toString())){
            txt_image.setError("Image is required");
            return;
        }
        if("".equals(txt_startdate.getText().toString())){
            txt_startdate.setError("Start date is required");
            return;
        }

        new TaskAddSeminar().execute(txt_name.getText().toString(),txt_description.getText().toString(),txt_category.getText().toString(),txt_image.getText().toString(),txt_startdate.getText().toString());

    }
    public void btnDeleteSeminarClicked(View view) {
        if ("".equals(txt_name.getText().toString())) {
            txt_name.setError("Name is required");
            return;
        }


        new TaskDeleteSeminar().execute(txt_name.getText().toString());
    }

    public void btnBackClicked(View view){
        finish();
    }

    public class TaskAddSeminar extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(WelcomeActivity.this,"Please wait..","Add seminar processing..",true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String,String> postParam = new HashMap<>();
            postParam.put("action","addsem");
            postParam.put("name",params[0]);
            postParam.put("description",(params[1]));
            postParam.put("category",params[2]);
            postParam.put("image",params[3]);
            postParam.put("startdate",params[4]);


            try {
                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL2,postParam);
                JSONObject jsonObject = new JSONObject(jsonString);

                return jsonObject.getInt("result");

            }catch (Exception e){
                e.printStackTrace();
                return Common.RESULT_ERROR;
            }

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            m_ProgressDialog.dismiss();

            if (integer == Common.RESULT_SUCCESS) {
                Toast.makeText(WelcomeActivity.this, "Adding seminar success", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("name", txt_name.getText().toString());
                i.putExtra("description", txt_description.getText().toString());
                i.putExtra("category", txt_category.getText().toString());
                i.putExtra("image", txt_image.getText().toString());
                i.putExtra("startdate", txt_startdate.getText().toString());
                setResult(1, i);
            } else if (integer == Common.RESULT_SEMINAR_EXISTS) {
                Toast.makeText(WelcomeActivity.this, "Seminar exists", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(WelcomeActivity.this, "Add seminar failed", Toast.LENGTH_LONG).show();
            }
        }


    }

    public class TaskDeleteSeminar extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("json",integer.toString());
            if (integer == Common.RESULT_SUCCESS) {
                Toast.makeText(WelcomeActivity.this, "Deleting seminar success", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("name", txt_name.getText().toString());
                setResult(1, i);
            } else if (integer == Common.RESULT_SEMINAR_DOESNT_EXIST) {
                Toast.makeText(WelcomeActivity.this, "Seminar doesnt exist", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(WelcomeActivity.this, "Delete seminar failed", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String,String> postParam = new HashMap<>();
            postParam.put("action","deletesem");
            postParam.put("name",params[0]);
            try {
                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL2,postParam);
                JSONObject jsonObject = new JSONObject(jsonString);
                Log.d("json","the result in doinbackground: " + jsonObject.getInt("result"));
                return jsonObject.getInt("result");
            }catch (Exception e){
                e.printStackTrace();
                return Common.RESULT_ERROR;
            }

        }
    }
}
