package viewers;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DoorButton extends JButton {
	
	public DoorButton() {
		ImageIcon myIcon = new ImageIcon("Door.png");
		this.setIcon(myIcon);
	}
}
