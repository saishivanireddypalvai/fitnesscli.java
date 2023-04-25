import java.util.List;
import java.util.Scanner;

public class FitnessCLI 
{
    private static final int DISPLAY_TIMETABLE_BY_DAY = 1;
    private static final int DISPLAY_TIMETABLE_BY_TYPE = 2;
    private static final int BOOK_A_LESSON = 3;
    private static final int CANCEL_A_BOOKING = 4;
    private static final int ATTEND_A_LESSON_AND_PROVIDE_A_RATING = 5;
    private static final int GENERATE_DAILY_REPORT = 6;
    private static final int GENERATE_CHAMPION_FITNESS_TYPE_REPORT = 7;
    private static final int EXIT = 8;

    private Scanner input;
    private static Customer customer;
    private FitnessClub fitnessClub;

    public void FitnessClubCLI()
    {
        input = new Scanner(System.in);
        fitnessClub = new FitnessClub();
        customer = new Customer();
        addExampleLessons();
    }

    private void addExampleLessons() 
    {
        fitnessClub.addLesson("Monday", "Yoga", 10);
        fitnessClub.addLesson("Monday", "Pilates", 12);
        fitnessClub.addLesson("Tuesday", "Yoga", 10);
    }

    public void start() 
    {
        int choice;
        do 
        {
            displayMenu();
            choice = input.nextInt();
            input.nextLine();

            switch (choice) 
            {
                case DISPLAY_TIMETABLE_BY_DAY:
                    displayTimetableByDay();
                    break;
                case DISPLAY_TIMETABLE_BY_TYPE:
                    displayTimetableByType();
                    break;
                case BOOK_A_LESSON:
                    bookLesson();
                    break;
                case CANCEL_A_BOOKING:
                    cancelBooking();
                    break;
                case ATTEND_A_LESSON_AND_PROVIDE_A_RATING:
                    attendLessonAndProvideRating();
                    break;
                case GENERATE_DAILY_REPORT:
                    generateDailyReport();
                    break;
                case GENERATE_CHAMPION_FITNESS_TYPE_REPORT:
                    generateChampionFitnessTypeReport();
                    break;
                case EXIT:
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice!");
                    break;
            }
        } while (choice != EXIT);
    }

    private void generateChampionFitnessTypeReport() 
    {
    }

    private void generateDailyReport() 
    {
    }

    private void attendLessonAndProvideRating() 
    {
    }

    private void displayMenu() 
    {
        System.out.println("\n1. Display timetable by day");
        System.out.println("2. Display timetable by type");
        System.out.println("3. Book a lesson");
        System.out.println("4. Cancel a booking");
        System.out.println("5. Attend a lesson and provide a rating");
        System.out.println("6. Generate daily report");
        System.out.println("7. Generate champion fitness type report");
        System.out.println("8. Exit");
        System.out.print("\nEnter your choice (1-8): ");
    }

    private void displayTimetableByDay() 
    {
        System.out.print("\nEnter day (e.g. Monday): ");
        String day = input.nextLine();
        fitnessClub.displayTimetableByDay(day);
    }

    private void displayTimetableByType() 
    {
        System.out.print("\nEnter type (e.g. Yoga): ");
        String type = input.nextLine();
        fitnessClub.displayTimetableByDay(type);
    }

    private void bookLesson()
    {
        System.out.print("\nEnter day (e.g. Monday): ");
        String day = input.nextLine();
        System.out.print("\nEnter type (e.g. Yoga): ");
        String type = input.nextLine();
        fitnessClub.addLesson(customer, day, type);
    }
    private void cancelBooking() 
    {
        System.out.println("\nYour bookings:");
        String bookings = customer.getBookings();
        if (bookings.isEmpty()) 
        {
            System.out.println("You have no bookings.");
            return;
        }
        for (int i = 0; i < bookings.size(); i++) 
        {
            System.out.println((i+1) + ". " + bookings.get(i).getType() + " on " + bookings.get(i).getDay());
        }
        System.out.print("\nEnter the index of the booking to cancel: ");
        int bookingIndex = input.nextInt();
        input.nextLine(); // Consume the newline character
        if (bookingIndex < 1 || bookingIndex > bookings.size()) 
        {
            System.out.println("Invalid index.");
            return;
        }
        Lesson booking = bookings.get(bookingIndex - 1);
        fitnessClub.cancelBooking(customer, booking);
    }
    private void attendLesson() 
    {
        System.out.println("\nYour bookings:");
        String bookings = customer.getBookings();
        if (bookings.isEmpty()) 
        {
            System.out.println("You have no bookings.");
            return;
        }
        for (int i = 0; i < bookings.size(); i++) 
        {
            System.out.println((i+1) + ". " + bookings.get(i).getType() + " on " + bookings.get(i).getDay());
        }
        System.out.print("\nEnter the index of the lesson to attend: ");
        int lessonIndex = input.nextInt();
        input.nextLine(); // Consume the newline character
        if (lessonIndex < 1 || lessonIndex > bookings.size())
        {
            System.out.println("Invalid index.");
            return;
        }
        Lesson lesson = bookings.get(lessonIndex - 1);
        System.out.print("\nEnter rating (1-5): ");
        int rating = input.nextInt();
        input.nextLine(); // Consume the newline character
        fitnessClub.attendLesson(customer, lesson, rating);
    }
        public static void main(String[] args) 
        {
            try (Scanner input = new Scanner(System.in)) 
            {
                FitnessClubCLI cli = new FitnessClubCLI();
                cli.printMenu();
                while (true) 
                {
                    System.out.print("\nEnter your choice (1-8): ");
                    int choice = input.nextInt();
                    input.nextLine(); // Consume the newline character
                    switch (choice) 
                    {
                        case 1:
                            System.out.print("\nEnter day (e.g. Monday): ");
                            String day = input.nextLine();
                            cli.displayTimetableByDay(day);
                            break;
                        case 2:
                            System.out.print("\nEnter type (e.g. Yoga): ");
                            String type = input.nextLine();
                            cli.displayTimetableByDay(type);
                            break;
                        case 3:
                            cli.attendLesson();
                            break;
                        case 4:
                            cli.cancelBooking(customer, null);
                            break;
                        case 5:
                            cli.attendLesson();
                            break;
                        case 6:
                            cli.generateDailyReport();
                            break;
                        case 7:
                            cli.generateChampionFitnessTypeReport();
                            break;
                        case 8:
                            System.out.println("\nGoodbye!");
                            System.exit(0);
                        default:
                            System.out.println("\nInvalid choice!");
                    }
                    cli.printMenu();
                }
            }
        }
    }

    