package eu.bytemaster.vfh.vwms.tictactoe.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import eu.bytemaster.vfh.vwms.tictactoe.Frame;

/**
 * Enthaelt die Methoden zur Auswertung des Fensterereignisse.
 * 
 * @author <joerg.birkholz@stud.fh-luebeck.de>
 */
public class WindowListener extends WindowAdapter {
	
	/**
	 * Spielfenster.
	 */
	private Frame frame;
	
	/**
	 * Klickereignisse auswerten auf dem Hauptfenster.
	 * 
	 * @param frame Spielfenster.
	 */
	public WindowListener(Frame frame) {
		this.frame = frame;
	}
	
	/**
	 * Wenn das Hauptfenster geschlossen wird.
	 */
    public void windowClosing(WindowEvent windowEvent) {
    	this.frame.dispose();
    	System.exit(0);
    }        

}
