package edu.nikita.ds;

import java.util.Set;

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

    public static void walkItDown(Maze maze) {
        int index = 0;
        int y = 0, x = 0;
        boolean forward = true;
        while(y != maze.n - 1 || x != maze.m - 1) {

            if(tryToGoIn(y + 1, x, maze) && !tryToGoIn(y, x - 1, maze) && forward)
            {
                while (tryToGoIn(y + 1, x, maze) && !tryToGoIn(y, x - 1, maze)) {
                    maze.maze[y][x] = path;
                    y++;
                }
                if(tryToGoIn(y, x - 1, maze)) {
                    x--;
                    forward = false;
                }
            }

            if(tryToGoIn(y, x + 1, maze) && !tryToGoIn(y + 1, x, maze) && forward) {
                while (tryToGoIn(y, x + 1, maze) && !tryToGoIn(y + 1, x, maze)) {
                    maze.maze[y][x] = path;
                    x++;
                }
                if(tryToGoIn(y + 1, x, maze))
                    y++;
            }

            if(tryToGoIn(y - 1, x, maze) && !tryToGoIn(y, x + 1, maze) && !forward) {
                while (tryToGoIn(y - 1, x, maze) && !tryToGoIn(y, x + 1, maze)) {
                    maze.maze[y][x] = path;
                    y--;
                }
                if(tryToGoIn(y, x + 1, maze)) {
                    x++;
                    forward = true;
                }
            }

            if(tryToGoIn(y, x - 1, maze) && !tryToGoIn(y - 1, x, maze) && !forward)
            {
                while (tryToGoIn(y, x - 1, maze) && !tryToGoIn(y - 1, x, maze)) {
                    maze.maze[y][x] = path;
                    x--;
                }
                if(tryToGoIn(y - 1, x, maze)) {
                    y--;
                }
            }

            index++;

            if(index == 100)
                maze.print();
        }
    }

    public static boolean memoryWalk(Maze maze, int x, int y, Set<Tuple> visited) {
        if (!tryToGoIn(x, y, maze))
            return false;

        if (isExit(x, y, maze))
            return true;

        boolean result;

        Tuple next = new Tuple(x + 1, y);
        result = checkSubPath(maze, visited, next);

        if (result) {
            return true;
        }

        next = new Tuple(x, y + 1);
        result = checkSubPath(maze, visited, next);

        if (result) {
            return true;
        }

        next = new Tuple(x, y - 1);
        result = checkSubPath(maze, visited, next);

        if (result) {
            return true;
        }

        next = new Tuple(x-1, y);
        result = checkSubPath(maze, visited, next);

        if (result) {
            return true;
        }

        return false;
    }

    private static boolean checkSubPath(Maze maze, Set<Tuple> visited, Tuple next) {
        boolean result = false;
        if(!visited.contains(next)){
            visited.add(next);
            result = memoryWalk(maze, next.x, next.y, visited);
        }

        if (result) {
            maze.maze[next.x][next.y] = path;
        }

        return result;
    }

    private static boolean isExit(int x, int y, Maze maze) {
        return x == maze.n - 1 && y == maze.m - 1;
    }

}
