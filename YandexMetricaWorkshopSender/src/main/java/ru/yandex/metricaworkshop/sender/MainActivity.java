package ru.yandex.metricaworkshop.sender;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.yandex.metrica.YandexMetrica;

public class MainActivity extends ActionBarActivity {

    private static final String API_KEY = "37970";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }

        YandexMetrica.initialize(this, API_KEY);
    }

    @Override
    public void onResume() {
        super.onResume();
        YandexMetrica.onResumeActivity(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        YandexMetrica.onPauseActivity(this);
    }

}
