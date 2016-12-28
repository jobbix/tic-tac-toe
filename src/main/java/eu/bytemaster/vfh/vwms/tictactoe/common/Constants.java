package eu.bytemaster.vfh.vwms.tictactoe.common;

/**
 * Konstanten
 * 
 * @author <joerg.birkholz@stud.fh-luebeck.de>
 */
public interface Constants {

	/**
	 *  Initial ist der Wert 0, da zu Beginn der Gewinnpruefung noch kein Feld einer Gewinnsituation besetzt ist.
	 */
	public final int INITIAL_CHECK_WON_COUNTER = 0;
	
	/**
	 *  Hier ist der Wert 3, da alle drei Felder einer Gewinnsituation besetzt sein muessen.
	 */
	public final int MAXIMUM_CHECK_WON_COUNTER = 3;

	/**
	 *  Initial ist der Wert 0, da die Bewertung eines Spielfeldes vor der eigentlichen Bewertung neutral ist.
	 */
	public final int INITIAL_RATING_VALUE = 0;

	/**
	 *  Initial ist der Wert 0, da dies einen neutralen Wert darstellt und kein Feld falsch bewertet werden kann.
	 */
	public final int INITIAL_MAXIMUM_RATING = 0;
	
	/**
	 * Anzahl der Felder horizontal ist beim herkoemmlichen Tic-Tac-Toe gleich 3.
	 */
	public final int PLAYING_FIELD_WIDTH = 3;

	/**
	 * Anzahl der Felder vertikal ist beim herkoemmlichen Tic-Tac-Toe gleich 3.
	 */
	public final int PLAYING_FIELD_HEIGHT = 3;

	/**
	 *  Initial ist der Wert -1, damit kein falsches Feld aus dem Array zurueckgegeben werden kann.
	 */
	public final int INITIAL_MAXIMUM_RATING_INDEX = -1;

	/**
	 * Fuer die Darstellung in der GUI.
	 */
	public final Character CHAR_CROSS_PLAYER = 'X';

	/**
	 * Fuer die Darstellung in der GUI.
	 */
	public final Character CHAR_CIRCLE_PLAYER = 'X';
	
	/**
	 * Titel des Hauptfenstern in der Kopfzeile.
	 */
	public final String FRAME_TITLE = "TIC-TAC-TOE";

	/**
	 * Fuer die Darstellung in der GUI.
	 */
	public final String CHAR_EMPTY_PLAYER = "";

	/**
	 * Text fuer Fehlermeldung beim Finden des naechsten Spielfeldes.
	 */
	public final String MAXIMUM_RATING_FIELD_ERROR_MESSAGE = "Es konnte Kein Feld fuer einen moeglich naechsten Zug ermittelt werden!";

	/**
	 * Faktor zur Groessenberechnung des Fensters.
	 * 
	 * 1 - Fenster wird ueber den gesamten Bildschirm dargestellt.
	 * 2 - Fenster wird ueber ein Viertel des Bildschirm dargestellt.
	 * 3 - Fenster wird ueber ein Neuntel des Bildschirm dargestellt.
	 * 4 - Fenster wird ueber ein Sechzehntel des Bildschirm dargestellt.
	 * 
	 * usw.
	 */
	public final int INITIAL_FRAME_SIZE_FACTOR = 4;
	
	/**
	 * Titel fuer Hinweismeldungsfenster.
	 */
	public final String OPTION_PANE_TITLE = "Hinweismeldung";
	
	/**
	 * Nachricht fuer Hinweismeldungsfenster.
	 */
	public final String OPTION_PANE_MESSAGE = "Spieler %s hat gewonnen!";

}
