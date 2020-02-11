package com.msg91.sendotp.sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
 EditText dname,dphone,demail,dexp,dblog;
 Button exp_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root= inflater.inflate(R.layout.fragment_chat, container, false);
         dname=root.findViewById(R.id.rdname);
        dphone=root.findViewById(R.id.docyou1);
        demail=root.findViewById(R.id.dw);
        dexp=root.findViewById(R.id.dexp);
        dblog=root.findViewById(R.id.dblog);

        exp_btn=root.findViewById(R.id.exp_btn);

        exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dname.getText().toString().isEmpty()){

                    dname.setError("field is empty");
                }
                else if (dphone.getText().toString().isEmpty()){
                    dphone.setError("field is empty");

                }
                else if (demail.getText().toString().isEmpty()){
                    demail.setError("field is empty");
                }
                else  if (dexp.getText().toString().isEmpty()){

                    dexp.setError("field is empty");
                }
                else if (dblog.getText().toString().isEmpty()){

                    dblog.setError("field is empty");
                }
                else{


                    StringRequest stringRequest;
                    stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Drug_Addicts_Counseling/Blog_Drug.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                                    dname.getText().clear();
                                    dphone.getText().clear();
                                    demail.getText().clear();
                                    dexp.getText().clear();
                                    dblog.getText().clear();

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

                            params.put("tname", dname.getText().toString());
                            params.put("tphone", dphone.getText().toString());
                            params.put("temail", demail.getText().toString());
                            params.put("tdance", dexp.getText().toString());
                            params.put("texperiance", dblog.getText().toString());
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





}

