package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1;
    Spinner spn2;
    Button btnGo;
    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.button);


        alNumbers = new ArrayList<>();

        //Get the string-array and store as an Array
        String[] strNumbers = getResources().getStringArray(R.array.RPsubCategory);

        //Convert Array to List and add to the ArrayList
        alNumbers.addAll(Arrays.asList(strNumbers));


        aaNumbers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alNumbers);

        spn2.setAdapter(aaNumbers);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), WebActivity.class);


//                if(spn1.getSelectedItemPosition() == 0 && spn2.getSelectedItemPosition() == 0){
//                    String url = "https//wwww.rp.edu.sg";
//                    intent.putExtra("url",url);
//                    startActivity(intent);
//                }
//                else if(spn1.getSelectedItemPosition() == 0 && spn2.getSelectedItemPosition() == 1){
//                    String url = "https://www.rp.edu.sg/student-life";
//                    intent.putExtra("url",url);
//                    startActivity(intent);
//                }
//                else if(spn1.getSelectedItemPosition() == 1 && spn2.getSelectedItemPosition() == 0){
//                    String url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
//                    intent.putExtra("url",url);
//                    startActivity(intent);
//                }
//                else if(spn1.getSelectedItemPosition() == 1 && spn2.getSelectedItemPosition() == 1){
//                    String url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
//                    intent.putExtra("url",url);
//                    startActivity(intent);
//                }
                String[][] sites ={
                        {
                            "https://www.rp.edu.sg",
                                "https://www.rp.edu.sg/student-life"
                        },{
                        "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                        "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                }
                };
                String url = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        alNumbers.clear();
                        //Get the string-array and store as an Array
                        String[] strNumbers = getResources().getStringArray(R.array.RPsubCategory);

                        //Convert Array to List and add to the ArrayList
                        alNumbers.addAll(Arrays.asList(strNumbers));
                        spn2.setSelection(0);
                        break;
                    case 1:
                        alNumbers.clear();
                        //Get the string-array and store as an Array
                        String[] strNumbers1 = getResources().getStringArray(R.array.SOIsubCategory);

                        //Convert Array to List and add to the ArrayList
                        alNumbers.addAll(Arrays.asList(strNumbers1));
                        spn2.setSelection(0);
                        break;
                }
                aaNumbers.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1b: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1c: Add the key-value pair
        int categoryPos = spn1.getSelectedItemPosition();
        int subCategoryPos = spn2.getSelectedItemPosition();


        prefEdit.putInt("cat", categoryPos);
        prefEdit.putInt("subcat", subCategoryPos);

        //Step 1d: Call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key "greeting from the SharedPreferences object
        int cat = prefs.getInt("cat", 0);
        int subcat = prefs.getInt("subcat", 0);

        spn1.setSelection(cat);
        spn2.setSelection(subcat);



    }




}
