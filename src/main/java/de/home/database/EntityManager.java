package de.home.database;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("restrict")
public class EntityManager {
	JAXBContext context;
	Marshaller marshaller;
	Unmarshaller unmarshaller;
	String path;

	public EntityManager() {

	}

	public EntityManager(JAXBContext context, String path) {

		try {
			this.context = context;
			this.path = path;
			marshaller = context.createMarshaller();
			unmarshaller = context.createUnmarshaller();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EntityManager setContext(JAXBContext context) throws JAXBException {
		this.context = context;
		marshaller = context.createMarshaller();
		unmarshaller = context.createUnmarshaller();
		return this;

	}

	public EntityManager setPath(String path) {
		this.path = path;
		return this;

	}

	public void save(Object collections) throws JAXBException {

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// uncomment to print the data inserting
		// marshaller.marshal(products, new PrintWriter( System.out));
		marshaller.marshal(collections, new File(path));

	}

	public Object read() throws JAXBException {

		File file = new File(path);
		if (!file.exists()) {
			System.out.println("File does not exists!");
			return null;
		}
		return unmarshaller.unmarshal(file);

	}

}
