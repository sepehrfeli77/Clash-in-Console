package Models.Buildings.TownBuildings;

import Enums.Resource;
import Functions.Functions;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Map;
import Models.Village;

public class Storage extends Building {

    public Storage( String resource ) {

        if ( resource.equals("gold") ) {

            setResource( Resource.GOLD );
            setMaxCapacity( 500 );
            setTypeCode( 3 );
            buildingTime = 200 ;
            upgradeTime = buildingTime;

        }

        else if( resource.equals("elixir") ) {

            setResource( Resource.ELIXIR );
            setMaxCapacity( 20 );
            setTypeCode( 4 );
            buildingTime = 100 ;
            upgradeTime = buildingTime;
        }

        upgradeCost = 100 ;
        buildingCost = 200 ;

        setMaxHealth(300);
        setHealth( getMaxHealth() );

        setLevel( 0 ) ;
        setHitScore(3);
        setSize(1);
    }

    private int maxCapacity ;
    private int currentCapacity = 0;

    private boolean isFull = false ;
    private Resource resource ;


    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getMaxCapacity() {
        return maxCapacity ;
    }

    public void setMaxCapacity ( int maxCapacity ) {
        this.maxCapacity = maxCapacity ;
    }

    public int getCurrentCapacity() {
        return currentCapacity ;
    }

    public void setCurrentCapacity ( int currentCapacity ) {
        this.currentCapacity = currentCapacity ;
    }

    public Resource getResource() {
        return resource ;
    }

    public void setResource ( Resource resource ) {
        this.resource = resource ;
    }


    public void increaseResource (Mine mine  , Village village) {

        if ( this.getMaxCapacity() - this.getCurrentCapacity() >= mine.getExtractionRate() ) {

            this.currentCapacity += mine.getExtractionRate() ;

            if( this.getResource() == Resource.GOLD ){

                village.getTotalGoldStorage().setCurrentCapacity( village.getTotalGoldStorage().getCurrentCapacity() + mine.getExtractionRate() ) ;
            }
            else if ( this.getResource() == Resource.ELIXIR ) {

                village.getTotalElixirStorage().setCurrentCapacity( village.getTotalElixirStorage().getCurrentCapacity() + mine.getExtractionRate() );
            }

        }

        else {

            this.currentCapacity = this.maxCapacity ;
            this.isFull = true ;

            if( this.getResource() == Resource.GOLD ){

                village.getTotalGoldStorage().setCurrentCapacity( village.getTotalGoldStorage().getMaxCapacity() + this.maxCapacity - this.currentCapacity  ) ;
            }
            else if ( this.getResource() == Resource.ELIXIR ) {

                village.getTotalElixirStorage().setCurrentCapacity( village.getTotalElixirStorage().getCurrentCapacity() + this.maxCapacity - this.currentCapacity );
            }
        }

    }

    public int decreaseResource ( int amount , Village village ) {

        if( this.currentCapacity > amount ) {

            this.currentCapacity -= amount ;

        }
        else {

            this.currentCapacity = 0 ;
        }

        this.isFull = false ;


        if( this.getResource() == Resource.GOLD ) {

            if( this.currentCapacity == 0 ) {

                village.getTotalGoldStorage().setCurrentCapacity( village.getTotalGoldStorage().getCurrentCapacity() - this.currentCapacity ) ;
                amount -= this.currentCapacity ;
            }
            else {

                village.getTotalGoldStorage().setCurrentCapacity( village.getTotalGoldStorage().getCurrentCapacity() - amount ) ;
                amount = 0 ;
            }
        }

        else if( this.getResource() == Resource.ELIXIR ) {

            if( this.currentCapacity == 0 ) {

                village.getTotalElixirStorage().setCurrentCapacity( village.getTotalElixirStorage().getCurrentCapacity() - this.currentCapacity ) ;
                amount -= this.currentCapacity ;
            }
            else {

                village.getTotalElixirStorage().setCurrentCapacity( village.getTotalElixirStorage().getCurrentCapacity() - amount ) ;
                amount = 0 ;
            }
        }

        return amount ;

    }

    public  void startConstructing (  Village village , int x , int y ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;

                if(this.resource == Resource.GOLD) {

                    village.getGoldStorages().add(this);
                    village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                    village.getTotalGoldStorage().setMaxCapacity( village.getTotalGoldStorage().getMaxCapacity() + this.getMaxCapacity() ); ;
                    new Functions().decreaseGold( village , this.getBuildingCost() );
                }

                else if( this.resource == Resource.ELIXIR ) {

                    village.getElixirStorages().add(this);
                    village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                    village.getTotalElixirStorage().setMaxCapacity( village.getTotalElixirStorage().getMaxCapacity() + this.getMaxCapacity() ); ;
                    new Functions().decreaseElixir( village , this.getBuildingCost() );
                }

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
        this.maxCapacity = this.maxCapacity * 16 / 10 ;
        setOnUpgrade(false);
        setActive(true);
    }

}
