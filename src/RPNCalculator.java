
/**
 * RPNCalculator that calculate the big number,
 * operations including add(+), multiply(*), and power(^)
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.1.30
 *
 */
public class RPNCalculator {

    /**
     * Add two number in the calculator
     * 
     * @param num1
     *            The larger number(longer length)
     * @param num2
     *            The smaller number(shorter length)
     * @return The sum of num1 and num2
     */
    public String add(String num1, String num2) {
        int num1Len = num1.length();
        int num2Len = num2.length();

        // if num2 if longer, switch the input location
        if (num2Len > num1Len) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        LinkedList<Integer> number1 = toList(num1);
        LinkedList<Integer> number2 = toList(num2);
        number1.moveToStart();
        number1.insert(null); // create empty node as the start
        number1.moveToEnd();
        number2.moveToStart();
        number2.insert(null); // create empty node as the start
        number2.moveToEnd();
        LinkedList<Integer> result = new LinkedList<>();

        int carry = 0;
        int sum = 0;

        // after append the number to list, we are at the end of lists
        while (!number2.isAtStart()) {
            sum = number1.getValue() + number2.getValue() + carry;
            if (sum >= 10) {
                carry = 1;
                sum = sum - 10;
            }
            else {
                carry = 0;
            }
            result.append(sum);
            // move the pointer to previous digit
            number1.prev();
            number2.prev();
        }
        // add the rest digit of the longer number
        while (!number1.isAtStart()) {
            sum = number1.getValue() + carry;
            if (sum >= 10) {
                sum = sum - 10;
                carry = 1;
                result.append(sum);
            }
            else {
                carry = 0;
                result.append(sum);
            }
            number1.prev();
        }
        if (carry != 0) {
            result.append(carry);
        }
        String resultSum = listToString(result);
        return resultSum;
    }


    /**
     * Multiply two number, num1 is longer, num2 is shorter
     * 
     * @param num1
     *            The longer number
     * @param num2
     *            The shorter number
     * @return The multiplication of the two numbers
     */
    public String multiply(String num1, String num2) {
        int num1Len = num1.length();
        int num2Len = num2.length();

        // if num2 if longer, switch the input location
        if (num2Len > num1Len) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        LinkedList<Integer> number1 = toList(num1);
        LinkedList<Integer> number2 = toList(num2);
        // any number times 0 is 0
        if (number2.length() == 1 && number2.getValue() == 0) {
            return "0";
        }
        number1.moveToStart();
        number1.insert(null); // create empty node as the start
        number1.moveToEnd();
        number2.moveToStart();
        number2.insert(null); // create empty node as the start
        number2.moveToEnd();

        String result = "0";
        int carry = 0;
        int index = 0; // use index to record complement-used zeros at
                       // the end
        // number1 is longer, number2 is shorter
        while (!number2.isAtStart()) {
            String round = "";
            // each round is longer number * a digit of shorter number
            for (int i = 0; i < index; i++) {
                round = 0 + round;
            }
            number1.moveToEnd();
            while (!number1.isAtStart()) {
                // multiplication result of each round
                int multiRound = number1.getValue() * number2.getValue()
                    + carry;
                if (multiRound >= 10) {
                    carry = multiRound / 10;
                    round = multiRound % 10 + round;
                }
                else {
                    round = multiRound + round;
                    carry = 0;
                }
                number1.prev();
            }
            if (carry != 0) {
                round = carry + round;
                carry = 0;
            }
            result = add(result, round);
            number2.prev();
            index++;
        }
        return result;
    }


    /**
     * Calculate the power of the number
     * 
     * @param num1
     *            The base number
     * @param num2
     *            The exponential number, power
     * @return The result of the power
     */
    public String pow(String num1, String num2) {

        // the easiest case is power = 1, 0, return number itself
        if (num2.equals("1")) {
            return num1;
        }
        if (num2.equals("0")) {
            return "1";
        }

        // use recursion to solve the problem
        if (Integer.valueOf(num2) % 2 == 0) { // even
            return pow(multiply(num1, num1), Integer.valueOf(num2) / 2 + "");
        }
        else { // odd
            return multiply(num1, pow(multiply(num1, num1), ((Integer.valueOf(
                num2) - 1) / 2) + ""));
        }
    }


    /**
     * Transfer the input string number to a LinkedList
     * 
     * @param number
     *            the number in string format
     * @return a list store the digit of number
     */
    private LinkedList<Integer> toList(String number) {
        LinkedList<Integer> numList = new LinkedList<>();
        char[] numArry = number.toCharArray();
        for (char num : numArry) {
            numList.append(Integer.valueOf(String.valueOf(num)));
        }

        return numList;
    }


    /**
     * Reverse and transfer the result list to a string
     * 
     * @param list
     *            result list that contains the calculation result
     * @return a string of the calculation result
     */
    private String listToString(LinkedList<Integer> list) {
        // we record the string from end to start
        list.moveToEnd();
        String string = "";
        while (!list.isAtStart()) {
            string += list.getValue();
            list.prev();
        }
        // add the final digit
        string += list.getValue();
        return string;
    }

}
