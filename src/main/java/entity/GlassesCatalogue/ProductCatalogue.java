package entity.GlassesCatalogue;


import entity.Product;

import java.util.HashMap;


public class ProductCatalogue{
    private HashMap<String, Product> map;
    public ProductCatalogue(){
        map = new HashMap<String, Product>();
    }

    public void addProduct(Product product){
        map.put(product.getId(),product);
    }
}
