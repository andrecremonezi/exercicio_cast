package br.com.cast.turmaformacao.exercicio.controllers.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import br.com.cast.turmaformacao.exercicio.R;

public class ProductListActivity extends ActionBarActivity {
    private ListView listViewTaskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_list);
        bindProductList();
    }

    private void bindProductList() {
        listViewTaskList = (ListView) findViewById(R.id.listViewProdutoList);
        registerForContextMenu(listViewTaskList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddProdutoClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddProdutoClick() {
        Intent goToNewProdutoActivity = new Intent(ProductListActivity.this, ProdutoFormActivity.class);
        startActivity(goToNewProdutoActivity);
    }
}

