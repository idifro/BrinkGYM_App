package io.brink.brinkgym.Model;

public class Team {
    private String Teamname, Participant1, Participant2, Rollno1, Rollno2;
    private int score;

    public String getTeamname() {
        return Teamname;
    }

    public void setTeamname(String teamname) {
        Teamname = teamname;
    }

    public String getParticipant1() {
        return Participant1;
    }

    public void setParticipant1(String participant1) {
        Participant1 = participant1;
    }

    public String getParticipant2() {
        return Participant2;
    }

    public void setParticipant2(String participant2) {
        Participant2 = participant2;
    }

    public String getRollno1() {
        return Rollno1;
    }

    public void setRollno1(String rollno1) {
        Rollno1 = rollno1;
    }

    public String getRollno2() {
        return Rollno2;
    }

    public void setRollno2(String rollno2) {
        Rollno2 = rollno2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team(String teamname, String participant1, String participant2, String rollno1, String rollno2, int score) {
        Teamname = teamname;
        Participant1 = participant1;
        Participant2 = participant2;
        Rollno1 = rollno1;

        Rollno2 = rollno2;
        this.score = score;
    }

    public Team() {

    }
}


