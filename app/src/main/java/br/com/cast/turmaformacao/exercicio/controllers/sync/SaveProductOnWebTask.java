package br.com.cast.turmaformacao.exercicio.controllers.sync;

import android.os.AsyncTask;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.http.ProductService;

/**
 * Created by Administrador on 28/09/2015.
 */
public class SaveProductOnWebTask extends AsyncTask<Product, Void, Void> {
    @Override
    protected Void doInBackground(Product... products) {
        ProductService.saveProduct(products[0]);
        return null;
    }
}
