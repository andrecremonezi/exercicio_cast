package br.com.cast.turmaformacao.exercicio.controllers.sync;

import android.os.AsyncTask;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.services.ProductBussinessService;

/**
 * Created by Administrador on 28/09/2015.
 */
public class DeleteProductTask extends AsyncTask<Product, Void, Void> {

    @Override
    protected Void doInBackground(Product...product) {
        ProductBussinessService.delete(product[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
