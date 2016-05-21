package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

public class AddFoodActivity extends AppCompatActivity {

    protected  TextView breakfastv, lunchv, dinnerv;
    protected Button Edit;
    private static ArrayList<String> arrayListBreakfast  =new ArrayList<String>();
    private static ArrayList<String> arrayListLunch  =new ArrayList<String>();
    private static ArrayList<String> arrayListDinner  =new ArrayList<String>();
    private static ArrayList<String> arrayListDailyKalori  =new ArrayList<String>();

    private ArrayAdapter<String> adapterBreakfast;
    private ArrayAdapter<String> adapterLunch;
    private ArrayAdapter<String> adapterDinner;
    private ArrayAdapter<String> adapterDailyKalori;


    SharedPreferences spref;
    private static float kaloriPagi ;
    private static float kaloriSiang ;
    private static float kaloriMalam ;
    SharedPreferences.Editor editor;
    float sisa;

    public AddFoodActivity(){

    }

    public static void setNull (){
        arrayListLunch.clear();
        arrayListBreakfast.clear();
        arrayListDinner.clear();
    }

    public static void addArrayBreakfast(String item){
        boolean same = false;
        for(int i = 0 ; i < arrayListBreakfast.size(); i++) {
            if(arrayListBreakfast.get(i).substring(0, arrayListBreakfast.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = arrayListBreakfast.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                arrayListBreakfast.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            arrayListBreakfast.add(item);
    }

    public  static void addArrayLunch(String item){

        boolean same = false;
        for(int i = 0 ; i < arrayListLunch.size(); i++) {
            if(arrayListLunch.get(i).substring(0, arrayListLunch.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = arrayListLunch.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                arrayListLunch.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            arrayListLunch.add(item);
    }

    public static void addArrayDinner(String item){


        boolean same = false;
        for(int i = 0 ; i < arrayListDinner.size(); i++) {
            if(arrayListDinner.get(i).substring(0, arrayListDinner.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = arrayListDinner.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                arrayListDinner.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            arrayListDinner.add(item);
    }

    public static void addArrayDailyKalori(String item){

            arrayListDailyKalori.add(item);
    }

    public static void AddKaloriPagi(double kaloriFood){
        kaloriPagi =  kaloriPagi + (float)kaloriFood;
    }

    public static void AddKaloriSiang(double kaloriFood){
        kaloriSiang =  kaloriSiang + (float)kaloriFood;
    }

    public static void AddKaloriMalam (double kaloriFood){
        kaloriMalam =  kaloriMalam + (float)kaloriFood;
    }




    public double getKaloriPagi(){
        return this.kaloriPagi;
    }

    public double getKaloriSiang(){
        return this.kaloriSiang;
    }

    public double getKaloriMalam(){

        return this.kaloriMalam;
    }

    public static ArrayList<String> getListBreakfast(){

        return arrayListBreakfast;
    }
    public static ArrayList<String> getArrayListDailyKalori(){

        return arrayListDailyKalori;
    }
    public static ArrayList<String> getListLunch(){
        return arrayListLunch;
    }
    public static ArrayList<String> getListDinner(){

        return arrayListDinner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add Food");

        spref = getApplicationContext().getSharedPreferences("my_data", 0);

        float bmr= spref.getFloat("BMR", 0);
        sisa = bmr - (spref.getFloat("kaloriPagi",0)+spref.getFloat("kaloriSiang",0)+spref.getFloat("kaloriMalam",0));

        TextView consume = (TextView) findViewById(R.id.judul);
        consume.setText("Consumed : "+ (spref.getFloat("kaloriPagi",0)+spref.getFloat("kaloriSiang",0)+ spref.getFloat ("kaloriMalam",0)+ " kal"));
        TextView kurang = (TextView) findViewById(R.id.tampil);
        if(sisa < 0){
            kurang.setText("Excess :  "+ ((-1) *sisa) + "  from " + bmr);
        } else {
            kurang.setText("Remaining : " + sisa + "  from " + bmr);
        }

        ListView listBreakfast = (ListView) findViewById(R.id.listv);
        Set<String> setPagi = spref.getStringSet("SetPagi", null);
        if(setPagi==null){

        }
        else{
            if(arrayListBreakfast.isEmpty()){
                arrayListBreakfast=new ArrayList<>(setPagi);

            }
            adapterBreakfast = new ArrayAdapter<String>(this, R.layout.list_item, arrayListBreakfast);
            listBreakfast.setAdapter(adapterBreakfast);
        }

        ListView listLunch = (ListView) findViewById(R.id.listvlunch);
        Set<String> setSiang = spref.getStringSet("SetSiang", null);
        if(setSiang==null){

        }
        else{
            if(arrayListLunch.isEmpty()){
                arrayListLunch=new ArrayList<>(setSiang);

            }
            adapterLunch = new ArrayAdapter<String>(this, R.layout.list_item, arrayListLunch);
            listLunch.setAdapter(adapterLunch);
        }

        ListView listDinner = (ListView) findViewById(R.id.listvdinner);
        Set<String> setMalam = spref.getStringSet("SetMalam", null);
        if(setMalam==null){

        }
        else{
            if(arrayListDinner.isEmpty()){
                arrayListDinner=new ArrayList<>(setMalam);

            }
            adapterDinner = new ArrayAdapter<String>(this, R.layout.list_item, arrayListDinner);
            listDinner.setAdapter(adapterDinner);
        }


        breakfastv = (TextView) findViewById(R.id.breakfastz);
        breakfastv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                editor = spref.edit();

                editor.putString("jenis", "breakfast");

                editor.commit();
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });

        lunchv = (TextView) findViewById(R.id.lunch);
        lunchv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                editor = spref.edit();

                editor.putString("jenis", "lunch");
                editor.commit();
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });

        dinnerv = (TextView) findViewById(R.id.dinner);
        dinnerv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // request your webservice here. Possible use of AsyncTask and ProgressDialog
                // show the result here - dialog or Toast
                editor = spref.edit();

                editor.putString("jenis", "dinner");
                editor.commit();
                Intent i = new Intent(v.getContext(), FoodActivity.class);
                startActivity(i);
            }

        });
    }
}