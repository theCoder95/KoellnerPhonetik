package KP;

public class Main {
	public static void main(String[] args) {
    	KoellnerPhonetik kp = new KoellnerPhonetik();
    	kp.addWord("Fernseher: DEL");
    	kp.addWord("Fernseher: Sony");
    	kp.addWord("Fetter Teddy");
    	kp.addWord("IPhone 5S");
    	kp.addWord("Physik Formelsammlung");
    	kp.addWord("Papula Formelsammlung");
    	
    	/*
    	 * Köllner Phonetik "ignoriert" Rechtschreibe-Fehler.
    	 * Da er Worte nach den phonetischen Lauten analysiert.
    	 */
    	System.out.println(kp.getString("Fohhrmehl"));
    }
}
