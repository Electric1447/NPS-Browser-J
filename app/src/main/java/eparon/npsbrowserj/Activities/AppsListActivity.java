package eparon.npsbrowserj.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import eparon.npsbrowserj.Adapters.AppAdapter;
import eparon.npsbrowserj.R;
import eparon.npsbrowserj.RecyclerTouchListener;
import eparon.npsbrowserj.TSV.BaseTSV;

public class AppsListActivity extends BaseActivity {

    static ArrayList<BaseTSV> apps;
    RecyclerView recyclerView;
    AppAdapter viewAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        viewAdapter = new AppAdapter(Objects.requireNonNull(apps), getApplicationContext());

        recyclerView = findViewById(R.id.appsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick (View view, int position) {
                Intent i = new Intent(AppsListActivity.this, SingleAppActivity.class);
                i.putExtra("app", apps.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick (View view, RecyclerView recyclerView, int position) {
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(AppsListActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
