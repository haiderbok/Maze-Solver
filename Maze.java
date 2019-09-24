import java.awt.*;

/**
 * Maze.java
 * <p>
 * Project 1, part 2. Solve the maze problem with stack and queue.
 */

//left up right down
public class Maze {

    private final char SPACE = '.';
    private final char WALL = '#';
    private final char START = '$';
    private final char END = '%';

    public Maze() {
    }


    /**
     * Finds the path using {@code MyStack}.
     * Returns the path and number of spaces checked as {@code String}.
     *
     * @param map a {@code char[][]} provide.
     * @return the path and number of spaces checked as {@code String}
     */
    public String solveWithStack(char[][] map) {
        // Stack to go through the maze
        MyStack<Point> maze = new MyStack<>();

        // A string to store the result and return
        String rez = "";

        // Integer array to store the current location
        int [] location = new int[2];

        //initialising the location with (0,0)
        location[0] = 0;
        location[1] = 0;

        // A counter to check the number of iterations it took to solve the maze
        int count = 0;

        // A boolean- 2d array with same dimensions as maps to check is the location has already been visited
        boolean [][] check = new boolean[map.length][map[0].length];

        //A Point Parent array that stores the parent of each place
        //to be used to find the correct path at then end
        Point [][] parent = new Point [map.length][map[0].length];

        // Creating a point to store the location of crystal
        Point crystal = new Point();

        //for loops to iterate through the maps
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length ; j++) {

                // Setting i and j indexes to the current location
                i = location[0];
                j = location[1];

                //Case 1 check down
                if (i < map.length - 1){
                    if (map[i + 1][j] == '.' && check[i + 1][j] == false){
                        // pushing the index of the . foung below into the stack "maze"
                        maze.push(new Point(i + 1, j));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i +1][j] = new Point(i, j);
                    } else if (map[i + 1][j] == '%'){

                        //Storing the location of crystal
                        crystal.setLocation(i + 1, j);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i +1][j] = new Point(i, j);

                        // break out of the loops
                        i = map.length;
                        break;
                    }
                }

                //Case 2 check right
                if (j < map[i].length - 1) {
                    if (map[i][j + 1] == '.' && check[i][j + 1] == false) {
                        // pushing the index of the . foung to the right, into the stack "maze"
                        maze.push(new Point(i, j + 1));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i][j + 1] = new Point(i, j);
                    } else if (map[i][j + 1] == '%'){

                        //Storing the location of crystal
                        crystal.setLocation(i, j + 1);


                        //pushing the parent of this point  into the location of the crystal found
                        parent[i][j + 1] = new Point(i, j);

                        // break out of the loops
                        i = map.length;
                        break;
                    }
                }

                // Case 3 check up
                if (i > 0){
                    if (map[i - 1][j] == '.' && check[i - 1][j] == false){

                        // pushing the index of the . foung above, into the stack "maze"
                        maze.push(new Point(i - 1, j));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i-1][j] = new Point(i, j);

                    } else if (map[i - 1][j] == '%'){

                        //Storing the location of crystal
                        crystal.setLocation(i - 1, j);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i-1][j] = new Point(i, j);

                        //break out of the loop
                        i = map.length;
                        break;
                    }
                }

                //Case 4 check left
                if (j > 0){
                    if (map[i][j - 1] == '.' && check[i][j - 1] == false){

                        // pushing the index of the . foung above, into the stack "maze"
                        maze.push(new Point(i, j - 1));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i][j - 1] = new Point(i, j);

                    } else if(map[i][j - 1] == '%'){

                        //Storing the location of crystal
                        crystal.setLocation(i, j - 1);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i][j - 1] = new Point(i, j);

                        //break out of the loop
                        i = map.length;
                        break;
                    }
                }
                // if maze is not empty then update stuff
                if (!maze.isEmpty()) {

                    // pop the the index out to store it as the current location
                    Point p = maze.pop();

                    rez = rez + "(" + String.valueOf((int) p.getX()) + "," + String.valueOf((int) p.getY()) + ")";

                    // Mark the location as already checked
                    check[(int) p.getX()][(int) p.getY()] = true;

                    //Make the indexes as the current locaion
                    location[0] = (int) p.getX();
                    location[1] = (int) p.getY();

                    // Make the position already been to 1 so that you cant count it at the end
                    map[location[0]][location[1]] = '1';

                } else {

                    // If the maze is empty and we have reached here that means that there is no way
                    return "no way";
                }
            }// end of inner for loop
        }// end of outer for loop

        // Using this Point object to update the index while searching for the correct path
        Point parents = new Point();


        //defining index and and initialing them with crystals location
        int x = (int) crystal.getX();
        int y = (int) crystal.getY();

        //Add the location of crystal to the rez
        rez = "(" + x + "," + y + ")";

        // Trace back the right location from the
        while(x != 0 || y != 0){
            // Store the parent in the result string called rez
            rez = "(" + (int) parent[x][y].getX() + "," + (int) parent[x][y].getY() + ")" + rez;
            // Store this into a variable
            parents = parent[x][y];

            //Update the index's
            x = (int) parents.getX();
            y = (int) parents.getY();
        }

        System.out.println( "Stack maze length" +  maze.size());
        count = count(map) + 1;
        //count += maze.size();
        rez = rez + " " +count;
        return rez;
    } // end of the solveWithStack function

    /**
     * Finds the path using {@code MyQueue}.
     * Returns the path and number of spaces checked as {@code String}.
     *
     * @param map a {@code char[][]} provide.
     * @return the path and number of spaces checked as {@code String}
     */
    public String solveWithQueue(char[][] map) {

        // Stack to go through the maze
        MyQueue<Point> maze = new MyQueue<>();

        // A string to store the result and return
        String rez = "";

        // Integer array to store the current location
        int [] location = new int[2];

        //initialising the location with (0,0)
        location[0] = 0;
        location[1] = 0;

        // A counter to check the number of iterations it took to solve the maze
        int count = 1;

        // A boolean- 2d array with same dimensions as maps to check is the location has already been visited
        boolean [][] check = new boolean[map.length][map[0].length];

        //A Point Parent array that stores the parent of each place
        //to be used to find the correct path at then end
        Point [][] parent = new Point [map.length][map[0].length];

        // Creating a point to store the location of crystal
        Point crystal = new Point();

        //for loops to iterate through the maps
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // Setting i and j indexes to the current location
                i = location[0];
                j = location[1];

                //Case 1 check down
                if (i < map.length - 1){
                    if (map[i + 1][j] == '.' && check[i + 1][j] == false){
                        // pushing the index of the . foung below into the stack "maze"
                        maze.enqueue(new Point(i + 1, j));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i +1][j] = new Point(i, j);
                    } else if (map[i + 1][j] == '%'){
                        // Add it to the string for now
                        rez = rez + "(" + (i + 1) + "," + (j) + ")";

                        //Storing the location of crystal
                        crystal.setLocation(i + 1, j);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i +1][j] = new Point(i, j);

                        // break out of the loops
                        i = map.length;
                        break;
                    }
                }

                //Case 2 check right
                if (j < map[i].length - 1) {
                    if (map[i][j + 1] == '.' && check[i][j + 1] == false) {
                        // pushing the index of the . foung to the right, into the stack "maze"
                        maze.enqueue(new Point(i, j + 1));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i][j + 1] = new Point(i, j);
                    } else if (map[i][j + 1] == '%'){
                        // Add it to the string for now
                        rez = rez + "(" + i + "," + (j + 1) + ")";

                        //Storing the location of crystal
                        crystal.setLocation(i, j + 1);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i][j + 1] = new Point(i, j);

                        // break out of the loops
                        i = map.length;
                        break;
                    }
                }

                // Case 3 check up
                if (i > 0){
                    if (map[i - 1][j] == '.' && check[i - 1][j] == false){

                        // pushing the index of the . foung above, into the stack "maze"
                        maze.enqueue(new Point(i - 1, j));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i-1][j] = new Point(i, j);

                    } else if (map[i - 1][j] == '%'){

                        // Add it to the string for now
                        rez = rez + "(" + (i - 1) + "," + j + ")";

                        //Storing the location of crystal
                        crystal.setLocation(i - 1, j);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i-1][j] = new Point(i, j);

                        // Increment the counter to take into the account the pop of 0,0
                        count++;

                        //break out of the loop
                        i = map.length;
                        break;
                    }
                }

                //Case 4 check left
                if (j > 0){
                    if (map[i][j - 1] == '.' && check[i][j - 1] == false){

                        // pushing the index of the . foung above, into the stack "maze"
                        maze.enqueue(new Point(i, j - 1));

                        //pushing the parent of this point  into the location of the dot found
                        parent[i][j - 1] = new Point(i, j);
                    } else if(map[i][j - 1] == '%'){

                        //Storing the location of crystal
                        crystal.setLocation(i, j - 1);

                        //pushing the parent of this point  into the location of the crystal found
                        parent[i][j - 1] = new Point(i, j);

                        // Increment the counter to take into the account the pop of 0,0
                        count++;

                        //break out of the loop
                        i = map.length;
                        break;
                    }
                }
                // if maze is not empty then update stuff
                if (!maze.isEmpty()) {

                    // pop the the index out to store it as the current location
                    Point p = maze.dequeue();

                    // Mark the location as already checked
                    check[(int) p.getX()][(int) p.getY()] = true;

                    //Make the indexes as the current locaion
                    location[0] = (int) p.getX();
                    location[1] = (int) p.getY();

                    // Change the dot to a 1 for counting at the end purpose
                    map[location[0]][location[1]] = '1';

                } else {

                    // If the maze is empty and we have reached here that means that there is no way
                    return "no way";
                }
            }// end of inner for loop
        }// end of outer for loop

        // Using this Point object to update the index while searching for the correct path
        Point parents = new Point();

        //defining index and and initialing them with crystals location
        int x = (int) crystal.getX();
        int y = (int) crystal.getY();


        //Add the location of crystal to the rez
        rez = "(" + x + "," + y + ")";
        while(x != 0 || y != 0){

            // Store the parent in the result string called rez
            rez = "(" + (int) parent[x][y].getX() + "," + (int) parent[x][y].getY() + ")" + rez;

            // Store this into a variable
            parents = parent[x][y];

            //Update the index's
            x = (int) parents.getX();
            y = (int) parents.getY();
        }


        count = count(map) + 1;
        rez = rez + " " + count;
        return rez;
    }

    public int count(char[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '1') {
                    count++;
                }
            }
        }
        return count;
    }
}




