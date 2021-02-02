package com.example.infinigentconsulting;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageBrowsingActivity extends AppCompatActivity {


    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    final int REQUEST_CODE_GALLERY = 999;
    DatabaseHelper mDatabaseHelper;
    private ImageButton _img_previous_button, _shop_img_one,_shop_img_two,_shop_img_three;
    private Button _image_submit_button,_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browsing);
        mDatabaseHelper = new DatabaseHelper(this);
        _img_previous_button = findViewById(R.id.img_previous_button);
        _shop_img_one = findViewById(R.id.shop_img_one);
        _image_submit_button= findViewById(R.id._submit_img);
        _submit=findViewById(R.id.submit);
        _img_previous_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ImageBrowsingActivity.this, SchemeDetailsActivity.class);
                startActivity(i);
            }
        });

        //Shop Image 1 set onClicklistener
        _shop_img_one.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
//                        ActivityCompat.requestPermissions(
//                                ImageBrowsingActivity.this,
//                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                REQUEST_CODE_GALLERY
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        }
                        else
                        {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }

                    }
                });

        //submit image button
        _image_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] NewImage = imageViewToByte(_shop_img_one);
                AddImage(NewImage);
               /* Intent intent = new Intent(ImageBrowsingActivity.this, MainActivity.class);
                startActivity(intent);*/

            }


        });
        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SentImageByApi();
            }


        });
    }

    // ******************
    private void SentImageByApi()
    {
        Cursor getImageData = mDatabaseHelper.getImageData();
        if(getImageData.getCount() == 0) {
            // show message

            return;
        }

        byte[] newImgEntry;
        while (getImageData.moveToNext()) {
             Toast(getImageData.getString(0));
             Toast(getImageData.getString(1));
             newImgEntry= getImageData.getBlob(2) ;
             Toast(getImageData.getString(3));




        }
    }
    private void AddImage(byte[] newImgEntry)
    {
        boolean insertData = mDatabaseHelper.addData(newImgEntry);
        if(insertData)
        {
            Toast("Data inserted");

        }
        else
        {
            Toast("Data not inserted");
        }
    }
    private  void Toast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


    public byte[] imageViewToByte(ImageButton image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if(requestCode == REQUEST_CODE_GALLERY){
//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_GALLERY);
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                _shop_img_one.setImageBitmap(bitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }



    //**************************

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            _shop_img_one.setImageBitmap(photo);
        }
    }


}