package rest.app.hello.dao.entity;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @After
    public void afterTest() {
        contactRepository.deleteAll();
    }

    @Test
    public void checkSaveAndRetrieveContactRepositoryTest() {
        Contact contact = contactRepository.save(new Contact("Test"));

        Contact foundContact = contactRepository.findOne(contact.getId());

        assertNotNull(foundContact);
        assertEquals(contact.getName(), foundContact.getName());
    }

    @Test
    public void checkFindAllAndSequenceTest() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Neo"));
        contacts.add(new Contact("Morpheus"));
        contacts.add(new Contact("Trinity"));
        contacts.add(new Contact("Agent Smith"));
        contacts.add(new Contact("Gandalf"));

        contactRepository.save(contacts);

        List<Contact> foundContacts = new LinkedList<>();

        foundContacts.addAll(contactRepository.findAll());

        for (int i = 0; i < contacts.size(); i++) {
            assertThat(contacts.get(i).getId(), Is.is(foundContacts.get(i).getId()));
            assertThat(contacts.get(i).getName(), Is.is(foundContacts.get(i).getName()));
        }
    }
}