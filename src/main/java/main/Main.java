package main;

import parser.domParser.DOMParser;
import parser.domParser.Document;
import parser.domParser.Node;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        DOMParser parser1 = new DOMParser();

        Document document = null;
        try {
            document = parser1.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node rootNode = document.getRootNode();
        File directory = new File("./");
        System.out.println(document);
        //System.out.println(document.findListNodesByTag("sunglasses"));

/*            if (rootNode.getTag().equals("catalogue")) {
                ProductCatalogue productCatalogue = new ProductCatalogue();
                for (Node childNode : rootNode.getChildList()) {
                    Product product = EntityCreateDirector.createProduct(childNode.getTag());
                    productCatalogue.addProduct(product);
                    for(Node attributeValue:childNode.getChildList()){
                        if(attributeValue.getTag().equals("model")){
                            product.setModel(attributeValue.getValue());
                        }else if(attributeValue.getTag().equals("price")){
                            product.setPrice(Integer.valueOf(attributeValue.getValue()));
                        }
                    }

                    System.out.println(product);
                }

            }*/

    }
}
