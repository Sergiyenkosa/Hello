package rest.app.hello.dao.dto;

import java.util.List;

public interface ContactDtoRepository {

    List<ContactDto> getContactDtosExcludeRegex(String regex);
}