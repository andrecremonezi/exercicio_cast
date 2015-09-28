package br.com.cast.turmaformacao.exercicio.controllers.sync;

import android.os.AsyncTask;

import java.util.List;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.services.ProductBussinessService;

/**
 * Created by Administrador on 28/09/2015.
 */
public class FindProductsTask extends AsyncTask<Void, Void, List<Product>> {

    @Override
    protected List<Product> doInBackground(Void... voids) {
        return ProductBussinessService.findAll();
    }
}
