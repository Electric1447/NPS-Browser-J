package eparon.npsbrowserj;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;

public class ThemePreference extends ListPreference {

    public ThemePreference (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ThemePreference (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThemePreference (Context context) {
        super(context);
    }

    @Override
    public boolean callChangeListener (Object newValue) {
        switch (Integer.parseInt(newValue.toString())) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED);
        }
        return super.callChangeListener(newValue);
    }

    @Override
    public void setValue (String value) {
        super.setValue(value);
        setSummary(value);
    }

    @Override
    public void setSummary (CharSequence summary) {
        super.setSummary(getEntry());
    }

}
