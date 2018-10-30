package adapter.util;

public class Converter {

    public static String toStringfromIntArray(int [] array) {
        StringBuilder result = new StringBuilder();
        for (int el : array) {
            result.append(el);
        }
        return result.toString();
    }
}
