package Models.Buildings.DefensiveBuildings;

import Functions.Functions;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Map;
import Models.Village;

public class Wall extends Building {


    public Wall( ) {

        setTypeCode( 12 );

        setMaxHealth(100);
        setHealth( getMaxHealth() );

        setLevel( 0 ) ;
        setHitScore(1) ;
        setSize(1);

        upgradeCost = 100 ;  //  gold
        upgradeTime = 20;  // delta T

        buildingCost = 100 ;  // gold
        buildingTime = 20 ;
    }


    public  void startConstructing (  Village village , int x , int y ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                village.getWalls().add(this);
                village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                new Functions().decreaseGold( village , this.getBuildingCost() );

                break;
            }
        }
    }

    @Override
    public void passOneTurn(Map map) {

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
    public void upgrade() {

        this.setLevel( this.getLevel() + 1 );
        this.setMaxHealth( this.getMaxHealth() + 10 );
        this.setHealth( this.getMaxHealth() );
        setOnUpgrade(false);
        setActive(true);
    }
}
