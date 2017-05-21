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

	static QuestionInflationType1 question;

	@BeforeClass
	public static void setUpBeforeClass() {
		//@formatter:off
		question = new QuestionInflationType1(
				new BigDecimal(1000),
				BigDecimal.valueOf(0.02f).setScale(4, BigDecimal.ROUND_HALF_UP),
				10,
				true);
	}
	
	@Test
	@Ignore
	public void testSetQuestionPrompt() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testSetCorrectAnswer() {
		fail("Not yet implemented");
	}

	@Test
	public void checkAndDisplayAnswerResults_WhenPlayerAnswerIsCorrect_ThenTellThemTheyAreCorrectAndAddToTheirScore() {
		Player mockedPlayer = mock(Player.class);
		
		assertEquals("CORRECT! ", question.checkAndDisplayAnswerResults("1218.99", mockedPlayer));
		
		verify(mockedPlayer).addScore(1);
	}
	
	@Test
	public void checkAndDisplayAnswerResults_WhenPlayerAnswerIsWrong_ThenTellThemTheyAreWrongAndDisplayCorrectAnswer() {
		Player mockedPlayer = mock(Player.class);
		
		assertEquals("WRONG!\nThe correct dollar amount is: $1,218.99", question.checkAndDisplayAnswerResults("1219.00", mockedPlayer));
	}

}
