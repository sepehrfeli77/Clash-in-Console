package Models.Buildings.TownBuildings;

import Functions.Functions;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Map;
import Models.Soldiers.Soldier;
import Models.Village;

import java.util.ArrayList;

public class Barrack extends Building{


    public Barrack( ) {

        setTypeCode(6) ;

        setMaxHealth(300) ;
        setHealth( getMaxHealth() ) ;

        setHitScore(1) ;
        setLevel(0) ;
        setSize(1);
        buildingCost = 200 ;
        buildingTime = 100 ;

        upgradeCost = 100 ;
        upgradeTime = 100 ;
    }

//    private HashMap < Soldier , Integer > inLineSoldiers ;
    private ArrayList < Soldier > lineOfSoldiers = new ArrayList<>() ;


//    public HashMap<Soldier, Integer> getInLineSoldiers() {
//        return inLineSoldiers;
//    }
//
//    public void setInLineSoldiers(HashMap<Soldier, Integer> inLineSoldiers) {
//        this.inLineSoldiers = inLineSoldiers;
//    }

    public ArrayList<Soldier> getLineOfSoldiers() {
        return lineOfSoldiers;
    }

    public void setLineOfSoldiers(ArrayList<Soldier> lineOfSoldiers) {
        this.lineOfSoldiers = lineOfSoldiers;
    }

    public  void startConstructing (  Village village , int x , int y ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                village.getBarracks().add(this);
                village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                new Functions().decreaseGold( village , this.getBuildingCost() );

                break;
            }
        }
    }

    public  void startUpGrading (  Village village  ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                new Functions().decreaseGold( village , this.getBuildingCost() );
                setOnUpgrade(true);
                setActive(false);
                break;
            }
        }
    }



    @Override
    public void passOneTurn(Map map) {

    }

    @Override
    public void upgrade() {

        this.setLevel( this.getLevel() + 1 );
        setOnUpgrade(false);
        setActive(true);
        setOnUpgrade(false);
        setActive(true);
    }
}
