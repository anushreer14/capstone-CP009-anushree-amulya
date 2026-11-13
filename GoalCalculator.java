
public class GoalCalculator {

    // Calculate monthly savings
    public static double calculateMonthlySaving(
            double target, double saved, int months) {

        if (months <= 0) {
            return 0;
        }

        double remaining = Math.max(target - saved, 0);
        return remaining / months;
    }

    // Calculate monthly SIP
    public static double calculateSIP(
            double target, double saved,
            double annualRate, int months) {

        if (months <= 0) {
            return 0;
        }

        double remaining = target - saved;

        if (remaining <= 0) {
            return 0;
        }

        double monthlyRate = annualRate / 1200;

        if (monthlyRate == 0) {
            return remaining / months;
        }

        double growth = Math.pow(1 + monthlyRate, months);
        double futureSavings = saved * growth;
        double required = target - futureSavings;

        if (required <= 0) {
            return 0;
        }

        return required * monthlyRate / (growth - 1);
    }

    // Calculate progress percentage
    public static double calculateProgress(
            double target, double saved) {

        if (target <= 0) {
            return 0;
        }

        return Math.min((saved / target) * 100, 100);
    }

    // Calculate remaining amount
    public static double calculateRemaining(
            double target, double saved) {

        return Math.max(target - saved, 0);
    }
}
