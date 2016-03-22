package com.example.hoanganh.fragmentdemo.activity;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.hoanganh.fragmentdemo.R;
import com.example.hoanganh.fragmentdemo.fragment.CheckedItemFragment;
import com.example.hoanganh.fragmentdemo.fragment.DetailFragment;
import com.example.hoanganh.fragmentdemo.fragment.ListItemFragment;

public class MainActivity extends FragmentActivity implements ListItemFragment.OnNextListener,
        CheckedItemFragment.OnClcikItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check screen orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (savedInstanceState != null) {
                return;
            }

            ListItemFragment firstFragment = new ListItemFragment();
            firstFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        } else {
            ListItemFragment firstFragment = new ListItemFragment();
            CheckedItemFragment secondFragment = new CheckedItemFragment();

            firstFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment1, firstFragment);
            transaction.add(R.id.fragment2, secondFragment);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public void onPersonNext(Bundle bundle) {
        CheckedItemFragment newFragment = new CheckedItemFragment();
        newFragment.setArguments(bundle);

        int[] a = bundle.getIntArray("AAA");
        //Toast.makeText(this, String.valueOf(a.length), Toast.LENGTH_SHORT).show();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();


        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onClickItemListenner(Bundle bundle) {
        DetailFragment newFragment = new DetailFragment();
        newFragment.setArguments(bundle);


        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        //Person p = (Person) bundle.getSerializable("BBB");
        //Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
