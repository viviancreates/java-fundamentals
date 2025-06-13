enum CoffeeSize {
    SMALL,
    MEDIUM,
    LARGE
}

enum SeatSection {
    GENERAL,
    PREMIUM,
    VIP
}

enum TrafficLight {
    RED,
    YELLOW,
    Green
}

enum Weekday {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

enum AlertLevel {
    LOW,
    MODERATE,
    HIGH,
    SEVERE
}

public class Main {
    public static void main(String[] args) {
        //part 1
        System.out.println("Selected coffee size: " + CoffeeSize.MEDIUM);

        //part2
        System.out.println("GENERAL is assigned value: " + SeatSection.GENERAL.ordinal());
        System.out.println("PREMIUM is assigned value: " + SeatSection.PREMIUM.ordinal());
        System.out.println("VIP is assigned value: " + SeatSection.VIP.ordinal());

        //part 3
        TrafficLight[] colors = TrafficLight.values();
        TrafficLight choice = colors[1];
        System.out.println("Traffic Light Signal: " + choice);

        //part 4
        //declare a predefined workday and print it
        //variable called workday -> can store one of the values from the Weekday enum
        Weekday workday = Weekday.WEDNESDAY;

        //check if the workday falls on the weekend
        boolean isWeekend = (workday == Weekday.SATURDAY || workday == Weekday.SUNDAY);
        System.out.println("Workday selected: " + workday);
        System.out.println("Is it the weekend? " + isWeekend);

        //part 5
        //declare alert level
        //variable currentAlert (from switch given) -> store one of the values from AlertLevel enum
        AlertLevel currentAlert = AlertLevel.HIGH;

        //print
        System.out.println("Current alert level: " + currentAlert);

        //use switch case to print message based on alert level
        switch (currentAlert) {
            case LOW:
                System.out.println("No immediate danger.");
                break;
            case MODERATE:
                System.out.println("Stay alert and aware.");
                break;
            case HIGH:
                System.out.println("Take precautions and stay informed.");
                break;
            case SEVERE:
                System.out.println("Immediate action required!");
                break;
        }
    }
}



