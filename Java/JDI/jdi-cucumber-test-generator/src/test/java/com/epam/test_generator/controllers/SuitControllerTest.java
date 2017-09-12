package com.epam.test_generator.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.services.SuitService;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class SuitControllerTest {

	private MockMvc mockMvc;

	@Mock
	private SuitService suitService;

	@InjectMocks
	private SuitController suitController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(suitController).build();
	}

	@Test
	public void getSuits_return200whenGetSuits() {
		when(suitService.getSuits()).thenReturn(ImmutableList.of());

		ResponseEntity<List<SuitDTO>> response = suitController.getSuits();

		assert(response.getStatusCode().equals(HttpStatus.OK));
		assert(response.getBody().equals(ImmutableList.of()));
		verify(suitService).getSuits();
	}

	@Test
	public void getSuits_return500whenGetSuits() {
		when(suitService.getSuits()).thenThrow(new RuntimeException());

		ResponseEntity<List<SuitDTO>> response = suitController.getSuits();

		assert(response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(suitService).getSuits();
	}

	@Test
	public void getSuit_return200whenGetSuit() {
		SuitDTO suitDTO = new SuitDTO();

		when(suitService.getSuit(anyLong())).thenReturn(suitDTO);

		ResponseEntity<SuitDTO> response = suitController.getSuit(1L);

		assert(response.getStatusCode().equals(HttpStatus.OK));
		assert(response.getBody().equals(suitDTO));
		verify(suitService).getSuit(1L);
	}

	@Test
	public void editSuit_return200whenEditSuit() {
		SuitDTO suitDTO = new SuitDTO();

		when(suitService.updateSuit(any(SuitDTO.class))).thenReturn(suitDTO);

		ResponseEntity<Void> response = suitController.editSuit(suitDTO);

		assert(response.getStatusCode().equals(HttpStatus.OK));
		verify(suitService).updateSuit(suitDTO);
	}

	@Test
	public void removeSuit_return200whenRemoveSuit() {
		doNothing().when(suitService).removeSuit(anyLong());

		ResponseEntity<Void> response = suitController.removeSuit(1L);

		assert(response.getStatusCode().equals(HttpStatus.OK));
		verify(suitService).removeSuit(1L);
	}

	@Test
	public void getSuits_return500whenRemoveSuit() throws Exception {
		doThrow(RuntimeException.class).when(suitService).removeSuit(anyLong());

		mockMvc.perform(get("/removeSuit/1"))
			.andExpect(status().isInternalServerError());
	}

	@Test
	public void addSuit_return200whenAddSuit() {
		SuitDTO suitDTO = new SuitDTO();

		when(suitService.addSuit(any(SuitDTO.class))).thenReturn(suitDTO);

		ResponseEntity<SuitDTO> response = suitController.addSuit(suitDTO);

		assert(response.getStatusCode().equals(HttpStatus.OK));
		verify(suitService).addSuit(suitDTO);
	}

}