package eu.bytemaster.vfh.vwms.tictactoe.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import eu.bytemaster.vfh.vwms.tictactoe.Frame;

/**
 * Enthaelt die Methoden zur Auswertung der angeklickten Spielfelder.
 * 
 * @author <joerg.birkholz@stud.fh-luebeck.de>
 */
public class ButtonClickListener implements MouseListener{

	private Frame frame;
	
	public ButtonClickListener(Frame frame) {
		this.frame = frame;
	}
	
	/**
	 * Wenn mit der Maus ein Spielfeld angeklickt wurde.
	 * 
	 * @event Ereignis, das ausgeloest wurde.
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		Object source = event.getSource();
		if(source instanceof JButton) {
			JButton clickedButton = (JButton) source;
			// Wurde das Spielfeld schon einmal gesetzt?
			if(this.frame.isFieldNotSetted(clickedButton)) {
				this.frame.setButtonText(clickedButton);
				this.frame.setNextField();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

}
