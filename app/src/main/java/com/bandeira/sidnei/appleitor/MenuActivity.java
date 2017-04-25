package com.bandeira.sidnei.appleitor;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnNova(View view) {
        Intent it = new Intent(this, CadastraColetaActivity.class);
        startActivityForResult(it,20);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 20) {
            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        }
    }
}
