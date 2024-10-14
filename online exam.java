
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String fullName;

    // Constructor to initialize a user
    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    // User login method
    public boolean login(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    // Method to update user's profile (full name)
    public void updateProfile(String newFullName) {
        this.fullName = newFullName;
        System.out.println("Profile updated successfully.");
    }

    // Method to update user's password
    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully.");
    }

    // Display user's profile information
    public void displayProfile() {
        System.out.println("Full Name: " + fullName);
        System.out.println("Username: " + username);
    }
}

class Exam {
    private String[] questions = {
        "1. Is Java Programming an object-oriented programming language? \nA) No\nB) Yes",
        "2. Which of the following are the rules of Java Programming?\nA) Encapsulation\nB) Println\nC) System.out\nD) Console"
    };
    private String[] correctAnswers = {"B", "C"};  // Correct answers for the MCQs
    private String[] userAnswers = new String[questions.length];
    private boolean examSubmitted = false;

    // Start the exam and allow the user to answer questions
    public void startExam() {
        Scanner scanner = new Scanner(System.in);

        // Loop through each question and record the user's answers
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer (A, B, C, D): ");
            userAnswers[i] = scanner.next().toUpperCase();
        }

        // After the exam is completed, calculate and show the results
        submitExam();
    }

    // Manually submit the exam and calculate the results
    private void submitExam() {
        examSubmitted = true;
        System.out.println("\nExam submitted! Calculating results...");
        calculateResults();
    }

    // Calculate the user's score and display it
    private void calculateResults() {
        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i].equals(correctAnswers[i])) {
                score++;
            }
        }
        System.out.println("Your score: " + score + "/" + questions.length);
    }
}

public class OnlineExaminationSystem {
    private static User user;
    private static Exam exam;
    private static boolean sessionActive = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a user with default login credentials
        user = new User("student1", "password123", "John Doe");

        // Perform user login
        System.out.println("Welcome to the Online Examination System!");
        System.out.print("Enter username: ");
        String enteredUsername = scanner.next();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.next();

        if (user.login(enteredUsername, enteredPassword)) {
            System.out.println("Login successful!");
            sessionActive = true;
            mainMenu(scanner);
        } else {
            System.out.println("Invalid login credentials.");
        }
    }

    // Display the main menu and handle user input
    public static void mainMenu(Scanner scanner) {
        while (sessionActive) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Start MCQ Exam");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your new full name: ");
                    String newFullName = scanner.next();
                    user.updateProfile(newFullName);
                    break;
                case 2:
                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.next();
                    user.updatePassword(newPassword);
                    break;
                case 3:
                    exam = new Exam();  // Start a new exam
                    exam.startExam();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Logout method to end the session
    public static void logout() {
        System.out.println("Logging out...");
        sessionActive = false;
    }
}

 
