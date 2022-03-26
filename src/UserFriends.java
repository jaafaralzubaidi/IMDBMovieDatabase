import java.util.Objects;

public class UserFriends {

    private int userID;
    private int friendID;

    public UserFriends(int userID, int friendID) {
        this.userID = userID;
        this.friendID = friendID;
    }

    public int getUserID() {
        return userID;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        UserFriends that = (UserFriends) o;
        return (userID == that.userID) && (friendID == that.friendID);
    }

    @Override
    public int hashCode() {
        return this.userID;
    }

    @Override
    public String toString() {
        return "userID= " + userID + ", friendID= " + friendID + "\n";
    }

}
