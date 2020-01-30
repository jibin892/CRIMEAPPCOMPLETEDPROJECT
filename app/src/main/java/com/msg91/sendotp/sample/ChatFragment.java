package com.msg91.sendotp.sample;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import android.widget.LinearLayout;
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
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

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
public class ChatFragment extends Fragment  implements
        AdapterView.OnItemSelectedListener {
    String[] genderr = { "Male","Female","Other"};
    String[] pla = { "Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad"};

    private DatePicker datePicker;

    private TextView dateView;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    EditText name,phone,date,missin,place,details;
 Spinner gender,distict;
 Button exp_btn;
    SharedPreferences sh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root= inflater.inflate(R.layout.fragment_chat, container, false);
        name=root.findViewById(R.id.name1);
        phone=root.findViewById(R.id.phone1);
        date=root.findViewById(R.id.date1);
        gender=root.findViewById(R.id.gender1);
        missin=root.findViewById(R.id.missing1);
        place=root.findViewById(R.id.phone1);
        details=root.findViewById(R.id.details1);
        distict=root.findViewById(R.id.distict1);

        sh=getActivity().getSharedPreferences("Official",MODE_PRIVATE);

        ArrayAdapter aas = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,genderr);
        aas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        gender.setAdapter(aas);

        ArrayAdapter aass = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,pla);
        aass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        distict.setAdapter(aass);




        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                 datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        exp_btn=root.findViewById(R.id.exp_btn);

        exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (date.getText().toString().isEmpty()){

                    date.setError("field is empty");
                }
                else if (name.getText().toString().isEmpty()){

                    name.setError("field is empty");
                }
               else  if (phone.getText().toString().isEmpty()){

                    phone.setError("field is empty");
                }
                else  if (place.getText().toString().isEmpty()){

                    place.setError("field is empty");
                }
                else  if (details.getText().toString().isEmpty()){

                    details.setError("field is empty");
                }
                else  if (missin.getText().toString().isEmpty()){

                    missin.setError("field is empty");
                }

                else{



                    StringRequest stringRequest;
                    stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Crime_manegument_system.php/Missing_people_Register.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                                    date.getText().clear();
                                    missin.getText().clear();
                                    place.getText().clear();
                                    details.getText().clear();
                                    name.getText().clear();
                                    phone.getText().clear();

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

                params.put("nm", name.getText().toString());
                params.put("ph", phone.getText().toString());
                params.put("tdance", date.getText().toString());
                params.put("tname",missin.getText().toString());
                params.put("tphone",gender.getSelectedItem().toString().toLowerCase());
                params.put("tphon",distict.getSelectedItem().toString().toLowerCase());
                params.put("temail",place.getText().toString());
                params.put("pa",details.getText().toString());
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
//            SharedPreferences.Editor e=sh.edit();
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

