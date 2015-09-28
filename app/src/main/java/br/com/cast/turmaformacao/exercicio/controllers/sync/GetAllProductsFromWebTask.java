package br.com.cast.turmaformacao.exercicio.controllers.sync;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ConcurrentModificationException;
import java.util.List;

import br.com.cast.turmaformacao.exercicio.controllers.activities.ProductListActivity;
import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.services.ProductBussinessService;

public class GetAllProductsFromWebTask extends AsyncTask<Void, Void, List<Product>> {

    public ProgressDialog progressDialog;
    public Context context;
    public ProductInterfaceListerner activity;

    public GetAllProductsFromWebTask(Context context, ProductInterfaceListerner activity) {
        this.context = context;
        this.activity = activity;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Carregando Lista...");
        progressDialog.show();
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
        ProductBussinessService.sincronized();
        return ProductBussinessService.findAll();
    }

    @Override
    protected void onPostExecute(List<Product> lista) {
        super.onPostExecute(lista);
        progressDialog.dismiss();
        activity.updateList(lista);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
