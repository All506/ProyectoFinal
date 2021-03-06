/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Domain.CircularDoublyLinkList;
import Domain.CircularLinkList;
import Domain.ListException;
import Objects.Food;
import Objects.Place;
import Objects.Product;
import Objects.Restaurant;
import Objects.Search;
import Objects.Security;
import Objects.Supermarket;
import Security.AES;
import XML.FileXML;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;
import list.SinglyLinkedList;
import org.xml.sax.SAXException;

/**
 *
 * @author Alán
 */
public class SaveData {

    public static CircularLinkList lSecurity = new CircularLinkList();

    public void saveData() throws ListException, TransformerException, SAXException, IOException, list.ListException {

        lSecurity = Util.Utility.lSecurity;
        FileXML fXML = new FileXML();

        //La información acerca de la seguridad es almacenada
        if (!fXML.exist("Security.xml")) { //Si el archivo no existe
            fXML.createXML("SecurityXML", "Security");
            if (!Util.Utility.getListSecurity().isEmpty()) {
                writeSecurity();
            }
        } else {
            fXML.deleteFile("Security");
            fXML.createXML("SecurityXML", "Security");
            if (!Util.Utility.getListSecurity().isEmpty()) {
                writeSecurity();
            }
        }

        //La información acerca de restaurantes es almacenada
        if (!fXML.exist("Restaurants.xml")) { //Si el archivo no existe
            fXML.createXML("RestaurantsXML", "Restaurants");
            if (!Util.Utility.gRestAndSuper.isEmpty()) {
                writeRestaurants();
            }
        } else {
            fXML.deleteFile("Restaurants");
            fXML.createXML("RestaurantsXML", "Restaurants");
            if (!Util.Utility.gRestAndSuper.isEmpty()) {
                writeRestaurants();
            }
        }

        //La información acerca de lugares es almacenada
        if (!fXML.exist("Places.xml")) { //Si el archivo no existe
            fXML.createXML("PlacesXML", "Places");
            if (!Util.Utility.gPlaces.isEmpty()) {
                writePlaces();
            }
        } else {
            fXML.deleteFile("Places");
            fXML.createXML("PlacesXML", "Places");
            if (!Util.Utility.gPlaces.isEmpty()) {
                writePlaces();
            }
        }

        //La información acerca de los productos es almacenada
        if (!fXML.exist("Products.xml")) { //Si el archivo no existe
            fXML.createXML("ProductsXML", "Products");
            if (!Util.Utility.treeProducts.isEmpty()) {
                writeProducts();
            }
        } else {
            fXML.deleteFile("Products");
            fXML.createXML("ProductsXML", "Products");
            if (!Util.Utility.treeProducts.isEmpty()) {
                writeProducts();
            }
        }

        //La información acerca de las comidas es almacenada
        if (!fXML.exist("Foods.xml")) { //Si el archivo no existe
            fXML.createXML("FoodsXML", "Foods");
            if (!Util.Utility.treeFoods.isEmpty()) {
                writeFoods();
            }
        } else {
            fXML.deleteFile("Foods");
            fXML.createXML("FoodsXML", "Foods");
            if (!Util.Utility.treeFoods.isEmpty()) {
                writeFoods();
            }
        }
        
        //La información acerca de las busquedas es almacenada
        if (!fXML.exist("Searches.xml")) { //Si el archivo no existe
            fXML.createXML("SearchesXML", "Searches");
            if (!Util.Utility.lSearches.isEmpty()) {
                writeSearches();
            }
        } else {
            fXML.deleteFile("Searches");
            fXML.createXML("SearchesXML", "Searches");
            if (!Util.Utility.lSearches.isEmpty()) {
                writeSearches();
            }
        }
    }

    public void writeSecurity() throws ListException, TransformerException, SAXException, IOException {

        FileXML fXML = new FileXML();

        if (Util.Utility.getListSecurity().isEmpty()) {
            if (fXML.exist("Security.xml")) {
                fXML.deleteFile("Security");
            }
        } else {
            AES encrypt = new AES();
            for (int i = 1; i <= lSecurity.size(); i++) {
                Security tempSec = (Security) lSecurity.getNode(i).data;
                Security encSec = new Security(encrypt.encrypt(tempSec.getUser(), "Proyecto"), encrypt.encrypt(tempSec.getPassword(), "Proyecto"),
                encrypt.encrypt(tempSec.getKindUser(), "Proyecto"));
                fXML.writeXML("Security.xml", "Security", encSec.getDataName(), encSec.getData());
            }
        }
    }

    private void writeRestaurants() throws list.ListException, TransformerException, SAXException, IOException {
        FileXML fXML = new FileXML();

        if (Util.Utility.gRestAndSuper.isEmpty()) {
            if (fXML.exist("Restaurants.xml")) {
                fXML.deleteFile("Restaurants");
            }
        } else {
            SinglyLinkedList restAndSuperToSave = Util.Utility.gRestAndSuper.getAllItemsAsList();
            for (int i = 1; i <= restAndSuperToSave.size(); i++) {
                if (restAndSuperToSave.getNode(i).data instanceof Restaurant) {
                    Restaurant restaurant = (Restaurant) restAndSuperToSave.getNode(i).data;
                    fXML.writeXML("Restaurants.xml", "Restaurants", restaurant.dataName(), restaurant.data());
                } else {
                    Supermarket supermarket = (Supermarket) restAndSuperToSave.getNode(i).data;
                    fXML.writeXML("Restaurants.xml", "Supermarkets", supermarket.dataName(), supermarket.data());
                }

            }
        }
    }

    private void writePlaces() throws list.ListException, TransformerException, SAXException, IOException {
        FileXML fXML = new FileXML();

        if (Util.Utility.gPlaces.isEmpty()) {
            if (fXML.exist("Places.xml")) {
                fXML.deleteFile("Places");
            }
        } else {
            SinglyLinkedList placesToSave = Util.Utility.gPlaces.getAllItemsAsList();
            for (int i = 1; i <= placesToSave.size(); i++) {
                Place place = (Place) placesToSave.getNode(i).data;
                fXML.writeXML("Places.xml", "Places", place.dataName(), place.data());
            }
        }
    }

    private void writeProducts() throws list.ListException, TransformerException, SAXException, IOException {
        FileXML fXML = new FileXML();

        if (Util.Utility.treeProducts.isEmpty()) {
            if (fXML.exist("Products.xml")) {
                fXML.deleteFile("Products");
            }
        } else {
            SinglyLinkedList productsToSave = Util.Utility.treeProducts.getTreeAsList();
            for (int i = 1; i <= productsToSave.size(); i++) {
                Product product = (Product) productsToSave.getNode(i).data;
                fXML.writeXML("Products.xml", "Products", product.dataName(), product.data());
            }
        }
    }

    private void writeFoods() throws list.ListException, TransformerException, SAXException, IOException {
        FileXML fXML = new FileXML();

        if (Util.Utility.treeFoods.isEmpty()) {
            if (fXML.exist("Foods.xml")) {
                fXML.deleteFile("Foods");
            }
        } else {
            SinglyLinkedList foodsToSave = Util.Utility.treeFoods.getTreeAsList();
            for (int i = 1; i <= foodsToSave.size(); i++) {
                Food food = (Food) foodsToSave.getNode(i).data;
                fXML.writeXML("Foods.xml", "Foods", food.dataName(), food.data());
            }
        }
    }

    private void writeSearches() throws list.ListException, TransformerException, SAXException, IOException, ListException {
        FileXML fXML = new FileXML();
        
        if (Util.Utility.lSearches.isEmpty()) {
            if (fXML.exist("Searches.xml")) {
                fXML.deleteFile("Searches");
            }
        } else {
            CircularDoublyLinkList searchesToSave = Util.Utility.lSearches;
            for (int i = 1; i <= searchesToSave.size(); i++) {
                Search search = (Search) searchesToSave.getNode(i).data;
                fXML.writeXML("Searches.xml", "Searches", search.dataName(), search.data());
            }
        }
    }

}
