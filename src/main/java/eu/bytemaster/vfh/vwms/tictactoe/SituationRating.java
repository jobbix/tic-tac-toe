package eu.bytemaster.vfh.vwms.tictactoe;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import eu.bytemaster.vfh.vwms.tictactoe.common.Constants;

/**
 * Enthaelt die Methoden zur Berechnung des naechsten Zuges fuer den Computergegner
 * und die 
 * 
 * @author <joerg.birkholz@stud.fh-luebeck.de>
 */
public class SituationRating implements Constants {
	
	/**
	 * Array mit den 9 Feldern des Spiels.
	 * 
	 * ---
	 * 123
	 * 456
	 * 789
	 * ---
	 */
	private String[] ratingSituationFields = new String[] {
			
		renderFieldName(0, 0), renderFieldName(0, 1), renderFieldName(0, 2),
		renderFieldName(1, 0), renderFieldName(1, 1), renderFieldName(1, 2),
		renderFieldName(2, 0), renderFieldName(2, 1), renderFieldName(2, 2)
		
	};

	/**
	 * Array mit allen moeglichen Geswinnsituationen des Spiels.
	 * 
	 *  1   2   3   4   5   6   7   8
	 * --- --- --- --- --- --- --- --- 
	 * xxx         x    x    x x     x
	 *     xxx     x    x    x  x   x
	 *         xxx x    x    x   x x
	 * --- --- --- --- --- --- --- ---
	 */
	private String[][] winSituations = new String[][] {
		
		{ratingSituationFields[0], ratingSituationFields[1], ratingSituationFields[2]},
		{ratingSituationFields[3], ratingSituationFields[4], ratingSituationFields[5]},
		{ratingSituationFields[6], ratingSituationFields[7], ratingSituationFields[8]},
		{ratingSituationFields[0], ratingSituationFields[3], ratingSituationFields[6]},
		{ratingSituationFields[1], ratingSituationFields[4], ratingSituationFields[7]},
		{ratingSituationFields[2], ratingSituationFields[5], ratingSituationFields[8]},
		{ratingSituationFields[0], ratingSituationFields[4], ratingSituationFields[8]},
		{ratingSituationFields[2], ratingSituationFields[4], ratingSituationFields[6]}
		
	};
	
	private List<String> crossPlayFields = new ArrayList<String>();
	
	private List<String> circlePlayFields = new ArrayList<String>();
	
	/**
	 * Flag ob Player X am Zug ist.
	 */
	private boolean crossPlay = false;
	
	/**
	 * Erstellt die Feldbezeichnung nach Zeile und Spalte des Spielfeldes.
	 * 
	 * @param i Zeile
	 * @param j Spalte
	 * @return Feldbezeichnung
	 */
	public String renderFieldName(int i, int j) {
		return i + " - " + j;
	}
	
	/**
	 * Schaltet um, welcher Spieler am Zug ist.
	 */
	public boolean toggleCrossPlay() {
		this.crossPlay = !this.crossPlay;
		return this.crossPlay;
	}
	
	/**
	 * Liste der Zuege, die Spieler X bereits gemacht hat.
	 * 
	 * @return
	 */
	public List<String> getCrossPlayFields() {
		return this.crossPlayFields;
	}
	
	/**
	 * Liste der Zuege, die Spieler O bereits gemacht hat.
	 * 
	 * @return
	 */
	public List<String> getCirclePlayFields() {
		return this.circlePlayFields;
	}

	/**
	 * Array der Spielfelder, die zu bewerten sind.
	 * 
	 * @return Array der Spielfelder.
	 */
	public String[] getRatingSituationFields() {
		return this.ratingSituationFields;
	}

	/**
	 * Ob Spieler X am Zug ist.
	 * 
	 * @return Flag
	 */
	public boolean isCrossPlay() {
		return this.crossPlay;
	}
	
	/**
	 * Die Listen der bereits gesetzten Felder beider Spieler leeren.
	 */
	public void cleanPlayField() {
    	if(this.crossPlayFields != null) {
    		this.crossPlayFields.clear();
    	}
    	if(this.circlePlayFields != null) {
    		this.circlePlayFields.clear();
    	}
	}
	
	/**
	 * Prueft, ob das Spielfeld bereits von dem Spieler gesetzt wurde, der am Zug ist.
	 * 
	 * @param clickedButton Spielfeld, das gesetzt werden soll.
	 * @return Flag, ob das Spielfeld bereits gesetzt wurde.
	 */
	public boolean isFieldNotSetted(JButton clickedButton) {
		String currentButtonName = clickedButton.getName();
    	if(this.isCrossPlay()) {
    		return isFieldNotSetted(currentButtonName, this.getCrossPlayFields());
    	}
    	else {
    		return isFieldNotSetted(currentButtonName, this.getCirclePlayFields());
    	}
	}

	
	/**
	 * Prueft, ob das Spielfeld bereits von einem Spieler gesetzt wurde, der am Zug ist.
	 * 
	 * @param clickedButton Spielfeld, das gesetzt werden soll.
	 * @return Flag, ob das Spielfeld bereits gesetzt wurde.
	 */
	private boolean isFieldNotSetted(String currentButtonName, List<String> playFields) {
    	if(playFields != null) {
    		for(String playField : playFields) {
    			if(playField.equals(currentButtonName)) {
    				return false;
    			}
    		}
    	}
    	return true;
	}

	/**
	 * Pruefen, ob ein Spiele mindestens die drei Felder einer Gewinnsituation besetzt hat.
	 * 
	 * @return Buchstabe des Spielers, der gewonnen hat (X oder O).
	 */
	public String checkWon() {
		for(String[] winSituation : this.winSituations) {
			int crossPlayerCounter = INITIAL_CHECK_WON_COUNTER;
			int circlePlayerCounter = INITIAL_CHECK_WON_COUNTER;
			// Eine Gewinnsituation durchgehen.
			for(String winSituationField : winSituation) {
				for(String crossPlayField : this.crossPlayFields) {
					if(winSituationField.equals(crossPlayField)) {
						crossPlayerCounter++;
					}
				}
				for(String circlePlayField : this.circlePlayFields) {
					if(winSituationField.equals(circlePlayField)) {
						circlePlayerCounter++;
					}
				}
			}
			if(crossPlayerCounter == MAXIMUM_CHECK_WON_COUNTER) {
				return CHAR_CROSS_PLAYER.toString();
			}
			if(circlePlayerCounter == MAXIMUM_CHECK_WON_COUNTER) {
				return CHAR_CIRCLE_PLAYER.toString();
			}
			crossPlayerCounter = INITIAL_CHECK_WON_COUNTER;
			circlePlayerCounter = INITIAL_CHECK_WON_COUNTER;
		}
		// Wenn bisher noch kein Spieler gewonnen hat.
		return CHAR_EMPTY_PLAYER;
	}

	/**
	 * Alle Spielfelder bewerten, welche Gewinnchanche auf jedem einzelnen Feld besetht, wenn es von dem Spieler besetzt wird.
	 * 
	 * @param ratingSituationFields Felder die geprueft werden, wenn der Spieler seinen naechsten Zug auf eines dieser Felder setzen wuerde.
	 * @param settedOpponentPlayFields Liste der bereits gesetzten Felder des Gegners.
	 * @param settedPlayerPlayFields Liste der bereits gesetzten Felder des Spielers, der am Zug ist.
	 * @return Liste mit den Bewertungen aller Felder, der die moegliche Gewinnchanche fuer jedes einzelne Feld ausdruecht, je hoeher der Wert ist.
	 */
	private int[] ratingSituation(String[] ratingSituationFields, 
			                      List<String> settedOpponentPlayFields, 
			                      List<String> settedPlayerPlayFields) {
		int[] ratingValues = new int[ratingSituationFields.length];
		for(int i = 0; i < ratingValues.length; i++) {
			ratingValues[i] = this.ratingSituationField(ratingSituationFields[i], settedOpponentPlayFields, settedPlayerPlayFields);
		}
		return ratingValues;
	}
	
	/**
	 * Ein Spielfeld bewerten, welche Gewinnchanche besetht, wenn es von dem Spieler besetzt wird.
	 * 
	 * @param currentPossiblePlayField Feld das geprueft wird, wenn der Spieler seinen naechsten Zug auf dieses Feld setzen wuerde.
	 * @param settedOpponentPlayFields Liste der bereits gesetzten Felder des Gegners.
	 * @param settedPlayerPlayFields Liste der bereits gesetzten Felder des Spielers, der am Zug ist.
	 * @return Positiver ganzzahliger Wert, der die moegliche Gewinnchanche ausdruecht, je hoeher der Wert ist.
	 */
	private int ratingSituationField(String currentPossiblePlayField, 
			                         List<String> settedOpponentPlayFields, 
			                         List<String> settedPlayerPlayFields) {
		int ratingValue = INITIAL_RATING_VALUE;
		for(String[] winSituation : this.winSituations) {
			boolean skipWinSituation = false;
			for(String opponentPlayField : settedOpponentPlayFields) {
				for(String winSituationField : winSituation) {
					if(winSituationField.equals(opponentPlayField)) {
						skipWinSituation = true;
						break;
					}
				}
				if(skipWinSituation) {
					break;
				}
			}
			if(skipWinSituation) {
				continue;
			}
			for(String winSituationField : winSituation) {
				if(winSituationField.equals(currentPossiblePlayField)) {
					ratingValue++;
				}
				for(String settedPlayerField : settedPlayerPlayFields) {
					if(settedPlayerField.equals(winSituationField)) {
						ratingValue++;
					}
				}
				for(String settedPlayerField : settedPlayerPlayFields) {
					if(settedPlayerField.equals(currentPossiblePlayField)) {
						ratingValue = INITIAL_RATING_VALUE;
						break;
					}
				}
			}
		}
		return ratingValue;
	}
	
	/**
	 * 
	 * 
	 * @param ratingSituationFields Felder die geprueft werden, wenn der Spieler seinen naechsten Zug auf eines dieser Felder setzen wuerde.
	 * @param settedOpponentPlayFields Liste der bereits gesetzten Felder des Gegners.
	 * @param settedPlayerPlayFields Liste der bereits gesetzten Felder des Spielers, der am Zug ist.
	 * @return Index des Feldes, das mit der maximalen Gewinnchanche bewertet wurde. Dies ist der Feldindex, der als naechstes gesetzt wird.
	 */
	private int getMaximumRatingFieldIndex(String[] ratingSituationFields, 
			                               List<String> settedOpponentPlayFields, 
			                               List<String> settedPlayerPlayFields) {
		int[] ratingValues = this.ratingSituation(ratingSituationFields, settedOpponentPlayFields, settedPlayerPlayFields);
		int maximumRating = INITIAL_MAXIMUM_RATING;
		int maximumRatingIndex = INITIAL_MAXIMUM_RATING_INDEX;
		for(int i = 0; i < ratingValues.length; i++) {
			if(maximumRating < ratingValues[i]) {
				maximumRating = ratingValues[i];
				maximumRatingIndex = i;
			}
		}
		return maximumRatingIndex;
	}
	
	/**
	 * Feld mit der hoechsten Bewertung suchen.
	 * 
	 * @param ratingSituationFields Felder die geprueft werden, wenn der Spieler seinen naechsten Zug auf eines dieser Felder setzen wuerde.
	 * @param settedOpponentPlayFields Liste der bereits gesetzten Felder des Gegners.
	 * @param settedPlayerPlayFields Liste der bereits gesetzten Felder des Spielers, der am Zug ist.
	 * @return Feld, das mit der maximalen Gewinnchanche bewertet wurde. Dies ist das Feld, der als naechstes gesetzt wird.
	 */
	public String searchMaximumRatingField(String[] ratingSituationFields, 
			                               List<String> settedOpponentPlayFields, 
			                               List<String> settedPlayerPlayFields) throws IndexOutOfBoundsException {
		int maximumRatingFieldIndex = this.getMaximumRatingFieldIndex(ratingSituationFields, settedOpponentPlayFields, settedPlayerPlayFields);
		if(maximumRatingFieldIndex < 0) {
			// Hier Ausnahme aufwerfen, da kein Feleindex fuer einen moeglichen naechsten Zug ermittelt werden konnte.
			throw new IndexOutOfBoundsException(MAXIMUM_RATING_FIELD_ERROR_MESSAGE);
		}
		return this.ratingSituationFields[maximumRatingFieldIndex];
		
	}

}
