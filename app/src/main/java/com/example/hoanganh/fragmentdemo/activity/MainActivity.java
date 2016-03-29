package com.example.hoanganh.fragmentdemo.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.Utils.PersonArrays;
import com.example.hoanganh.fragmentdemo.fragment.ListItemFragment;

public class MainActivity extends FragmentActivity {
    private static final int CONTAINER = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getFragmentManager().findFragmentById(CONTAINER);
        if (fragment == null) {
            fragment = ListItemFragment.newInstance(PersonArrays.personList);
            getFragmentManager().beginTransaction()
                    .add(CONTAINER, fragment).commit();
        }

        Log.d("AAA", "MainActivity onCreate()");
    }

    // TODO không cần thiết
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
