package com.world.meet.w2m.integration;

import com.jayway.jsonpath.JsonPath;
import com.world.meet.w2m.MindataApplication;
import com.world.meet.w2m.SuperheroService;
import com.world.meet.w2m.dto.SuperHeroDto;
import com.world.meet.w2m.model.SuperHero;
import com.world.meet.w2m.utils.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MindataApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(value = {"classpath:application-test.properties"})
@WebAppConfiguration
public class SuperHeroTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private SuperheroService superheroService;

	@Autowired
	private FileUtils fileUtils;

	private MockMvc mockMvc;
	private final String user = "jucaguilar";
	private final String password = "user123";
	private final String codeError = "COD04";
	private String token;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

		MvcResult result = this.mockMvc.perform(post("/api/v1/authentication/token")
				.param("user",this.user)
				.param("password",this.password))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		this.token = JsonPath.read(result.getResponse().getContentAsString(), "$.data.token");
	}

	@Test
	@Order(1)
	public void givenSave_superHero_whenMockMVC_thenReturns_Code_200() throws Exception
	{
		SuperHero same = new SuperHero();
		same.setName("linternaVerde");
		this.mockMvc.perform(post("/api/v1/management/superHero/")
				.header("Authorization",this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.fileUtils.asJsonString(same)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(5));
		SuperHeroDto response = superheroService.findById(5L);
		Assert.isTrue(response.getName().equals("linternaVerde"),"save superHero = " + response.getName());

	}


	@Test
	@Order(2)
	public void givenUpdate_superHero_whenMockMVC_thenReturns_Code_200() throws Exception
	{
		SuperHero updateSuperHero = new SuperHero();
		updateSuperHero.setId(3L);
		updateSuperHero.setName("hulk");
		SuperHeroDto beforeSuperHero = superheroService.findById(3L);
		this.mockMvc.perform(put("/api/v1/management/superHero/")
				.header("Authorization",this.token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.fileUtils.asJsonString(updateSuperHero)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.name").value("hulk"));
		SuperHeroDto afterSuperHero = superheroService.findById(3L);
		Assert.isTrue(!beforeSuperHero.getName().equals(afterSuperHero.getName()),"update superHero with ID = " + afterSuperHero.getId());
	}

	@Test
	@Order(3)
	public void givenFindAll_superHero_whenMockMVC_thenReturns_Code_200_and_sizeCorrect() throws Exception
	{
		this.mockMvc.perform(get("/api/v1/management/superHero")
				.header("Authorization",this.token))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(5)));
	}

	@Test
	@Order(4)
	public void givenFindAllById_superHero_whenMockMVC_thenReturns_Code_200() throws Exception
	{
		this.mockMvc.perform(get("/api/v1/management/superHero/{id}","1")
				.header("Authorization",this.token))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.name").value("superman"));
	}

	@Test
	@Order(5)
	public void givenFindAllByPattern_superHero_whenMockMVC_thenReturns_Code_200_and_sizeCorrect() throws Exception
	{
		this.mockMvc.perform(get("/api/v1/management/superHero/pattern/{pattern}","man")
				.header("Authorization",this.token))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(2)));
	}

	@Test
	@Order(6)
	public void givenDeleteById_superHero_whenMockMVC_thenReturns_Code_200() throws Exception
	{
		List<SuperHeroDto> beforeSuperHero = superheroService.findAll();
		this.mockMvc.perform(delete("/api/v1/management/superHero/{id}","1")
				.header("Authorization",this.token))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("borrado exitoso"));
		List<SuperHeroDto> afterSuperHero = superheroService.findAll();
		Assert.isTrue(afterSuperHero.size() == (beforeSuperHero.size() - 1),"current number of superHero = " + afterSuperHero.size());


	}




}
