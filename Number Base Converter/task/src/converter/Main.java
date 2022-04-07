package converter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean isContinue = true;

        while (isContinue) {
            isContinue = run();
        }
    }

    static boolean run() {
        Scanner sc = new Scanner(System.in);
        String num;
        int[] baseAsInt;
        boolean isBack = false;
        Converter converter = new Converter(5);

        System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");

        String[] inpFormat = sc.nextLine().split(" ");
        if (!"/exit".equals(inpFormat[0])) {
            baseAsInt = getBase(inpFormat);

            while (!isBack) {
                System.out.println("Enter number in base " + baseAsInt[0] + " to convert to base " + baseAsInt[1] +
                        " (To go back type /back) ");
                num = sc.nextLine();
                isBack = "/back".equals(num);
                if (!isBack) {
                    System.out.println("Conversion result: " + converter.convert(num, baseAsInt[0], baseAsInt[1]));
                }

            }

        }

        return !"/exit".equals(inpFormat[0]);
    }

    /**
     * @param arrayInp index 0 is source base index 1 is target base
     * @return that same value if is int if not return -1
     */
    static int[] getBase(String[] arrayInp) {
        int[] intsArray = new int[2];

        try {
            for (int i = 0; i < 2; i++) {
                intsArray[i] = Integer.parseInt(arrayInp[i]);
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong base!");
            intsArray[0] = -1;
        }
        return intsArray;
    }
}
