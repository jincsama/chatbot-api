package cn.itstack.api.domain.zsxq.model.vo;

public class UserSpecific {

    private boolean liked;
    private boolean subscribed;

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean getLiked() {
        return liked;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public boolean getSubscribed() {
        return subscribed;
    }

}

