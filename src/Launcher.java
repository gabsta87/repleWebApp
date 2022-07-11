import control.Controller;

/**
 * Reple is the contraction of "Repetitive Learning". This tools allows to learn collections of tuples 
 * by the principle of repetition
 * 
 * @author Formation
 *
 */
public class Launcher {
	public static void main(String[] args) {
		
		// TODO tester les arguments pour choisir entre mode console ou mode webApp
		Controller.getInstance().startMenu();
	}
}
