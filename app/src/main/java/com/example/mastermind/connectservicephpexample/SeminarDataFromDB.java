package com.example.mastermind.connectservicephpexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mastermind on 26/3/2018.
 */

public class SeminarDataFromDB {
    ArrayList<Seminar> seminars = new ArrayList<>();
    AccessServiceAPI m_AccessServiceAPI;


    public ArrayList<Seminar> getAllSeminars() {
        ArrayList<Seminar> seminarsList = new ArrayList<>();



        return seminarsList;
    }





    public class TaskAddSeminar extends AsyncTask<String,Void,Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String,String> postParam = new HashMap<>();
            postParam.put("action","showsem");

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


        }


    }

}
