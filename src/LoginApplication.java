import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginApplication {
    // Declare user credentials and personal information
    String enteredUserName;
    String enteredPassword;
    String firstName;
    String surname;
    String password;
    String userName;

    // Method to check if the username is formatted correctly (contains an underscore and has a length of 5 or less)
    public boolean checkUsername() {
        boolean check = false;
        // Iterate through each character in the username
        for (int i = 0; i < userName.length(); i++) {
            if (userName.length() <= 5) { // Username should be 5 characters or less
                if (userName.charAt(i) == '_') { // Check if there's an underscore
                    check = true;
                }
            }
        }
        return check;
    }

    // Method to check if the password meets complexity requirements
    public boolean checkPasswordComplexity() {
        boolean capitalLetter = false;
        boolean number = false;
        boolean special = false;

        // Password should be at least 8 characters long
        if (password.length() >= 8) {
            // Iterate through each character in the password
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (Character.isUpperCase(ch)) {
                    capitalLetter = true; // Check for uppercase letter
                } else if (Character.isDigit(ch)) {
                    number = true; // Check for numeric character
                } else if (!Character.isLetterOrDigit(ch)) {
                    special = true; // Check for special character
                }
            }
        }
        // Return true if all conditions (capital letter, number, special character) are met
        return capitalLetter && number && special;
    }

    // Method to register the user by checking the username and password
    public String registerUser() {
        // Check username and password
        if (checkUsername()) {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your Username contains an underscore and is no more than 5 characters in length.");
        }

        // Check password complexity
        if (checkPasswordComplexity()) {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
        }

        // If both username and password are correctly formatted, show success message
        if (checkUsername() && checkPasswordComplexity()) {
            JOptionPane.showMessageDialog(null, "The two above conditions have been met and the user has been registered successfully.");
        } else {
            // If either one of the conditions fails, show specific error message
            if (!checkPasswordComplexity()) {
                JOptionPane.showMessageDialog(null, "The Password does not meet the complexity requirements.");
            }
            if (!checkUsername()) {
                JOptionPane.showMessageDialog(null, "The username is incorrectly formatted.");
            }
        }
        return "";
    }

    // Method to check if the entered username and password match the stored ones
    public boolean loginUser() {
        return userName.equals(enteredUserName) && password.equals(enteredPassword);
    }

    // Method to display login status and messages based on login success or failure
    public String returnLoginStatus() {
        if (loginUser()) {
            JOptionPane.showMessageDialog(null, "Successful login\nWelcome " + firstName + " " + surname + " it is great to see you again.");
        } else {
            JOptionPane.showMessageDialog(null, "A failed login\nUsername or Password incorrect please try again.");
        }
        return "";
    }

    public static void main(String[] args) {
        // Create LoginApplication and Task3 objects
        LoginApplication login = new LoginApplication();
        Task3 info = new Task3();

        // Register user
        JOptionPane.showMessageDialog(null, "Register..........");
        login.firstName = JOptionPane.showInputDialog("Enter FirstName:");
        login.surname = JOptionPane.showInputDialog("Enter LastName:");
        login.userName = JOptionPane.showInputDialog("Enter UserName:");
        login.password = JOptionPane.showInputDialog("Enter Password:");

        // Register the user and validate username and password
        login.registerUser();
        while (!login.checkUsername() || !login.checkPasswordComplexity()) {
            JOptionPane.showMessageDialog(null, "Try to register again!!!!!");
            login.userName = JOptionPane.showInputDialog("Enter UserName:");
            login.password = JOptionPane.showInputDialog("Enter Password:");
            login.registerUser();
        }

        // Login user
        JOptionPane.showMessageDialog(null, "Login..........");
        login.enteredUserName = JOptionPane.showInputDialog("Enter Username:");
        login.enteredPassword = JOptionPane.showInputDialog("Enter Password:");
        login.returnLoginStatus();

        // Retry login if failed
        while (!login.loginUser()) {
            JOptionPane.showMessageDialog(null, "Try to Login again ..........");
            login.enteredUserName = JOptionPane.showInputDialog("Enter Username:");
            login.enteredPassword = JOptionPane.showInputDialog("Enter Password:");
            login.returnLoginStatus();
        }

        // If login is successful, allow user to perform tasks
        if (login.loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome To EasyKanban");
            int choice;
            do {
                // Display main menu options
                info.input = JOptionPane.showInputDialog("Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");
                choice = Integer.parseInt(info.input);

                switch (choice) {
                    case 1:
                        // Add tasks option
                        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
                        int totalHours = 0;

                        for (int i = 0; i < numTasks; i++) {
                            // Input task details
                            String taskName = JOptionPane.showInputDialog("Enter task name:");
                            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
                            String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name:");
                            String developerLastName = JOptionPane.showInputDialog("Enter developer's last name:");
                            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration:"));
                            String taskID = info.createTaskID(taskName, i, developerLastName);
                            String taskStatus = "";

                            // Input task status
                            int option = Integer.parseInt(JOptionPane.showInputDialog("Please choose the Status of this task from the three options.\n1.To Do\n2.Doing\n3.Done"));
                            switch (option) {
                                case 1:
                                    taskStatus = "To Do";
                                    break;
                                case 2:
                                    taskStatus = "Doing";
                                    break;
                                case 3:
                                    taskStatus = "Done";
                                    break;
                            }

                            // Add task to the system
                            info.addTask(taskName, taskDescription, developerFirstName + " " + developerLastName, taskID, taskDuration, taskStatus);
                            String taskDetails = info.printTaskDetails(taskStatus, developerFirstName, developerLastName, i, taskName, taskDescription, taskID, taskDuration);
                            JOptionPane.showMessageDialog(null, taskDetails);
                            JOptionPane.showMessageDialog(null, "Task successfully captured.");
                            totalHours += taskDuration;
                        }

                        // Display total hours
                        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                        break;

                    case 2:
                        // Show report options
                        int reportChoice;
                        do {
                            reportChoice = Integer.parseInt(JOptionPane.showInputDialog("Show Report Options:\n1. Display all tasks\n2. Display done tasks\n3. Display longest task\n4. Search task by name\n5. Search tasks by developer\n6. Delete task\n7. Go back"));

                            switch (reportChoice) {
                                case 1:
                                    info.showTaskReport();
                                    break;
                                case 2:
                                    info.displayDoneTasks();
                                    break;
                                case 3:
                                    info.displayLongestTask();
                                    break;
                                case 4:
                                    String searchTaskName = JOptionPane.showInputDialog("Enter task name to search:");
                                    info.searchTaskByName(searchTaskName);
                                    break;
                                case 5:
                                    String searchDeveloper = JOptionPane.showInputDialog("Enter developer name to search tasks:");
                                    info.searchTasksByDeveloper(searchDeveloper);
                                    break;
                                case 6:
                                    String deleteTaskName = JOptionPane.showInputDialog("Enter task name to delete:");
                                    info.deleteTask(deleteTaskName);
                                    break;
                                case 7:
                                    JOptionPane.showMessageDialog(null, "Returning to main menu.");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                                    break;
                            }
                        } while (reportChoice != 7);
                        break;

                    case 3:
                        // Quit application
                        JOptionPane.showMessageDialog(null, "Exiting the application.");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 3);
        }
    }
}

// Task3 class that manages tasks and generates reports
class Task3 {
    // Variables for task information
    String input = "";
    List<String> developers = new ArrayList<>();
    List<String> tasks = new ArrayList<>();
    List<String> taskStatuses = new ArrayList<>();
    List<Integer> taskDurations = new ArrayList<>();
    List<String> taskIDs = new ArrayList<>();
    List<String> taskDescriptions = new ArrayList<>();
    int totalTasks = 0;

    // Method to create a task ID based on the task name, index, and developer's last name
    public String createTaskID(String taskName, int index, String developerLastName) {
        return taskName + "_" + (index + 1) + "_" + developerLastName;
    }

    // Method to add a task to the system
    public void addTask(String taskName, String taskDescription, String developer, String taskID, int taskDuration, String taskStatus) {
        tasks.add(taskName);
        taskDescriptions.add(taskDescription);
        developers.add(developer);
        taskStatuses.add(taskStatus);
        taskDurations.add(taskDuration);
        taskIDs.add(taskID);
        totalTasks++;
    }

    // Method to print task details
    public String printTaskDetails(String taskStatus, String developerFirstName, String developerLastName, int index, String taskName, String taskDescription, String taskID, int taskDuration) {
        return "Task Details:\n" +
                "Task ID: " + taskID + "\n" +
                "Task Name: " + taskName + "\n" +
                "Description: " + taskDescription + "\n" +
                "Developer: " + developerFirstName + " " + developerLastName + "\n" +
                "Duration: " + taskDuration + " hours\n" +
                "Status: " + taskStatus;
    }

    // Method to show the task report (all tasks)
    public void showTaskReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (int i = 0; i < totalTasks; i++) {
            report.append(printTaskDetails(taskStatuses.get(i), developers.get(i).split(" ")[0], developers.get(i).split(" ")[1], i, tasks.get(i), taskDescriptions.get(i), taskIDs.get(i), taskDurations.get(i)))
                    .append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    // Method to display all done tasks
    public void displayDoneTasks() {
        StringBuilder doneTasks = new StringBuilder("Done Tasks:\n");
        for (int i = 0; i < totalTasks; i++) {
            if (taskStatuses.get(i).equals("Done")) {
                doneTasks.append(printTaskDetails(taskStatuses.get(i), developers.get(i).split(" ")[0], developers.get(i).split(" ")[1], i, tasks.get(i), taskDescriptions.get(i), taskIDs.get(i), taskDurations.get(i)))
                        .append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString());
    }

    // Method to display the longest task
    public void displayLongestTask() {
        int longestDuration = 0;
        String longestTask = "";
        for (int i = 0; i < totalTasks; i++) {
            if (taskDurations.get(i) > longestDuration) {
                longestDuration = taskDurations.get(i);
                longestTask = tasks.get(i);
            }
        }
        JOptionPane.showMessageDialog(null, "Longest task: " + longestTask + " (" + longestDuration + " hours)");
    }

    // Method to search for a task by name
    public void searchTaskByName(String taskName) {
        for (int i = 0; i < totalTasks; i++) {
            if (tasks.get(i).equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, printTaskDetails(taskStatuses.get(i), developers.get(i).split(" ")[0], developers.get(i).split(" ")[1], i, tasks.get(i), taskDescriptions.get(i), taskIDs.get(i), taskDurations.get(i)));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Method to search for tasks by developer's name
    public void searchTasksByDeveloper(String developer) {
        StringBuilder developerTasks = new StringBuilder("Tasks by " + developer + ":\n");
        for (int i = 0; i < totalTasks; i++) {
            if (developers.get(i).equalsIgnoreCase(developer)) {
                developerTasks.append(printTaskDetails(taskStatuses.get(i), developers.get(i).split(" ")[0], developers.get(i).split(" ")[1], i, tasks.get(i), taskDescriptions.get(i), taskIDs.get(i), taskDurations.get(i)))
                        .append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, developerTasks.toString());
    }

    // Method to delete a task by name
    public void deleteTask(String taskName) {
        for (int i = 0; i < totalTasks; i++) {
            if (tasks.get(i).equalsIgnoreCase(taskName)) {
                tasks.remove(i);
                taskDescriptions.remove(i);
                developers.remove(i);
                taskStatuses.remove(i);
                taskDurations.remove(i);
                taskIDs.remove(i);
                totalTasks--;
                JOptionPane.showMessageDialog(null, "Task " + taskName + " deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }
}
