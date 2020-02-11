package com.msg91.sendotp.sample;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment1 extends Fragment {
//Button b;
//TextView it
// ;
    SharedPreferences sh,logout;
    Button bb,log;
    TextView dname,dph,did,dob,daddress,dgender,demail;
final int RequestPermissionCode=1;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root= inflater.inflate(R.layout.fragment_call1, container, false);
        dname=root.findViewById(R.id.rdname);
        did=root.findViewById(R.id.docyou3);
        dph=root.findViewById(R.id.dw);

        demail=root.findViewById(R.id.demail);
        logout= Objects.requireNonNull(getActivity()).getSharedPreferences("Official",MODE_PRIVATE);
        dob=root.findViewById(R.id.dob);
        daddress=root.findViewById(R.id.daddress);
        dgender=root.findViewById(R.id.dgender);
       bb=root.findViewById(R.id.bb);
sh= Objects.requireNonNull(getActivity()).getSharedPreferences("data",MODE_PRIVATE);
String Item=sh.getString("phone",null);

     String ii=   sh.getString("id",null);
      String nn=  sh.getString("name",null);
    String ee=    sh.getString("email",null);
   String add=     sh.getString("address",null);
    String ex=    sh.getString("gender",null);
     String ty=   sh.getString("dob",null);


        dph.setText(Item);
        did.setText(nn);
        dname.setText(ii);
        demail.setText(ee);
        daddress.setText(add);
        dgender.setText(ex);
        dob.setText(ty);


       bb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent i=new Intent(getActivity(),Online.class);
        startActivity(i);
    }
});

        return root;
    }



    }



