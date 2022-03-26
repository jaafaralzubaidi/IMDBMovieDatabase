import java.util.*;

public class LastFMRecommender {

    private UserFriendsList userFriendsList;
    private UserArtistsList userArtistsList;
    private final int TOP = 10;

    public LastFMRecommender(String artistsFile, String userFriendsFile, String userArtistsFile){
        userFriendsList = new UserFriendsList(userFriendsFile);
        userArtistsList = new UserArtistsList(userArtistsFile);
    }

    // Prints user friends
    public void listFriends(int user) {
        ArrayList<Integer> listOfFriends = getListFriends(user);

        // Printing the result
        System.out.print("User " + user + " has: ");
        for(int i = 0; i < listOfFriends.size(); i++)
            System.out.print(listOfFriends.get(i) +  " ");
        System.out.println();
    }
    //  Returns a ArrayList of UserFriends object for a given user
    public ArrayList<Integer> getListFriends(int user) {
        ArrayList<Integer> array = new ArrayList<Integer>();

        // Getting the list at an index of LinkedList array
        List<UserFriends> listOfFriends = (List<UserFriends>) userFriendsList.getListAt(user);

        for(UserFriends i: listOfFriends)
            array.add(i.getFriendID());
        return array;
    }
    // Prints Common friends between two users
    public void commonFriends(int user1, int user2){
        // Hashset to store the common artistIDs
        HashSet<Integer> set = getCommonFriends(user1, user2);

        // Printing the result
        if(!set.isEmpty())
            System.out.println("Common Friends: " + set);
        else
            System.out.println("There are no common friends between " + user1 + " and " + user2);
    }
    //  Returns a Hashset of the intersection of two users friends
    public HashSet<Integer> getCommonFriends(int user1, int user2){
        // Getting the list at an index of LinkedList array
        List<UserFriends> list1 = (List<UserFriends>) userFriendsList.getListAt(user1);
        List<UserFriends> list2 = (List<UserFriends>) userFriendsList.getListAt(user2);

        // Hashset to store the common artistIDs
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();

        // Adding friendIDs to the sets
        for(UserFriends obj: list1)
            set1.add(obj.getFriendID());

        for(UserFriends obj: list2)
            set2.add(obj.getFriendID());

        // Finding the intersection between the two sets
        set1.retainAll(set2);

        return set1;
    }

    // Prints the list of artists listened by both users
    public void listArtists(int user1, int user2){
        // Hashset to store the common artistIDs
        HashSet<Integer> set = getListArtists(user1, user2);
        // Printing the result
        if(!set.isEmpty())
            System.out.println("Artists listened to by " + user1 +" and " + user2 +": " + set);
        else
            System.out.println("There are no common artist between " + user1 + " and " + user2);
    }
    //  Returns a Hashset of intersection of artist between two users
    public HashSet<Integer> getListArtists(int user1, int user2){
        // Getting the list at an index of LinkedList array
        List<UserArtists> list1 = (List<UserArtists>) userArtistsList.getListAt(user1);
        List<UserArtists> list2 = (List<UserArtists>) userArtistsList.getListAt(user2);

        // Hashset to store the common artistIDs
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();

        // Adding artistIDs to the sets
        for(UserArtists obj: list1 )
            set1.add(obj.getArtistId());

        for(UserArtists obj: list2)
            set2.add(obj.getArtistId());

        // Finding the intersection between the two sets
        set1.retainAll(set2);

        return set1;

    }

    // Prints the list of top 10 most popular artists listened by all users
    public void listTop10(){
        UserArtists [] array = getListTop10();

        // Printing the result
        System.out.println("The top 10 artists are: ");
            for(int i = 0; i < array.length; i++)
                System.out.println( i + 1 + ". " + array[i].getArtistId() +
                        " with listening count of " + array[i].getListeningCount());
    }

    // Returns an Array of top 10 userArtists
    public UserArtists[] getListTop10(){
        // Max Priority queue to store the artists
        PriorityQueue<UserArtists> pq = new PriorityQueue<UserArtists>(Collections.reverseOrder());

        // Hashset to eliminate the duplicates before adding the to the priority queue
        HashSet<UserArtists> set = new HashSet<UserArtists>();

        // Adding UserArtists obj to the the set
        for(int i = 0; i < userArtistsList.size(); i++)
            for(UserArtists obj: userArtistsList.getListAt(i))
                set.add(obj);

        // Adding UserArtists objects to the priority queue based on weight(CompareTo() in UserArtist class)
        for(UserArtists obj: set)
            pq.add(obj);

        // Array to store the top 10 UserArtists objects
        UserArtists [] array = new UserArtists[TOP];
        for(int i = 0; i < TOP; i++)
            array[i] = pq.remove();

        return array;

    }

    // Print Recommends 10 most popular artists listened by the given user and his/her friends.
    public void recommend10(int user){
        // Array of user and his friends
        int[] array = getUserFriendsIDs(user);

        System.out.print("The user " + user + " friendsID: ");
        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();

        for(int i = 0; i < array.length; i++){
            UserArtists[] top10 = top10MostListened(array[i], array.length);
            System.out.println("Top 10 most listened artists by user " + array[i]);

            for(int k = 0; k < TOP; k++)
                System.out.println(k + 1 + ". Artist: " + top10[k].getArtistId() +
                        ", listening count: " +top10[k].getListeningCount());

            System.out.println();

        }

    }

    // To generate an array with user friends. The user is included in the array at index 0
    public int[] getUserFriendsIDs(int user) {
        // A list of UserArtists objects for a specific index/user
        List<UserFriends> listOfUserFriends = (List<UserFriends>) userFriendsList.getListAt(user);

        // Array to store friends ID's staring at index 1 and for index 0 will have the user ID
        int array[] = new int [listOfUserFriends.size() + 1];
        int i = 0;

        // Adding the given user to index 0 of the array
        array[i++] = user;

        //Adding the friends ID's to the array
        for(UserFriends obj: listOfUserFriends)
            array[i++] = obj.getFriendID();

        return array;
    }

    // Returns an array of top 10 most listened artists with size of user plus the friends
    public UserArtists[] top10MostListened(int user, int size) {
        PriorityQueue<UserArtists> pqUser = null;


        for(int j = 0; j < size; j++)
            pqUser = (PriorityQueue<UserArtists>) getUserMaxPriorityQueue(user);

            // Array to store the top 10
            UserArtists [] array = new UserArtists[TOP];
            for(int i = 0; i < TOP; i++)
                array[i] = pqUser.remove();
            return array;
    }



    //Returns a max priority queue top 10 based for a given index/user
    public Iterable<UserArtists> getUserMaxPriorityQueue(int index) {
        // List of userArtist which has artists listened count number
        List<UserArtists> list = (List<UserArtists>) userArtistsList.getListAt(index);

        // Hashset to eliminate the duplicate before adding it to the priority queue
        HashSet<UserArtists> set = new HashSet<>();

        // Max Priority queue to store top 10 most listened artists
        PriorityQueue<UserArtists> pq = new PriorityQueue<UserArtists>(Collections.reverseOrder());

        //Adding the elements to the set
        for(UserArtists obj: list)
            set.add(obj);
        //Soring the objects of UserArtist to the priorityQueue based on weight(CompareTo() in UserArtist class)
        for(UserArtists obj: set)
            pq.add(obj);

        return pq;
    }
}
