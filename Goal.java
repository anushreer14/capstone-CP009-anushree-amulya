
import java.time.LocalDate;
import java.util.ArrayList;

public class Goal {

    int goalId;
    String goalName;
    double targetAmount, initialSavings, expectedAnnualReturn;
    LocalDate createdDate, targetDate;

    ArrayList<Contribution> contributions = new ArrayList<>();

    public Goal(int id, String name, double target, double saved, LocalDate date) {
        goalId = id;
        goalName = name;
        targetAmount = target;
        initialSavings = saved;
        targetDate = date;
        createdDate = LocalDate.now();
    }

    public int getGoalId() {
        return goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public double getInitialSavings() {
        return initialSavings;
    }

    public double getExpectedAnnualReturn() {
        return expectedAnnualReturn;
    }

    public void setExpectedAnnualReturn(double rate) {
        expectedAnnualReturn = rate;
    }

    public void addContribution(Contribution c) {
        contributions.add(c);
    }

    public double getTotalSaved() {
        double total = initialSavings;

        for (Contribution c : contributions) {
            total += c.getAmount();
        }

        return total;
    }
}
