package Models.Buildings.TownBuildings;

import Functions.Functions;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Map;
import Models.Village;

import java.util.ArrayList;

public class TownHall extends Building {

    public TownHall( ) {

        setTypeCode( 5 ) ;
        labors.add( new Labor() ) ;
        setMaxHealth(1000);
        setHealth( getMaxHealth() ) ;
        setHitScore( 8 ) ;
        setSize(2);

        goldStorage.setCurrentCapacity(10000);
        goldStorage.setMaxCapacity(10000);

        elixirStorage.setMaxCapacity(5000);
        elixirStorage.setCurrentCapacity(1000);

        buildingTime = 100 ;
        upgradeCost = 500 ; // gold
        upgradeTime = 100 ;

        setOnConstruct(false);

    }

    private int score = 0 ;

    private ArrayList < Labor > labors = new ArrayList<>() ;

    private ArrayList < Building > inLineConstructions = new ArrayList<>() ;

    private Storage goldStorage = new Storage("gold") ;
    private Storage elixirStorage = new Storage("elixir") ;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Labor> getLabors() {
        return labors;
    }

    public void setLabors(ArrayList<Labor> labors) {
        this.labors = labors;
    }

    public ArrayList<Building> getInLineConstructions() {
        return inLineConstructions;
    }

    public void setInLineConstructions(ArrayList<Building> inLineConstructions) {
        this.inLineConstructions = inLineConstructions;
    }


    public Storage getGoldStorage() {
        return goldStorage;
    }

    public void setGoldStorage(Storage goldStorage) {
        this.goldStorage = goldStorage;
    }

    public Storage getElixirStorage() {
        return elixirStorage;
    }

    public void setElixirStorage(Storage elixirStorage) {
        this.elixirStorage = elixirStorage;
    }



    @Override
    public void passOneTurn(Map map) {

    }

    public void startUpGrading ( Village village) {

        this.setOnUpgrade(true);
        for( Labor l : labors ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                this.getInLineConstructions().add(this) ;
                new Functions().decreaseGold( village , this.getUpgradeCost() );
                setOnUpgrade(true);
                setActive(false);
                return;
            }
        }

        System.out.println("You don’t have any worker to upGrade this building.");
    }

    @Override
    public void upgrade() {

        this.setLevel( this.getLevel() + 1 );
        setMaxHealth( getMaxHealth() + 500 ) ;
        setHealth ( getMaxHealth() ) ;
        labors.add( new Labor() ) ;
        this.setOnUpgrade(false);
        setOnUpgrade(false);
        setActive(true);
    }


}
