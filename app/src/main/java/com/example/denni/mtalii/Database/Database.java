package com.example.denni.mtalii.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.denni.mtalii.Model.Bookings;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denni on 12/12/2017.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="MtaliiDb.db";
    private  static final int DB_VER=1;
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    public List<Bookings> getCatrs()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"ProductName","ProductId","Quantity","Price","Discount"};
        String sqlTable="Bookings";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Bookings> result = new ArrayList<>();
        if (c.moveToFirst())
        {
            do {

                result.add(new Bookings(c.getString(c.getColumnIndex("ProductId")),
                       c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                       c.getString(c.getColumnIndex("Price")),
                       c.getString(c.getColumnIndex("Discount"))
                ));
            }while (c.moveToNext());
            }
        return result;
    }
    public void addToCart (Bookings bookings)
    {
        SQLiteDatabase db= getReadableDatabase();
        String query= String.format("INSERT INTO Bookings(ProductId,ProductName,Quantity,Price,Discount)VALUES('%s','%s','%s','%s','%s');",
                bookings.getProductId(),
                bookings.getProductName(),
                bookings.getQuantity(),
                bookings.getPrice(),
                bookings.getDiscount());
        db.execSQL(query);


    }

    public void clearCart ()
    {
        SQLiteDatabase db= getReadableDatabase();
        String query= String.format("DELETE FROM BOOKINGS");
        db.execSQL(query);

    }
}
