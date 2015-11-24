package barinder.example.com.navigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner weightSpinner;
    Spinner lengthSpinner;

    private String[] state = { "Grams", "Pounds"};
    private String[] lengthArray = { "Meters", "Inches"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        weightSpinner = (Spinner) findViewById(R.id.weightSpinner);
        lengthSpinner = (Spinner) findViewById(R.id.lengthSpinner);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        ArrayAdapter<String> adapter_lenthArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lengthArray);

        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_lenthArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        weightSpinner.setAdapter(adapter_state);
        lengthSpinner.setAdapter(adapter_lenthArray);

        weightSpinner.setOnItemSelectedListener(this);
        lengthSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        weightSpinner.setSelection(position);
        lengthSpinner.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
