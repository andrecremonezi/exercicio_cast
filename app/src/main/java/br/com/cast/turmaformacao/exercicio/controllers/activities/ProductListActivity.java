package br.com.cast.turmaformacao.exercicio.controllers.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import br.com.cast.turmaformacao.exercicio.R;
import br.com.cast.turmaformacao.exercicio.controllers.adapters.ProdutoAdapter;
import br.com.cast.turmaformacao.exercicio.controllers.sync.GetAllProductsFromWebTask;
import br.com.cast.turmaformacao.exercicio.controllers.sync.ProductInterfaceListerner;
import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.services.ProductBussinessService;

public class ProductListActivity extends AppCompatActivity implements ProductInterfaceListerner<Product> {
    private ListView listViewProductList;
    private Product selectedProduct;
    private List<Product> getProducts;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_list);
        bindProductList();
        bindFloatingButtonAdd();
    }

    private void bindFloatingButtonAdd() {
        floatingActionButton = (FloatingActionButton ) findViewById(R.id.floatingButton);
        floatingActionButton.attachToListView(listViewProductList);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent redirectToTaskList = new Intent(ProductListActivity.this, ProdutoFormActivity.class);
                startActivity(redirectToTaskList);
            }
        });
    }

    private void bindProductList() {
        listViewProductList = (ListView) findViewById(R.id.listViewProdutoList);
        registerForContextMenu(listViewProductList);

        listViewProductList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProdutoAdapter adapter = (ProdutoAdapter) listViewProductList.getAdapter();
                selectedProduct = (Product) adapter.getItem(position);
                return false;
            }
        });

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
            case R.id.menu_up:
                onMenuUpProduct();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuUpProduct() {
        refreshList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void onUpdateList(List<Product> lista) {
        listViewProductList.setAdapter(new ProdutoAdapter(this, lista));
        ProdutoAdapter adapter = (ProdutoAdapter) listViewProductList.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void onMenuAddProdutoClick() {
        Intent goToNewProdutoActivity = new Intent(ProductListActivity.this, ProdutoFormActivity.class);
        startActivity(goToNewProdutoActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuUpdateClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuUpdateClick() {
        Intent goToCadastroProduto = new Intent(ProductListActivity.this, ProdutoFormActivity.class);
        goToCadastroProduto.putExtra(ProdutoFormActivity.PARAM_TASK,selectedProduct);
        startActivity(goToCadastroProduto);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ProductListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductBussinessService.delete(selectedProduct);
                        selectedProduct = null;
                        String message = getString(R.string.msg_delete_sucessfull);
                        Toast.makeText(ProductListActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }


    @Override
    public void refreshList() {
        new GetAllProductsFromWebTask(this,this).execute();
    }

    @Override
    public void updateList(List<Product> lista) {
        onUpdateList(lista);
    }
}

