package com.ggfied.samplelogin;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

public class AuthenticationControllerTest extends AbstractTest {

	@Test
	public void testRegister() throws Exception {
    this.mockMvc.perform(post("/authentication/register")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content("{\"username\":\"sss\",\"password\":\"ssss\",\"name\":\"22222\"}")
    	    .characterEncoding("utf-8"))
    .andDo(print())
    .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));
	}

}
