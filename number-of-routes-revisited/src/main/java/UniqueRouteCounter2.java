import java.awt.*;

public class UniqueRouteCounter2
{
    public int getNumberOUniquefRoutes(Point start, Point finish)
    {
        if(start.x > finish.x || start.y > finish.y)
        {
            throw new IllegalArgumentException("Destination can't be above or to the left of the start");
        }

        int xLength = finish.x - start.x;
        int yLength = finish.y - start.y;
        int[][] routeCounts = new int[xLength+1][yLength+1];

        // For simplicity, I treat it as moving up and left rather than down and right, so destination is at origin.
        routeCounts[0][0] = 1;
        for(int y = 0; y <= yLength; y++)
        {
            {
                for(int x = 0; x <= xLength; x++)
                {
                    int numAbove = y == 0 ? 0 : routeCounts[x][y - 1];
                    int numLeft = x == 0 ? 0 : routeCounts[x-1][y];
                    routeCounts[x][y] = Math.max(numAbove + numLeft, 1);
                }
            }
        }
        return routeCounts[xLength][yLength];
    }
}
