package com.oli.RestController;

import org.apache.commons.lang3.Validate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.oli.RestController.JsonUtil.getMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

abstract class RestControllerTestHelperAbstract<T, ID> {
    private String API_URL = "/api/leads/";
    private MockMvc mockMvc;
    private Class<T> classType;

    RestControllerTestHelperAbstract(String API_URL, MockMvc mockMvc, Class<T> classType) {
        this.API_URL = API_URL;
        this.mockMvc = mockMvc;
        this.classType = classType;
    }

    // C Create
    T save(T entity) throws Exception {
        String jsonStr = getMapper().writeValueAsString(entity);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(API_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStr);
        MvcResult mvcResult = mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        return getMapper().readValue(mvcResult.getResponse().getContentAsString(), classType);
    }

    // R Read
    T findById(ID id) throws Exception {
        // Validate Preconditions
        Validate.notNull(id);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get(API_URL + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        return getMapper().readValue(mvcResult.getResponse().getContentAsString(), classType);
    }

    // U Update
    T replace(T entity, ID id) throws Exception {
        String jsonStr = getMapper().writeValueAsString(entity);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put(API_URL + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStr);
        MvcResult mvcResult = mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        return getMapper().readValue(mvcResult.getResponse().getContentAsString(), classType);
    }

    // D Delete
    void deleteById(ID id) throws Exception {
        MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders
                .delete(API_URL + id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(builder2)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
