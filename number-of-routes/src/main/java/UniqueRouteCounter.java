import java.awt.*;

public class UniqueRouteCounter
{
    public int getNumberOUniquefRoutes(Point start, Point finish)
    {
        if(start.x > finish.x || start.y > finish.y)
        {
            throw new IllegalArgumentException("Destination can't be above or to the left of the start");
        }

        // Move start to the origin (0, 0) to make hitting the cache simpler
        finish.x -= start.x;
        finish.y -= start.y;
        start.x = 0;
        start.y = 0;

        int[][] cache = new int[finish.x+1][finish.y+1];
        cache[finish.x][finish.y] = 1;

        return countRoutesFromTo(start, finish, cache);
    }

    // Lots of heaps will get created - if it's an issue could just take primitives instead
    private int countRoutesFromTo(Point start, Point destination, int[][] cache)
    {
        if(cache[start.x][start.y] != 0)
        {
            return cache[start.x][start.y];
        }

        int result = 0;
        if(start.x < destination.x)
        {
            result += countRoutesFromTo(new Point(start.x + 1, start.y), destination, cache);
        }
        if(start.y < destination.y)
        {
            result += countRoutesFromTo(new Point(start.x, start.y + 1), destination, cache);
        }

        cache[start.x][start.y] = result;
        return result;
    }
}
