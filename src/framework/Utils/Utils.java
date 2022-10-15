package framework.Utils;

import java.text.DecimalFormat;

public class Utils {
    public static String formatNumber(long num) {
        String[] suffix = new String[] { "K", "M", "B", "T" };
        int size = (num != 0) ? (int) Math.log10(num) : 0;
        if (size >= 3) {
            while (size % 3 != 0) {
                size = size - 1;
            }
        }

        return (size >= 3) ? (+(Math.round((num / Math.pow(10, size)) * 10) / 10d) + suffix[(size / 3) - 1]) : +num + "";

    }
}
