package com.rulex.rulex.DTO;

import java.util.Objects;

public class DecisionClass {
    private String decision;
    private String Reason;
    private String matchedRules;

    public DecisionClass(String decision, String Reason, String matchedRules) {
        this.decision = decision;
        this.Reason = Reason;
        this.matchedRules = matchedRules;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(String matchedRules) {
        this.matchedRules = matchedRules;
    }

    @Override
    public String toString() {
        return "DecisionClass{" +
                "decision='" + decision + '\'' +
                ", Reason='" + Reason + '\'' +
                ", matchedRules='" + matchedRules + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecisionClass that = (DecisionClass) o;
        return Objects.equals(decision, that.decision) &&
                Objects.equals(Reason, that.Reason) &&
                Objects.equals(matchedRules, that.matchedRules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decision, Reason, matchedRules);
    }
}
