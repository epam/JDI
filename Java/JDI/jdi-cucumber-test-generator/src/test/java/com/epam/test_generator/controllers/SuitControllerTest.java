package com.epam.test_generator.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.services.SuitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class SuitControllerTest {

	private ObjectMapper mapper = new ObjectMapper();

	private MockMvc mockMvc;

	private SuitDTO suitDTO;

	private final long TEST_SUIT_ID = 1L;

	@Mock
	private SuitService suitService;

	@InjectMocks
	private SuitController suitController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(suitController)
			.setControllerAdvice(new GlobalExceptionController())
			.build();
		suitDTO = new SuitDTO();
		suitDTO.setId(TEST_SUIT_ID);
		suitDTO.setName("Suit name");
		suitDTO.setPriority(1);
		suitDTO.setDescription("Suit description");
	}

	@Test
	public void getSuits_return200whenGetSuits() throws Exception {
		when(suitService.getSuits()).thenReturn(ImmutableList.of());

		mockMvc.perform(get("/suits"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void getSuits_return500whenGetSuits() throws Exception {
		when(suitService.getSuits()).thenThrow(new RuntimeException());

		mockMvc.perform(get("/suits"))
			.andDo(print())
			.andExpect(status().isInternalServerError());
	}

	@Test
	public void getSuit_return200whenGetSuit() throws Exception {
		when(suitService.getSuit(anyLong())).thenReturn(suitDTO);

		mockMvc.perform(get("/suit/" + TEST_SUIT_ID))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void getSuit_return500whenGetSuit() throws Exception {
		when(suitService.getSuit(anyLong())).thenThrow(new RuntimeException());

		mockMvc.perform(get("/suit/" + TEST_SUIT_ID))
			.andDo(print())
			.andExpect(status().isInternalServerError());
	}

	@Test
	public void editSuit_return200whenEditSuit() throws Exception {
		when(suitService.updateSuit(any(SuitDTO.class))).thenReturn(suitDTO);

		mockMvc.perform(put("/suit/" + TEST_SUIT_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(suitDTO)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void editSuit_return500whenEditSuit() throws Exception {
		when(suitService.updateSuit(any(SuitDTO.class))).thenThrow(new RuntimeException());

		mockMvc.perform(put("/suit/" + TEST_SUIT_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(suitDTO)))
			.andDo(print())
			.andExpect(status().isInternalServerError());
	}

	@Test
	public void removeSuit_return200whenRemoveSuit() throws Exception {
		when(suitService.getSuit(anyLong())).thenReturn(suitDTO);
		doNothing().when(suitService).removeSuit(anyLong());

		mockMvc.perform(delete("/suit/" + TEST_SUIT_ID))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void getSuits_return500whenRemoveSuit() throws Exception {
		doThrow(RuntimeException.class).when(suitService).removeSuit(anyLong());

		mockMvc.perform(delete("/suit/" + TEST_SUIT_ID))
			.andDo(print())
			.andExpect(status().isInternalServerError());
	}

	@Test
	public void addSuit_return200whenAddSuit() throws Exception {
		when(suitService.addSuit(any(SuitDTO.class))).thenReturn(suitDTO);

		mockMvc.perform(post("/suit")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(suitDTO)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(mapper.writeValueAsString(suitDTO)));
	}

	@Test
	public void addSuit_return500whenAddSuit() throws Exception {
		when(suitService.addSuit(any(SuitDTO.class))).thenThrow(new RuntimeException());

		mockMvc.perform(post("/suit")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(suitDTO)))
			.andDo(print())
			.andExpect(status().isInternalServerError());
	}
}