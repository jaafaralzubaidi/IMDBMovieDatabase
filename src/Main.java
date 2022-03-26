public class Main {


    public static void main(String[] args) {
        LastFMRecommender recommender = new LastFMRecommender("artists.dat",
                "user_friends.dat", "user_artists.dat");
        recommender.listFriends(10);
        System.out.println();

        recommender.commonFriends(5, 6);
        recommender.commonFriends(2,5);
        recommender.commonFriends(2, 11);
        System.out.println();

        recommender.listArtists(2,5);
        recommender.listArtists(10,50);
        System.out.println();

        recommender.listTop10();
        System.out.println();

        recommender.recommend10(2);

    }

}
