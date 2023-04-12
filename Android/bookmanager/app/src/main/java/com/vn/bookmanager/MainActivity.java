package com.vn.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vn.bookmanager.ADAPTER.BookAdapter;
import com.vn.bookmanager.MODEL.Book;
import com.vn.bookmanager.MODEL.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    final String SERVER ="http://192.168.3.8:8080/BookManager/rest/";

    private List<Book> bookList ;
    private ArrayList<Category> categoryList = new ArrayList<>();
    //private ArrayList<String> categoryListString = new ArrayList<>();
    private ArrayAdapter<Category> categoryAdapter;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;

    // Spinner List Category
    Spinner spinnerCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCategory = findViewById(R.id.spinner);

        getCategoryList();

        recyclerView = findViewById(R.id.rvProduct);
        bookList = new ArrayList<>();

//        getBookList();
//
//        bookAdapter = new BookAdapter(bookList);
//
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.clearOnScrollListeners();
        //getCategoryList();




    }

    private void getCategoryList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SERVER + "categories", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray categoryArray = obj.getJSONArray("Category");
                    for (int i = 0; i < categoryArray.length(); i++) {
                        JSONObject categoryObject = categoryArray.getJSONObject(i);
                        Category category = new Category(categoryObject.getString("categoryName"), categoryObject.getInt("id"));
                        //String catName = categoryObject.getString("categoryName");
                        categoryList.add(category);
                        //categoryListString.add(catName);

                        categoryAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,categoryList);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerCategory.setAdapter(categoryAdapter);
                        //Toast.makeText(MainActivity.this,category.getCategoryName()+category.getId(),Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        spinnerCategory.setOnItemSelectedListener(MainActivity.this);
    }

    private void getBookList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SERVER + "books/catID=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray bookArray = obj.getJSONArray("Book");
                    for (int i = 0; i < bookArray.length(); i++) {
                        JSONObject bookObject = bookArray.getJSONObject(i);
                        Book book = new Book(bookObject.getString("bookName"), bookObject.getInt("catId"),bookObject.getString("description"),bookObject.getInt("id"),bookObject.getString("image"),bookObject.getDouble("price"));
                        bookList.add(book);
                        //Toast.makeText(MainActivity.this,book.getBookName()+book.getDescription(),Toast.LENGTH_LONG).show();
                    }
                    recyclerView.setAdapter(bookAdapter);
                    bookAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    // bắt sự kiện selected trong spinner

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        int catId = categoryList.get(i).getId();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SERVER + "books/catID="+catId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray bookArray = obj.getJSONArray("Book");
                    for (int i = 0; i < bookArray.length(); i++) {
                        JSONObject bookObject = bookArray.getJSONObject(i);
                        Book book = new Book(bookObject.getString("bookName"), bookObject.getInt("catId"),bookObject.getString("description"),bookObject.getInt("id"),bookObject.getString("image"),bookObject.getDouble("price"));
                        bookList.add(book);
                        //Toast.makeText(MainActivity.this,book.getBookName()+book.getDescription(),Toast.LENGTH_LONG).show();
                    }
                    recyclerView.setAdapter(bookAdapter);
                    bookAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        //Toast.makeText(MainActivity.this,catId+" "+categoryList.get(i).getId(),Toast.LENGTH_LONG).show();

        bookAdapter = new BookAdapter(bookList);
//      ===Show kiểu LinearLayoutManager===//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //===Show kiểu LinearLayoutManager (HORIZONTAL cuộn ngang - VERTICAL cuộn dọc) (true show từ đầu list- false show từ cuối list)===//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //===Show kiểu GridLayoutManager===//
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.clearOnScrollListeners();

        bookList.clear();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        getBookList();

        bookAdapter = new BookAdapter(bookList);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    //search



}