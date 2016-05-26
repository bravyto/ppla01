package ppla01.foodo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private static ArrayList<String> arrayListKonsume =new ArrayList<String>();
    private static ArrayList<String> arrayListIdeal =new ArrayList<String>();

    private ArrayAdapter<String> adapterBreakfast;
    private ArrayAdapter<String> adapterLunch;
    private ArrayAdapter<String> adapterDinner;
    SharedPreferences spref;
    private static float kaloriPagi ;
    private static float kaloriSiang ;
    private static float kaloriMalam ;
    SharedPreferences.Editor editor;
    float sisa;

    public AddFoodActivity(){

    }

    public void setNull (){
        this.arrayListLunch.clear();
        this.arrayListBreakfast.clear();
        this.arrayListDinner.clear();
    }
    public  void addListKonsume(String item){

        this.arrayListKonsume.add(item);
    }
    public  void addListIdeal(String item){

        this.arrayListIdeal.add(item);
    }

    public void addArrayBreakfast(String item){
        boolean same = false;
        for(int i = 0 ; i < this.arrayListBreakfast.size(); i++) {
            if(this.arrayListBreakfast.get(i).substring(0, this.arrayListBreakfast.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = this.arrayListBreakfast.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                this.arrayListBreakfast.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            this.arrayListBreakfast.add(item);
    }

    public void addArrayLunch(String item){

        boolean same = false;
        for(int i = 0 ; i < this.arrayListLunch.size(); i++) {
            if(this.arrayListLunch.get(i).substring(0, this.arrayListLunch.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = this.arrayListLunch.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                this.arrayListLunch.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            this.arrayListLunch.add(item);
    }

    public void addArrayDinner(String item){


        boolean same = false;
        for(int i = 0 ; i < this.arrayListDinner.size(); i++) {
            if(this.arrayListDinner.get(i).substring(0, this.arrayListDinner.get(i).lastIndexOf("(") - 1).equals(item.substring(0, item.lastIndexOf("(") - 1))) {
                same = true;
                String word = this.arrayListDinner.get(i);
                double calory1 = Double.parseDouble(word.substring(word.lastIndexOf("(") + 1, word.length() - 1 - 4));
                double calory2 = Double.parseDouble(item.substring(item.lastIndexOf("(") + 1, item.length() - 1 - 4));
                calory1 += calory2;
                this.arrayListDinner.set(i, word.substring(0, word.lastIndexOf("(") - 1) + " (" + calory1 + " kcal)");
            }
        }
        if(!same)
            this.arrayListDinner.add(item);
    }

    public int search (String item,ArrayList<String>arrayListBreakfast){
        int i = 0;
        for(i=0; i<=arrayListBreakfast.size();i++)
        {
            if(arrayListBreakfast.get(i).equals(item)){
                return i;
            }
        }
        return i;

    }
    public void rmArrayPagi (int idx){
        this.arrayListBreakfast.remove(idx);
    }
    public void rmArraySiang (int idx){
        this.arrayListLunch.remove(idx);
    }
    public void rmArrayMalam (int idx){
        this.arrayListDinner.remove(idx);
    }
    public void minKaloriPagi(double kaloriFood){
        this.kaloriPagi =  this.kaloriPagi - (float)kaloriFood;
    }
    public void minKaloriSiang(double kaloriFood){
        this.kaloriSiang =  this.kaloriSiang - (float)kaloriFood;
    }
    public void minKaloriMalam(double kaloriFood){
        this.kaloriMalam =  this.kaloriMalam - (float)kaloriFood;
    }

    public void AddKaloriPagi(double kaloriFood){
        this.kaloriPagi =  this.kaloriPagi + (float)kaloriFood;
    }

    public void AddKaloriSiang(double kaloriFood){
        this.kaloriSiang =  this.kaloriSiang + (float)kaloriFood;
    }

    public void AddKaloriMalam (double kaloriFood){
        this.kaloriMalam =  this.kaloriMalam + (float)kaloriFood;
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

    public ArrayList<String> getListBreakfast(){

        return this.arrayListBreakfast;
    }
    public ArrayList<String> getListLunch(){
        return this.arrayListLunch;
    }
    public ArrayList<String> getListDinner(){

        return this.arrayListDinner;
    }
    public ArrayList<String> getListKonsume(){

        return this.arrayListKonsume;
    }
    public ArrayList<String> getArrayListIdeal(){

        return this.getArrayListIdeal();
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
            listBreakfast.setOnItemClickListener(new ItemListPagi());
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
            listLunch.setOnItemClickListener(new ItemListSiang());
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
            listDinner.setOnItemClickListener(new ItemListMalam());
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
    class ItemListPagi implements AdapterView.OnItemClickListener{
        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            spref = getApplicationContext().getSharedPreferences("my_data", 0);
            editor = spref.edit();
            editor.putString("delete","pagi");
//            ViewGroup vg = (ViewGroup) view;
            TextView yv= (TextView) findViewById(R.id.label);
            Toast.makeText(AddFoodActivity.this, "Data Adalah " +yv.getText().toString(), Toast.LENGTH_SHORT).show();
            editor.putString("makanan", yv.getText().toString());
            editor.commit();
            Intent i = new Intent(view.getContext(), DeleteActivity.class);
            startActivity(i);

        }
    }
    class ItemListSiang implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            spref = getApplicationContext().getSharedPreferences("my_data", 0);
            editor = spref.edit();
            editor.putString("delete","siang");
//            ViewGroup vg = (ViewGroup) view;
            TextView yv= (TextView) findViewById(R.id.label);
            Toast.makeText(AddFoodActivity.this, "Data Adalah " +yv.getText().toString(), Toast.LENGTH_SHORT).show();
            editor.putString("makanan", yv.getText().toString());
            editor.commit();
            Toast.makeText(AddFoodActivity.this, "kategori " +spref.getString("delete"," "), Toast.LENGTH_SHORT).show();
            Toast.makeText(AddFoodActivity.this, "makanan " +spref.getString("makanan"," "), Toast.LENGTH_SHORT).show();

            Intent i = new Intent(view.getContext(), DeleteActivity.class);
            startActivity(i);

        }
    }
    class ItemListMalam implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            spref = getApplicationContext().getSharedPreferences("my_data", 0);
            editor = spref.edit();
            editor.putString("delete","malam");
//            ViewGroup vg = (ViewGroup) view;
            TextView yv= (TextView) findViewById(R.id.label);
            Toast.makeText(AddFoodActivity.this, "Data Adalah " +yv.getText().toString(), Toast.LENGTH_SHORT).show();
            editor.putString("makanan", yv.getText().toString());
            editor.commit();
            Intent i = new Intent(view.getContext(), DeleteActivity.class);
            startActivity(i);

        }
    }
}