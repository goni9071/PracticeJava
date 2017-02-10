package jaxb;

import javax.xml.bind.*;
import java.io.*;

class MarshallTest {
    public static void main(String args[]) throws Exception {
        MarshallTest mt = new MarshallTest();
        mt.marshalling();
        mt.unMarshalling();
    }

    private void marshalling() throws Exception {
        JAXBContext jContext = JAXBContext.newInstance("jaxb");
        Marshaller marshaller = jContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(createUser(), new FileOutputStream("/user.xml"));
    }

    private User createUser() {
        ObjectFactory factory = new ObjectFactory();
        User user = (User) (factory.createUser());
        user.setId("YuYu");
        user.setName("YuYuJung");
        user.setEmail("yuyu10293847@gmail.com");
        return user;
    }

    private void unMarshalling() throws Exception {
        JAXBContext jContext = JAXBContext.newInstance("jaxb");
        Unmarshaller unMarshaller = jContext.createUnmarshaller();
        User user = (User) unMarshaller.unmarshal(new File("/user.xml"));

        System.out.println("ID    : " + user.getId());
        System.out.println("NAME  : " + user.getName());
        System.out.println("EMAIL : " + user.getEmail());
    }
}
