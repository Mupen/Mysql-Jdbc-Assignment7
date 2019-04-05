package se.lexicon.daniel.mysql_jdbc;
import se.lexicon.daniel.mysql_jdbc.controller.ConsoleMenuController;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main( String[] args ) {
    	ConsoleMenuController ui = new ConsoleMenuController();
    	while(ui.isRunning()) { ui.run(); }
    }
}

