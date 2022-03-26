import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

public class LastFMRecommenderTEST {
    static LastFMRecommender recommender;

    @BeforeAll
    static void init() {
        recommender = new LastFMRecommender("artists.dat",
                "user_friends.dat", "user_artists.dat");
    }

    // Test the fiends of a user
    @Test
    public void testingListingFriendsOfUser() {
        ArrayList<Integer> actual = recommender.getListFriends(10);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1196);
        expected.add(1498);
        expected.add(1530);
        expected.add(1562);
        expected.add(1986);

        assertArrayEquals(actual.toArray(), expected.toArray());
    }

    // Test the common friends between the two users
    @Test
    public void testingCommonFriends() {

        HashSet<Integer> actual = recommender.getCommonFriends(5, 6);
        // Hashset with common friends with between user 5 and 6
        HashSet<Integer> expected = new HashSet<>();
        expected.add(1271);
        expected.add(831);
        assertArrayEquals(actual.toArray(), expected.toArray());
    }

    // Test the common friends between the two users, when there are no common friends
    @Test
    public void testingCommonFriends2() {

        HashSet<Integer> actual = recommender.getCommonFriends(2, 11);
        HashSet<Integer> expected = new HashSet<>();

        assertArrayEquals(actual.toArray(), expected.toArray());
    }

    // Test the Artists listened to by two users
    @Test
    public void testingListArtist() {
        HashSet<Integer> actual = recommender.getListArtists(2, 5);
        HashSet<Integer> expected = new HashSet<>();
        expected.add(53);

        assertArrayEquals(actual.toArray(), expected.toArray());
    }

    // Test the Artists listened to by two users
    @Test
    public void testingListArtist2() {
        HashSet<Integer> actual = recommender.getListArtists(10, 50);
        HashSet<Integer> expected = new HashSet<>();
        expected.add(159);
        expected.add(428);
        expected.add(424);
        expected.add(229);
        expected.add(228);
        expected.add(429);

        assertEquals(actual, expected);
    }

    // Test the top 10 most listed to artist and sorted by they the listening count
    @Test
    public void testingListTop10() {
        UserArtists[] actual = recommender.getListTop10();
        UserArtists[] expected = new UserArtists[10];
        expected[0] = new UserArtists(1642, 72, 352698);
        expected[1] = new UserArtists(2071, 792, 324663);
        expected[2] = new UserArtists(1094, 511, 320725);
        expected[3] = new UserArtists(1905, 203, 257978);
        expected[4] = new UserArtists(1664, 498, 227829);
        expected[5] = new UserArtists(1146, 378, 203165);
        expected[6] = new UserArtists(514, 292, 176133);
        expected[7] = new UserArtists(1983, 701, 172496);
        expected[8] = new UserArtists(757, 701, 169596);
        expected[9] = new UserArtists(1086, 679, 165902);
        assertArrayEquals(actual, expected);

    }

    // testRecommend is divided into 3 different methods
    // 1. Test the array including the user and his friends
    @Test
    public void testingRecommend10_getUserFriendsIDs() {
        // array of user and his friends
        int[] actual = recommender.getUserFriendsIDs(2);
        int[] expected = {2, 275, 428, 515, 761, 831, 909, 1209, 1210, 1230, 1327, 1585, 1625, 1869};
        assertArrayEquals(actual, expected);
    }

    // 2. Test the most listened artist by user
    @Test
    public void testingRecommend10_top10MostListened() {

        UserArtists[] actual = recommender.top10MostListened(2, 14);
        UserArtists[] expectedUser2 = new UserArtists[10];
        expectedUser2[0] = new UserArtists(2, 51, 13883);
        expectedUser2[1] = new UserArtists(2, 52, 11690);
        expectedUser2[2] = new UserArtists(2, 53, 11351);
        expectedUser2[3] = new UserArtists(2, 54, 10300);
        expectedUser2[4] = new UserArtists(2, 55, 8983);
        expectedUser2[5] = new UserArtists(2, 56, 6152);
        expectedUser2[6] = new UserArtists(2, 57, 5955);
        expectedUser2[7] = new UserArtists(2, 58, 4616);
        expectedUser2[8] = new UserArtists(2, 59, 4337);
        expectedUser2[9] = new UserArtists(2, 60, 4147);
        assertArrayEquals(actual, expectedUser2);
    }

    // 3. Testing the size of the priority queue
    @Test
    public void testingRecommend10_getUserMaxPriorityQueue() {
        PriorityQueue<UserArtists> pq = (PriorityQueue<UserArtists>) recommender.getUserMaxPriorityQueue(2);
        int actual = pq.size();
        int expected = 50;
        assertEquals(actual, expected);
    }
}
