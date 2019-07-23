package GameModes;

import Enums.*;
import Exceptions.*;
import Models.Buildings.Building;
import Models.Buildings.TownBuildings.*;
import Models.Entity;
import Models.Position.*;
import Models.Soldiers.Soldier;
import jdk.nashorn.api.tree.GotoTree;

import java.util.ArrayList;
import java.util.HashMap;

public class War {

    private Map warMap;

    private ArrayList<Soldier> freshSoldiers;

    private ArrayList<Soldier> deployedSoldiers;

    private HashMap<Resource, Integer> loot;

    private ArrayList<Building> villageThings;

    private boolean warIsOn;

    private int timePassed ;


    private int aliveSoldiersNumber(){
        int soldierCounter = 0;

        if (deployedSoldiers != null)
            for (Soldier soldier : deployedSoldiers) {
            if (!soldier.isDestroyed())
                soldierCounter ++;

            }
        if (freshSoldiers != null)
            for (Soldier soldier : freshSoldiers) {
                if (!soldier.isDestroyed())
                    soldierCounter ++;

            }

        return soldierCounter;
    }

    public War(Map map, ArrayList<Soldier> freshSoldiers) {
        this.warIsOn = true;
        this.freshSoldiers = freshSoldiers;
        this.warMap = map;
        this.loot = new HashMap<>(0);
        loot.put(Resource.GOLD, 0);
        loot.put(Resource.ELIXIR, 0);
        loot.put(Resource.SCORE, 0);
        freshSoldiers = new ArrayList<>(0);
        deployedSoldiers = new ArrayList<>(0);
        this.timePassed = 0 ;
        villageThings = warMap.extractVillageThings();

    }

    public HashMap<Resource, Integer> getLoot() {
        return loot;
    }

    public void setLoot(HashMap<Resource, Integer> loot) {
        this.loot = loot;
    }

    public void setVillageThings(ArrayList<Building> villageThings) {
        this.villageThings = villageThings;
    }

    public ArrayList<Building> getVillageThings() {
        return villageThings;
    }

    public ArrayList<Soldier> getFreshSoldiers() {
        return freshSoldiers;
    }

    public ArrayList<Soldier> getDeployedSoldiers() {
        return deployedSoldiers;
    }

    public boolean isWarOn() {
        return warIsOn;
    }

    public void setWarIsOn(boolean warIsOn) {
        this.warIsOn = warIsOn;
    }

    public Map getWarMap() {
        return warMap;
    }

    public  void deploySoldier(Class soldierType, Point position) throws InvalidSoldier, InvalidPoint {

        Soldier deployingSoldier = null;

        if (!position.canDeployArmyHere() || Map.outOfMap(position.getHeight(), position.getWidth()))
            throw new InvalidPoint();

        if (freshSoldiers == null)
            throw new InvalidSoldier();

        for (Soldier reservedSoldier : freshSoldiers) {
            if (reservedSoldier.getClass().equals(soldierType)) {
                deployingSoldier = reservedSoldier;
                break;
            }

        }

        if (deployingSoldier == null) {
            throw new InvalidSoldier();
        }

        if ((warMap.getCell(position).getSoldiersJustDeployedNumber() >= 5)) {
            throw new InvalidPoint();
        }

        deployingSoldier.setPosition(position);
        freshSoldiers.remove(deployingSoldier);
        warMap.locate(deployingSoldier);
        deployedSoldiers.add(deployingSoldier);
        warMap.getCell(position).setSoldiersJustDeployedNumber(warMap.getCell(position).getSoldiersJustDeployedNumber() + 1);

    }

    public void passBattleTime() {

        timePassed ++;

        for (Soldier deployedSoldier : deployedSoldiers) {
            if (!deployedSoldier.isDestroyed()) {    // can be deleted if destroyed things are removed from arrayLists.
                deployedSoldier.passOneTurn(getWarMap());
            }
        }
        for (Building villageThing : villageThings) {
            if (!villageThing.isDestroyed()) {     // can be deleted if destroyed things are removed from arrayLists.
                villageThing.passOneTurn(getWarMap());
            }

        }

        for (Entity villageThings : villageThings) {
            if (villageThings.getHealth() <= 0)
                villageThings.setDestroyed(true);
        }

        for (Soldier deployedSoldier : deployedSoldiers) {
            if (deployedSoldier.getHealth() <= 0)
                deployedSoldier.setDestroyed(true);

        }

        killTheDead();

        if (isWarFinished())
            warIsOn = false;
    }


    public boolean isWarFinished() {

        if (aliveSoldiersNumber() == 0)
            return true;

        if (timePassed >= 10000)
            return true;

        for (Building villageBuilding : villageThings) {
            if (villageBuilding.getClass().equals(TownHall.class))
                if (villageBuilding.isDestroyed())
                    return true;
        }

        return false;

    }

    public void killTheDead(){

        ArrayList<Entity> toKill = new ArrayList(0);
        for (Building villageThing : villageThings) {
            if (getVillageThings().size() == 1)
                break;
            if (!villageThing.isDestroyed())
                continue;

            loot.replace(Resource.GOLD, getLoot().get(Resource.GOLD) + villageThing.getBuildingCost());
            loot.replace(Resource.SCORE, getLoot().get(Resource.SCORE) + villageThing.getHitScore());

            if (villageThing.getClass().equals(Storage.class))
                loot.replace(((Storage) villageThing).getResource(),
                        getLoot().get(((Storage) villageThing).getResource()) + ((Storage) villageThing).getCurrentCapacity());
            toKill.add(villageThing);

        }

        for (Entity entity : toKill) {
            villageThings.remove(entity);

        }
        toKill.clear();

        if (villageThings.size() == 1){
            if (!villageThings.get(0).isDestroyed())
                return;

            loot.replace(Resource.GOLD, getLoot().get(Resource.GOLD) + villageThings.get(0).getBuildingCost());

            if (villageThings.getClass().equals(Storage.class))
                loot.replace(((Storage) villageThings.get(0)).getResource(),
                        getLoot().get(((Storage) villageThings.get(0)).getResource()) + ((Storage) villageThings.get(0)).getCurrentCapacity());

            getVillageThings().remove(villageThings.get(0));

        }
    }

}