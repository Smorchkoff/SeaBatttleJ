class User {
    String nickName = "";
    double winLoseRatio = 0.0;
    int wins = 0;
    int loses = 0;

    User() {}
    User(String nickNameSet){
        this.nickName = nickNameSet;
    }
    User(int wins, int loses){
        this.wins = wins;
        this.loses = loses;
    }

    User(String nickNameSet, int winsSet, int losesSet){
        this.nickName = nickNameSet;
        this.wins = winsSet;
        this.loses = losesSet;
    }
}
