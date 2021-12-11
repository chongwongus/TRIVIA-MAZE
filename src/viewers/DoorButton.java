package viewers;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Creates a button specifically for checking door information
 * 
 * @author Jason Hsu
 *
 */
public class DoorButton extends JButton {

    public DoorButton() {
        ImageIcon myIcon = new ImageIcon("Door.png");
        this.setIcon(myIcon);
    }
}