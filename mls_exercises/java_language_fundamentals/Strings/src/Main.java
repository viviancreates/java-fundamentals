//Code along notes
public class Main {
    public static void main(String[] args) {
        //Declare and assign a string variable
        String example = "This is a String literal being assigned to a variable.";
        //String tryACharLiteral = 'this will not compile because it is single quoted';
        //char notAString = "a"; //Also will not compile because a String literal isnt only a char
        System.out.println("----------");

        //Concatenate string variables - join strings together
        String firstHalf = "Jack and Jill ";
        String secondHalf = "went up the hill";
        String fullSentence = firstHalf + secondHalf;
        System.out.println(fullSentence);
        //... works with string literals too
        //String fullSentence = "Jack and Jill" + "went up the hill";
        //System.out.println(fullSentence);
        System.out.println("----------");

        //length() method - returns how many characters are in the String
        //string literal
        System.out.println("Count the characters".length());
        //vs string variable
        String countThese = "Count the characters";
        System.out.println(countThese.length());
        System.out.println("----------");

        //indexOf() method - finds teh first location of a given character or String inside of a String
        String searchThis = "The quick brown fox jumped over the lazy dog";
        System.out.println(searchThis);
        System.out.println("Looking for the letter 'e'...");
        System.out.println(searchThis.indexOf('e'));
        //searching for a string
        System.out.println("Looking for: quick");
        System.out.println(searchThis.indexOf("quick"));
        //searching for something not there -> returns a -1
        System.out.println("I won't find the word: foobar");
        System.out.println(searchThis.indexOf("foobar"));
        System.out.println("----------");

        //substring() method - good to use if you need to pull a string apart to get at its components
        String dateTime = "20220103 05:10:32";
        //giving it start and end positions
        String year = dateTime.substring (0,4);
        String month = dateTime.substring(4, 6);
        String day = dateTime.substring(6, 8);
        String hours = dateTime.substring(9, 11);
        String minutes = dateTime.substring(12, 14);
        String seconds = dateTime.substring(15, 17);
        //print
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hours: " + hours);
        System.out.println("Minutes: " + minutes);
        System.out.println("Seconds: " + seconds);
        System.out.println("----------");

        //split() method
        // Split once on the space between date and time
        String[] dateAndTime = dateTime.split(" ");
        // Split the time part on ":" to get hours, minutes, seconds
        String[] timeFields = dateAndTime[1].split(":");
        //print each value
        System.out.println("Hours: " + timeFields[0]);
        System.out.println("Minutes: " + timeFields[1]);
        System.out.println("Seconds: " + timeFields[2]);
        //give constants to make code readable
        final int DATE_FIELD = 0;
        final int TIME_FIELD = 1;
        final int HOURS = 0;
        final int MINUTES = 1;
        final int SECONDS = 2;
        String[] dateAndTime2 = dateTime.split(" ");
        String[] timeFields2 = dateAndTime2[TIME_FIELD].split(":");
        System.out.println("Hours: " + timeFields2[HOURS]);
        System.out.println("Minutes: " + timeFields2[MINUTES]);
        System.out.println("Seconds: " + timeFields2[SECONDS]);
        System.out.println("----------");

        //charAt() method - examines a String to find what character is at a given index
        //uses same value of index of would have returned if searched for and found that character
        String data = "001SOMETHINGY123345";
        System.out.println("Do I process this line? " +
                data.charAt(12));
        System.out.println("----------");

        //replace()
        String underscoredVersion = "This_text_needs_spaces_in_it";
        System.out.println(underscoredVersion);
        System.out.println(underscoredVersion.replace('_', ' ').replace("needs", "has"));
    }
}