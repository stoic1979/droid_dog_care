package barinder.example.com.navigationdrawer;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weavebytes.dogsapp.model.DaoSession;
import com.weavebytes.dogsapp.model.Dogs;
import com.weavebytes.dogsapp.model.DogsDao;

import java.util.List;

public class MyDogCard extends AppCompatActivity implements View.OnClickListener{
    String TAG = "MyDogCard";
    String dogName, breed, Sex;
    TextView name, weight, height, birthday, chip;
    RelativeLayout btnName, btnWeight, btnHeight, btnBirthday, btnChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dog_card);
        name        = (TextView)        findViewById(R.id.name);
        weight      = (TextView)        findViewById(R.id.valweight);
        height      = (TextView)        findViewById(R.id.valheight);
        birthday    = (TextView)        findViewById(R.id.valbirthday);
        chip        = (TextView)        findViewById(R.id.valchip);
        btnName     = (RelativeLayout)  findViewById(R.id.btnDogsCard);
        btnWeight     = (RelativeLayout)  findViewById(R.id.btnWeight);
        btnHeight     = (RelativeLayout)  findViewById(R.id.btnHeight);
        btnBirthday     = (RelativeLayout)  findViewById(R.id.btnBday);
        btnChip     = (RelativeLayout)  findViewById(R.id.btnChip);

        btnName.setOnClickListener(this);
        btnBirthday.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
        btnWeight.setOnClickListener(this);
        btnChip.setOnClickListener(this);

        Intent intent = getIntent();
        intent.getExtras();
        dogName = intent.getStringExtra("name");
        Log.d(TAG, TAG + dogName);
        name.setText(dogName);
        getDogDetailFromDb();

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

    public void getDogDetailFromDb() {

        String dogname = dogName;
        Log.d(TAG, "dog is" + dogname);
        DaoSession daoSession = ((DogsApplication) getApplicationContext()).getDaoSession();
        DogsDao dogsDao = daoSession.getDogsDao();
        List<Dogs> dogsList = dogsDao.queryBuilder()
                .where(DogsDao.Properties.Name.like("%" + dogName + "%"))
                .list();
        Log.d(TAG, "dogslist is" + dogsList);
        for (int i = 0; i < dogsList.size(); i++) {
            Dogs dogsData = new Dogs();
            dogsList.get(i).getName();
            dogsList.get(i).getWeight();
            dogsList.get(i).getWithers();
            dogsList.get(i).getBirthDate();
            dogsList.get(i).getChip();
            weight.setText(dogsList.get(i).getWeight() + " Kg");
            height.setText(dogsList.get(i).getWithers() + " cm");
            birthday.setText(dogsList.get(i).getBirthDate());
            chip.setText(dogsList.get(i).getChip());
            breed = dogsList.get(i).getBreed();
            Sex = dogsList.get(i).getSex();
            Log.d(TAG, " name" + dogsList.get(i).getName() + " weight" + dogsList.get(i).getWeight() + "withers" + dogsList.get(i).getWithers());
        }
    }

    public void updateDogInfo() {

        Intent intent = new Intent(MyDogCard.this, UpdateDogInfo.class);
        intent.putExtra("name", name.getText());
        intent.putExtra("height", height.getText());
        intent.putExtra("birthday", birthday.getText());
        intent.putExtra("chip", chip.getText());
        intent.putExtra("breed", breed);
        intent.putExtra("Sex", Sex);
        intent.putExtra("weight", weight.getText());
        startActivity(intent);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnDogsCard:
                updateDogInfo();
                break;

            case R.id.btnWeight:
                updateDogInfo();

                break;

            case R.id.btnHeight:
                updateDogInfo();

                break;

            case R.id.btnChip:
                updateDogInfo();

                break;

            case R.id.btnBday:
                updateDogInfo();

                break;

        }

    }
}
