package eu.bytemaster.vfh.vwms.tictactoe;

import static org.junit.Assert.assertEquals;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SituationRatingTest {

	private SituationRating situationRating;
	
	@Before
	public void setUp() {
		if(this.situationRating == null) {
			this.situationRating = new SituationRating();
		}
	}
	
	@After
	public void tearDown() {
	    this.situationRating = null;
	}

	@Test
	public void checkWon() {
		int checkWonResultLength = this.situationRating.checkWon().length();
		assertEquals(true, checkWonResultLength <= 1);
	}
	
	@Test
	public void isFieldNotSetted() {
		String name = this.situationRating.renderFieldName(0, 0);
		JButton button = new JButton();
		button.setName(name);
		boolean isFieldNotSetted = this.situationRating.isFieldNotSetted(button);
		assertEquals(true, isFieldNotSetted);
	}

	@Test
	public void renderFieldName() {
		String fieldName = this.situationRating.renderFieldName(0, 0);
		assertEquals("0 - 0", fieldName);
	}

	@Test
	public void toggleCrossPlay() {
		boolean isCroggPlay = this.situationRating.isCrossPlay();
		boolean toggleCrossPlay = this.situationRating.toggleCrossPlay();
		assertEquals(isCroggPlay, !toggleCrossPlay);
	}

}
