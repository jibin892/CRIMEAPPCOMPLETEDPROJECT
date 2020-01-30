package com.msg91.sendotp.sample;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Teachers_detailes_Admin1 extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    EditText cph,cdate,csatus,cenq;

    private DatePicker datePicker;

    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    Button dance_btn;
Spinner dance_techer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_detailes__admin1);
        cph = findViewById(R.id.cph1);
        cdate = findViewById(R.id.cdate11);
        csatus= findViewById(R.id.status1);
        cenq = findViewById(R.id.enq1);
        dance_btn = findViewById(R.id.d_btn1);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//        dance_ph= findViewById(R.id.dob);
        cdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(Teachers_detailes_Admin1.this,
                        (datePicker, year, month, day) -> cdate.setText(day + "/" + (month + 1) + "/" + year), year, month, dayOfMonth);
                 datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dance);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        dance_techer.setAdapter(aa);
        dance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cph.getText().toString().isEmpty()) {


                    cph.setError("Enter a Value");
                }
                else if (cdate.getText().toString().isEmpty()) {

                    cdate.setError("enter a value");
                }
                else if (cenq.getText().toString().isEmpty()) {

                   cenq.setError("enter a value");

                }


                else
                    {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Crime_manegument_system.php/Admin_update_status_complint.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                               cph.getText().clear();
                                   cdate.getText().clear();
                                    csatus.getText().clear();
                                  cenq.getText().clear();

                                    Toast.makeText(Teachers_detailes_Admin1.this, response, Toast.LENGTH_LONG).show();

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                                        }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
//Adding parameters to request

                            params.put("tname", cph.getText().toString());
                            params.put("tphone", cdate.getText().toString());
                            params.put("temail", csatus.getText().toString());
                            params.put("tdance", cenq.getText().toString());


// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Teachers_detailes_Admin1.this);
                    requestQueue.add(stringRequest);
                }
            }


        });


    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}