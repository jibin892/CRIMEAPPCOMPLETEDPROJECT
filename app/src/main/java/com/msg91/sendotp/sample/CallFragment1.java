package com.msg91.sendotp.sample;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment1 extends Fragment   implements
        AdapterView.OnItemSelectedListener {
    String[] pla = { "Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad"};

    private DatePicker datePicker;

    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    EditText name1,phone1,date1,city,sub,discriptin;
    Spinner distict1;
    Button exp_btn;
    SharedPreferences sh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root= inflater.inflate(R.layout.fragment_call1, container, false);
        name1=root.findViewById(R.id.name2);
        phone1=root.findViewById(R.id.phone2);
        date1=root.findViewById(R.id.date2);
        distict1=root.findViewById(R.id.d);
        city=root.findViewById(R.id.place2);
        sub=root.findViewById(R.id.details2);
        discriptin=root.findViewById(R.id.missing2);

        sh=getActivity().getSharedPreferences("Official",MODE_PRIVATE);



        ArrayAdapter aass = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,pla);
        aass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        distict1.setAdapter(aass);




        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date1.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        exp_btn=root.findViewById(R.id.exp_btn1);

        exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (date1.getText().toString().isEmpty()){

                    date1.setError("field is empty");
                }
                else if (name1.getText().toString().isEmpty()){

                    name1.setError("field is empty");
                }
                else  if (phone1.getText().toString().isEmpty()){

                    phone1.setError("field is empty");
                }
                else  if (sub.getText().toString().isEmpty()){

                    sub.setError("field is empty");
                }
                else  if (city.getText().toString().isEmpty()){

                    city.setError("field is empty");
                }
                else  if (discriptin.getText().toString().isEmpty()){

                    discriptin.setError("field is empty");
                }

                else{



                    StringRequest stringRequest;
                    stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Crime_manegument_system.php/complaint_registration.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                                    name1.getText().clear();
                                    date1.getText().clear();
                                    sub.getText().clear();
                                    phone1.getText().clear();
                                    discriptin.getText().clear();
                                    city.getText().clear();

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

                            params.put("name", name1.getText().toString());
                            params.put("phone", phone1.getText().toString());
                            params.put("date", date1.getText().toString());
                            params.put("dis",distict1.getSelectedItem().toString().toLowerCase());
                            params.put("city",city.getText().toString());
                            params.put("sub",sub.getText().toString());
                            params.put("disc",discriptin.getText().toString());
//                            params.put("texperiance", dblog.getText().toString());
// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }





            }

        });






        return root;






    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();



//        }
//        if (id==R.id.onlinesupport){
//            Intent iii=new Intent(getActivity(),Online.class);
//            startActivity(iii);
//
////            Toast.makeText(getActivity(),"class",Toast.LENGTH_LONG).show();
//
//
//        }
        if (id==R.id.newdance){


            Intent iiii=new Intent(getActivity(), Phycology2.class);
            startActivity(iiii);


        }

        if (id==R.id.event){


            Intent iiiii=new Intent(getActivity(), Phycology3.class);
            startActivity(iiiii);


        }
//        if (id==R.id.logou){
//
//
//          SharedPreferences.Editor e=sh.edit();
//            e.clear();
//            e.apply();
//            startActivity(new Intent(getActivity(), Admin_login.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//
//
//        }




        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }


}

