package ppla01.foodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//<<<<<<< HEAD
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
//=======
import com.github.clans.fab.FloatingActionMenu;
//>>>>>>> refs/remotes/origin/master
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    SharedPreferences spref;
    SharedPreferences.Editor editor;
   // AddFoodActivity food = new AddFoodActivity();;
    ArrayList<String> listKonsum = new ArrayList<>();
    Set<String> setKonsum = new HashSet<>();

    int count = 0;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private int activeTabNo;
    private TabLayout tabLayout;
    private Menu mOptionsMenu;

    FloatingActionMenu fab;

    private static Toast toast;

    private static float[] yData = new float[4];
    private static String[] xData = { "Not consumed", "Breakfast", "Lunch", "Dinner"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spref = getApplicationContext().getSharedPreferences("my_data", 0);
        editor = spref.edit();

        setTitle("Home");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_stat);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_eaten);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_reminder);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_profile);
        int tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.activeTab);
        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.notActiveTab);
        for(int i = 1; i < 4; i++) {
            tabLayout.getTabAt(i).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }
        fab = (FloatingActionMenu) findViewById(R.id.menu);
        fab.setVisibility(View.INVISIBLE);
        fab.hideMenuButton(true);

        com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item);
        com.github.clans.fab.FloatingActionButton fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item1);
        com.github.clans.fab.FloatingActionButton fab3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item2);


        long INTERVAL_MSEC = 60000;
        Timer timer = new Timer();
//        timer.schedule(
//                new
//        );

//        TimerTask task = new TimerTask()
//        {
//            public void run() {
//                coba();
//            }
//        };
//        timer.scheduleAtFixedRate(task, 0, INTERVAL_MSEC);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.close(true);
                SharedPreferences spref = getApplicationContext().getSharedPreferences("my_data", 0);
                SharedPreferences.Editor editor = spref.edit();
                editor.putString("jenis", "breakfast");
                editor.commit();
                Intent intent = new Intent(Main2Activity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.close(true);
                SharedPreferences spref = getApplicationContext().getSharedPreferences("my_data", 0);
                SharedPreferences.Editor editor = spref.edit();
                editor.putString("jenis", "lunch");
                editor.commit();
                Intent intent = new Intent(Main2Activity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.close(true);
                SharedPreferences spref = getApplicationContext().getSharedPreferences("my_data", 0);
                SharedPreferences.Editor editor = spref.edit();
                editor.putString("jenis", "dinner");
                editor.commit();
                Intent intent = new Intent(Main2Activity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.activeTab);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        MenuInflater inflater = getMenuInflater();
                        int tabNo = tabLayout.getSelectedTabPosition();
                        mOptionsMenu.clear();
                        if (tabNo == 3) {
                            fab.hideMenuButton(true);
                            inflater.inflate(R.menu.profile_menu, mOptionsMenu);
                            setTitle("Profile");
                        }
                        else if (tabNo == 2) {
                            fab.hideMenuButton(true);
                            inflater.inflate(R.menu.reminder_menu, mOptionsMenu);
                            setTitle("Reminder");
                        }
                        else if (tabNo == 1) {
                            fab.setVisibility(View.VISIBLE);
                            fab.showMenuButton(true);
                            inflater.inflate(R.menu.main, mOptionsMenu);
                            setTitle("Today's Food");
                        }
                        else {
                            fab.hideMenuButton(true);
                            inflater.inflate(R.menu.menu_main2, mOptionsMenu);
                            setTitle("Home");
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(Main2Activity.this, R.color.notActiveTab);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) tab.setCustomView(R.layout.view_home_tab);
        }
        //Toast.makeText(Main2Activity.this, "tanggal sekarang di spref " + date, Toast.LENGTH_SHORT).show();


       // =editor = spref.edit();


        int curent = Calendar.getInstance().get(Calendar.MINUTE);
//
        int date =  spref.getInt("tanggal", 0);
//
        float kalori = spref.getFloat("kaloriPagi", 0)  + spref.getFloat("kaloriSiang", 0)+ spref.getFloat("kaloriMalam", 0);

//

        float BMR = spref.getFloat("BMR", 0);

        if(curent != date) {

            AddFoodActivity.addArrayDailyKalori(date + ";" +kalori + ";" + BMR);

            listKonsum =  AddFoodActivity.getArrayListDailyKalori();

            setKonsum.addAll(listKonsum);
            spref.edit();
            editor.remove("setKonsum");
            editor.apply();
            editor.putStringSet("setKonsum", setKonsum);
            editor.apply();

            editor.putStringSet("SetSiang", null);
            editor.putStringSet("SetPagi", null);
            editor.putStringSet("SetMalam", null);
            editor.putFloat("kaloriPagi", 0);
            editor.putFloat("kaloriSiang", 0);
            editor.putFloat("kaloriMalam", 0);

            editor.putInt("tanggal", curent);


            AddFoodActivity.setNull();
            editor.commit();
        }

    }



//    public void coba(){
//        int curent = Calendar.getInstance().get(Calendar.MINUTE);
//
//        int date =  spref.getInt("tanggal", 0);
//
//
//
//
//
//
//        // Toast.makeText(Main2Activity.this, "tanggal sekarang  " + curent, Toast.LENGTH_SHORT).show();
//
//
//        float tempA = 0 ;
//        float tempB = 0;
//        float tempC = 0;
//
//        if(curent == date){
//            editor = spref.edit();
//            tempA = spref.getFloat("kaloriPagi", 0);
//            tempB = spref.getFloat("kaloriSiang", 0);
//            tempC = spref.getFloat("kaloriMalam", 0);
////            tempA =  consumedFoodBreakfast;
////            tempB = consumedFoodLunch;
////            tempC = consumedFoodDinner;
//            int tempaaaa = (int) tempA + (int)  tempB + (int) tempC;
//
//        }
//        else{
//            food = new AddFoodActivity();
//
//            float BMR = spref.getFloat("BMR", 0);
//            food.addArrayDailyKalori( "; " +date + ";" + tempA + ";" + tempB+ ";" + tempC + ";" + BMR);
//            listKonsum = food.getArrayListDailyKalori();
//            //listKonsum.add("haha");
//            //listKonsum.add("yeye");
//            setKonsum.addAll(listKonsum);
//            editor.putStringSet("setKonsum", setKonsum);
//            editor.putStringSet("SetSiang", null);
//            editor.putStringSet("SetPagi", null);
//            editor.putStringSet("SetMalam", null);
//            editor.putFloat("kaloriPagi", 0);
//            editor.putFloat("kaloriSiang", 0);
//            editor.putFloat("kaloriMalam", 0);
//            editor.commit();
//            editor.putInt("tanggal", curent);
//
//
//            food.setNull();
//            editor.commit();
//
//
//        }
//



   // }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mOptionsMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        return true;
    }

    protected void onStart(){
        super.onStart();

        SharedPreferences spref = getApplicationContext().getSharedPreferences("my_data", 0);
        if (!spref.getString("log","").equals("1") && !spref.getString("log","").equals("2")){
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            finish();
            startActivity(intent);
        } else if (spref.getString("log","").equals("2")) {
            Intent intent = new Intent(Main2Activity.this, WeekEvaluationActivity.class);
            finish();
            startActivity(intent);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_edit_reminder) {
            Intent intent = new Intent(Main2Activity.this, JadwalMakanActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_favorite) {
            Intent intent = new Intent(Main2Activity.this, RecommendationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return null;
                case 1:
                    return null;
                case 2:
                    return null;
                case 3:
                    return null;
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        SharedPreferences spref;
        SharedPreferences.Editor editor;
        String  nama, tinggi, umur, beratnow, gender, aktivitasnya;
        double bawahBMI,atasBMI,ideal1,ideal2;
        protected  TextView breakfastv, lunchv, dinnerv;
        protected Button Edit;
        private static  ArrayList<String> arrayListBreakfast  =new ArrayList<String>();
        private static ArrayList<String> arrayListLunch  =new ArrayList<String>();
        private static ArrayList<String> arrayListDinner  =new ArrayList<String>();
       // private static ArrayList<String> arrayListTemp =new ArrayList<String>();
        private ArrayAdapter<String> adapterBreakfast;
        private ArrayAdapter<String> adapterLunch;
        private ArrayAdapter<String> adapterDinner;
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;


            if(getArguments().getInt(ARG_SECTION_NUMBER) == 4) {
                rootView = inflater.inflate(R.layout.fragment_profile, container, false);
                spref = getContext().getSharedPreferences("my_data", 0);

                tinggi = spref.getString("tinggi", "");
                nama = spref.getString("nama", "");
                umur = spref.getString("umur", "");
                beratnow = spref.getString("beratnow", "");
                gender = spref.getString("gender", "");
                aktivitasnya=spref.getString("Activity","");

                TextView tinggiv = (TextView) rootView.findViewById(R.id.height);
                tinggiv.setText(tinggi + " cm");

                TextView namav = (TextView) rootView.findViewById(R.id.name);
                namav.setText(nama);

                TextView umurv = (TextView) rootView.findViewById(R.id.birthdate);
                umurv.setText(umur);

                TextView beratnowv = (TextView) rootView.findViewById(R.id.weight);
                beratnowv.setText(beratnow + " kg");

                TextView genderv = (TextView) rootView.findViewById(R.id.gender);
                genderv.setText(gender);

                TextView aktivitas= (TextView) rootView.findViewById(R.id.activity);
                aktivitas.setText(aktivitasnya);

//                TextView beratIdeal= (TextView) rootView.findViewById(R.id.weightIdeal);
                double BMI = Double.parseDouble(beratnow)/(Double.parseDouble(tinggi)/100*Double.parseDouble(tinggi)/100);

                if (BMI< 18.5){
                    atasBMI = 18.5;
                    bawahBMI = 18.5;
                }else if (BMI >  18.5 || BMI <= 24.9){
                    bawahBMI = 18.5;
                    atasBMI = 24.9;
                }
                else if (BMI >= 25 || BMI <= 29.9){
                    bawahBMI = 25;
                    atasBMI = 29.9;
                }else if (BMI >= 30 || BMI <= 34.9){
                    bawahBMI = 30;
                    atasBMI = 34.9;
                }
                else if (BMI >= 35 || BMI <= 39.9){
                    bawahBMI = 35;
                    atasBMI = 39.9;
                }
                else if (BMI >= 40 ){
                    bawahBMI = 40;
                    atasBMI = 40;
                }

                ideal1 = Math.round(bawahBMI * Double.parseDouble(tinggi)/100*Double.parseDouble(tinggi)/100);
                ideal2 = Math.round(atasBMI * Double.parseDouble(tinggi)/100*Double.parseDouble(tinggi)/100);

                if(ideal1 == ideal2){
//                    beratIdeal.setText(""+ideal1 +" kg");
                }
                else{
//                    beratIdeal.setText(""+ideal1 +" - " + ideal2 +" kg");
                }
            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                rootView = inflater.inflate(R.layout.fragment_reminder, container, false);

                spref = getContext().getSharedPreferences("my_data", 0);

                TextView breakfast= (TextView) rootView.findViewById(R.id.breakfast);
                breakfast.setText(spref.getString("pagi", ""));
                TextView lunch= (TextView) rootView.findViewById(R.id.lunch);
                lunch.setText(spref.getString("siang", ""));
                TextView dinner= (TextView) rootView.findViewById(R.id.dinner);
                dinner.setText(spref.getString("malam", ""));
            } else {
                if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                    rootView = inflater.inflate(R.layout.fragment_food, container, false);

                    spref = getContext().getSharedPreferences("my_data", 0);

                    float bmr = spref.getFloat("BMR", 0);
                    float sisa = bmr - (spref.getFloat("kaloriPagi", 0) + spref.getFloat("kaloriSiang", 0) + spref.getFloat("kaloriMalam", 0));
                    float lebih = sisa;
                    float totalKonsumsi = bmr - sisa;


                    TextView textBMR = (TextView) rootView.findViewById(R.id.textBMR);
                    textBMR.setText((int) bmr + " kcal");

                    TextView textSisa = (TextView) rootView.findViewById(R.id.textSisa);
                    textSisa.setText((int) totalKonsumsi + " kcal");
                    PieChart pieChart = (PieChart) rootView.findViewById(R.id.chart);
// creating data values


                    pieChart.setDrawHoleEnabled(true);
                    pieChart.setHoleRadius(7);
                    pieChart.setTransparentCircleRadius(10);

                    // enable rotation of the chart by touch
                    pieChart.setRotationAngle(0);
                    pieChart.setRotationEnabled(true);
                    pieChart.setDescription("");

                    yData[1] = spref.getFloat("kaloriPagi", 0);
                    yData[2] = spref.getFloat("kaloriSiang", 0);
                    yData[3] = spref.getFloat("kaloriMalam", 0);
                    if (sisa < 0.0) {
                        lebih = totalKonsumsi - bmr;
                        yData[0] = lebih;
                        xData[0] = "Kelebihan Kalori";
                    } else {
                        yData[0] = sisa;
                        xData[0] = "Not consumed";
                    }

                    // add data
                    final ArrayList<String> arrayX = addData(pieChart);

                    // set a chart value selected listener
                    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                        @Override
                        public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                            // display msg when value selected
                            int indeks = e.getXIndex();
//                        if(xData[0].equalsIgnoreCase("Kelebihan Kalori")){
//                            indeks += 1;
//                        }

                            System.out.println("Index: " + indeks);
                            if (e == null)
                                return;

                            else {
                                if (arrayX.get(indeks).equalsIgnoreCase("breakfast")) {
//                                    Set<String> setPagi = spref.getStringSet("SetPagi", null);
//                                    PlaceholderFragment.showToast(getContext(), setPagi, "Breakfast");
                                    Set<String> setBaru = spref.getStringSet("setKonsum",null);

                                    PlaceholderFragment.showToast(getContext(), setBaru,"Breakfast");
                                } else if (arrayX.get(indeks).equalsIgnoreCase("lunch")) {
                                    Set<String> setSiang = spref.getStringSet("SetSiang", null);
                                    PlaceholderFragment.showToast(getContext(), setSiang, "Lunch");
                                } else if (arrayX.get(indeks).equalsIgnoreCase("dinner")) {
                                    Set<String> setMalam = spref.getStringSet("SetMalam", null);
                                    PlaceholderFragment.showToast(getContext(), setMalam, "Dinner");
                                } else if (arrayX.get(indeks).equalsIgnoreCase("Not consumed")) {

                                } else if (arrayX.get(indeks).equalsIgnoreCase("Kelebihan Kalori")) {

                                }
                            }
                        }

                        @Override
                        public void onNothingSelected() {

                        }
                    });


                    // customize legends
                    Legend l = pieChart.getLegend();
                    l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
                    l.setXEntrySpace(7);
                    l.setYEntrySpace(5);


//                LinearLayout item = (LinearLayout) rootView.findViewById(R.id.item);
//                Set<String> setPagi = spref.getStringSet("SetPagi", null);
//                if(setPagi == null) {
//                    TextView textView = new TextView(getContext());
//                    textView.setText("No food eaten");
//                    textView.setPadding(10, 0, 10, 0);
//                    item.addView(textView);
//                } else {
//                    arrayListBreakfast = new ArrayList<>(setPagi);
//
//                    for (int i = 0; i < arrayListBreakfast.size(); i++) {
//                        TextView textView = new TextView(getContext());
//                        textView.setText(arrayListBreakfast.get(i));
//                        textView.setPadding(10, 0, 10, 0);
//                        item.addView(textView);
//                        if (i != arrayListBreakfast.size() - 1) {
//                            ImageView divider = new ImageView(getContext());
//                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//                            lp.setMargins(0, 10, 0, 5);
//                            divider.setLayoutParams(lp);
//                            divider.setBackgroundColor(Color.BLACK);
//                            item.addView(divider);
//                        }
//                    }
//                }
//
//                LinearLayout item2 = (LinearLayout) rootView.findViewById(R.id.item2);
//                Set<String> setSiang = spref.getStringSet("SetSiang", null);
//                if(setSiang == null) {
//                    TextView textView = new TextView(getContext());
//                    textView.setText("No food eaten");
//                    textView.setPadding(10, 0, 10, 0);
//                    item2.addView(textView);
//                } else {
//
//                    arrayListLunch = new ArrayList<>(setSiang);
//                    for (int i = 0; i < arrayListLunch.size(); i++) {
//                        TextView textView = new TextView(getContext());
//                        textView.setText(arrayListLunch.get(i));
//                        textView.setPadding(10, 0, 10, 0);
//                        item2.addView(textView);
//
//                        if (i != arrayListLunch.size() - 1) {
//                            ImageView divider = new ImageView(getContext());
//                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//                            lp.setMargins(0, 10, 0, 5);
//                            divider.setLayoutParams(lp);
//                            divider.setBackgroundColor(Color.BLACK);
//                            item2.addView(divider);
//                        }
//                    }
//                }
//
//                LinearLayout item3 = (LinearLayout) rootView.findViewById(R.id.item3);
//                Set<String> setMalam = spref.getStringSet("SetMalam", null);
//                if(setMalam == null) {
//                    TextView textView = new TextView(getContext());
//                    textView.setText("No food eaten");
//                    textView.setPadding(10, 0, 10, 0);
//                    item3.addView(textView);
//                } else {
//                    arrayListDinner = new ArrayList<>(setMalam);
//                    for (int i = 0; i < arrayListDinner.size(); i++) {
//                        TextView textView = new TextView(getContext());
//                        textView.setText(arrayListDinner.get(i));
//                        textView.setPadding(10, 0, 10, 0);
//                        item3.addView(textView);
//
//                        if (i != arrayListDinner.size() - 1) {
//                            ImageView divider = new ImageView(getContext());
//                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//                            lp.setMargins(0, 10, 0, 5);
//                            divider.setLayoutParams(lp);
//                            divider.setBackgroundColor(Color.BLACK);
//                            item3.addView(divider);
//                        }
//                    }
//                }
                } else {
                    rootView = inflater.inflate(R.layout.fragment_main2, container, false);
                    spref = getContext().getSharedPreferences("my_data", 0);

                    LineChart lineChart = (LineChart) rootView.findViewById(R.id.graph);
//                    PlaceholderFragment.showToast(getContext(), setBaru,"Breakfast");
                   // Set<String> setBaru = spref.getStringSet("setKonsum",null);
                  //  PlaceholderFragment.showToast(getContext(), setBaru,"Breakfast");

//                    // creating list of entry
                    Set<String>  setkonsum = spref.getStringSet("setKonsum", null);
                    PlaceholderFragment.showToast(getContext(), setkonsum,"Breakfast");
//                    //Set<String> setMalam = spref.getStringSet("SetMalam", null);
//                   // arrayListDinner = new ArrayList<>(setMalam);
//
//                    List<String> arrayListTemp = new ArrayList<String>(setkonsum);

//
//
//                    //ArrayList<String>arrayListTemp=new ArrayList<>(arrayListTempz);
//
//                    ArrayList<Entry> entries_line = new ArrayList<>();
//
//                 //   Collections.sort(arrayListTemp);
//                    int size = arrayListTemp.size();
//
//
//
//
//                    // creating labels
//                    String[] labels_line = new String[size];
//                    int count = 0;
//                    for (String isi1 : arrayListTemp){
//                        String[] isi = isi1.split(";");
//
//                       // int tanggal = Integer.parseInt(isi[0]);
//
//                        float kalori = (float) Double.parseDouble(isi[1]) + (float)Double.parseDouble(isi[2])+ (float) Double.parseDouble(isi[3]);
//
//                        entries_line.add(new Entry(kalori, count));
//
//                       // entries_line.add(new Entry( kalori, i));
//                        labels_line[count] = count + "";
//                        count++;
//                    }



                    ArrayList<Entry> entries_line = new ArrayList<>();
                    entries_line.add(new Entry(4f, 0));
                    entries_line.add(new Entry(8f, 1));
                    entries_line.add(new Entry(6f, 2));
                    entries_line.add(new Entry(2f, 3));
                    entries_line.add(new Entry(18f, 4));
                    entries_line.add(new Entry(9f, 5));
                    entries_line.add(new Entry(18f, 6));
                    entries_line.add(new Entry(9f, 7));

//                    ArrayList<Entry> entries_line2 = new ArrayList<>();
//                    entries_line2.add(new Entry(1f, 0));
//                    entries_line2.add(new Entry(2f, 1));
//                    entries_line2.add(new Entry(3f, 2));
//                    entries_line2.add(new Entry(4f, 3));
//                    entries_line2.add(new Entry(5f, 4));
//                    entries_line2.add(new Entry(6f, 5));
//                    entries_line2.add(new Entry(7f, 6));
//                    entries_line2.add(new Entry(8f, 7));

                     // creating labels
                   String[] labels_line = new String[]{"21 May 2016", "22 May 2016", "23 May 2016", "24 May 2016", "25 May 2016", "26 May 2016", "27 May 2016", "28 May 2016"};

                    ArrayList<ILineDataSet> lines = new ArrayList<ILineDataSet>();

                    final LineDataSet dataset_line = new LineDataSet(entries_line, "Konsumsi Kalori Anda");
                    //final LineDataSet dataset_line2 = new LineDataSet(entries_line2, "Konsumsi Kalori Ideal");
                    dataset_line.setCircleColor(Color.rgb(220, 66, 76));
                    dataset_line.setColor(Color.rgb(220, 66, 76));
                    dataset_line.setLineWidth(3f);
                    dataset_line.setCircleRadius(5f);
                    dataset_line.setValueTextSize(10f);
                    dataset_line.setDrawValues(false);
                    dataset_line.setFillColor(Color.rgb(220, 66, 76));

//                    dataset_line2.getCircleColor(Color.rgb(1, 169, 157));
//                    dataset_line2.getCircleColor(Color.rgb(1, 169, 157));
//                    dataset_line2.setLineWidth(3f);
//                    dataset_line2.setCircleRadius(5f);
//                    dataset_line2.setValueTextSize(10f);
//                    dataset_line2.setDrawValues(false);

                    lines.add(dataset_line);
                    //lines.add(dataset_line2);

//                LineData data_line = new LineData(labels_line, dataset_line);
//                data_line.setValueTextSize(12f);

                    lineChart.setData(new LineData(labels_line, lines));// set the data and list of lables into chart
                    lineChart.setBackgroundColor(Color.WHITE);
                    lineChart.setDescription("in Kcal");  // set the description
                    lineChart.setVisibleXRangeMaximum(5);
                    lineChart.moveViewToX(labels_line.length - 5);

                    lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                        @Override
                        public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                            // display msg when value selected
                            if (e == null)
                                return;

                            dataset_line.setDrawValues(true);
                          //  dataset_line2.setDrawValues(true);
                            Toast.makeText(getActivity(), "Please long press the key", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected() {
                            dataset_line.setDrawValues(false);
                          ///  dataset_line2.setDrawValues(false);
                        }
                    });
//                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                }
            }

            return rootView;
        }

        private static ArrayList<String> addData(PieChart pieChart) {
            ArrayList<Entry> yVals1 = new ArrayList<Entry>();

            if(xData[0].equalsIgnoreCase("Kelebihan Kalori")){
                for (int i = 0; i < yData.length-1; i++) {
                    if (yData[i+1] > 0) {
                        yVals1.add(new Entry(yData[i+1], i));
                    }
                }
            }
            else {
                for (int i = 0; i < yData.length; i++) {
                    if (yData[i] > 0) {
                        yVals1.add(new Entry(yData[i], i));
                    }
                }
            }
            ArrayList<String> xVals = new ArrayList<String>();

            for (int i = 0; i < xData.length; i++) {
                if(yData[i] > 0){
                    if(!xData[i].equalsIgnoreCase("Kelebihan Kalori"))
                        xVals.add(xData[i]);
                }
            }

            System.out.println(xVals.toString());
            System.out.println(yVals1.toString());

            // create pie data set
//            PieDataSet dataSet = new PieDataSet(yVals1, "(in kcal)");
            PieDataSet dataSet = new PieDataSet(yVals1, "");
            dataSet.setSliceSpace(3);
            dataSet.setSelectionShift(5);

            // add many colors
            ArrayList<Integer> colors = new ArrayList<Integer>();

//            for (int c : ColorTemplate.VORDIPLOM_COLORS)
//                colors.add(c);

//            for (int c : ColorTemplate.JOYFUL_COLORS)
//                colors.add(c);
//
//            for (int c : ColorTemplate.COLORFUL_COLORS)
//                colors.add(c);
//
//            for (int c : ColorTemplate.LIBERTY_COLORS)
//                colors.add(c);
//
//            for (int c : ColorTemplate.PASTEL_COLORS)
//                colors.add(c);
//
//            colors.add(ColorTemplate.getHoloBlue());
            if(!xData[0].equals("Kelebihan Kalori"))
                colors.add(Color.rgb(118, 92, 83));
            if(yData[1] > 0)
                colors.add(Color.rgb(14, 143, 41));
            if(yData[2] > 0)
                colors.add(Color.rgb(220, 66, 76));
            if(yData[3] > 0)
                colors.add(Color.rgb(34, 121, 169));


            dataSet.setColors(colors);

            // instantiate pie data object now
            PieData data = new PieData(xVals, dataSet);
            data.setValueFormatter(new MyValueFormatter());
            data.setValueTextSize(11f);
            data.setValueTextColor(Color.WHITE);
//        data.setValueTextColor();

            pieChart.setData(data);

            // undo all highlights
            pieChart.highlightValues(null);

            // update pie chart
            pieChart.invalidate();

            return xVals;
        }

        public static void showToast(Context context,Set<String> listMakanan, String stat){
            LayoutInflater inflater = LayoutInflater.from(context);

            View layout = inflater.inflate(R.layout.toast,
                    (ViewGroup) ((Activity) context).findViewById(R.id.toast_layout_root));
            LinearLayout item = (LinearLayout) layout.findViewById(R.id.listMakanan);

//            LineChart lineChart = (LineChart) layout.findViewById(R.id.graph);
//            // creating list of entry
//            ArrayList<Entry> entries_line = new ArrayList<>();
//            entries_line.add(new Entry(4f, 0));
//            entries_line.add(new Entry(8f, 1));
//            entries_line.add(new Entry(6f, 2));
//            entries_line.add(new Entry(2f, 3));
//            entries_line.add(new Entry(18f, 4));
//            entries_line.add(new Entry(9f, 5));
//            entries_line.add(new Entry(18f, 6));
//            entries_line.add(new Entry(9f, 7));
//
//            LineDataSet dataset_line = new LineDataSet(entries_line, "dalam satuan kalori");
//
//            TextView shows = (TextView) layout.findViewById(R.id.text);
//
//            String ex = index+"";
//            shows.setText(ex);
//
//            // creating labels
//            ArrayList<String> labels_line = new ArrayList<String>();
//            labels_line.add("January");
//            labels_line.add("February");
//            labels_line.add("March");
//            labels_line.add("April");
//            labels_line.add("May");
//            labels_line.add("June");
//            labels_line.add("July");
//            labels_line.add("August");
//
//            LineData data_line = new LineData(labels_line, dataset_line);
//            data_line.setValueTextSize(12f);
//            lineChart.setData(data_line); // set the data and list of lables into chart
//            lineChart.setDescription("Konsumsi Kalori");  // set the description
//            lineChart.setVisibleXRangeMaximum(5);


//             set a message
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(stat);

            if(listMakanan == null) {
                TextView textView = new TextView(context);
                textView.setText("No food eaten");
                textView.setPadding(10, 0, 10, 0);
                textView.setTextColor(Color.WHITE);
                item.addView(textView);
            } else {
                arrayListBreakfast = new ArrayList<>(listMakanan);

                for (int i = 0; i < arrayListBreakfast.size(); i++) {
                    TextView textView = new TextView(context);
                    textView.setText(arrayListBreakfast.get(i));
                    textView.setPadding(10, 0, 10, 0);
                    textView.setTextColor(Color.WHITE);
                    item.addView(textView);
                    if (i != arrayListBreakfast.size() - 1) {
                        ImageView divider = new ImageView(context);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                        lp.setMargins(0, 10, 0, 5);
                        divider.setLayoutParams(lp);
                        divider.setBackgroundColor(Color.WHITE);
                        item.addView(divider);
                    }
                }
            }

            // Toast...
            if(toast != null){
                toast.cancel();
            }

            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }

    }

    public static class MyValueFormatter implements ValueFormatter {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        private DecimalFormat mFormat = (DecimalFormat)nf;

//        public MyValueFormatter() {
//            mFormat = new DecimalFormat("###.###.##0"); // use one decimal
//        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format((int)value) + " Kcal"; // e.g. append a dollar-sign
        }
    }

}
