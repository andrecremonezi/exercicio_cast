package br.com.cast.turmaformacao.exercicio.controllers.sync;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.services.ProductBussinessService;

public class syncCreate extends AsyncTask<Product,Void,Void> {
    private Activity context;
    private ProgressDialog progressDialog;

    public syncCreate(Activity context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Product... products) {
        ProductBussinessService.save(products[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
