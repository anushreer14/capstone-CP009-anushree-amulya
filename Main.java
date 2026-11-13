
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static GoalManager manager = new GoalManager();
    static int id = 1;

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== GOALVAULT =====");
            System.out.println("1. Create New Goal");
            System.out.println("2. Calculate Monthly Savings / SIP");
            System.out.println("3. Record Monthly Contribution");
            System.out.println("4. View Goal Progress");
            System.out.println("5. Check Goal Status");
            System.out.println("6. View All Goals");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    createGoal();
                    break;

                case 2:
                    calculatePlan();
                    break;

                case 3:
                    addContribution();
                    break;

                case 4:
                    viewProgress();
                    break;

                case 5:
                    checkStatus();
                    break;

                case 6:
                    manager.displayAllGoals();
                    break;

                case 7:
                    System.out.println("Thank you for using GoalVault!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Create a new goal
    static void createGoal() {

        System.out.print("Enter goal name: ");
        String name = sc.nextLine();

        System.out.print("Enter target amount: ");
        double target = sc.nextDouble();
        sc.nextLine();

        if (target <= 0) {
            System.out.println("Invalid target amount!");
            return;
        }

        System.out.print("Enter current savings: ");
        double saved = sc.nextDouble();
        sc.nextLine();

        if (saved < 0 || saved > target) {
            System.out.println("Invalid initial savings!");
            return;
        }

        System.out.print("Enter target date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        LocalDate targetDate;

        try {
            targetDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Use YYYY-MM-DD.");
            return;
        }

        if (!targetDate.isAfter(LocalDate.now())) {
            System.out.println("Target date must be in the future!");
            return;
        }

        Goal g = new Goal(id, name, target, saved, targetDate);

        manager.addGoal(g);

        System.out.println("Goal created successfully!");
        System.out.println("Goal ID: " + id);

        id++;
    }

    // SELECT GOAL - FIXED METHOD
    static Goal selectGoal() {

        manager.displayAllGoals();

        System.out.print("Enter Goal ID: ");
        int selectedId = sc.nextInt();
        sc.nextLine();

        Goal g = manager.getGoalById(selectedId);

        if (g == null) {
            System.out.println("Goal not found!");
            return null;
        }

        return g;
    }

    // Calculate monthly savings and SIP
    static void calculatePlan() {

        Goal g = selectGoal();

        if (g == null) return;

        int months = (int) ChronoUnit.MONTHS.between(
                LocalDate.now(), g.getTargetDate());

        if (months < 1) months = 1;

        double target = g.getTargetAmount();
        double saved = g.getTotalSaved();

        double monthly = GoalCalculator.calculateMonthlySaving(
                target, saved, months);

        System.out.println("Monthly Savings: Rs. " + monthly);

        System.out.print("Enter annual return rate (%): ");
        double rate = sc.nextDouble();
        sc.nextLine();

        if (rate < 0) {
            System.out.println("Invalid return rate!");
            return;
        }

        g.setExpectedAnnualReturn(rate);

        double sip = GoalCalculator.calculateSIP(
                target, saved, rate, months);

        System.out.println("Monthly SIP: Rs. " + sip);
    }

    // Record monthly contribution
    static void addContribution() {

        Goal g = selectGoal();

        if (g == null) return;

        System.out.print("Enter contribution amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid contribution amount!");
            return;
        }

        if (g.getTotalSaved() + amount > g.getTargetAmount()) {
            System.out.println("Contribution exceeds remaining goal amount!");
            return;
        }

        Contribution c = new Contribution(amount);

        g.addContribution(c);

        System.out.println("Contribution added successfully!");
        System.out.println("Total Savings: Rs. " + g.getTotalSaved());
    }

    // View goal progress
    static void viewProgress() {

        Goal g = selectGoal();

        if (g == null) return;

        double progress = GoalCalculator.calculateProgress(
                g.getTargetAmount(), g.getTotalSaved());

        System.out.println("\n===== GOAL PROGRESS =====");

        System.out.println("Goal Name: " + g.getGoalName());
        System.out.println("Target Amount: Rs. " + g.getTargetAmount());
        System.out.println("Total Savings: Rs. " + g.getTotalSaved());

        System.out.println("Progress: " + progress + "%");

        System.out.println("Remaining Amount: Rs. " +
                GoalCalculator.calculateRemaining(
                        g.getTargetAmount(), g.getTotalSaved()));

        if (g.getTotalSaved() >= g.getTargetAmount()) {
            System.out.println("Goal Completed!");
        } else {
            System.out.println("Goal In Progress!");
        }
    }

    // Check goal status
    static void checkStatus() {

        Goal g = selectGoal();

        if (g == null) return;

        double saved = g.getTotalSaved();
        double target = g.getTargetAmount();

        if (saved >= target) {
            System.out.println("Congratulations! Goal Completed!");
            return;
        }

        LocalDate today = LocalDate.now();

        if (g.getTargetDate().isBefore(today)) {
            System.out.println("Warning! Deadline has passed.");
            return;
        }

        long totalMonths = ChronoUnit.MONTHS.between(
                g.getCreatedDate(), g.getTargetDate());

        long passedMonths = ChronoUnit.MONTHS.between(
                g.getCreatedDate(), today);

        long remainingMonths = ChronoUnit.MONTHS.between(
                today, g.getTargetDate());

        if (totalMonths < 1) totalMonths = 1;
        if (remainingMonths < 1) remainingMonths = 1;

        double plannedSavings = g.getInitialSavings()
                + (target - g.getInitialSavings())
                * passedMonths / (double) totalMonths;

        System.out.println("\n===== GOAL STATUS =====");

        System.out.println("Current Savings: Rs. " + saved);
        System.out.println("Planned Savings: Rs. " + plannedSavings);

        if (saved < plannedSavings) {

            System.out.println("Warning! You are behind schedule.");

            double newMonthly = (target - saved) / remainingMonths;

            System.out.println(
                    "New Monthly Savings Needed: Rs. " + newMonthly);

        } else {

            System.out.println("Your goal is on schedule!");
        }
    }
}
