import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class UserArtistsList {

    private int size;
    private LinkedList<UserArtists>[] list;

    public UserArtistsList(String fileName) {
        size = numOfUsers(fileName) + 1;    // + 1 to account for the 0 index in the array

        if (size > 1) {
            list = (LinkedList<UserArtists>[]) new LinkedList[size];
            //Initialize the list
            for (int i = 0; i < size; i++)
                list[i] = new LinkedList<UserArtists>();

            buildList(fileName);
        } else
            throw new IllegalArgumentException();
    }

    private void buildList(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine(); //get the title line
            int userId, artistId, weightId;
            while (scan.hasNextLine()) {
                userId = scan.nextInt();
                artistId = scan.nextInt();
                weightId = scan.nextInt();

                UserArtists userArtists = new UserArtists(userId, artistId, weightId);
                addValue(userId, userArtists);

                scan.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addValue(int index, UserArtists userArtists) {
        list[index].add(userArtists);
    }

    public Iterable<UserArtists> getListAt(int i) {
        return list[i];
    }

    private boolean hasElements(int i) {
        return !list[i].isEmpty();
    }

    public void printList() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (hasElements(i)) {
                System.out.println("UserID: " + i);

                for (UserArtists obj : list[i])
                    System.out.println("ArtistID: " + obj.getArtistId() + " weight is " + obj.getListeningCount());

                System.out.println();
            }
        }
    }

    private int numOfUsers(String fileName) {
        File file = new File(fileName);
        int userId = 0;
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()) {
                userId = scan.nextInt();
                scan.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    public int size() {
        return size;
    }
}
