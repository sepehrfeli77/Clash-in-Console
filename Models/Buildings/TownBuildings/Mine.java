package Models.Buildings.TownBuildings;

import Enums.Resource;
import Functions.Functions;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Map;
import Models.Village;

import java.util.HashMap;

import static Enums.Resource.*;

public class Mine extends Building {


    public Mine( String resource ) {


        if( resource.equals( "gold" ) ) {

            this.resource = GOLD ;
            this.extractionRate = 10 ;
            setTypeCode( 1 );
            mineBuildingCost.put( 150 , 5 ) ;
            buildingTime = 300 ; //delta T
            upgradeTime = buildingTime;
        }

        else if( resource.equals( "elixir" ) ) {

            this.resource = Resource.ELIXIR ;
            this.extractionRate = 5 ;
            setTypeCode( 2 );
            mineBuildingCost.put( 100 , 3 ) ;
            buildingTime = 100 ; //delta T.
            upgradeTime = buildingTime;
        }

        setMaxHealth( 200 ) ; // saz
        setHealth( getMaxHealth() ) ;

        upgradeCost = 100 ;
        setHitScore(2) ;  // two score will added to attacker if completely destroyed

        setLevel(0);
        this.isOff = false ;
        setSize(1);

    }

    private Resource resource ;
    private int capacity ;
    private int extractionRate ;
    private boolean isOff ;


    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getExtractionRate() {
        return extractionRate;
    }

    public void setExtractionRate(int extractionRate) {
        this.extractionRate = extractionRate;
    }

    public boolean isOff() {
        return isOff;
    }

    public void setOff(boolean off) {
        isOff = off;
    }


    public void startConstructing ( Village village , int x , int y ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                if(this.resource == Resource.GOLD) {

                    village.getGoldMines().add(this);
                    village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                }

                else if( this.resource == Resource.ELIXIR ) {

                    village.getElixirMines().add(this);
                    village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                }

                new Functions().decreaseBothResources( village , this.getMineBuildingCost() );

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
                new Functions().decreaseBothResources( village , this.getMineBuildingCost() );
                setOnUpgrade(true);
                setActive(false);
                break;
            }
        }
    }

    @Override
    public void upgrade() {

        this.setLevel( this.getLevel() + 1 );
        this.extractionRate = this.extractionRate * 16 / 10 ;
        setOnUpgrade(false);
        setActive(true);

    }
}
