package rest.app.hello.dao.dto;

import java.util.List;
import java.util.regex.Pattern;

public interface ContactDtoRepository {

    List<ContactDto> getContactDtosExcludeRegex(Pattern pattern);
}