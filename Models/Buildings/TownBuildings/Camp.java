package Models.Buildings.TownBuildings;

import Functions.Functions;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Map;
import Models.Soldiers.Soldier;
import Models.Village;

import java.util.ArrayList;

public class Camp extends Building {


    public Camp( ) {

        setTypeCode(7) ;
        setMaxHealth(900) ;
        setHealth( getMaxHealth() ) ;
        setHitScore(1) ;
        setLevel(0) ;
        setSize(1);
        buildingCost = 200 ;
        buildingTime = 100 ;
    }

    private transient ArrayList <Soldier> army = new ArrayList<>();

    private int maxCapacity = 50 ;
    private int currentCapacity = 50 ;


    public ArrayList<Soldier> getArmy() {
        return army;
    }

    public void setArmy(ArrayList<Soldier> army) {
        this.army = army;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public  void startConstructing (  Village village , int x , int y ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                village.getCamps().add(this);
                village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                new Functions().decreaseGold( village , this.getBuildingCost() );
                break;
            }
        }
    }


    @Override
    public void passOneTurn(Map map) {

    }

    @Override
    public void upgrade() {

        // camp cant  upgraded
    }

    public void startUpGrading (Village village){

    }



}
