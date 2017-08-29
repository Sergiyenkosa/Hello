package rest.app.hello.dao.entity;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact,Long> {

    @Override
    @Cacheable("contacts")
    List<Contact> findAll();
}