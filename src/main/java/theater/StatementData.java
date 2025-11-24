package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data transfer object for preparing invoice statements.
 */
public class StatementData {
    private final Invoice invoice;
    private final Map<String, Play> plays;
    private final List<PerformanceData> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
        this.performances = new ArrayList<>();
        for (Performance performance : invoice.getPerformances()) {
            performances.add(createPerformanceData(performance));
        }
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Gets the play associated with a performance.
     * @param performance the performance
     * @return the play
     */
    public Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    /**
     * Calculates the total amount for all performances.
     * @return the total amount
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getAmount();
        }
        return result;
    }

    /**
     * Calculates the total volume credits for all performances.
     * @return the total volume credits
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getVolumeCredits();
        }
        return result;
    }

    private PerformanceData createPerformanceData(Performance performance) {
        final AbstractPerformanceCalculator performanceCalculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(performance, getPlay(performance));
        final int amount = performanceCalculator.amountFor();
        final int volumeCredits = performanceCalculator.volumeCredits();
        return new PerformanceData(performanceCalculator, amount, volumeCredits);
    }
}
