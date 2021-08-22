package com.rup.dit.companyservice.controller;


import com.rup.dit.companyservice.model.Company;
import com.rup.dit.companyservice.model.Owner;
import com.rup.dit.companyservice.service.CompanyService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    public static final String API_TOKEN = "Basic YWRtaW46UEAkJHcwcmQh";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService service;

    @Test
    public void shouldCreateCompany() throws Exception {
        Company data = new Company();
        data.setCompanyName("new company");
        when(service.createCompany(any(Company.class))).thenReturn(data);
        mockMvc.perform(post("/company")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", API_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"id\":null,\"companyName\":\"new company\",\"country\":null,\"phoneNumber\":null,\"owners\":[]}"));
    }

    @Test
    public void shouldGetAllCompanies() throws Exception {
        Company data = new Company();
        data.setCompanyName("all companies");
        when(service.getAllCompanies()).thenReturn(Lists.list(data));
        mockMvc.perform(get("/company")
                .header("Authorization", API_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("[{\"id\":null,\"companyName\":\"all companies\",\"country\":null,\"phoneNumber\":null,\"owners\":[]}]"));
    }

    @Test
    public void shouldGetCompanyDetails() throws Exception {
        Company data = new Company();
        data.setCompanyName("detailed company");
        when(service.getCompanyDetails(eq(1234L))).thenReturn(data);
        mockMvc.perform(get("/company/1234")
                .header("Authorization", API_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"id\":null,\"companyName\":\"detailed company\",\"country\":null,\"phoneNumber\":null,\"owners\":[]}"));
    }

    @Test
    public void shouldUpdateCompany() throws Exception {
        Company data = new Company();
        data.setCompanyName("updated company");
        when(service.updateCompany(any(Company.class), eq(1234L))).thenReturn(data);
        mockMvc.perform(put("/company/1234")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", API_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"id\":null,\"companyName\":\"updated company\",\"country\":null,\"phoneNumber\":null,\"owners\":[]}"));
    }

    @Test
    public void shouldAddOwners() throws Exception {
        Company data = new Company();
        data.setCompanyName("new company");
        data.getOwners().add(new Owner());
        when(service.addOwner(anyLong(), any(Owner.class))).thenReturn(data);
        mockMvc.perform(post("/company/1234/owner")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", API_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .string("{\"id\":null,\"companyName\":\"new company\",\"country\":null,\"phoneNumber\":null," +
                                "\"owners\":[{\"name\":null,\"socialSecurityNumber\":null}]}"));
    }

    @Test
    public void shouldValidateSSN() throws Exception {
        when(service.checkSocialSecurityNumber(eq("13579"))).thenReturn("valid");
        mockMvc.perform(get("/company/1234/owner/validate")
                .param("ssNumber", "13579")
                .header("Authorization", API_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content()
                        .string("valid"));
    }
}