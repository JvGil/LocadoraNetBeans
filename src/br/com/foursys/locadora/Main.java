package br.com.foursys.locadora;

import br.com.foursys.locadora.view.MenuView;
import javax.swing.UIManager;

/**
 *
 * @author jgil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
        new MenuView();
    }
}
