package com.example.crewmembers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.crewmembers.Adapter.CourseAdapter;
import com.example.crewmembers.ViewModel.CourseModal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button refreshBtn, deleteBtn;
    public static CrewMemberDatabase crewMemberDatabase;

    // creating variables for
    // our ui components.
    private RecyclerView courseRV;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    //private ArrayList<CourseModal> courseModalArrayList;
    private List<CrewMember> crewMemberList;

    // below line is the variable for url from
    // where we will be querying our data.
    String url = "https://api.spacexdata.com/v4/crew";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshBtn = findViewById(R.id.refresh);
        deleteBtn = findViewById(R.id.delete);
        crewMemberDatabase = Room.databaseBuilder(getApplicationContext(), CrewMemberDatabase.class, "userdb").allowMainThreadQueries().build();
        //crewMemberList = crewMemberDatabase.myDao().getUsers();

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);

        // below line we are creating a new array list
        //courseModalArrayList = new ArrayList<>();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crewMemberDatabase.myDao().emptydb();
                getData();
                adapter.notifyDataSetChanged();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Deleted all data", Toast.LENGTH_SHORT).show();
                crewMemberDatabase.myDao().emptydb();
                //buildRecyclerView();
                adapter.notifyDataSetChanged();
            }
        });

        //Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();

        // calling method to
        // build recycler view.
        progressBar.setVisibility(View.GONE);
        buildRecyclerView();

    }

    private void getData() {

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);

                        String name = responseObj.getString("name");
                        String agency = responseObj.getString("agency");
                        String status = responseObj.getString("status");
                        String wiki = responseObj.getString("wikipedia");
                        String image = responseObj.getString("image");

                        CrewMember crewlone = new CrewMember( name,  image,  status,  agency,  wiki);
                        crewMemberDatabase.myDao().addUser(crewlone);

                        //courseModalArrayList.add(new CourseModal(name, image, agency, wiki, status));
                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
        //crewMemberList = crewMemberDatabase.myDao().getUsers();
    }

    private void buildRecyclerView() {

       // adapter = new CourseAdapter(courseModalArrayList, MainActivity.this);
        adapter = new CourseAdapter(crewMemberDatabase.myDao().getUsers(), MainActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }
}


