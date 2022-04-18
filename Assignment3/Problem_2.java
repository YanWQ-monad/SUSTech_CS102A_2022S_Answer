import java.util.*;

class Core {
    HashMap<String, Integer> statementCounter = new HashMap<>();
    MedianMaintainer medianMaintainer = new MedianMaintainer();
    int influenceGauge;
    int statementsCount = 0;
    int matchedPairs = 0;

    void workAddStatement(String statement) {
        statementsCount++;
        medianMaintainer.add(statement.length());
        statementCounter.merge(statement, 1, Integer::sum);
    }

    void workBlastBullet(String bullet) {
        influenceGauge++;
        matchedPairs += statementCounter.getOrDefault(bullet, 0);

        int median = medianMaintainer.getMedian();
        System.out.println(median);

        if (influenceGauge < median)
            influenceGauge -= statementsCount;
    }

    public void work() {
        Scanner scanner = new Scanner(System.in);
        long sumOfA = 0, sumOfB = 0, sumOfM = 0;

        int n = scanner.nextInt();
        influenceGauge = scanner.nextInt();

        for (int i = 0; i < n; i++)
            sumOfM += scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int type = scanner.nextInt();

            if (type == 1)
                workAddStatement(scanner.next());
            else if (type == 2)
                workBlastBullet(scanner.next());
            else
                System.out.println(matchedPairs);

            sumOfA += medianMaintainer.getMedian();
            sumOfB += matchedPairs;
        }

        int score = Long.signum(sumOfA) * Long.signum(sumOfB) * Long.signum(sumOfM);
        System.out.println(score >= 0 && influenceGauge >= 0 ? "Qi Fei" : "Fail");
    }
}

class MedianMaintainer {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    void add(int value) {
        if (maxHeap.isEmpty() || value < maxHeap.element()) {
            maxHeap.add(value);
            if (maxHeap.size() - minHeap.size() >= 2)
                minHeap.add(maxHeap.remove());
        }
        else {
            minHeap.add(value);
            if (minHeap.size() > maxHeap.size())
                maxHeap.add(minHeap.remove());
        }
    }

    int getMedian() {
        return maxHeap.size() == 0 ? 0 : maxHeap.element();
    }
}

public class Problem_2 {
    public static void main(String[] args) {
        new Core().work();
    }
}
