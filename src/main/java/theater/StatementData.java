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

    public Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getAmount();
        }
        return result;
    }

    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getVolumeCredits();
        }
        return result;
    }

    private PerformanceData createPerformanceData(Performance performance) {
        AbstractPerformanceCalculator performanceCalculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(performance, getPlay(performance));
        int amount = performanceCalculator.amountFor();
        int volumeCredits = performanceCalculator.volumeCredits();
        return new PerformanceData(performanceCalculator, amount, volumeCredits);
    }
}
