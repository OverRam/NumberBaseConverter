package converter;

import java.math.BigDecimal;

import static converter.IntegerSystem.fromDec;
import static converter.IntegerSystem.toDec;

public class FractionalSystem {

    static String fromDecFractional(String num, int fractionalTarget) {
        StringBuilder sb = new StringBuilder();

        BigDecimal numToConvert;
        if (num.contains(".")) {
            numToConvert = new BigDecimal(num);
        } else {
            numToConvert = new BigDecimal("0." + num);
        }

        int repeat = Math.max(num.length(), 5);

        for (int i = 0; i < repeat; i++) {
            numToConvert = numToConvert.multiply(BigDecimal.valueOf(fractionalTarget));

            sb.append(fromDec(numToConvert.toBigInteger().toString(), fractionalTarget));

            numToConvert = numToConvert.subtract(new BigDecimal(numToConvert.toBigInteger()));

        }
        return sb.toString();
    }

    static BigDecimal toDecFractional(String num, int base) {
        num = num.toUpperCase();

        BigDecimal big = BigDecimal.ZERO;

        double powRes;

        for (int i = 0, pow = 1; i < num.length(); i++, pow++) {
            powRes = 1 / Math.pow(base, pow);
            big = big.add(BigDecimal.valueOf((Character.getNumericValue(num.charAt(i)) * powRes)));
        }
        return big;
    }

    static String calculate(String numberToConvert, int sourceBase, int targetBase) {

        String[] splitNum = numberToConvert.split("\\.");

        String intValue = splitNum[0];
        String fractionalValue = splitNum[1];

        if (sourceBase == 10) {
            return toDec(intValue, sourceBase)
                    .concat(".")
                    .concat(fromDecFractional(fractionalValue, targetBase));
        } else {
            String integerValue = fromDec(toDec(intValue, sourceBase), targetBase);
            String fractionValue = toDecFractional(fractionalValue, sourceBase).toString();

            if (targetBase != 10) {
                fractionValue = fromDecFractional(fractionValue, targetBase);
            }

            return integerValue.concat(".").concat(fractionValue);
        }
    }
}
