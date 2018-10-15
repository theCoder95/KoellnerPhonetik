package KP;

/**
 * {@code KoellnerPhonetik} Klasse um Wörter zu codieren nach den Köllner-Phonetik Regeln.
 *
 * @see <a href = "https://de.wikipedia.org/wiki/Kölner_Phonetik">Wiki: Köllner-Phonetik</a>
 * @see KoellnerPhonetikCharacterHelper
 */
public class KoellnerPhonetikDecoder {
    private KoellnerPhonetikCharacterHelper koellnerPhonetikCharacterHelper;
    private String anlaut;
    private static final int CHAR_BEFORE_INDEX = 0;
    private static final int CHAR_AFTER_INDEX = 1;
    private static final int CHAR_CURRENT_INDEX = 2;

    KoellnerPhonetikDecoder(KoellnerPhonetikCharacterHelper koellnerPhonetikCharacterHelper, String anlaut) {
        this.koellnerPhonetikCharacterHelper = koellnerPhonetikCharacterHelper;
        this.anlaut = anlaut;
    }

    /**
     * decodiert ein Zeichen nach den Köllner-Phonetik Regeln.
     *
     * @return decodierter String
     */
    public String getDecodedPartString() {
        String currentChar = koellnerPhonetikCharacterHelper.getCharacterList().get(CHAR_CURRENT_INDEX).toLowerCase();
        String charBefore = koellnerPhonetikCharacterHelper.getCharacterList().get(CHAR_BEFORE_INDEX).toLowerCase();
        String charAfter = koellnerPhonetikCharacterHelper.getCharacterList().get(CHAR_AFTER_INDEX).toLowerCase();
        if (currentChar.matches("[aeijouy]")) {
            return "0";
        } else if (currentChar.matches("[b]")) {
            return "1";
        } else if (currentChar.matches("[p]") && !charAfter.matches("[h]")) {
            return "1";
        } else if (currentChar.matches("[dt]") && !charAfter.matches("[c]") && !charAfter.matches("[s]") && !charAfter.matches("[z]")) {
            return "2";
        } else if (currentChar.matches("[fvw]")) {
            return "3";
        } else if (currentChar.matches("[p]") && charAfter.matches("[h]")) {
            return "3";
        } else if (currentChar.matches("[gkq]")) {
            return "4";
        } else if (currentChar.matches("[c]") && anlaut.matches("c") && charAfter.matches("[ahkloqrux]")) {
            return "4";
        } else if (currentChar.matches("[c]") && charAfter.matches("[ahkoqux]") && !charBefore.matches("[sz]")) {
            return "4";
        } else if (currentChar.matches("[x]") && !charBefore.matches("[ckq]")) {
            return "48";
        } else if (currentChar.matches("[l]")) {
            return "5";
        } else if (currentChar.matches("[mn]")) {
            return "6";
        } else if (currentChar.matches("[r]")) {
            return "7";
        } else if (currentChar.matches("[sz]")) {
            return "8";
        } else if (currentChar.matches("[c]") && charBefore.matches("[sz]")) {
            return "8";
        } else if (currentChar.matches("[c]") && anlaut.matches("[c]") && !charAfter.matches("[ah]") && charAfter.matches("[kloqrux]")) {
            return "8";
        } else if (currentChar.matches("[c]") && !charAfter.matches("[ahko]") && charAfter.matches("[qux]")) {
            return "8";
        } else if (currentChar.matches("[dt]") && charAfter.matches("[csz]")) {
            return "8";
        } else if (currentChar.matches("[x]") && charBefore.matches("[ckq]")) {
            return "8";
        }
        return "";
    }
}
