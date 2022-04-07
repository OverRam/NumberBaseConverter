package converter;

import java.math.BigInteger;

public class IntegerSystem {

    static String toDec(String numNotDecimalFormat, int base) {

        numNotDecimalFormat = numNotDecimalFormat.toUpperCase();

        BigInteger big = BigInteger.ZERO;
        BigInteger range = BigInteger.valueOf(base);
        for (int i = 0, j = numNotDecimalFormat.length() - 1; i < numNotDecimalFormat.length(); i++, j--) {
            big = big.add(BigInteger.valueOf(numNotDecimalFormat.charAt(j) >= 65
                            ? numNotDecimalFormat.charAt(j) - 55 : numNotDecimalFormat.charAt(j) - 48)
                    .multiply(range.pow(i)));
        }
        return big.toString();
    }

    static String fromDec(String numInDecimalFormat, int targetBase) {

        BigInteger numberToConvert = new BigInteger(numInDecimalFormat);
        StringBuilder sb = new StringBuilder();
        BigInteger targetBaseFormat = BigInteger.valueOf(targetBase);
        BigInteger rem;

        do {
            rem = numberToConvert.remainder(targetBaseFormat);
            numberToConvert = (numberToConvert.subtract(rem)).divide(targetBaseFormat);
            if (rem.compareTo(BigInteger.valueOf(9)) > 0) {
                sb.append((char) ('A' + rem.intValue() - 10));
            } else {
                sb.append(rem);
            }

            if (numberToConvert.compareTo(targetBaseFormat) < 0 && numberToConvert.compareTo(BigInteger.ZERO) > 0) {
                if (numberToConvert.compareTo(BigInteger.valueOf(9)) > 0) {
                    sb.append((char) ('A' + numberToConvert.intValue() - 10));
                } else {
                    sb.append(numberToConvert);
                }
            }
        } while (numberToConvert.compareTo(targetBaseFormat) > -1);

        return sb.reverse().toString();

    }

    static String calculate(String numberToConvert, int sourceBase, int targetBase) {
        String decNumFormat = IntegerSystem.toDec(numberToConvert, sourceBase);
        if (targetBase == 10) {
            return decNumFormat;
        } else {
            return IntegerSystem.fromDec(decNumFormat, targetBase);
        }
    }
}
