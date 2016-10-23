package in.jaaga.learning.bots.skillbot.problems;

import java.util.*;

import in.jaaga.learning.bots.skillbot.Problem;

public class Division extends SimpleProblem {
    int dividendMax, divisorMax;

    public Division(int dividendMax, int divisorMax) {
        this.dividendMax = dividendMax;
        this.divisorMax = divisorMax;
        int a, b;

        int quotientMax =  dividendMax / divisorMax;
        b = new Random().nextInt(divisorMax) + 1;
        a = b * new Random().nextInt(quotientMax);

        prompt = a + " ÷ " + b + " = ?";
        answer = Integer.toString(a / b);
    }

    public Problem next(){
        return  new Division(dividendMax, divisorMax);
    }

    public String getTitle() {
        return "Division with dividend to " + dividendMax +
                " and divisor to " + divisorMax;
    }
}
