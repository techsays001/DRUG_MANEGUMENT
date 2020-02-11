package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class regtwo extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener {
        String[] dance = { "Male", "Female","Other"};
 ;
SharedPreferences sp;
    TextView nametxt, emailtxt, dobtxt,addresstxt, login_title;
    TextView logo;
    EditText pass,cpass,uname;
    Spinner projectname;
    LinearLayout already_have_account_layout;
    CardView register_card;
    Intent i;
    CheckBox pchek,cpchek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtwo);
        i=getIntent();
        pchek=findViewById(R.id.checkBox5);
        cpchek=findViewById(R.id.checkBox6);
        pass= findViewById(R.id.pass);
        cpass=findViewById(R.id.cpass);
        uname=findViewById(R.id.uname);
        nametxt = findViewById(R.id.nametxt);
        projectname = findViewById(R.id.projectname);
        emailtxt = findViewById(R.id.emailtxt);
        projectname.setOnItemSelectedListener(this);

        sp=getSharedPreferences("reg",MODE_PRIVATE);
        dobtxt = findViewById(R.id.dobtxt);
        addresstxt= findViewById(R.id.addresstxt);
//        logo = findViewById(R.id.logo);

        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dance);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        projectname.setAdapter(aa);
//        Toast.makeText(regtwo.this,projectname.getSelectedItem().toString(),Toast.LENGTH_LONG).show();


        pchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pchek.setText("Hide");
                }
                else
                {

                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pchek.setText("Show");
                }
            }
        });

        cpchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    cpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cpchek.setText("Hide");
                }
                else
                {

                   cpass .setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cpchek.setText("Show");
                }
            }
        });

    }


    public void registerButtonf(View view) { {
        if (uname.getText().toString().isEmpty()){
            uname.setError("Empty Field");
        }

       else if (pass.getText().toString().isEmpty()){

            pass.setError("Empty Field");
        }
        else if (cpass.getText().toString().isEmpty()){
            cpass.setError("Empty Field");
        }
        else if (pass.getText().toString().length()<=6){

            pass.setError("Password Must Contain 6 Digits");
        }

        else if (cpass.getText().toString().length()<=6){

            cpass.setError("Password Must Contain 6 Digits");
        }

        else if (!(pass.getText().toString().equals(cpass.getText().toString()))) {

            Toast.makeText(regtwo.this,"Password not match",Toast.LENGTH_LONG).show();

        }

     else

        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Drug_Addicts_Counseling/Registration_Drug.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server
                            Toast.makeText(regtwo.this,response,Toast.LENGTH_LONG).show();
                            if(response.equals("Registration Successful"))
                            {

                                new SweetAlertDialog(regtwo.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Registration Success")
                                        .setContentText("Login to Dashboard!")
                                        .setConfirmText("Yes,Login")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog
                                                        .setTitleText("Logining...!")

                                                        .setConfirmText("OK")

                                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                Intent in=new Intent(regtwo.this,Signin.class);
                                                                 startActivity(in);
                                                            }
                                                        })
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .show();




//
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
params.put("sname",i.getStringExtra("name"));
params.put("semail",i.getStringExtra("email"));
params.put("phone",sp.getString("ph",null));
                    params.put("sdob",i.getStringExtra("dob"));
                    params.put("project",i.getStringExtra("address"));

//                    params.put("pname", projectfield.getText().toString());
                    params.put("saddress",projectname.getSelectedItem().toString().toLowerCase());
                    params.put("username", uname.getText().toString());
                    params.put("passw", pass.getText().toString());


// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                    return params;
                }

            };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(regtwo.this);
            requestQueue.add(stringRequest);
        }



    }

}

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}

