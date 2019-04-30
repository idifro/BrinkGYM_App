package io.brink.brinkgym.Model;

public class ScoreBoard {
    private String TeamName;
    private long score;

    public ScoreBoard() {
    }

    public ScoreBoard(String teamName, long score) {
        TeamName = teamName;
        this.score = score;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}

