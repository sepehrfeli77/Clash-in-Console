package Models.Position;

import Models.Buildings.Building;
import Models.Buildings.DefensiveBuildings.Trap;
import Models.Buildings.DefensiveBuildings.Wall;
import Models.Entity;
import Models.Soldiers.Archer;
import Models.Soldiers.Dragon;
import Models.Soldiers.Giant;

import java.util.ArrayList;

public class Cell {

    private ArrayList<Entity> onGroundThings = new ArrayList<Entity>(5);
    private ArrayList<Entity> inAirThings = new ArrayList<Entity>(5);
    private int soldiersJustDeployedNumber = 0;

    public ArrayList<Entity> getOnGroundThings() {
        return onGroundThings;
    }

    public void setOnGroundThings(ArrayList onGroundThings) {
        this.onGroundThings = onGroundThings;
    }

    public ArrayList getInAirThings() {
        return inAirThings;
    }

    public int getSoldiersJustDeployedNumber() {
        return soldiersJustDeployedNumber;
    }

    public void setSoldiersJustDeployedNumber(int soldiersJustDeployedNumber) {
        this.soldiersJustDeployedNumber = soldiersJustDeployedNumber;
    }

    public void setInAirThings(ArrayList inAirthings) {
        this.inAirThings = inAirthings;
    }

    public boolean emptyCell() {

        if (this.getOnGroundThings().size() == 0) {
            return true;
        }

        return false;
    }


    public boolean isEmptyOnGround() {

        for (int i = 0; i < onGroundThings.size(); i++) {
            if (onGroundThings.get(i).getClass().getSuperclass().equals(Building.class)
                    && !onGroundThings.get(i).getClass().equals(Trap.class)) {
                return false;
            }
        }
        return true;

    }

    public boolean isEmptyInAir() {
        for (int i = 0; i < onGroundThings.size(); i++) {
            if (onGroundThings.get(i).getClass().getSuperclass().equals(Building.class)) {
                if (!(onGroundThings.get(i).getClass().getSuperclass().equals(Trap.class) || onGroundThings.get(i).getClass().getSuperclass().equals(Wall.class)))
                    return false;
            }
        }
        return true;
    }

    public Entity find(Class toFind) {

        for (Object o : onGroundThings) {
            if (!((Entity) o).isDestroyed())
                if (toFind.equals(o.getClass()))
                    return ((Entity) o);
        }

        for (Object o : inAirThings) {
            if (!((Entity) o).isDestroyed())
                if (toFind.equals(o.getClass()))
                    return ((Entity) o);
        }
        return null;
    }


    public boolean contains(ArrayList typeCodes) {


        for (Object o : onGroundThings) {
            if (!((Entity) o).isDestroyed())
                if (typeCodes.contains(o.getClass()))
                    return true;
        }

        for (Object o : inAirThings) {
            if (typeCodes.contains(o.getClass()))
                if (!((Entity) o).isDestroyed())
                    return true;
        }
        return false;
    }


    public static void main(String[] args) {

        Cell cell = new Cell();
        cell.onGroundThings.add(new Archer());
        cell.onGroundThings.add(new Giant());

        ArrayList arrayList = new ArrayList(0);
        arrayList.add((Giant.class));
        System.out.println(cell.contains(arrayList));
    }
}