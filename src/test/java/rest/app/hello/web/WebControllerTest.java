package rest.app.hello.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rest.app.hello.dao.dto.ContactDto;
import rest.app.hello.dao.dto.ContactDtoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
public class WebControllerTest {

    @MockBean
    ContactDtoRepository contactDtoRepository;
    @Autowired
    private MockMvc mvc;

    @Test
    public void getNameFilteredContactsJsonTest() throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        contactDtos.add(new ContactDto(1, "Chuck Norris"));

        given(this.contactDtoRepository.getContactDtosExcludeRegex("^Rembo$"))
                .willReturn(contactDtos);

        this.mvc.perform(get("/hello/contacts?nameFilter=^Rembo$").accept(MediaType.ALL))
                .andExpect(status().isOk()).andExpect(content().
                json("[{\"id\":1,\"name\":\"Chuck Norris\"}]"));
    }

    @Test
    public void getNameFilteredContactsJsonWithWrongRegexTest() throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        contactDtos.add(new ContactDto(1, ""));

        given(this.contactDtoRepository.getContactDtosExcludeRegex("^[[[[[$"))
                .willReturn(contactDtos);

        this.mvc.perform(get("/hello/contacts?nameFilter=^[[[[[$").accept(MediaType.ALL))
                .andExpect(status().isBadRequest()).andExpect(content().
                string("Wrong \"nameFilter\" regex : \"^[[[[[$\", status=400"));
    }
}