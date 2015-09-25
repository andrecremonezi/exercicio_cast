package br.com.cast.turmaformacao.exercicio.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;

import br.com.cast.turmaformacao.exercicio.R;
import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.services.ProductBussinessService;
import br.com.cast.turmaformacao.exercicio.util.FormHelper;


public class ProdutoFormActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextAmount;
    private EditText editTextAmountMin;
    private EditText editTextUnitaryValue;
    private Button buttonSave;
    private Product product;

    public static final String PARAM_TASK = "PARAM_PROD";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_form);



        initProduct();
        bindItens();
        bindButtonSave();
    }

    private void initProduct() {

            Bundle extras = getIntent().getExtras();

            if(extras != null){
                this.product = (Product) extras.getParcelable(PARAM_TASK);
            }

            this.product = this.product == null ? new Product() : this.product;

    }


    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.buttonAdd);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requiredMessage = getResources().getString(R.string.msg_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextName, editTextDescription, editTextAmount, editTextAmountMin, editTextUnitaryValue)) {
                    bindProduct();
                    ProductBussinessService.save(product);
                    Toast.makeText(ProdutoFormActivity.this, getString(R.string.msg_save_sucessfull), Toast.LENGTH_LONG).show();
                    ProdutoFormActivity.this.finish();
                }
            }
        });
    }

    public void bindProduct(){
        product.setName(editTextName.getText().toString());
        product.setDescription(editTextDescription.getText().toString());
        product.setAmount(Long.parseLong(editTextAmount.getText().toString()));
        product.setAmountMin(Long.parseLong(editTextAmountMin.getText().toString()));
        product.setUnitaryValue(Double.parseDouble(editTextUnitaryValue.getText().toString()));
    }



    private void bindItens() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(product.getName() == null ? "" : product.getName());

        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(product.getDescription() == null ? "" : product.getDescription());

        editTextAmount = (EditText) findViewById(R.id.editTextAmount);
        editTextAmount.setText(product.getAmount() == null ? "" : product.getAmount().toString());

        editTextAmountMin = (EditText) findViewById(R.id.editTextAmountMin);
        editTextAmountMin.setText(product.getAmountMin() == null ? "" : product.getAmountMin().toString());


        editTextUnitaryValue = (EditText) findViewById(R.id.editTextUnitaryValue);
    }
}
