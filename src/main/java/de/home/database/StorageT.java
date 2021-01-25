package de.home.database;

import de.home.collections.Products;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Locale;

public class StorageT<T> {
    Class<T> objectClass;

    EntityManager entityManager;

    public StorageT() {
        entityManager = new EntityManager();
    }

    public String getSourcePath(String document) {
        File file = new File(Paths.MAIN_RESOURCES_PATH);
        String absolutePath = file.getAbsolutePath();
        System.out.printf("Paht is : %s/%s", absolutePath, document.toLowerCase(Locale.ROOT));

        if (absolutePath.endsWith(Paths.MAIN_RESOURCES_PATH)) {
            System.out.printf("Paht is : %s/%s", absolutePath, document.toLowerCase(Locale.ROOT));
            return "%s/%s".formatted(absolutePath, document.toLowerCase(Locale.ROOT));
        }

        throw new RuntimeException("Data files does not founded");
    }

    public Object getItems() {
        try {
            entityManager.setPath(getSourcePath(Paths.PRODUCTS_PATH)).setContext(JAXBContext.newInstance(objectClass));
            return (Object) entityManager.read();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public void setItems(Object products) {
        try {
            entityManager.setPath(getSourcePath(Paths.PRODUCTS_PATH)).setContext(JAXBContext.newInstance(objectClass));
            entityManager.save(products);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
