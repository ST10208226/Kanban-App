import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Task3Test {

    private Task3 taskManager;

    // JUnit 4 uses @Before instead of @BeforeEach to set up the test environment
    @Before
    public void setUp() {
        taskManager = new Task3();
        taskManager.addTask("Task 1", "Description of Task 1", "John Doe", "TA:0:DOE", 5, "To Do");
        taskManager.addTask("Task 2", "Description of Task 2", "Jane Smith", "TA:1:SMI", 10, "Done");
    }

   

    // Testing createTaskID method
    @Test
    public void testCreateTaskID() {
        String taskName = "Task 1";
        int taskNumber = 1;
        String developerLastName = "Smith";
        String taskID = taskManager.createTaskID(taskName, taskNumber, developerLastName);
        assertEquals("Task ID should be generated correctly.", "TA:1:SMI", taskID);
    }

    // Testing printTaskDetails method
    @Test
    public void testPrintTaskDetails() {
        String taskDetails = taskManager.printTaskDetails("To Do", "John", "Doe", 1, "Task 1", "Description of Task 1", "TA:0:DOE", 5);
        assertNotNull("Task details should not be null.", taskDetails);
        assertTrue("Task details should contain task name.", taskDetails.contains("Task Name: Task 1"));
    }


    // Testing showTaskReport method
    @Test
    public void testShowTaskReport() {
        // For JUnit 4, we don't have assertDoesNotThrow, so we just ensure no exceptions are thrown
        try {
            taskManager.showTaskReport();
        } catch (Exception e) {
            fail("Task report should be displayed without exceptions.");
        }
    }

    // Testing displayDoneTasks method
    @Test
    public void testDisplayDoneTasks() {
        // Same as above, ensure no exception is thrown
        try {
            taskManager.displayDoneTasks();
        } catch (Exception e) {
            fail("Done tasks should be displayed without exceptions.");
        }
    }

    // Testing displayLongestTask method
    @Test
    public void testDisplayLongestTask() {
        // Same as above, ensure no exception is thrown
        try {
            taskManager.displayLongestTask();
        } catch (Exception e) {
            fail("Longest task should be displayed without exceptions.");
        }
    }

    // Testing searchTaskByName method
    @Test
    public void testSearchTaskByName() {
        try {
            taskManager.searchTaskByName("Task 1");
        } catch (Exception e) {
            fail("Task should be found by name.");
        }
    }

    // Testing searchTasksByDeveloper method
    @Test
    public void testSearchTasksByDeveloper() {
        try {
            taskManager.searchTasksByDeveloper("John Doe");
        } catch (Exception e) {
            fail("Tasks should be found by developer.");
        }
    }

}
