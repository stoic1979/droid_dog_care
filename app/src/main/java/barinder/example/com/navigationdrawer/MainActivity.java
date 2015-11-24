package barinder.example.com.navigationdrawer;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.weavebytes.dogsapp.model.DaoSession;
import com.weavebytes.dogsapp.model.Dogs;
import com.weavebytes.dogsapp.model.DogsDao;

import java.util.ArrayList;
import java.util.List;
/// function
public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{
    String TITLES[] = {"Remove", "Settings", "Online Backup", "Backup/Restore", "Promo Code", "Info"};
    int ICONS[] = {R.drawable.remove,
            R.drawable.setting,
            R.drawable.onlinebackup,
            R.drawable.backuprestore,
            R.drawable.promocode,
            R.drawable.info};
    private Toolbar toolbar;                              // Declaring the Toolbar Object
    RecyclerView mRecyclerView;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    private Spinner spinner_nav;
    private ImageView img_add;
    private Button btn_add;
    private  String TAG = " MainActivity : ";
    private int 		 pos = 0;
    TextView dogName , dogAge;
    ArrayList<Dogs> model = new ArrayList<Dogs>();
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayList<String> array = new ArrayList<String>();
    ArrayList<Integer> agearray = new ArrayList<Integer>();
    RelativeLayout dogsCard , memo, medicalRecords, veternaries;
   int birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar        =      (Toolbar)       findViewById(R.id.tool_bar);
        spinner_nav    =      (Spinner)       findViewById(R.id.spinner_nav);
        img_add        =      (ImageView)     findViewById(R.id.img_add);
        btn_add        =      (Button)        findViewById(R.id.btnAdd);
        dogName        =      (TextView)      findViewById(R.id.dogName);
        dogAge         =       (TextView)     findViewById(R.id.dogAge);
        mRecyclerView  =      (RecyclerView)  findViewById(R.id.RecyclerView);
        Drawer         =      (DrawerLayout)  findViewById(R.id.DrawerLayout);
        dogsCard       =      (RelativeLayout)findViewById(R.id.btnDogsCard);
        memo       =      (RelativeLayout)findViewById(R.id.btnMemo);
        medicalRecords       =      (RelativeLayout)findViewById(R.id.btnMedicalRec);
        veternaries       =      (RelativeLayout)findViewById(R.id.btnVeternaries);

        /*listeners*/
        dogsCard.setOnClickListener(this);
        memo.setOnClickListener(this);
        medicalRecords.setOnClickListener(this);
        veternaries.setOnClickListener(this);


        mLayoutManager =      new LinearLayoutManager(this);   // Creating a layout Manager
        mAdapter       =      new MyAdapter(TITLES, ICONS); // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mRecyclerView.setHasFixedSize(true); // Letting the system know that the list objects are of fixed size
        mRecyclerView.setAdapter(mAdapter);     // Setting the adapter to RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);   // Setting the layout Manager
        setSupportActionBar(toolbar);

        mDrawerToggle  =  new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        }; // Drawer Toggle Object Made

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        model = getAllDataFromDB();
        addDogsToSpinner();
        dispalyingDogsDetails();
    }

    /*Adding Dogs To Spinner*/
    public  void addDogsToSpinner() {

        if (model.size() != 0) {
            for (int i = 0; i < model.size(); i++) {

                array.add(model.get(i).getName());
                Log.d(TAG, "names" + array);
                agearray.add(model.get(i).getBirthDate());
                Log.d(TAG, "BirthDate" + agearray);
                Log.d(TAG, "birthdate" + model.get(i).getBirthDate());
                birthdate = model.get(i).getBirthDate();

            }
            spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_nav.setAdapter(spinnerArrayAdapter);
            pos = spinner_nav.getSelectedItemPosition();
            spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // On selecting a spinner item
                    String item = adapterView.getItemAtPosition(i).toString();
                    dogName.setText(item);
                    // Showing selected spinner item
                    Toast.makeText(getApplicationContext(), "Selected  : " + item,
                            Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    // function returning the Dogs Details From Database
    public  ArrayList<Dogs> getAllDataFromDB(){
        ArrayList<Dogs> array = new ArrayList<Dogs>();

        DaoSession daoSession  =  ((DogsApplication)getApplicationContext()).getDaoSession();
        DogsDao dogsDao        =  daoSession.getDogsDao();
        List<Dogs> dogsArray = dogsDao.queryBuilder().list();

        for (int i = 0 ; i<dogsArray.size();i++){
            Dogs data = new Dogs();
            data.setId				(dogsArray.get(i).getId());
            data.setName		(dogsArray.get(i).getName());
            data.setWithers	(dogsArray.get(i).getWithers());
            data.setBirthDate(dogsArray.get(i).getBirthDate());
            data.setChip		(dogsArray.get(i).getChip());
            data.setWeight		(dogsArray.get(i).getWeight());
            data.setBreed			(dogsArray.get(i).getBreed());
            data.setSex			(dogsArray.get(i).getSex());
            array.add(data);
        }
        Log.d(TAG,"dogs Array"+array);
        return array;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Intent intent = new Intent(getApplicationContext(), Add.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

     public  void dispalyingDogsDetails(){
       dogAge.setText(String.valueOf(birthdate));
     }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnDogsCard:
                Toast.makeText(getApplicationContext(),"Dog Card",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyDogCard.class);
                intent.putExtra("name",dogName.getText().toString());
                startActivity(intent);
                return;
            case R.id.btnMedicalRec:
                Toast.makeText(getApplicationContext(),"Dog Medical Record",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this,MedicalRecords.class);
                startActivity(intent1);
                return;

            case R.id.btnMemo:
                Toast.makeText(getApplicationContext(),"Dog Memo",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this,Memo.class);
                startActivity(intent2);
                return;
            case R.id.btnVeternaries:

                Toast.makeText(getApplicationContext(),"Dog Veternaries",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(MainActivity.this,Veternaries.class);
                startActivity(intent3);
                return;




        }

    }
}
