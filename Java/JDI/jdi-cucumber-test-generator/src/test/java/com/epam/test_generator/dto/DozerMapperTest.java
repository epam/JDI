package com.epam.test_generator.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DozerMapperTest {

	private DozerMapper dozerMapper;

	@Before
	public void setUp() {
		dozerMapper = new DozerMapper();
	}

	@Test
	public void mapSuitToSuitDTO() {
		Suit suit = new Suit();
		SuitDTO suitDTO = new SuitDTO();

		suit.setId(1L);
		suit.setName("Suit name");
		suit.setDescription("Suit description");
		suit.setPriority(1);

		dozerMapper.map(suit, suitDTO);

		assertThat(suit.getId(), is(equalTo(suitDTO.getId())));
		assertThat(suit.getName(), is(equalTo(suitDTO.getName())));
		assertThat(suit.getDescription(), is(equalTo(suitDTO.getDescription())));
		assertThat(suit.getPriority(), is(equalTo(suitDTO.getPriority())));
	}

	@Test
	public void mapCaseToCaseDTO() {
		Case caze = new Case();
		CaseDTO caseDTO = new CaseDTO();

		caze.setId(1L);
		caze.setDescription("Case description");
		caze.setPriority(1);

		dozerMapper.map(caze, caseDTO);

		assertThat(caze.getId(), is(equalTo(caseDTO.getId())));
		assertThat(caze.getDescription(), is(equalTo(caseDTO.getDescription())));
		assertThat(caze.getPriority(), is(equalTo(caseDTO.getPriority())));
	}

	@Test
	public void mapStepToStepDTO() {
		Step step = new Step();
		StepDTO stepDTO = new StepDTO();

		step.setId(1L);
		step.setType(StepType.GIVEN.ordinal());
		step.setDescription("Case description");
		step.setRowNumber(1);

		dozerMapper.map(step, stepDTO);

		assertThat(step.getId(), is(equalTo(stepDTO.getId())));
		assertThat(StepType.values()[step.getType()].ordinal(), is(equalTo(stepDTO.getType())));
		assertThat(step.getDescription(), is(equalTo(stepDTO.getDescription())));
		assertThat(step.getRowNumber(), is(equalTo(stepDTO.getRowNumber())));
	}

}