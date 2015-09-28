package br.com.cast.turmaformacao.exercicio.model.services;

import java.util.List;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;
import br.com.cast.turmaformacao.exercicio.model.http.ProductService;
import br.com.cast.turmaformacao.exercicio.model.persistence.ProductRepository;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductBussinessService {

    public ProductBussinessService(){
        super();
    }

    public static List<Product> findAll() {
        return ProductRepository.getAll();
    }

    public static void save(Product product) {
        ProductRepository.save(product);
    }

    public static void delete(Product product){
        ProductRepository.delete(product.getId());
    }

    public static void sincronized() {

        List<Product> webProducts = ProductService.getProductsWeb();


        for(Product product : webProducts){

            Long id = ProductRepository.getIdByWebId(product.getIdWeb());

            product.setId(id == null ? null : id);

            save(product);
        }
    }


}
