package barinder.example.com.navigationdrawer;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UpdateDogInfo extends AppCompatActivity implements View.OnClickListener {
    EditText name, birthDate, weight, withers, breed, chip, sex;
    Button back, addDogs;
    String TAG = " UpdateDogInfo ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dog_info);
        name        = (EditText) findViewById(R.id.edittext1);
        birthDate  =  (EditText) findViewById(R.id.edittext2);
        weight      = (EditText) findViewById(R.id.edittext3);
        withers     = (EditText) findViewById(R.id.edittext4);
        breed       = (EditText) findViewById(R.id.edittext5);
        chip        = (EditText) findViewById(R.id.edittext6);

        /*getting values from intent and setting in the respective edittext*/
        settingValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_dog_info, menu);
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


        switch (view.getId()) {
            case R.id.back:
                Intent intent;
                intent = new Intent(getApplicationContext(), MyDogCard.class);
                startActivity(intent);
                break;

            case R.id.save:
                intent = new Intent(getApplicationContext(), MyDogCard.class);
                startActivity(intent);

            case R.id.valbirthday:



        }
    }
    public  void settingValues(){
        Intent intent = getIntent();
        intent.getExtras();
        Log.d(TAG,TAG+intent.getExtras());
        Log.d(TAG, "intent " + intent.getStringExtra("name") + intent.getStringExtra("birthday") + intent.getStringExtra("birthday") + intent.getStringExtra("height")
                + intent.getStringExtra("breed") + intent.getStringExtra("chip"));
        name.setText(intent.getStringExtra("name"));
        birthDate.setText(intent.getStringExtra("birthday"));
        weight.setText(intent.getStringExtra("birthday"));
        withers.setText(intent.getStringExtra("height"));
        breed.setText(intent.getStringExtra("breed"));
        chip.setText(intent.getStringExtra("chip"));

    }
}
