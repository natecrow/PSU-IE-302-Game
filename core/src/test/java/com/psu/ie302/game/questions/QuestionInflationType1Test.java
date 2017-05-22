package com.psu.ie302.game.questions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.psu.ie302.game.Player;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Player.class })
public class QuestionInflationType1Test {

	//@formatter:off
	
	static QuestionInflationType1 questionForFutureValue;
	static QuestionInflationType1 questionForPastValue;

	@BeforeClass
	public static void setUpBeforeClass() {
		questionForFutureValue = new QuestionInflationType1(
				new BigDecimal(1000),
				BigDecimal.valueOf(0.02f).setScale(4, BigDecimal.ROUND_HALF_UP),
				10,
				true
			);
		
		questionForPastValue = new QuestionInflationType1(
				new BigDecimal(1000),
				BigDecimal.valueOf(-0.05f).setScale(4, BigDecimal.ROUND_HALF_UP),
				10,
				false
			);
	}
	
	@Test
	public void setQuestionPrompt_WhenFutureValueIsToBeCalculated_ThenDisplayPromptForFutureValue() {
		questionForFutureValue.setQuestionPrompt();
		
		assertEquals(
				"If I have $1,000.00 today, how much will this amount be in actual dollars after 10 years of inflation at a rate of 2.00% per year?",
				questionForFutureValue.questionPrompt
			);
	}
	
	@Test
	public void setQuestionPrompt_WhenPastValueIsToBeCalculated_ThenDisplayPromptForPastValue() {
		questionForPastValue.setQuestionPrompt();
		
		assertEquals(
				"If I have $1,000.00 today, how much was this amount in constant dollars 10 years ago with an inflation rate of -5.00% per year?",
				questionForPastValue.questionPrompt
			);
	}

	@Test
	@Ignore
	public void testSetCorrectAnswer() {
		fail("Not yet implemented");
	}

	@Test
	public void checkAndDisplayAnswerResults_WhenPlayerAnswerIsCorrect_ThenTellThemTheyAreCorrectAndAddToTheirScore() {
		Player mockedPlayer = mock(Player.class);
		
		assertEquals("CORRECT! ", questionForFutureValue.checkAndDisplayAnswerResults("1218.99", mockedPlayer));
		
		verify(mockedPlayer).addScore(1);
	}
	
	@Test
	public void checkAndDisplayAnswerResults_WhenPlayerAnswerIsWrong_ThenTellThemTheyAreWrongAndDisplayCorrectAnswer() {
		Player mockedPlayer = mock(Player.class);
		
		assertEquals("WRONG!\nThe correct dollar amount is: $1,218.99", questionForFutureValue.checkAndDisplayAnswerResults("1219.00", mockedPlayer));
	}

}
