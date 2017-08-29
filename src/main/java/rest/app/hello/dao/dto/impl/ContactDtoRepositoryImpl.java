package rest.app.hello.dao.dto.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rest.app.hello.dao.dto.ContactDto;
import rest.app.hello.dao.dto.ContactDtoRepository;
import rest.app.hello.dao.entity.Contact;
import rest.app.hello.dao.entity.ContactRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class ContactDtoRepositoryImpl implements ContactDtoRepository {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactDtoRepositoryImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDto> getContactDtosExcludeRegex(Pattern pattern) {
        List<ContactDto> contactDtos = new LinkedList<>();

        for (Contact contact: contactRepository.findAll()) {
            if (!pattern.matcher(contact.getName()).matches()) {
                contactDtos.add(new ContactDto(contact.getId(), contact.getName()));
            }
        }

        return contactDtos;
    }
}