package rest.app.hello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.app.hello.dao.dto.ContactDto;
import rest.app.hello.dao.dto.ContactDtoRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class WebController {

    private final ContactDtoRepository contactDtoRepository;

    @Autowired
    public WebController(ContactDtoRepository contactDtoRepository) {
        this.contactDtoRepository = contactDtoRepository;
    }

    @RequestMapping("/hello/contacts")
    public Iterable<ContactDto> getNameFilteredContactsJson(@RequestParam(value="nameFilter") String nameFilter) {
        Pattern pattern = Pattern.compile(nameFilter);
        Matcher matcher = pattern.matcher("Test");
        matcher.matches(); //Check regex for validity throws PatternSyntaxException
        // that is handled in RestResponseEntityExceptionHandler class.

        return contactDtoRepository.getContactDtosExcludeRegex(pattern);
    }
}