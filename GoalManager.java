
import java.util.ArrayList;
import java.util.List;

public class GoalManager {

    private List<Goal> goals = new ArrayList<>();

    // Add a new goal
    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    // Get all goals
    public List<Goal> getGoals() {
        return goals;
    }

    // Find goal using ID
    public Goal getGoalById(int id) {
        for (Goal goal : goals) {
            if (goal.getGoalId() == id) {
                return goal;
            }
        }
        return null;
    }

    // Display all goals
    public void displayAllGoals() {

        if (goals.isEmpty()) {
            System.out.println("No goals available.");
            return;
        }

        System.out.println("\n======= YOUR GOALS =======");

        for (Goal goal : goals) {
            System.out.println("ID: " + goal.getGoalId());
            System.out.println("Name: " + goal.getGoalName());
            System.out.println("Target: Rs. " + goal.getTargetAmount());
            System.out.println("Saved: Rs. " + goal.getTotalSaved());
            System.out.println("-------------------------");
        }
    }
}
