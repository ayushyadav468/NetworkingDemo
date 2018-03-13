package com.example.ayushyadav.networkingdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    CustomAdapter customAdapter;
    ArrayList<ArrayListForListView> arrayListForListViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);

        customAdapter = new CustomAdapter(this, arrayListForListViews);
        listView.setAdapter(customAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }
        });
    }

    private void fetchData()
    {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        String urlString = "https://jsonplaceholder.typicode.com/users";

        CoursesAsyncTask coursesAsyncTask = new CoursesAsyncTask(new CoursesAsyncTask.CourseDownloadListener() {
            @Override
            public void onDownloadComplete(ArrayList<Course> courses) {
                if(courses!= null){
                    for(int i = 0;i<courses.size();i++){
                        Course course = courses.get(i);
                        ArrayListForListView customObject = new ArrayListForListView(course.getName(), course.getUserName(), course.getEmailId());
                        arrayListForListViews.add(customObject);
                    }
                    customAdapter.notifyDataSetChanged();
                } else {
                    Snackbar.make(listView,"Try Again.",Snackbar.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });

        coursesAsyncTask.execute(urlString);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
