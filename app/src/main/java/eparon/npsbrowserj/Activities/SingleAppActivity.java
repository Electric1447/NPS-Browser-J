package eparon.npsbrowserj.Activities;

import android.os.Bundle;
import android.widget.TextView;

import eparon.npsbrowserj.R;
import eparon.npsbrowserj.TSV.BaseTSV;

public class SingleAppActivity extends BaseActivity {

    BaseTSV app;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_app);

        app = (BaseTSV)getIntent().getSerializableExtra("app");

        ((TextView)findViewById(R.id.titleID)).setText(app.getTitleID());
        ((TextView)findViewById(R.id.gameTitle)).setText(app.getTitle());
    }

}
