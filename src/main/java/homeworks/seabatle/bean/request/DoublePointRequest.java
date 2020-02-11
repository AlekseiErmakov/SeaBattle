package homeworks.seabatle.bean.request;

import lombok.Getter;

@Getter
public final class DoublePointRequest {
    private int xOne;
    private int yOne;
    private int xTwo;
    private int yTwo;

    public DoublePointRequest(int xOne, int yOne, int xTwo, int yTwo) {
        this.xOne = xOne;
        this.yOne = yOne;
        this.xTwo = xTwo;
        this.yTwo = yTwo;
    }
    public int getLength(){
        return xOne == xTwo ? Math.abs(yTwo-yOne) + 1: Math.abs(xTwo - xOne) + 1;
    }
}
