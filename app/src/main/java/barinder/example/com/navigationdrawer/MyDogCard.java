package barinder.example.com.navigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.weavebytes.dogsapp.model.DaoSession;
import com.weavebytes.dogsapp.model.DogsDao;

public class MyDogCard extends AppCompatActivity {
    String TAG = "MyDogCard";
    String dogName;
    TextView name, weight, height, birthday, chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dog_card);
        name = (TextView) findViewById(R.id.name);
        weight = (TextView) findViewById(R.id.valweight);
        height = (TextView) findViewById(R.id.valheight);
        birthday = (TextView) findViewById(R.id.valbirthday);
        chip = (TextView) findViewById(R.id.valchip);
        Intent intent = getIntent();
        intent.getExtras();
        dogName = intent.getStringExtra("name");
        Log.d(TAG, TAG + dogName);
        name.setText(dogName);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_dog_card, menu);
        menu.add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MyDogCard.this, Add.class);
                startActivity(intent);
                return true;
            }
        }).setIcon(R.drawable.edit).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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

    public void getDogDetailFromDb(){

        String dogname =  dogName;

        DaoSession daoSession = ((DogsApplication) getApplicationContext()).getDaoSession();
        DogsDao dogsDao = daoSession.getDogsDao();


    }
}
