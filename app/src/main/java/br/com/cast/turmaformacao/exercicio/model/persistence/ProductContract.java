package br.com.cast.turmaformacao.exercicio.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductContract {

    public static final String TABLE = "task";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String AMOUNTMIN = "amountmin";
    public static final String UNITARYVALUE = "unitaryvalue";

    public static final String[] COLUNS = {ID, NAME, DESCRIPTION, AMOUNT, AMOUNTMIN, UNITARYVALUE};

    public ProductContract(){
        super();
    }


    public static String getCreateTableScript() {

        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT NOT NULL, ");
        create.append(AMOUNT + " INT NOT NULL, ");
        create.append(AMOUNTMIN + " INT NOT NULL, ");
        create.append(UNITARYVALUE + " FLOAT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();

        values.put(ProductContract.ID, product.getId());
        values.put(ProductContract.NAME, product.getName());
        values.put(ProductContract.DESCRIPTION, product.getDescription());
        values.put(ProductContract.AMOUNT, product.getAmount());
        values.put(ProductContract.AMOUNTMIN, product.getAmountMin());
        values.put(ProductContract.UNITARYVALUE, product.getUnitaryValue());

        return values;
    }


    public static Product getProduct(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getLong(cursor.getColumnIndex(ProductContract.ID)));
            product.setName(cursor.getString(cursor.getColumnIndex(ProductContract.NAME)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(ProductContract.DESCRIPTION)));
            product.setAmount(cursor.getLong(cursor.getColumnIndex(ProductContract.AMOUNT)));
            product.setAmountMin(cursor.getLong(cursor.getColumnIndex(ProductContract.AMOUNTMIN)));
            product.setUnitaryValue(cursor.getDouble(cursor.getColumnIndex(ProductContract.UNITARYVALUE)));

            return product;
        }
        return null;
    }


    public static List<Product> getProducts(Cursor cursor) {

        List<Product> products = new ArrayList<>();

        while (cursor.moveToNext()) {
            products.add(getProduct(cursor));
        }
        return products;
    }

}
