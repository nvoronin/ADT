package edu.nikita.ds;

public class MazeWalker {

    static final char wall = '0';
    static final char path = 'x';
    static final char space = ' ';

    static enum Direction {
        FromTheLeft,
        FromTheTop,
        FromTheRight,
        FromTheBottom
    }

    public static void walkIt(Maze maze){
        Tuple prev = new Tuple();
        prev.x = 0;
        prev.y = -1;

        Tuple current = new Tuple();
        current.x = 0;
        current.y = 0;

        while (current.x != maze.n - 1 || current.y != maze.m - 1) {
            // traverse from right
            Direction d = getDirection(current, prev);

            int dx = 0, dy = 0;
            do {
                switch (d) {
                    case FromTheLeft:
                        dx = 0;
                        dy = 1;
                        d = Direction.FromTheBottom;
                        break;
                    case FromTheTop:
                        dx = -1;
                        dy = 0;
                        d = Direction.FromTheLeft;
                        break;
                    case FromTheRight:
                        dx = 0;
                        dy = -1;
                        d = Direction.FromTheTop;
                        break;
                    case FromTheBottom:
                        dx = 1;
                        dy = 0;
                        d = d.FromTheRight;
                        break;
                }
            }
            while (!tryToGoIn(current.x + dx, current.y + dy, maze));

            prev = current;
            current = new Tuple();
            current.x = prev.x + dx;
            current.y = prev.y + dy;

            maze.maze[current.x][current.y] = path;
        }
    }

    private static boolean tryToGoIn(int i, int j, Maze maze) {
        return i < maze.n && j < maze.m && i >= 0 && j >= 0 && (maze.maze[i][j] != (wall));
    }

    private static Direction getDirection(Tuple current, Tuple prev) {
        if(current.x != prev.x)
        {
            if(current.x > prev.x)
                return Direction.FromTheLeft;
            else
                return Direction.FromTheRight;
        }
        if(current.y != prev.y)
        {
            if(current.y > prev.y)
                return Direction.FromTheTop;
            else
                return Direction.FromTheBottom;
        }
        return Direction.FromTheLeft;
    }
}
