package com.msg91.sendotp.sample;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatusFragment3 extends Fragment implements AdapterView.OnItemSelectedListener {
    Spinner doctorone;
    TextView dname,dob,daddress,daddres;
    private ArrayList<Doctor> goodModelArrayList;
Button abc;
    private ArrayList<String> names = new ArrayList<String>();
    String a,b,c,d,e,f,g;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_status3, container, false);
        doctorone=root.findViewById(R.id.p_spiner1);
        dname=root.findViewById(R.id.rdname1);
        dob=root.findViewById(R.id.dob1);
        daddress=root.findViewById(R.id.daddress1);
        daddres=root.findViewById(R.id.daddres1);
abc=root.findViewById(R.id.on11);


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dob.getText().toString()));
                startActivity(intent);



            }
        });
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Crime_manegument_system.php/Complient_spiner.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
//                        Toast.makeText(Phycology.this, response, Toast.LENGTH_LONG).show();

                        goodModelArrayList = new ArrayList<>();


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");

                                Doctor doctor=new Doctor();
                                doctor.setName(json_obj.getString("name"));
                                goodModelArrayList.add(doctor);
                            }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < goodModelArrayList.size(); i++){
                            names.add(goodModelArrayList.get(i).getName().toString());
                        }

                        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, names);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        doctorone.setAdapter(spinnerArrayAdapter);



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



// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


      abc.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {




             // Toast.makeText(Phycology.this,doctorone.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
              StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Crime_manegument_system.php/Complient_detailes.php",
                      new Response.Listener<String>() {
                          @Override
                          public void onResponse(String response) {
//If we are getting success from server
//                       Toast.makeText(Phycology.this,response,Toast.LENGTH_LONG).show();
                              try {
                                  JSONArray jsonArray = new JSONArray(response);
                                  for (int i = 0; i < jsonArray.length(); i++) {
                                      JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                      a=json_obj.getString("name");
                                      b=json_obj.getString("phone");
                                      c=json_obj.getString("district");
                                      d=json_obj.getString("discription");
//                                e=json_obj.getString("address");
//                                f=json_obj.getString("gender");
//                                g=json_obj.getString("dob");

                                      dname.setText(a);
                                      dob.setText(b);
                                      daddress.setText(c);
                                      daddres.setText(d);
                                  }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                              } catch (JSONException e) {
                                  e.printStackTrace();
                              }
                              //   Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();

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
                      params.put("docname",doctorone.getSelectedItem().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                      return params;
                  }

              };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
              RequestQueue requestQueue = Volley.newRequestQueue(getContext());
              requestQueue.add(stringRequest);
          }
      });
      return root;
    }





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}