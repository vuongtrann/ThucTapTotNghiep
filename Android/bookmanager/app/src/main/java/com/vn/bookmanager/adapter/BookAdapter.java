package com.vn.bookmanager.ADAPTER;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.vn.bookmanager.MODEL.Book;
import com.vn.bookmanager.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> dsBook;
    private List<String> dsBookAll;
    public BookAdapter(List<Book> dsBook) {
        this.dsBook = dsBook;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product,viewGroup,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int pos) {
        Book book = dsBook.get(pos);
        if(book==null){
            return;
        }
        bookViewHolder.txtTenSach.setText(book.getBookName());
        bookViewHolder.txtDes.setText(book.getDescription());
        bookViewHolder.txtGia.setText(String.valueOf(book.getPrice())+" VND");
        bookViewHolder.imageView.setImageResource(R.drawable.b6);

    }

    @Override
    public int getItemCount() {
        if (dsBook !=null){
            return dsBook.size();
        }
        return 0;
    }



    class BookViewHolder extends RecyclerView.ViewHolder{
    private TextView txtTenSach, txtDes, txtGia;
    private ImageView imageView;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTenSach = itemView.findViewById(R.id.tv_name);
        txtDes = itemView.findViewById(R.id.tv_description);
        txtGia = itemView.findViewById(R.id.tv_price);
        imageView = itemView.findViewById(R.id.iv_product);
    }
    }
}
