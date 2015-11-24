package barinder.example.com.navigationdrawer;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.weavebytes.dogsapp.model.DaoMaster;
import com.weavebytes.dogsapp.model.DaoSession;
import com.weavebytes.dogsapp.model.Dogs;
import com.weavebytes.dogsapp.model.DogsDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Add extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button back, addDogs;
    EditText name, birthDate, weight, withers, breed, chip, sex;
    Spinner spinnerOsversions;
    private String[] state = {"Male", "Female"};
     private  String TAG = "Add Dogs Activity ";
    String sexValue;
    Calendar myCalendar = Calendar.getInstance();

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
                List<Dogs> dogsList = dogsDao.loadAll();
               /* Log.d(TAG, " Dog Name"+dogsList);
                Log.d(TAG, " Dog Name"+dogsList.get(1).getName());
                Log.d(TAG, " Dog Id " + dogsList.get(1).getId());
                Log.d(TAG, " Dog Bredd "+dogsList.get(1).getBreed());
                Log.d(TAG, " Dog Chip "+dogsList.get(1).getChip());
                Log.d(TAG, " Dog sex "+dogsList.get(1).getSex());
                Log.d(TAG, " Dog Birthdate "+dogsList.get(1).getBirthDate());
                Log.d(TAG, " Dog Withers "+dogsList.get(1).getWithers());
                Log.d(TAG, " Dog Weight " + dogsList.get(1).getWeight());*/
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                break;

            case R.id.save:
                addDog(daoSession);
                Log.d(TAG," spinner value "+sexValue);
                Log.d(TAG," clicked on save ");
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            case R.id.edittext2:
                  datePicker();


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
         dogs.setBirthDate(birthDate.getText().toString());
        // dogs.setBirthDate(Integer.parseInt(birthDate.getText().toString()));
        dogs.setBreed(breed.getText().toString());
        dogs.setChip(chip.getText().toString());
        dogs.setSex(sexValue);
        dogs.setWeight(Integer.parseInt(weight.getText().toString()));
        dogs.setWithers(Integer.parseInt(withers.getText().toString()));
        DogsDao dogsDao = daoSession.getDogsDao();
        dogsDao.insert(dogs);

        List<Dogs> DogsList = dogsDao.loadAll();
        Log.d(TAG, " Dog Name"+DogsList.get(0).getName());
        Log.d(TAG, " Dog Name" + DogsList.get(0).getId());
        Log.d(TAG, " Dog Name"+DogsList.get(0).getBreed());
        Log.d(TAG, " Dog Name"+DogsList.get(0).getChip());
        Log.d(TAG, " Dog Name"+DogsList.get(0).getSex());
        Log.d(TAG, " Dog Name"+DogsList.get(0).getBirthDate());
        Log.d(TAG, " Dog Name"+DogsList.get(0).getWithers());
        Log.d(TAG, " Dog Name"+DogsList.get(0).getWeight());

    }
      public  void  datePicker(){
          DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker view, int year, int monthOfYear,
                                    int dayOfMonth) {
                  // TODO Auto-generated method stub
                  myCalendar.set(Calendar.YEAR, year);
                  myCalendar.set(Calendar.MONTH, monthOfYear);
                  myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                  updateLabel();
              }

          };
          new DatePickerDialog(Add.this, date, myCalendar
                  .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                  myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        birthDate.setText(sdf.format(myCalendar.getTime()));
    }

}
