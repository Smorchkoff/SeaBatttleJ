class User {
    String nickName = "";
    double winLoseRatio = 0.0;
    int wins = 0;
    int loses = 0;

    User(String nickNameSet){
        nickName = nickNameSet;
    }

    User(String nickNameSet, int winsSet, int losesSet){
        nickName = nickNameSet;
        wins = winsSet;
        loses = losesSet;
    }

    public void winLoseRatioCalc(int wins, int loses){
        try{
            winLoseRatio = ((double) wins /loses);
        }
        catch(Exception e){
            System.out.println(e.getMessage()+" - деление на ноль");
        }
    }
}
