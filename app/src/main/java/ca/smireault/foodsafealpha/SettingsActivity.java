package ca.smireault.foodsafealpha;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private ProductViewModel mProductViewModel;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, new MySettingsFragment()).commit();

        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    }

    @Override
    protected void onPause() {
        super.onPause();



    }
}
