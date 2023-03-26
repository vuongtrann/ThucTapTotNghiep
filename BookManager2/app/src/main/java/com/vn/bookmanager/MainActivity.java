package com.vn.bookmanager;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vn.bookmanager.model.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText txtSearch;
    Button btnSearch;
    ArrayList<Book> dsBook;
    ArrayAdapter<Book> adapterBook;
    ListView listView;
    final String API ="http://192.168.2.4:8080/BookManager/rest/books";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         addControls();
         hienthidanhsach();
        //Toast.makeText(MainActivity.this,"bookName",Toast.LENGTH_LONG).show();
         addEvent();

    }

    private void addControls() {
        txtSearch = findViewById(R.id.txtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.lv);

        dsBook = new ArrayList<>();
        adapterBook = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,dsBook);
        listView.setAdapter(adapterBook);
    }

    private void addEvent() {
    }

    private void hienthidanhsach() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dsBook.clear();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Book");
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String bookName = jsonObject.getString("bookName");
                        String description = jsonObject.getString("description");
                        Double price = jsonObject.getDouble("price");
                        String image = jsonObject.getString("image");
                        int catId = jsonObject.getInt("catId");

//                        dsBook.add(new Book(bookName, catId, description, id, image, price));
                        Toast.makeText(MainActivity.this, bookName, Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, "bookName", Toast.LENGTH_LONG).show();
                    }
                    adapterBook.notifyDataSetChanged();
                } catch (Exception ex) {

                }
            }
        };

//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API, null,
//                new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try{
//                    JSONArray jsonArray = response.getJSONArray("Book");
//                    for (int i=0;i< jsonArray.length();i++){
//                        JSONObject book = jsonArray.getJSONObject(i);
//
//                        String bookName = book.getString("bookName");
//                        int catId = book.getInt("catId");
//                        String des = book.getString("description");
//                        int id = book.getInt("id");
//                        String image = book.getString("image");
//                        double price = book.getDouble("price");
//                    }
//                }catch (Exception ex){
//
//                }
//            }, new Response.ErrorListener(){
//               @Override
//                       public void onErrorResponse(VolleyError error)
//                    }
//            });
//                };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        Uri.Builder builder = Uri.parse(API).buildUpon();


        String url = builder.build().toString();
        StringRequest request = new StringRequest(
                Request.Method.GET, url, responseListener, errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(request);
    }
    }