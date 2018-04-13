package com.example.nitesh.smartgarbagetracksystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class garbage_dumping_complaint extends AppCompatActivity {


    Button upload_button;
    ImageView com_image;
    EditText y_name,location,remark;
    public static final int READ_EXTERNAL_STORAGE = 0;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog progressDialog;
    private Firebase mRoofRef;
    private StorageReference mStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_dumping_complaint);

        Firebase.setAndroidContext(this);

        com_image = (ImageView)findViewById(R.id.iv_garcom);
        y_name = (EditText)findViewById(R.id.et_your_name);
        location = (EditText)findViewById(R.id.et_location);
        remark = (EditText)findViewById(R.id.et_remark);
        upload_button = (Button)findViewById(R.id.btn_complaint1_register);

        //Initialize the Progress Bar
        progressDialog = new ProgressDialog(garbage_dumping_complaint.this);

        com_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check for Runtime Permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "Call for Permission", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
                    }
                }
                else
                {
                    callgalary();
                }
            }
        });


        //DatabaseReference mdatabaseRef = FirebaseDatabase.getInstance().getReference();
        mRoofRef = new Firebase("https://smartgarbagetracksystem.firebaseio.com/").child("NewComplaintUpdates").push();  // Push will create new child every time we upload data
        mStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://smartgarbagetracksystem.appspot.com/");

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = y_name.getText().toString().trim();
                final String LOC = location.getText().toString().trim();
                final String REM = remark.getText().toString().trim();


                if(Name.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill all Field", Toast.LENGTH_SHORT).show();
                    return;
                }else if(LOC.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fill all Field", Toast.LENGTH_SHORT).show();
                    return;
                }else if (REM.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all Field", Toast.LENGTH_SHORT).show();
                    return;
                }


                Firebase childRef_name = mRoofRef.child("Complainter_Name");
                childRef_name.setValue(Name);
                Firebase childRef_LOC = mRoofRef.child("Location");
                childRef_LOC.setValue(LOC);
                Firebase childRef_REM = mRoofRef.child("Remark");
                childRef_REM.setValue(REM);


                Toast.makeText(getApplicationContext(), "Updated Info", Toast.LENGTH_SHORT).show();
            }

        });

    }

    //Check for Runtime Permissions for Storage Access
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    callgalary();
                return;
        }
        Toast.makeText(getApplicationContext(), "...", Toast.LENGTH_SHORT).show();
    }

    //If Access Granted gallery Will open
    private void callgalary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }
    //After Selecting image from gallery image will directly uploaded to Firebase Database
    //and Image will Show in Image View
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            Uri mImageUri = data.getData();
            com_image.setImageURI(mImageUri);
            StorageReference filePath = mStorage.child("Complaint_Image").child(mImageUri.getLastPathSegment());

            progressDialog.setMessage("Uploading Image....");
            progressDialog.show();

            filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();  //Ignore This error

                    mRoofRef.child("Image").setValue(downloadUri.toString());

                    Glide.with(getApplicationContext())
                            .load(downloadUri)
                            .crossFade()
                            .placeholder(R.drawable.loading)
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(com_image);
                    Toast.makeText(getApplicationContext(), "Updated.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

}
