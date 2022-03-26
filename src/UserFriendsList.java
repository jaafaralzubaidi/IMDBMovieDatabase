
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class UserFriendsList {


    private int size;
    private LinkedList<UserFriends>[] list;


    public UserFriendsList(String fileName) {
        size = numOfUsers(fileName) + 1;    // the first index is null, list starts with 1;

        if (size > 1) {
            list = (LinkedList<UserFriends>[]) new LinkedList[size];
            // Initialize the list
            for (int i = 0; i < size; i++)
                list[i] = new LinkedList<UserFriends>();

            buildList(fileName);
        } else
            throw new IllegalArgumentException();


    }

    // Counts number of users
    private int numOfUsers(String fileName) {
        File file = new File(fileName);
        int userId = 0;
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();    // title line
            while (scan.hasNextLine()) {
                userId = scan.nextInt();
                scan.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userId;
    }

    // Stores objects to list from by reading the file
    private void buildList(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();    // title line
            int userID = 0, friendID;

            while (scan.hasNextLine()) {
                userID = scan.nextInt();
                friendID = scan.nextInt();

                UserFriends ufObject = new UserFriends(userID, friendID);
                addValue(userID, ufObject);

                scan.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addValue(int index, UserFriends userFriends) {
        list[index].add(userFriends);

    }

    public boolean hasElements(int i) {
        return !list[i].isEmpty();

    }

    public void printList() {
        System.out.println("UserID: FriendID");
        for (int i = 0; i < size; i++) {
            if (hasElements(i)) {
                System.out.print(i + ": ");
                for (UserFriends object : list[i])
                    System.out.print(+object.getFriendID() + " ");
                System.out.println();
            }
            System.out.println();
        }

    }

    public Iterable<UserFriends> getListAt(int i) {
        return list[i];
    }


}
