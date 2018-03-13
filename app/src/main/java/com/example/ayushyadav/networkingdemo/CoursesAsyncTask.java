package com.example.ayushyadav.networkingdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ayushyadav on 11/03/18.
 */

public class CoursesAsyncTask extends AsyncTask<String, Void, ArrayList<Course>> {

    public interface CourseDownloadListener{
        void onDownloadComplete(ArrayList<Course> courses);
    }

    private CourseDownloadListener mListener;

    public CoursesAsyncTask(CourseDownloadListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Course> doInBackground(String... strings) {
        String urlString = strings[0];
        try {
            URL url = new URL(urlString);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");

            httpsURLConnection.connect();
            InputStream inputStream = httpsURLConnection.getInputStream();

            String result = "";
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNext()){
                result = result.concat(scanner.next());
            }

            ArrayList<Course> courses = parseCourses(result);

            Log.d("String from website", result);
            httpsURLConnection.disconnect();

            return courses;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Course> parseCourses(String result) throws JSONException {
        ArrayList<Course> courses = new ArrayList<>();
        JSONArray rootArray = new JSONArray(result);
        for(int i = 0; i < rootArray.length(); i++){
            JSONObject jsonObject = rootArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String userName = jsonObject.getString("username");
            String emailId = jsonObject.getString("email");
            Course course = new Course(id, name,userName, emailId);
            courses.add(course);
        }

        return courses;
    }

    @Override
    protected void onPostExecute(ArrayList<Course> courses) {
        super.onPostExecute(courses);
        mListener.onDownloadComplete(courses);
    }
}
