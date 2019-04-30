package io.brink.brinkgym.Model;

public class TeamScore {
    private String Teamname,Gamename;
    private String Score;

    public TeamScore() {
    }

    public TeamScore(String teamname, String gamename, String score) {
        Teamname = teamname;
        Gamename = gamename;
        Score = score;
    }

    public String getTeamname() {
        return Teamname;
    }

    public void setTeamname(String teamname) {
        Teamname = teamname;
    }

    public String getGamename() {
        return Gamename;
    }

    public void setGamename(String gamename) {
        Gamename = gamename;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}

