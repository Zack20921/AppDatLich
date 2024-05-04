package com.example.banhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.banhang.Database.Database;
import com.example.banhang.Fragment.FragmentAdd;
import com.example.banhang.Fragment.FragmentHome;
import com.example.banhang.Fragment.FragmentMe;
import com.example.banhang.R;
import com.example.banhang.databinding.ActivityHomeBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;


    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int i = 1;
        binding.bottomNav.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        binding.bottomNav.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_add_circle_outline_24));
        binding.bottomNav.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_person_24));

        binding.bottomNav.show(1, true);

        database = new Database(this);
        database.creatData("INSERT INTO note VALUES(null, 'ha','ha',true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");
        replace(new FragmentHome());
        binding.bottomNav.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()){
                    case 1:
                        replace(new FragmentHome());
                        binding.txtTitle.setText("Home");
                        break;
                    case 2:
                        replace(new FragmentAdd());
                        binding.txtTitle.setText("Add");
                        break;

                    case 3:
                        replace(new FragmentMe());
                        binding.txtTitle.setText("Me");
                        break;
                }

                return null;
            }
        });

        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
            }
        });
    }

    public void replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }

}