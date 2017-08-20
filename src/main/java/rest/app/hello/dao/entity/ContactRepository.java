package rest.app.hello.dao.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact,Long> {

    @Override
    List<Contact> findAll();
}