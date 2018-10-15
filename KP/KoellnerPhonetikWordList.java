package KP;

/**
 * {@code KoellnerPhonetikWordList} Klasse um ein decodiertes und encodiertes Wort abzubilden.
 */
public class KoellnerPhonetikWordList {
    private String encodedWord;
    private String decodedWord;

    KoellnerPhonetikWordList(String encodedWord, String decodedWord) {
        this.decodedWord = decodedWord;
        this.encodedWord = encodedWord;
    }

    public String getEncodedWord() {
        return encodedWord;
    }

    public String getDecodedWord() {
        return decodedWord;
    }
}
