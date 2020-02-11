package com.msg91.sendotp.sample;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;



import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallFragment extends Fragment {

//TextView it;
    Button t_btn,p_btn;
final int RequestPermissionCode=1;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root= inflater.inflate(R.layout.fragment_call, container, false);
         t_btn=root.findViewById(R.id.t_btn);
       p_btn=root.findViewById(R.id.p_btn);



       p_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent j=new Intent(getActivity(),Phycology.class);
               startActivity(j);
           }
       });

       t_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getActivity(),Therapy.class);
               startActivity(i);
           }
       });

        return root;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()),
                Manifest.permission.CAMERA)) {

// Toast.makeText(Cpature_image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);


        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

// Toast.makeText(Cpature_image.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getContext(), "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    }

