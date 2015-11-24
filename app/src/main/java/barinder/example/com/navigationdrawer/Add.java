package barinder.example.com.navigationdrawer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.weavebytes.dogsapp.model.DaoMaster;
import com.weavebytes.dogsapp.model.DaoSession;
import com.weavebytes.dogsapp.model.Dogs;
import com.weavebytes.dogsapp.model.DogsDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Add extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button back, addDogs;
    EditText name, birthDate, weight, withers, breed, chip, sex;
    Spinner spinnerOsversions;
    private String[] state = {"Male", "Female"};
     private  String TAG = "Add Dogs Activity ";
    String sexValue;
    private  DaoSession daoSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        back        = (Button) findViewById(R.id.back);
        addDogs     = (Button) findViewById(R.id.save);
        name        = (EditText) findViewById(R.id.edittext1);
        birthDate  =  (EditText) findViewById(R.id.edittext2);
        weight      = (EditText) findViewById(R.id.edittext3);
        withers     = (EditText) findViewById(R.id.edittext4);
        breed       = (EditText) findViewById(R.id.edittext5);
        chip        = (EditText) findViewById(R.id.edittext6);
        birthDate.setOnClickListener(this);

        spinnerOsversions = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOsversions.setAdapter(adapter_state);
        spinnerOsversions.setOnItemSelectedListener(this);
        spinnerOsversions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                sexValue = adapter.getItemAtPosition(position).toString();

               /* // Showing selected spinner item
                Toast.makeText(getApplicationContext(), "Selected  : " + item,
                        Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        back.setOnClickListener(this);
        addDogs.setOnClickListener(this);
        daoSession = ((DogsApplication)getApplicationContext()).getDaoSession();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back:
                DogsDao dogsDao = daoSession.getDogsDao();
                List<Dogs> leaseList = dogsDao.loadAll();
                Log.d(TAG, " Dog Name"+leaseList);
                Log.d(TAG, " Dog Name"+leaseList.get(1).getName());
                Log.d(TAG, " Dog Id " + leaseList.get(1).getId());
                Log.d(TAG, " Dog Bredd "+leaseList.get(1).getBreed());
                Log.d(TAG, " Dog Chip "+leaseList.get(1).getChip());
                Log.d(TAG, " Dog sex "+leaseList.get(1).getSex());
                Log.d(TAG, " Dog Birthdate "+leaseList.get(1).getBirthDate());
                Log.d(TAG, " Dog Withers "+leaseList.get(1).getWithers());
                Log.d(TAG, " Dog Weight " + leaseList.get(1).getWeight());
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                break;

            case R.id.save:
                addDog(daoSession);
                Log.d(TAG," spinner value "+sexValue);
                Log.d(TAG," clicked on save ");
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            case R.id.valbirthday:



        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerOsversions.setSelection(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private  void addDog(DaoSession daoSession){

        Dogs dogs = new Dogs();
        dogs.setName(name.getText().toString());
        dogs.setBirthDate(Integer.parseInt(birthDate.getText().toString()));
        dogs.setBreed(breed.getText().toString());
        dogs.setChip(chip.getText().toString());
        dogs.setSex(sexValue);
        dogs.setWeight(Integer.parseInt(weight.getText().toString()));
        dogs.setWithers(Integer.parseInt(withers.getText().toString()));
        DogsDao dogsDao = daoSession.getDogsDao();
        dogsDao.insert(dogs);

        List<Dogs> leaseList = dogsDao.loadAll();
        Log.d(TAG, " Dog Name"+leaseList.get(0).getName());
        Log.d(TAG, " Dog Name" + leaseList.get(0).getId());
        Log.d(TAG, " Dog Name"+leaseList.get(0).getBreed());
        Log.d(TAG, " Dog Name"+leaseList.get(0).getChip());
        Log.d(TAG, " Dog Name"+leaseList.get(0).getSex());
        Log.d(TAG, " Dog Name"+leaseList.get(0).getBirthDate());
        Log.d(TAG, " Dog Name"+leaseList.get(0).getWithers());
        Log.d(TAG, " Dog Name"+leaseList.get(0).getWeight());


    }

}
