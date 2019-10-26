package edu.uga.cs.captialquiz;

public class QuizObjects {
    private int id;
    private String stateName;
    private String stateCapital;
    private String secondLargeCity;
    private String thirdLargeCity;

    public QuizObjects() {
        this.id = -1;
        this.stateName = null;
        this.stateCapital = null;
        this.secondLargeCity = null;
        this.thirdLargeCity = null;
    }

    public QuizObjects(long id, String stateName, String stateCapital, String secondLargeCity, String thirdLargeCity) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.stateName = stateName;
        this.stateCapital = stateCapital;
        this.secondLargeCity = secondLargeCity;
        this.thirdLargeCity = thirdLargeCity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCapital() {
        return stateCapital;
    }

    public void setStateCapital(String stateCapital) {
        this.stateCapital = stateCapital;
    }

    public String getSecondLargeCity() {
        return secondLargeCity;
    }

    public void setSecondLargeCity(String secondCity) {
        this.secondLargeCity = secondCity;
    }

    public String getThirdLargeCity() {
        return thirdLargeCity;
    }

    public void setThirdLargeCity(String thirdCity) {
        this.thirdLargeCity = thirdCity;
    }

    public String toString() {
        return id + ": " + stateName + " " + stateCapital + " " + secondLargeCity + " " + thirdLargeCity;
    }


}
