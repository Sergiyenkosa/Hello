package rest.app.hello.dao.dto;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import rest.app.hello.dao.entity.Contact;
import rest.app.hello.dao.entity.ContactRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactDtoRepositoryTest {

    @MockBean
    private ContactRepository contactRepository;
    @Autowired
    private ContactDtoRepository contactDtoRepository;

    @Test
    public void findAllTest() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Daenerys Targaryen"));
        contacts.add(new Contact("Jon Snow"));
        contacts.add(new Contact("Arya Stark"));
        contacts.add(new Contact("Tyrion Lannister"));
        contacts.add(new Contact("Dr. Gregory House"));

        given(contactRepository.findAll()).willReturn(contacts);

        List<ContactDto> foundContacts = contactDtoRepository.getContactDtosExcludeRegex("^A.*$");

        contacts.remove(2);

        for (int i = 0; i < contacts.size(); i++) {
            assertThat(contacts.get(i).getId(), Is.is(foundContacts.get(i).getId()));
            assertThat(contacts.get(i).getName(), Is.is(foundContacts.get(i).getName()));
        }
    }
}