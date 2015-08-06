package me.kordun.test.office.front;

import me.kordun.enums.CompanyCategory;
import me.kordun.enums.CompanyStatus;
import me.kordun.json.CompanyJson;
import me.kordun.office.front.controller.CompanyController;
import me.kordun.office.front.service.CompanyFrontService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.Resource;
import java.util.UUID;

import static me.kordun.test.office.front.TestUtil.convertObjectToJsonBytes;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompanyApiTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger("CompanyApiTest");

    @Mock
    private CompanyFrontService companyFrontService;
    @InjectMocks
    @Resource
    private CompanyController companyController;


    private CompanyJson json = null;


    @Before
    public void setUp() {
        super.setUp();
        log.info("CompanyApiTest setUp");

        json = CompanyJson.builder()
                .id(1L)
                .category(CompanyCategory.HIGHT_RISK)
                .details("details")
                .info("info")
                .phone("123123")
                .name("NAME")
                .status(CompanyStatus.ACTIVE)
                .email("email@asda.ru")
                .createCompanyJson();

        MockitoAnnotations.initMocks(this);
        when(companyFrontService.createCompany(any(CompanyJson.class)))
                .thenReturn(json);
        when(companyFrontService.updateCompany(any(Long.class), any(CompanyJson.class)))
                .thenReturn(json);

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testSuccessCreate() throws Exception {


        MvcResult result = mockMvc
                .perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(json)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(
                        content().json("{\"id\":1,\"status\":\"ACTIVE\"," +
                                "\"name\":\"NAME\",\"phone\":\"123123\",\"info\":\"info\"," +
                                "\"details\":\"details\",\"category\":\"HIGHT_RISK\", \"email\":\"email@asda.ru\"}")
                )
                .andReturn();

        String content = result.getResponse().getContentAsString();
        log.info("result = {}", content);

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testSuccessUpdate() throws Exception {
        MvcResult result = mockMvc
                .perform(put("/companies/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(json)))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(
                        content().json("{\"id\":1,\"status\":\"ACTIVE\"," +
                                "\"name\":\"NAME\",\"phone\":\"123123\",\"email\":\"email@asda.ru\",\"info\":\"info\"," +
                                "\"details\":\"details\",\"category\":\"HIGHT_RISK\"}")
                )
                .andReturn();

        String content = result.getResponse().getContentAsString();
        log.info("result = {}", content);

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testEmptyName() throws Exception {
        CompanyJson json = CompanyJson.builder()
                .category(CompanyCategory.HIGHT_RISK)
                .details("details")
                .info("info")
                .phone("123123")

                .email("email@asda.ru").createCompanyJson();
        MvcResult result = mockMvc
                .perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(json)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        //TODO: need more specific test
        Assert.assertThat(content, CoreMatchers.containsString("\"field\":\"name\""));

    }

    @Test
    public void testUnauthorized() throws Exception {
        mockMvc
                .perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(json)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
