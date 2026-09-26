import java.util.Scanner;

public class ProjectEliza {

    public static String getFirstWord(String sentence) {
        int spaceIndex = sentence.indexOf(" ");

        if (spaceIndex == -1) {
            return cleanWord(sentence);
        }

        String firstWord = sentence.substring(0, spaceIndex);
        return cleanWord(firstWord);
    }

    public static String getLastWord(String sentence) {
        int spaceIndex = sentence.lastIndexOf(" ");

        if (spaceIndex == -1) {
            return cleanWord(sentence);
        }

        String lastWord = sentence.substring(spaceIndex + 1);
        return cleanWord(lastWord);
    }

    public static String cleanWord(String word) {
        word = word.replace(".", "");
        word = word.replace("?", "");
        word = word.replace("!", "");
        word = word.replace(",", "");
        return word;
    }

    public static String replaceBlanks(String sentence, String word1, String word2) {
        sentence = sentence.replace("BLANK1", word1);
        sentence = sentence.replace("BLANK2", word2);
        return sentence;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean runAgain = true;

        while (runAgain) {
            PromptBank promptBank = new PromptBank();

            System.out.println("Hello, my name is Eliza. What is your name?");
            String userName = scnr.nextLine();

            System.out.println("Hello, " + userName + " Tell me what is on your mind today in 1 sentence.");

            boolean keepTalking = true;

            while (keepTalking) {
                String userInput = scnr.nextLine();

                if (userInput.equalsIgnoreCase("EXIT")) {
                    keepTalking = false;
                } else {
                    String firstWord = getFirstWord(userInput);
                    String lastWord = getLastWord(userInput);

                    char lastChar = userInput.charAt(userInput.length() - 1);
                    String response;

                    if (lastChar == '?') {
                        response = promptBank.getRandomQuestionTrunk();
                        response = replaceBlanks(response, firstWord, lastWord);
                        System.out.println(response);
                    } else if (lastChar == '!') {
                        response = promptBank.getRandomStatementTrunk();
                        response = replaceBlanks(response, firstWord, lastWord);
                        System.out.println("WOW! Dramatic! " + response);
                    } else {
                        response = promptBank.getRandomStatementTrunk();
                        response = replaceBlanks(response, firstWord, lastWord);
                        System.out.println(response);
                    }
                }
            }

            System.out.println("Do you want to run the session again?");
            String answer = scnr.nextLine();

            if (answer.equalsIgnoreCase("YES")) {
                runAgain = true;
            } else if (answer.equalsIgnoreCase("NO")) {
                runAgain = false;
                System.out.println("Goodbye, until next time");
            }
        }

        scnr.close();
    }
}
