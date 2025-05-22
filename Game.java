import java.util.Scanner;

public class Game
{
    int countNorth;
    int countEast;
    int countSouth;
    int countWest;
    int row;
    int col;

    boolean move = false;
    boolean[][] track;

    String direction;
    
    GameMap map;


    public Game(GameMap map)
    {
        this.map = map;
        this.row = 0;
        this.col = 0;
        track = new boolean[map.getHeight()][map.getWidth()];
        track[this.row][this.col] = true;

        System.out.println("Welcome to the maze!");
        map.getLocation(this.row, this.col).togglePlayerHere();
        System.out.println(toString());
        map.getLocation(this.row, this.col).togglePlayerHere();
        
    }

    @Override
    public String toString()
    {
        String drawing = "";

        for (int r = 0; r < map.getHeight(); r++)
        {
            for (int c = 0; c < map.getWidth(); c++)
            {
                if (track[r][c] == true)
                {
                    drawing += map.getLocation(r, c).mapRepresentation();
                }
                else{
                    drawing += " ";
                }
            } 
            drawing += "\n";
        }
        return (drawing);
    }

    public void playGame()
    {
        Scanner sc = new Scanner(System.in);

        while (map.getLocation(this.row, this.col).isExit() == false)
        {
            System.out.print(map.getLocation(this.row, this.col));
            System.out.print("Which way would you like to go? ");

            while (this.move == false)
            {
                direction = sc.nextLine();

                if (map.getLocation(this.row, this.col).hasDirection(direction) == true) 
                {
                    switch(direction)
                    {
                        case "north":
                            this.row -= 1;
                            track[this.row][this.col] = true;
                            break;
                        case "east":
                            this.col += 1;
                            track[this.row][this.col] = true;
                            break;
                        case "south":
                            this.row += 1;
                            track[this.row][this.col] = true;
                            break;
                        case "west":
                            this.col -= 1;
                            track[this.row][this.col] = true;
                            break;
                    }
                    this.move = true;
                    map.getLocation(this.row, this.col).togglePlayerHere();
                    System.out.println(toString());
                    map.getLocation(this.row, this.col).togglePlayerHere();
                }
                else
                {
                    System.out.println("You can't go that way! Try again.");
                    System.out.print("Which way would you like to go? ");
                }
            }
            this.move = false;
        }

    System.out.println("You made it to an exit. You have escaped!");
    }
}
