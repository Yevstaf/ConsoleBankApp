
import net.yevstaf.ConsoleBankApp.ConsoleBankAppCommander;

public class CommandLineTest {
	//static final String SAVE_FILE = "Save.txt";
	//static ConsoleCommandsSettings settings;
	public static void main(String[] args) {
		
		ConsoleBankAppCommander commander = new ConsoleBankAppCommander("http://data.fixer.io/api/latest?access_key=22aa274be0bb639943c77cbef65aa8d1");
		commander.run(); 
		

		}
    }
	
	
	
	
	

		

