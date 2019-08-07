package ru.kuznecov.ivan.testapp;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class MainActivity extends AppCompatActivity
        implements SearchFragment.OnFragmentSearchListener {

    private static final String TAG = "Main Activity";
    private NavController navController;
    private boolean searchFragmentshow = true;
    private boolean time = false;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(TAG, searchFragmentshow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            searchFragmentshow = savedInstanceState.getBoolean(TAG, false);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void clickBtnSearch(String name) {
        Bundle args = ResultFragment.getBundle(name);
        navController.navigate(R.id.resultFragment, args);
        searchFragmentshow = false;
    }

    @Override
    public void onBackPressed() {
        if (!searchFragmentshow) {
            navController.popBackStack();
            searchFragmentshow = true;
            return;
        }
        if (time){
            finish();
        }
        if (!time) {
            startTimer();
        }

    }

    private void startTimer() {
        time = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    time = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Toast.makeText(this, R.string.exit_app, Toast.LENGTH_SHORT).show();
    }
}
