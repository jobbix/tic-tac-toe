package eu.bytemaster.vfh.vwms.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import eu.bytemaster.vfh.vwms.tictactoe.common.Constants;
import eu.bytemaster.vfh.vwms.tictactoe.listener.ButtonClickListener;
import eu.bytemaster.vfh.vwms.tictactoe.listener.WindowListener;

/**
 * Hauptfenster des Spiels Tic-Tac-Toe.
 * 
 * @author <joerg.birkholz@stud.fh-luebeck.de>
 */
@SuppressWarnings("serial")
public class Frame extends JFrame implements Constants {
	
	/**
	 * Anzahl Spielfelder horizontal.
	 */
	private int playingFieldWidth = PLAYING_FIELD_WIDTH;

	/**
	 * Anzahl Spielfelder vertikal.
	 */
	private int playingFieldHeight = PLAYING_FIELD_HEIGHT;
	
	/**
	 * Array mit den Spielfeldern in der GUI (Schaltflaechen).
	 */
	private JButton[][] playingField = new JButton[this.playingFieldHeight][this.playingFieldWidth];
	
	/**
	 * Objekt mit den Methoden zur Bewertung der Spielsituation und der Gewinnsituation.
	 */
	private SituationRating sRating;

	public static void main(String[] args) {
		new Frame();
	}
	
	public Frame() {
		if(this.sRating == null) {
			this.sRating = new SituationRating();
		}
		this.init();	
	}
	
	private void init() {
		double frameSizeFactor = INITIAL_FRAME_SIZE_FACTOR;
		Dimension frameSize = this.getFrameSize(frameSizeFactor);
		this.setLocation(this.getFrameLocation(frameSize));
		this.setSize(frameSize);
		this.setTitle(FRAME_TITLE);
		this.setMaximumSize(this.getScreenSize());
		this.addWindowListener(new WindowListener(this));
		this.setLayout(new GridLayout(this.playingFieldWidth, this.playingFieldHeight));
		this.initPlayingField();
		this.setVisible(true);
	}
	
	/**
	 * Prueft, ob das Spielfeld bereits von dem Spieler gesetzt wurde, der am Zug ist.
	 * 
	 * @param clickedButton Spielfeld, das gesetzt werden soll.
	 * @return Flag, ob das Spielfeld bereits gesetzt wurde.
	 */
	public boolean isFieldNotSetted(JButton clickedButton) {
		return this.sRating.isFieldNotSetted(clickedButton);
	}
	
	/**
	 * Hier wird das Spielfeld belegt in der GUI von dem jeweiligen Spieler.
	 * 
	 * @param clickedButton Spielfeld, das in der GUI angeklickt wurde.
	 */
	public void setButtonText(JButton clickedButton) {
    	for(int i = 0; i < this.playingFieldHeight; i++) {
        	for(int j = 0; j < this.playingFieldWidth; j++) {
        		String clickedButtonName = clickedButton.getName();
        		String currentButtonName = this.sRating.renderFieldName(i, j);
        		if(currentButtonName.equals(clickedButtonName)) {
        			String currentButtonText = this.playingField[i][j].getText();
        			// Kein Text vorhanden, dann Spielfeld noch frei.
        			if(currentButtonText.length() <= 0) {
        				if(this.sRating.isCrossPlay()) {
        					this.sRating.getCrossPlayFields().add(clickedButtonName);
                			this.playingField[i][j].setText("X");
        				}
        				else {
        					this.sRating.getCirclePlayFields().add(clickedButtonName);
                			this.playingField[i][j].setText("O");
        				}
        				this.sRating.toggleCrossPlay();
        			}
        		}
        	}
    	}
    	this.checkWon();
	}
	
	/**
	 * Prueft, ob ein Spieler gewonnen hat.
	 */
	public void checkWon() {
		String checkWonResult = this.sRating.checkWon();
		// Hat einer gewonnen?
		if(checkWonResult != null && checkWonResult.length() > 0) {
			String title = OPTION_PANE_TITLE;
			String message = String.format(OPTION_PANE_MESSAGE, checkWonResult);
			int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.CLOSED_OPTION);
			if(result == 0) {
				this.cleanPlayingField();
			}
		}
	}
	
	/**
	 * Hier wird das naechste Feld ermittelt, dass der Computergegner setzt. Je nach 
	 * Spieler wird das beste Spielfeld geucht, das als naechstes gesetzt wird.
	 */
	public void setNextField() {
		String maximumRatingField = null;
		if(this.sRating.isCrossPlay()) {
			maximumRatingField = this.sRating.searchMaximumRatingField(this.sRating.getRatingSituationFields(), 
																	   this.sRating.getCirclePlayFields(), 
																	   this.sRating.getCrossPlayFields());
		}
		else {
			maximumRatingField = this.sRating.searchMaximumRatingField(this.sRating.getRatingSituationFields(), 
																	   this.sRating.getCrossPlayFields(), 
																	   this.sRating.getCirclePlayFields());
		}
		if(maximumRatingField != null) {
	    	for(int i = 0; i < this.playingFieldHeight; i++) {
	        	for(int j = 0; j < this.playingFieldWidth; j++) {
	        		String buttonName = this.playingField[i][j].getName();
	        		if(maximumRatingField.equals(buttonName)) {
	        			this.setButtonText(this.playingField[i][j]);
	        			break;
	        		}
	        	}
	    	}
		}
	}
		
	/**
	 * Spielfeld zuruecksetzen, fuer ein neues Spiel
	 */
	private void cleanPlayingField() {
    	for(int i = 0; i < this.playingFieldHeight; i++) {
        	for(int j = 0; j < this.playingFieldWidth; j++) {
        		this.playingField[i][j].setText("");
        	}
    	}
    	this.sRating.cleanPlayField();
	}

	/**
	 * Hier wird das Spielfeld mit allen Feldern initialisiert.
	 */
	private void initPlayingField() {
    	for(int i = 0; i < this.playingFieldHeight; i++) {
        	for(int j = 0; j < this.playingFieldWidth; j++) {
        		this.playingField[i][j] = new JButton();
        		String renderedButtonName = this.sRating.renderFieldName(i, j);
        		this.playingField[i][j].setName(renderedButtonName);
        		ButtonClickListener buttonClickListener = new ButtonClickListener(this);
        		this.playingField[i][j].addMouseListener(buttonClickListener);
        		this.add(this.playingField[i][j]);
        	}
    	}
    }
	
	/**
	 * Bildschirmgroesse ermitteln.
	 * 
	 * @return Bildschirmgroesse.
	 */
	private Dimension getScreenSize() {
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = defaultToolkit.getScreenSize();
		return(screenSize);
	}
	
	/**
	 * Fenstergroesse berechnen.
	 * 
	 * @param sizeFactor Faktor, um den das Fenster im Verhaeltnis zum Bildschirn verkleinert wird.
	 * @return Fenstergroesse.
	 */
	private Dimension getFrameSize(double sizeFactor) {
		Dimension screenSize = getScreenSize();
		double screenWidth = screenSize.getWidth() / sizeFactor;
		double screenHeight = screenSize.getHeight() / sizeFactor;
		Dimension frameSize = new Dimension((int)screenWidth, (int)screenHeight);
		return(frameSize);
	}
	
	/**
	 * Als Fensterposition genau den Mittelpunkt des Bildschirms berechnen.
	 * 
	 * @param frameSize Bildschirmgroesse.
	 * @return Fensterposition im Mittelpunkt des Bildschirms.
	 */
	private Point getFrameLocation(Dimension frameSize) {
		Dimension screenSize = getScreenSize();
		double framePositionX = screenSize.getWidth() / 2 - frameSize.getWidth() / 2;
		double framePositionY = screenSize.getHeight() / 2 - frameSize.getHeight() / 2;
		Point frameLocation = new Point((int)framePositionX, (int)framePositionY);		
		return frameLocation;
	}

}
