import java.util.Comparator;
import java.util.Objects;

public class UserArtists implements Comparable<UserArtists> {

    private int userId;
    private int artistId;
    private int listeningCount;

    public UserArtists(int userId, int artistId, int listeningCount) {
        this.userId = userId;
        this.artistId = artistId;
        this.listeningCount = listeningCount;
    }



    public int getUserId() {
        return userId;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getListeningCount() {
        return listeningCount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setListeningCount(int listeningCount) {
        this.listeningCount = listeningCount;
    }


    @Override
    public String toString() {
        return "userId= " + userId + ", artistId= " + artistId + ", listeningCount= " + listeningCount ;
    }

    @Override
    public int compareTo(UserArtists o) {
        if(listeningCount < o.getListeningCount())
            return -1;
        else if(listeningCount > o.getListeningCount())
            return +1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserArtists that = (UserArtists) o;
        return userId == that.userId && artistId == that.artistId && listeningCount == that.listeningCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, artistId, listeningCount);
    }
}
