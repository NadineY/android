package com.example.myfit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.jar.JarEntry;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfit.LoginActivity;
import com.example.myfit.R;



    public class RegisterActivity<accountDB> extends AppCompatActivity {
        Button submit;
        EditText nom, prenom, email, password, checkpassword;
        RadioGroup sexe;
        RadioButton femme, homme;
        private static final int PERMISSION_CODE = 2 ;
        private static final int IMAGE_CAPTURE_CODE = 1;
        ImageView pdp;
        Button plus;
        private String pathImage;


        BDD dbHelper = new BDD(this, "Utilisateurs", null, 1);


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            changeStatusBarColor();

            submit = (Button) findViewById(R.id.cirRegisterButton);
            nom = (EditText) findViewById(R.id.editTextNom);
            prenom = (EditText) findViewById(R.id.editTextPrenom);
            email = (EditText) findViewById(R.id.editTextEmail);
            password = (EditText) findViewById(R.id.editTextPassword);
            checkpassword = (EditText) findViewById(R.id.editTextCheckPassword);
            sexe = (RadioGroup) findViewById(R.id.sexe);
            femme = (RadioButton) findViewById(R.id.radio_f);
            homme = (RadioButton) findViewById(R.id.radio_h);
            plus = (Button) findViewById(R.id.plus);
            pdp = (ImageView) findViewById(R.id.pdp);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if ((nom.getText().toString()).isEmpty() || (prenom.getText().toString()).isEmpty() || (email.getText().toString()).isEmpty() || (password.getText().toString()).isEmpty()) {
                            openDialog("Champs Vides");
                        } else {
                            if ((password.getText().toString()).equals(checkpassword.getText().toString())) {
                                if (dbHelper.existe(email.getText().toString()) == true) {
                                    dbHelper.insertData(nom.getText().toString(), prenom.getText().toString(), email.getText().toString(), password.getText().toString(), pathImage);
                                    nom.setText("");
                                    prenom.setText("");
                                    email.setText("");
                                    password.setText("");
                                    checkpassword.setText("");
                                    pathImage.toString();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    Toast.makeText(RegisterActivity.this, R.string.Bienvenue, Toast.LENGTH_SHORT).show();
                                } else {
                                    openDialog("Email existant");
                                }
                            } else {
                                openDialog("Les mots de passes ne sont pas identiques");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if request runtime permission already checked
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) ==
                                PackageManager.PERMISSION_DENIED ||
                                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                        PackageManager.PERMISSION_DENIED) {
                            //permissions not enabled request it
                            String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            //show popup to request permissions
                            requestPermissions(permissions, PERMISSION_CODE);
                        } else {
                            //permission already granted
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 0);

                        }
                    } else {
                        // system os < marshmallow
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 0);

                    }

                }
            });



        }
            private void changeStatusBarColor () {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
                    window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
                }
            }


            public void onLoginClick (View view){
                startActivity(new Intent(this, LoginActivity.class));
                // overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

            }


            public void openDialog (String text){
                final androidx.appcompat.app.AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage(text);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.create();
                dialog.show();
            }

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            //called when imag ewas captured from camera
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            pathImage=BitmapToString(bitmap);
            // Log.i("Imagepath",pathImage);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            //this methode called when user presses allow or deny from permission request popup
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case PERMISSION_CODE: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //permission granted
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 0);

                    } else {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        //methode pour changer une image en string pour la stocker dans la bdd
        private String BitmapToString(Bitmap bitmap){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
            byte [ ] byte_arr = stream.toByteArray();
            return android.util.Base64.encodeToString(byte_arr,0);

        }
    }






