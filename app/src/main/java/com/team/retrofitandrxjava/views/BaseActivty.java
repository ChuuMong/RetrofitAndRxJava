package com.team.retrofitandrxjava.views;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by leejonghun on 15. 7. 28.
 */
public class BaseActivty extends AppCompatActivity {

    public void errorToast(String s) {
        if (s != null && s.length() != 0) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
