package Models.Position;

public class Point {

    private int height;
    private int width;

    private int a;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Point(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean canDeployArmyHere(){

        if (this.getWidth() == 0 || this.getWidth() == Map.getMapSize() - 1)
            return true;
        if (this.getHeight() == 0 || this.getHeight() == Map.getMapSize() - 1)
            return true;

        return false;

    }

    public int euclideanDistanceFrom(Point point){  //TODO

        return 0;
    }

    public Point nextPointInWay(Point point){   //TODO

        return point;
    }

    @Override
    public String toString() {

        String toString = new String("(");
        toString = toString  + Integer.toString(this.getHeight()) + "," + Integer.toString(this.getWidth()) + ")";
        return toString;
    }
}
