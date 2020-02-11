package homeworks.seabatle.bean.request;

import lombok.Getter;

@Getter
public final class SinglePointRequest {
    private int x;
    private int y;

    public SinglePointRequest(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
