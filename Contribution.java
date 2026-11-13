
import java.time.LocalDate;

public class Contribution {

    double amount;
    LocalDate contributionDate;

    public Contribution(double amount) {
        this.amount = amount;
        contributionDate = LocalDate.now();
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getContributionDate() {
        return contributionDate;
    }
}
