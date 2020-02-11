package homeworks.seabatle.bean.field;

public enum StrikeResult {
    KILL,WOUND,MISS,SHOOT,LOSE;
    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append(name().toLowerCase());
        sb.append("ed");
        return sb.toString();
    }
}
