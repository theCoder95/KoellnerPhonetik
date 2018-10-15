package KP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@code KoellnerPhonetik} Klasse für die unscharfe Suche eines Wortes.
 *
 * @see KoellnerPhonetikWordList
 */
public class KoellnerPhonetik {
    private List<KoellnerPhonetikWordList> koellnerPhonetikList = new ArrayList<>();
    private KMP kmp = new KMP();
    public List<KoellnerPhonetikWordList> getKoellnerPhonetikList() {
        return koellnerPhonetikList;
    }

    /**
     * fügt ein Suchwort und das dazugehörige decodiertes Wort in eine Liste.
     *
     * @param word Suchwort des Benutzers
     */
    public void addWord(String word) {
        String decodedString = decodeWord(word);
        koellnerPhonetikList.add(new KoellnerPhonetikWordList(word, decodedString));
    }

    public List<String> getString(String suchwort) {
        if (suchwort.isEmpty())
            return new ArrayList<String>();
        if (getEqualString(suchwort).size() != 0) {
            return getEqualString(suchwort);
        } else if (getExactString(suchwort).size() == 0) {
            return getNextRelatedString(suchwort);
        } else {
            return getExactString(suchwort);
        }
    }

    private List<String> getEqualString(String suchwort) {
        List<String> match = new ArrayList<>();
        for (KoellnerPhonetikWordList koellnerPhonetikList : koellnerPhonetikList) {
            if (suchwort.equals(koellnerPhonetikList.getEncodedWord())) {
                match.add(suchwort);
            }
        }
        return match;
    }

    private List<String> getExactString(String suchwort) {
        List<String> match = new ArrayList<>();
        String suchChar = decodeWord(suchwort);
        for (KoellnerPhonetikWordList koellnerPhonetikList : koellnerPhonetikList) {
            if (suchChar.equals(koellnerPhonetikList.getDecodedWord()))
                match.add(koellnerPhonetikList.getEncodedWord());
        }
        return match;
    }

    private List<String> getNextRelatedString(String suchWort) {
        int temporaryMaxMatches = 0;
        int temporaryMaxRelated = 0;
        int grenze;
        int grenzeNormal;
        List<String> match = new ArrayList<>();
        String suche = decodeWord(suchWort);
        for (int i = 0; i < koellnerPhonetikList.size(); i++) {
            if (suche.length() >= koellnerPhonetikList.get(i).getDecodedWord().length()) {
                grenze = koellnerPhonetikList.get(i).getDecodedWord().length();
            } else {
                grenze = suche.length();
            }
            if (suchWort.length() >= koellnerPhonetikList.get(i).getEncodedWord().length()) {
                grenzeNormal = koellnerPhonetikList.get(i).getEncodedWord().length();
            } else {
                grenzeNormal = suchWort.length();
            }
            String currentEncodedWord = koellnerPhonetikList.get(i).getEncodedWord();
            String currentDecodedWord = koellnerPhonetikList.get(i).getDecodedWord();
            int countMatches = 0;
            int countRelated = 0;
            for (int j = 0; j < grenze; j++) {
                if (kmp.KMPSearch( suche.substring(j),currentDecodedWord)) {
                    countRelated++;
                }
            }
            for (int k = 0; k < grenzeNormal; k++) {
                if (kmp.KMPSearch(suchWort.substring(k),currentEncodedWord)) {
                    countMatches++;
                }
            }
            if (countMatches + countRelated == temporaryMaxMatches) {
                match.add(koellnerPhonetikList.get(i).getEncodedWord());
            }
            if (countMatches + countRelated > temporaryMaxMatches) {
                temporaryMaxMatches = countMatches + countRelated;
                temporaryMaxRelated = countRelated;
                match = new ArrayList<>();
                match.add(koellnerPhonetikList.get(i).getEncodedWord());
            }
        }
        if ((double) temporaryMaxRelated / suchWort.length() >= 0.4) {
        	Collections.sort(match);
            return match;
        }
        return new ArrayList<String>();
    }

    private String removeZero(String codeString) {
        char[] code = codeString.toCharArray();
        StringBuilder newCode = new StringBuilder();
        for (int i = 0; i < code.length; i++) {
            if (i == code.length - 1 && code[i] != '0') {
                newCode.append(code[i]);
                break;
            }
            if (code[i] != '0' || (code[i] == '0' && i == 0)) {
                newCode.append(code[i]);
            }
        }
        return newCode.toString();
    }

    private String getCodeWithoutDuplikates(String s) {
        char[] code = s.toCharArray();
        StringBuilder newCode = new StringBuilder();
        for (int i = 0; i < code.length; i++) {
            if (i == code.length - 1) {
                newCode.append(code[i]);
                break;
            }
            if (code[i] != code[i + 1]) {
                newCode.append(code[i]);
            }
        }
        return removeZero(newCode.toString());
    }

    private String decodeWord(String word) {
        StringBuilder code = new StringBuilder();
        String anlaut = word.substring(0, 1);
        for (int i = 0; i < word.length(); i++) {
            KoellnerPhonetikDecoder koellnerPhonetikDecoder = new KoellnerPhonetikDecoder(new KoellnerPhonetikCharacterHelper(word, i), anlaut);
            code.append(koellnerPhonetikDecoder.getDecodedPartString());
        }
        return getCodeWithoutDuplikates(code.toString());
    }
}
