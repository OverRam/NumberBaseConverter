package converter;


public class Converter {
    private int scale;

    public Converter(int scale) {
        this.scale = scale;
    }

    String convert(String numberToConvert, int sourceBase, int targetBase) {

        //fraction system
        if (numberToConvert.contains(".")) {
            String convertNumber = FractionalSystem.calculate(numberToConvert, sourceBase, targetBase);
            int precision = Math.min(convertNumber.indexOf(".") + 1 + scale, convertNumber.length());

            return convertNumber.substring(0, precision);
            //integer system
        } else {
            return IntegerSystem.calculate(numberToConvert, sourceBase, targetBase);
        }
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
