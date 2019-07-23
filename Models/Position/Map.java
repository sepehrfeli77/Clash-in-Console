package Models.Position;

import Enums.Field;
import Models.Buildings.Building;
import Models.Entity;
import Models.Soldiers.Soldier;

import java.util.ArrayList;

public class Map {

    public Map() {

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                this.getCells()[i][j] = new Cell();
            }
        }
    }

    private static int mapSize = 30;

    private Cell[][] cells = new Cell[mapSize][mapSize]; //[height][width]

    /*
    * returned values as width and height
    * are zero_based (started from 0)
    * */

    private ArrayList<String> paths = new ArrayList<String>();

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public static int getMapSize() {

        return mapSize;
    }

    public static void setMapSize(int mapSize) {

        Map.mapSize = mapSize;
    }


    public Cell[][] getCells() {

        return cells;
    }

    public void setCells(Cell[][] cells) {

        this.cells = cells;
    }



    public void locate(Entity entity) {
        /*
        gets an entity and
        locates it on map
        by putting it on the
        right place in cells
        array , if possible
         */
        Cell selectedCell = this.getCell(entity.getPosition());

        if (entity.getClass().getSuperclass().equals(Building.class)) {
            selectedCell.getOnGroundThings().add(entity);
        }

        if (entity.getClass().getSuperclass().equals(Soldier.class)) {
            if (((Soldier) entity).getMovementField().equals(Field.GROUND)) {
                selectedCell.getOnGroundThings().add(entity);
            }

            if (((Soldier) entity).getMovementField().equals(Field.AIR)) {
                selectedCell.getInAirThings().add(entity);
            }
        }
    }

    public void remove(Entity entity){
        /*
        removes an entity from
        a cell in map
        */
    }
    public boolean outOfMap(Point point){

        if (point.getHeight() < 0 || Map.mapSize <= point.getHeight())
            return true;
        if (point.getWidth() < 0 || Map.mapSize <= point.getWidth())
            return true;

        return false;
    }

    public static boolean outOfMap(int height, int width){

        if (width < 0 || Map.mapSize <= width)
            return true;
        if (height < 0 || Map.mapSize <= height)
            return true;

        return false;
    }

    public Cell getCell(Point point){

        if (this.outOfMap(point))
            return null;

        return this.cells[point.getHeight()][point.getWidth()];
    }

    public Cell getCell(int height, int width){

        if (this.outOfMap(height, width))
            return null;

        return this.cells[height][width];
    }

    public Point verticalSearch(Point centralPoint, int columnDistance, int searchRange, ArrayList toFind){
        int columnWidth = centralPoint.getWidth() + columnDistance;

        int[] direction = {-1, 1}; //to check cells in both sides of centralCell

        for (int dir : direction){

            int checkingCellHeight = centralPoint.getHeight() + searchRange * dir;

            if (!this.outOfMap(checkingCellHeight, columnWidth)) {
                Cell checkingCell = this.getCell(checkingCellHeight, columnWidth);

                if (checkingCell.contains(toFind)) {
                    //to see if there is any thing we search for
                    return new Point(checkingCellHeight, columnWidth);
                }
            }
        }
        return null;  //if still couldn't find
    }

    public Point horizontalSearch(Point centralPoint, int rowDistance, int searchRange, ArrayList toFind) {
        int rowHeight = centralPoint.getHeight() + rowDistance;

        int[] direction = {-1, 1}; //to check cells in both sides of centralCell
        for (int dir : direction){
            int checkingCellWidth = centralPoint.getWidth() + searchRange * dir;

            if (!this.outOfMap( rowHeight, checkingCellWidth)) {
                Cell checkingCell = this.getCell(rowHeight, checkingCellWidth);
                if (checkingCell.contains(toFind)) {  //to see if there is any thing we search for
                    return new Point(rowHeight, checkingCellWidth);
                }
            }
        }

        return null;  //if still couldn't find
    }

    public ArrayList extractVillageThings(){
        Cell mapCell ;
        ArrayList<Entity> villageThings = new ArrayList<>(0);
        for (int i = 0; i < Map.getMapSize(); i++) {
            for (int j = 0; j < Map.getMapSize(); j++) {
                mapCell = this.getCell(i , j);
                if (mapCell.getOnGroundThings().size() == 0)
                    continue;
                if (mapCell.getOnGroundThings().get(0).getClass().getSuperclass().equals(Building.class))
                    villageThings.add((Entity) mapCell.getOnGroundThings().get(0));
            }
        }
        return villageThings;

    }
}