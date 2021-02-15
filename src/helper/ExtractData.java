package helper;

/**
 *
 * @author javier
 */
public class ExtractData {
    public static String extractValue(String str, String sep) {
        String[] extractValue = str.split(sep);
        extractValue = extractValue[1].split(":");
        return extractValue[1];
    }
}
