package ppla01.foodo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences spref;
    SharedPreferences.Editor editor;
    protected EditText user_name;
    String  nama, tinggi, beratnow, gender, birthdate;
    boolean valid = true ;
    protected EditText user_birthdate;
    protected EditText user_weight;
    protected EditText user_height;
    protected Button submit_profile;
    protected ImageView more_info;
    RadioButton pria, wanita;
    Spinner Aktivitas;
    double indeksMassa;
    double massa = 0;
    String [] separate;
    String Activity;

    Calendar myCalendar = Calendar.getInstance();

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

    public void showDatePicker(View v) {
        new DatePickerDialog(MainActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        user_birthdate.setText(sdf.format(myCalendar.getTime()));
        user_birthdate.setError(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DC424C")));
        spref = getSharedPreferences("my_data", 0);
        if (spref.getString("log", "").equals("1")) {
            setTitle("Edit Personal Info");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            scheduleAlarm(findViewById(android.R.id.content));
            setTitle("Input Personal Info");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        more_info = (ImageView) findViewById(R.id.moreinfo);
        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Low : Get little to no exercise. Normally will not make you sweat. You sit a lot. \n\nLight : You walk a lot. And you move a lot. \n\nModerate : Rarely sit. Have an exercise several times a week. \n\nActive : Have a routine exercise 50 minutes a day such as jogging. Another example : cleaning home. \n\nHigh : You push yourself really hard. Usually have a really intense activity, like : swimming, heavy lifting, mountain biking.", Toast.LENGTH_LONG).show();
            }
        });
        user_name = (EditText) findViewById(R.id.name);
        user_birthdate = (EditText) findViewById(R.id.birthdate);
        user_weight = (EditText) findViewById(R.id.weight);
        user_height = (EditText) findViewById(R.id.height);
        pria = (RadioButton) findViewById(R.id.pria);
        wanita = (RadioButton) findViewById(R.id.wanita);
        Aktivitas = (Spinner)findViewById(R.id.spin);
        Aktivitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (Aktivitas.getSelectedItem().toString().equals("Light Activity")) {
                    indeksMassa = 1.375;
                    Activity = "Light Activity";
                    editor.putFloat("Aktivity",(float)indeksMassa);
                    editor.commit();
                }
                else if (Aktivitas.getSelectedItem().toString().equals("Moderate Activity")) {
                    indeksMassa = 1.55;
                    Activity = "Moderate Activity";
                    editor.putFloat("Aktivity",(float)indeksMassa);
                    editor.commit();
                }
                else if (Aktivitas.getSelectedItem().toString().equals("Active Activity")) {
                    indeksMassa = 1.725;
                    Activity ="Active Activity";
                    editor.putFloat("Aktivity",(float)indeksMassa);
                    editor.commit();
                }
                else if (parent.getSelectedItem().toString().equals("Extreme Activity")) {
                    indeksMassa = 1.9;
                    Activity = "Extreme Activity";
                    editor.putFloat("Aktivity",(float)indeksMassa);
                    editor.commit();
                }
                else {
                    indeksMassa = 1.2;
                    Activity = "Low Activity";
                    editor.putFloat("Aktivity",(float)indeksMassa);
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        user_name.setText(spref.getString("nama", ""), null);
        user_birthdate.setText(spref.getString("umur", ""), null);
        user_height.setText(spref.getString("tinggi", ""), null);
        user_weight.setText(spref.getString("beratnow", ""), null);

//        Typeface font = Typeface.createFromAsset(getAssets(), "Yellowtail.ttf");
//        TextView tv=(TextView) findViewById(R.id.textView4);
//        tv.setTypeface(font);

        String gen = spref.getString("gender", "");
        if (gen.equals("Wanita")){
            wanita.setChecked(true);
        } else {
            pria.setChecked(true);
        }

//        massa = spref.getFloat("Aktivity",0);
        String aktivitas = spref.getString("Activity", "");
        if(aktivitas.equals("Extreme Activity")){
            Aktivitas.setSelection(4);
        } else if(aktivitas.equals("Light Activity")){
            Aktivitas.setSelection(1);
        }else if(aktivitas.equals("Moderate Activity")){
            Aktivitas.setSelection(2);
        }else if(aktivitas.equals("Active Activity")){
            Aktivitas.setSelection(3);
        }else{
            Aktivitas.setSelection(0);
        }
        submit_profile = (Button) findViewById(R.id.submitProfile);
        submit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spref = getSharedPreferences("my_data", 0);
                editor = spref.edit();
                valid = true;

                nama = user_name.getText().toString();
                tinggi = user_height.getText().toString();
                birthdate = user_birthdate.getText().toString();
                beratnow = user_weight.getText().toString();
                if (nama.equals("")) {
                    user_name.setError("Type your name");
                    valid = false;
                }
                if (birthdate.equals("")) {
                    user_birthdate.setError("Set your birthdate");
                    valid = false;
                }
                if (tinggi.equals("")) {
                    user_height.setError("Type your height in cm");
                    valid = false;
                }
                if (beratnow.equals("")) {
                    user_weight.setError("Type your weight in kg");
                    valid = false;
                }
                if(pria.isChecked()) {
                    gender = "Pria";
                } else {
                    gender = "Wanita";
                }
                editor.putString("nama", nama);
                editor.putString("tinggi", tinggi);
                editor.putString("umur", birthdate);
                editor.putString("beratnow", beratnow);
                editor.putString("gender", gender);
                editor.putFloat("Aktivity", (float) indeksMassa);
                editor.putString("Activity", Activity);
                editor.commit();

                if (valid) {
                    massa = spref.getFloat("Aktivity",0);
                    double berat = 0.0;
                    if(!spref.getString("beratnow", "").equals(""))
                        berat = Double.parseDouble(spref.getString("beratnow", ""));
                    double tinggi = 0.0;
                    if(!spref.getString("beratnow", "").equals(""))
                        tinggi = Double.parseDouble(spref.getString("tinggi", ""));
                    int curent = Calendar.getInstance().get(Calendar.YEAR);
                    separate = birthdate.split("/");
                    double BMR=0;
                    String gen = spref.getString("gender", "");

                    if(gen.equals("Pria")){
                        BMR = 66.473 + (13.7516 * berat) + (5 * tinggi) - (6.755 * (curent-Double.parseDouble(separate[2])) ) *  massa;
                    }
                    else{
                        BMR = 655.095 + (9.5634 * berat) + (1.8496 * tinggi ) - (4.6756 * (curent - Double.parseDouble(separate[2])) * massa) ;
                    }
                    editor.putFloat("BMR", (float) BMR);
                    editor.commit();

                    double BMRr = spref.getFloat("BMR",0);
//                    Toast.makeText(v.getContext(), "Calori adalah " +BMRr, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(v.getContext(), "Tahun Lahir  " +separate[2], Toast.LENGTH_SHORT).show();
//                    Toast.makeText(v.getContext(), "indeksmassa adalah "+ spref.getFloat("Aktivity",0),Toast.LENGTH_SHORT).show();

                    if (!spref.getString("log", "").equals("1")) {

                        if(!spref.getString("fromHome","").equals(null) && spref.getString("fromHome","").equals("1")){
                            editor = spref.edit();
                            editor.putString("fromHome", "");
                            editor.putString("log", "1");
                            editor.commit();
                            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(v.getContext(), JadwalMakanActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    } else {

                        Intent intent = new Intent(v.getContext(), Main2Activity.class);
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });
    }


    public void scheduleAlarm(View V)
    {
        // time at which alarm will be scheduled here alarm is scheduled at 1 day from current time,
        // we fetch  the current time in milliseconds and added 1 day time
        // i.e. 24*60*60*1000= 86,400,000   milliseconds in a day
        Long time = new GregorianCalendar().getTimeInMillis()+7*24*60*60*1000;

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers and
        //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
        Intent intentAlarm = new Intent(this, AlarmReciever.class);

        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    }
//<<<<<<< HEAD
    protected void onStart(){
        super.onStart();
    }
    public  double getBMR(){
        spref = getSharedPreferences("my_data", 0);
        editor = spref.edit();
        double berat =Double.parseDouble(spref.getString("beratnow",""));
        double tinggi = Double.parseDouble(spref.getString("tinggi",""));
        int curent = Calendar.getInstance().get(Calendar.YEAR);
        int year = myCalendar.get(Calendar.YEAR);
        double massa = spref.getFloat("Aktivity",0);


        double BMR=0;
        String gen = spref.getString("gender", "");
        if(gen.equals("Pria")){
           BMR = 66.473 + (13.7516 * berat) + (5 * tinggi) - (6.755 * (curent-year) ) *  massa;

        }
        else{
            BMR = 655.095 + (9.5634 * berat) + (1.8496 * tinggi )- (4.6756 *(curent-year) * massa) ;
        }
        editor.putFloat("BMR", (float)BMR);
        editor.commit();
        return BMR;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                //System.out.println("Mausk");
                //Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                this.finish();
                //startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
//=======
//>>>>>>> refs/remotes/origin/master
}
