package barinder.example.com.navigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MedicalRecords extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout btnVaccination, btnAntiparasitic, btnMedicine, btnVisits, btnNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);
        btnVaccination   =  (RelativeLayout)  findViewById(R.id.btnVaccination);
        btnAntiparasitic =  (RelativeLayout)  findViewById(R.id.btnAntiparistics);
        btnMedicine      =  (RelativeLayout)  findViewById(R.id.btnMedicine);
        btnVisits        =  (RelativeLayout)  findViewById(R.id.btnVisitsAndSurgeries);
        btnNotes         =  (RelativeLayout)  findViewById(R.id.btnNotes);
        btnNotes.setOnClickListener(this);
        btnVisits.setOnClickListener(this);
        btnAntiparasitic.setOnClickListener(this);
        btnMedicine.setOnClickListener(this);
        btnVaccination.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medical_records, menu);
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
    public void onClick(View view) {
        switch (view.getId()){


            case  R.id.btnVaccination:
                Intent intent = new Intent(MedicalRecords.this,Vaccination.class);
                startActivity(intent);

        }

    }
}
