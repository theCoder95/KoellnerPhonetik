package KP;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code KoellnerPhonetikCharacterHelper} Helper-Klasse, die Strings extrahiert, welche beim Decodieren benötigt werden.
 */
public class KoellnerPhonetikCharacterHelper {
    private List<String> chars;
    private static final String EMPTRY_STRING = "";

    KoellnerPhonetikCharacterHelper(String word, int position) {
        chars = new ArrayList<>();
        chars.add((position != 0) ? word.substring(position - 1, position) : EMPTRY_STRING);
        chars.add((position != (word.length() - 1)) ? word.substring(position + 1, position + 2) : EMPTRY_STRING);
        chars.add(word.substring(position, position + 1));
    }

    public List<String> getCharacterList() {
        return chars;
    }
}
