package edu.nikita.ds;

/**
 * Created by nickvoronin on 6/21/14.
 */
public class Maze {
    char[][] maze;

    int n, m;


    public Maze(int n, int m){
        this.n = n;
        this.m = m;
        maze = new char[n][m];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                maze[i][j] = '0';

    }

    public void generate(int i, int j, boolean isBranch) {
        boolean condition = true;
        while(condition) {
            maze[i][j] = ' ';
            int goTo = 1 + (int)(Math.random() * (4));
            GoTo goTo1 = new GoTo(i, j, isBranch, goTo).invoke();
            i = goTo1.getI();
            j = goTo1.getJ();
            if(isBranch)
                condition = i < n && j < m && (maze[i][j] != ' ');
            else
            {
                if(i < 0) {
                    i = 0;
                }

                if (i > n - 1 && j != m - 1)
                {
                    i--;
                    j++;
                }
                if (i != n - 1 && j > m - 1) {
                    j--;
                    i++;
                }
                if (i == n - 1 && j == m - 1)
                    break;
            }
        }
    }

    public void print() {
        for(int i = 0; i < n; i++)
        {
            System.out.println();
            for(int j = 0; j < m; j++)
                System.out.print(maze[i][j]);
        }
    }

    private class GoTo {
        private int i;
        private int j;
        private boolean isBranch;
        private int goTo;

        public GoTo(int i, int j, boolean isBranch, int goTo) {
            this.i = i;
            this.j = j;
            this.isBranch = isBranch;
            this.goTo = goTo;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public GoTo invoke() {
            switch(goTo) {
                case 1:
                    if(isBranch)
                        i--;
                    else {
                        if(i > 2)
                            i--;
                        else {
                            if(Math.random() > 0.5d)
                                i++;
                            else
                                j++;
                        }
                    }
                    break;
                case 2:
                    j++;
                    break;
                case 3:
                    i++;
                    break;
                // branch
                case 4:
                    if(!isBranch)
                        generate(i, j, true);
                    break;
            }
            return this;
        }
    }
}
