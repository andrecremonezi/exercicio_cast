package br.com.cast.turmaformacao.exercicio.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductRepository {

    public ProductRepository(){
        super();
    }

    public static void save(Product product) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = ProductContract.getContentValues(product);

        if (product.getId() == null) {

            db.insert(ProductContract.TABLE, null, values);

        } else {

            String where = ProductContract.ID + " = ? ";
            String[] params = {product.getId().toString()};
            db.update(ProductContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();

    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = ProductContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(ProductContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();

    }

    public static List<Product> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(ProductContract.TABLE, ProductContract.COLUNS, null, null, null, null, ProductContract.ID);
        List<Product> values = ProductContract.getProducts(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }

    public static Long getIdByWebId(Long webId) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = ProductContract.IDWEB + " = ? ";
        String params[] = {String.valueOf(webId)};

        Cursor cursor = db.query(ProductContract.TABLE,ProductContract.COLUNS,where,params,null,null,null);

        Product product = ProductContract.getProduct(cursor);

        return product == null ? null : product.getId();
    }


}
