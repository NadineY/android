package com.example.myfit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfit.LoginActivity;
import com.example.myfit.R;


public class RegisterActivity extends AppCompatActivity {

    public class MainActivity<accountDB> extends AppCompatActivity {
        Button submit;
        EditText nom, prenom, email, sexe, pasword, checkpasword;
        String FILE_NAME = "exple.txt";
        //associer activité à la boite de dialogue
        private AccountDB accountDB;
        private MainActivity activity;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            changeStatusBarColor();

            this.activity = this;
        }

        private void changeStatusBarColor() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
                window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
            }
        }


        public void onLoginClick(View view) {
            startActivity(new Intent(this, LoginActivity.class));
            // overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

        }

    }

}
