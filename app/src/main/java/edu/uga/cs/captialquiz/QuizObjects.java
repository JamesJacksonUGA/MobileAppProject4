package edu.uga.cs.captialquiz;

public class QuizObjects {
    private int id;
    private  String stateName;
    private  String stateCapital;
    private  String secondLargeCity;
    private  String thirdLargeCity;

    /**
     * The default constructor of how a quiz question will look like
     */
    public QuizObjects() {
        this.id = -1;
        this.stateName = null;
        this.stateCapital = null;
        this.secondLargeCity = null;
        this.thirdLargeCity = null;
    }

    /**
     * User defined constructor that creates an object of quizes with their name, capital, second city, and third city
     * @param id id of state
     * @param stateName name of state
     * @param stateCapital state capital
     * @param secondLargeCity state second city
     * @param thirdLargeCity state third city
     */
    public QuizObjects(int id, String stateName, String stateCapital, String secondLargeCity, String thirdLargeCity) {
        //this.id = -1;  // the primary key id will be set by a setter method
        this.id = id;
        this.stateName = stateName;
        this.stateCapital = stateCapital;
        this.secondLargeCity = secondLargeCity;
        this.thirdLargeCity = thirdLargeCity;
    }

    /**
     * Get id of state
     * @return returns the id of the state
     */
    public int getId() {
        return id;
    }

    /**
     * Set id of state
     * @param id set id of state
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get state name
     * @return get state name
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Set state name
     * @param stateName set state name
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Get state capital
     * @return get state capital
     */
    public String getStateCapital() {
        return stateCapital;
    }

    /**
     * Set state capital
     * @param stateCapital set state capital
     */
    public void setStateCapital(String stateCapital) {
        this.stateCapital = stateCapital;
    }

    /**
     * Get second largest city
     * @return returns second largest city
     */
    public String getSecondLargeCity() {
        return secondLargeCity;
    }

    /**
     * Set second largest city
     * @param secondCity set second largest city
     */
    public void setSecondLargeCity(String secondCity) {
        this.secondLargeCity = secondCity;
    }

    /**
     * Get third largest city
     * @return returns third largest city
     */
    public String getThirdLargeCity() {
        return thirdLargeCity;
    }

    /**
     * Set third largest city
     * @param thirdCity set third largest city
     */
    public void setThirdLargeCity(String thirdCity) {
        this.thirdLargeCity = thirdCity;
    }

    /**
     * Display all information of quiz
     * @return returns string information of quizes
     */
    public String toString() {
        return id + ": " + stateName + " " + stateCapital + " " + secondLargeCity + " " + thirdLargeCity;
    }



}
