package br.com.cast.turmaformacao.exercicio.model.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.exercicio.model.entities.Product;

public class ProductService {

    private static final String URL = "http://10.11.21.193:4000/api/v1/products/";

    private ProductService(){
        super();
    }

    public static Product getProductByWebId(Long webId){
        Product product = null;
        try {
            java.net.URL url = new URL(URL + webId);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(inputStream, Product.class);

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }


    public static List<Product> getProductsWeb(){
        List<Product> products = new ArrayList<>();

        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            products = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }


}
