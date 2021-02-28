package com.example.rampagegg.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rampagegg.R;
import com.example.rampagegg.database.DotaRoomDatabase;
import com.example.rampagegg.model.Hero;
import com.example.rampagegg.model.Item;
import com.example.rampagegg.model.useraccount.Profile;
import com.example.rampagegg.util.DotaUtils;
import com.example.rampagegg.util.Status;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Toolbar toolbar;
    private PagerAdapter adapter;
    private ImageView imgProfilePic;
    private TextView tvProfileName;
    private TextView tvSteamId;
    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Initialize Views
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        tvProfileName = (TextView) findViewById(R.id.tvProfilenName);
        tvSteamId = (TextView) findViewById(R.id.tvSteamId);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initTabs();

//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        homeViewModel.init();
//        homeViewModel.getUserProfile().observe(this, new Observer<Account>() {
//            @Override
//            public void onChanged(Account account) {
//                initUserProfile(account.getProfile());
//            }
//        });

        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(this.getApplication())).get(HomeViewModel.class);
        homeViewModel.getUserProfile().observe(this, account -> initUserProfile(account.getProfile()));
        getHeroJson();
        getItemJson();
    }

    private void initTabs() {
        FragmentManager fm = this.getSupportFragmentManager();
        adapter = new PagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initUserProfile(Profile profile) {
        Glide.with(HomeActivity.this)
                .load(profile.getAvatarFull())
                .into(imgProfilePic);

        Resources res = this.getResources();
        String userName = res.getString(R.string.user_name, profile.getPersonaName());
        tvProfileName.setText(userName);
        String steamId = res.getString(R.string.user_steam_id, profile.getAccountId());
        tvSteamId.setText(steamId);
    }

    private void getItemJson() {
        List<Item> itemList = DotaUtils.getItemJson(this);
        DotaRoomDatabase dotaRoomDatabase = DotaRoomDatabase.getInstance(this);
        dotaRoomDatabase.dotaDao().insertAllItems(itemList);
    }

    private void getHeroJson() {
        List<Hero> heroList = DotaUtils.getHeroJson(this);
        DotaRoomDatabase dotaRoomDatabase = DotaRoomDatabase.getInstance(this);
        dotaRoomDatabase.dotaDao().insertAllHeroes(heroList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            Toast.makeText(this, "Fetching data", Toast.LENGTH_SHORT).show();
            homeViewModel.getStatus().observe(this, new Observer<Status>() {
                @Override
                public void onChanged(Status status) {
                    if (status == Status.SUCCESS) {
                        Log.d(TAG, "SUCCESS");
                    } else {
                        Log.d(TAG, "ERROR");
                    }
                }
            });
        }
        return true;
    }
}