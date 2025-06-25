public class Main {
    public static void main(String[] args) {
        //part 1
        String firstName = "Harry";
        String lastName = "Potter";
        String fullName = firstName + " " + lastName;
        System.out.println("Full name: " + fullName);
        System.out.println("Length: " + fullName.length());
        System.out.println("First character: " + fullName.charAt(0));
        System.out.println("Index of 'r': " + fullName.indexOf('r'));
        System.out.println("-------");

        //part 2
        String sentence = "Learning Java is fun!";
        String word1 = sentence.substring(0, 8);
        String word2 = sentence.substring(9, 13);
        String word3 = sentence.substring(17);
        System.out.println("First word: " + word1);
        System.out.println("Second word: " + word2);
        System.out.println("Last word: " + word3);
        System.out.println("-------");

        // part 3
        String csvData = "apple,banana,grape,orange";
        String[] fruits = csvData.split(",");
        //for loop
        for (int i = 0; i < fruits.length; i++) {
            System.out.println("Fruit " + (i + 1) + ": " + fruits[i]);
        }
        System.out.println("-------");

        //part 4
        String sentence2 = "The quick brown fox.";
        String modified = sentence2.replace("quick", "slow").replace(' ', '_');
        System.out.println("Modified sentence: " + modified);
        System.out.println("-------");

        //part 5
        //empty string
        String nullable = null;
        if (nullable == null) {
            System.out.println("The string is null, cannot compute length.");
        } else {
            System.out.println("Length: " + nullable.length());
        }
        String empty = "";
        System.out.println("Empty string length: " + empty.length());
        System.out.println("-------");
    }
}