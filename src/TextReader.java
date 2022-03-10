import java.io.File;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Read file and provide answer as the big number calculator
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.2.1
 *
 */

public class TextReader {

    /**
     * Read the file and output the answer
     * 
     * @param fileName
     *            The name of text file
     * @throws FileNotFoundException
     *             Throw when the file is not found
     */
    public void readFile(String fileName) throws FileNotFoundException {
        File newFile = new File(fileName);
        Scanner file = new Scanner(newFile);

        while (file.hasNextLine()) {
            String line = file.nextLine();
            line = line.trim();
            if (!line.equals("")) {
                String cleanString = formatter(line);
                printAnswer(cleanString);

            }
        }
        file.close();
    }


    /**
     * Method to print the result to standard output
     * 
     * @param expression
     *            The input clean equation
     * @return The answer string
     */
    public String printAnswer(String expression) {
        String[] express = expression.split(" ");
        String result = arithmetic(express);
        String answer = expression;
        answer += " ";
        answer += "=";
        if (!result.equals("")) {
            answer += " ";
            answer += result;
        }
        System.out.println(answer);
        return answer;
    }


    /**
     * Use RPNCalculator to do the arithmetic calculation
     * 
     * @param inputs
     *            The input string array that is cleaned by formatter
     * @return result The result of the expression in string
     */
    public String arithmetic(String[] inputs) {
        RPNCalculator calculator = new RPNCalculator();
        Stack<String> numStack = new Stack<>();
        String result = "";
        for (String input : inputs) {
            numStack.push(input);
            try {
                if (numStack.peek().equals("+")) {
                    numStack.pop(); // pop out the operator
                    String number1 = numStack.pop();
                    String number2 = numStack.pop();
                    result = calculator.add(number1, number2);
                    numStack.push(result);
                }
                else if (numStack.peek().equals("*")) {
                    numStack.pop();
                    String number1 = numStack.pop();
                    String number2 = numStack.pop();
                    result = calculator.multiply(number1, number2);
                    numStack.push(result);
                }
                // seems like canvas example use another ^, just in case
                else if (numStack.peek().equals("^") || numStack.peek().equals(
                    "ˆ")) {
                    numStack.pop();
                    String number2 = numStack.pop(); // first pop out is power
                    String number1 = numStack.pop();
                    result = calculator.pow(number1, number2);
                    numStack.push(result);
                }
            }
            catch (EmptyStackException e) {
                return "";
            }
        }
        result = numStack.pop();
        // if we still have number in stack, fail to calculate
        // reader should move to the next line
        if (!numStack.isEmpty()) {
            result = "";
        }
        return result;
    }


    /**
     * Clean the data string and call cleanString method to clean 0's
     * 
     * @param line
     *            The raw line data
     * @return The cleaned string array data
     */
    public String formatter(String line) {
        line = line.trim();
        String[] rawData = line.split(" ");
        String strings = "";
        for (String string : rawData) {
            string = string.trim();
            if (!string.equals("")) {
                string = cleanString(string);
                strings += string;
                strings += " ";
            }
        }
        strings = strings.trim();
        return strings;
    }


    /**
     * A helper method to clean 0's in string arrays for formatter
     * 
     * @param rawString
     *            The raw string that is unclean
     * @return A clean string
     */
    private String cleanString(String rawString) {
        char[] rawArry = rawString.toCharArray();
        char target = '0';
        int zeros = 0;
        while (zeros < rawString.length() && rawArry[zeros] == target) {
            zeros++;
        }
        // if the messy string is only mean 0, we remove a string 0
        if (rawString.substring(zeros).equals("")) {
            return "0";
        }
        return rawString.substring(zeros);
    }

}
