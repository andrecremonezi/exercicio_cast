package br.com.cast.turmaformacao.exercicio.controllers.adapters;


import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.exercicio.R;
import br.com.cast.turmaformacao.exercicio.model.entities.Product;

public class ProdutoAdapter extends BaseAdapter {
    private List<Product> produtoList;
    private Activity context;


    public ProdutoAdapter(Activity context, List<Product> produtoList) {
        this.produtoList = produtoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return produtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product produto = (Product) getItem(position);

        View produtoListItemView = context.getLayoutInflater().inflate(R.layout.activity_list_item, parent, false);

        TextView textViewNome = (TextView) produtoListItemView.findViewById(R.id.textViewName);
        textViewNome.setText(produto.getName());

        TextView textViewDesciption = (TextView) produtoListItemView.findViewById(R.id.textViewDescription);
        textViewDesciption.setText(produto.getDescription());

        TextView textViewAmount = (TextView) produtoListItemView.findViewById(R.id.textViewAmount);
        textViewAmount.setText(produto.getAmount().toString());

        TextView textViewAmountMin = (TextView) produtoListItemView.findViewById(R.id.textViewAmountMin);
        textViewAmountMin.setText(produto.getAmountMin().toString());

        TextView textViewUnitaryValue = (TextView) produtoListItemView.findViewById(R.id.textViewUnitaryValue);
        textViewUnitaryValue.setText(String.valueOf(produto.getUnitaryValue()));


        return produtoListItemView;
    }

    public void setDataValues(List<Product> values) {
        produtoList.clear();
        produtoList.addAll(values);
    }
}
