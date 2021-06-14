package com.world.meet.w2m.integration;

import com.world.meet.w2m.MindataApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MindataApplication.class)
@TestPropertySource(value = {"classpath:application-test.properties"})
@WebAppConfiguration
public class UserTest
{

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private final String user = "jucaguilar";
	private final String password = "user123";
	private final String codeError = "COD04";

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

	}

	@Test
	public void givenValidateUser_whenMockMVC_thenReturns_Token() throws Exception
	{
		        this.mockMvc.perform(post("/api/v1/authentication/token")
				.param("user", this.user)
				.param("password",this.password))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.token").isNotEmpty());
	}

	@Test
	public void givenValidateUser_whenMockMVC_thenReturns_Error_Code_400_missingParameter() throws Exception
	{
		this.mockMvc.perform(post("/api/v1/authentication/token")
				.param("user", this.user))
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void givenValidateUser_whenMockMVC_thenReturns_Error_Code_500_invalidCredential() throws Exception
	{
		this.mockMvc.perform(post("/api/v1/authentication/token")
				.param("user", this.user)
				.param("password","bad"))
				.andDo(print())
				.andExpect(status().is5xxServerError())
				.andExpect(jsonPath("$.error.codeError").value(this.codeError));
	}

}


