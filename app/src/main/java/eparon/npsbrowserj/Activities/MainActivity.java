package eparon.npsbrowserj.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eparon.npsbrowserj.R;
import eparon.npsbrowserj.TSV.BaseTSV;
import eparon.npsbrowserj.TSV.Constants;
import eparon.npsbrowserj.TSV.PS3Games;
import eparon.npsbrowserj.TSV.VitaGames;

public class MainActivity extends BaseActivity {

    private static final int PSV_REQUEST_CODE = 56, PS3_REQUEST_CODE = 63;

    CSVReader tsvReader;

    Button psvButton, ps3Button;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        psvButton = findViewById(R.id.psv_button);
        ps3Button = findViewById(R.id.ps3_button);

        psvButton.setOnClickListener(v -> loadTSV(PSV_REQUEST_CODE));
        ps3Button.setOnClickListener(v -> loadTSV(PS3_REQUEST_CODE));
    }

    private void loadTSV (int type) {
        if (!isStoragePermissionGranted())
            return;

        Intent i = new Intent(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE).setType("text/tab-separated-values");
        startActivityForResult(i, type);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                CSVParser parser = new CSVParserBuilder().withEscapeChar('\\').withSeparator('\t').build();
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                tsvReader = new CSVReaderBuilder(new InputStreamReader(inputStream)).withCSVParser(parser).build();
                try {
                    List<String[]> entries = tsvReader.readAll();
                    ArrayList<BaseTSV> apps = new ArrayList<>();

                    for (String[] item : entries.subList(1, entries.size())) {
                        if (item[0].equals(""))
                            continue;

                        if (requestCode == PSV_REQUEST_CODE)
                            apps.add(new VitaGames(item[Constants.PSV_TITLEID], item[Constants.PSV_REGION], item[Constants.PSV_NAME], item[Constants.PSV_PKGLINK], item[Constants.PSV_ZRIF], item[Constants.PSV_CONTENTID], item[Constants.PSV_LASTMODDATE], 0, item[Constants.PSV_SHA256], item[Constants.PSV_MINFW]));
                        else if (requestCode == PS3_REQUEST_CODE)
                            apps.add(new PS3Games(item[Constants.PS3_TITLEID], item[Constants.PS3_REGION], item[Constants.PS3_NAME], item[Constants.PS3_PKGLINK], item[Constants.PS3_RAP], item[Constants.PS3_CONTENTID], item[Constants.PS3_LASTMODDATE], 0, item[Constants.PS3_SHA256]));
                        else return;
                    }

                    // Sort by title
                    Collections.sort(apps, (a1, a2) -> {
                        String a1name = a1.getTitle().toLowerCase();
                        String a2name = a2.getTitle().toLowerCase();

                        return a1name.compareTo(a2name);
                    });

                    AppsListActivity.apps = apps;
                    startActivity(new Intent(MainActivity.this, AppsListActivity.class));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isStoragePermissionGranted () {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        return false;
    }

}
