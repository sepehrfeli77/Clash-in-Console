package Functions;

import Enums.Resource;
import Models.*;
import Models.Buildings.Building;
import Models.Buildings.DefensiveBuildings.*;
import Models.Buildings.TownBuildings.Barrack;
import Models.Buildings.TownBuildings.Camp;
import Models.Buildings.TownBuildings.Mine;
import Models.Buildings.TownBuildings.Storage;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {


    public void skipForward (Village village , int deltaT ) {

        for( int i = 0 ; i < deltaT ; i++ ){

            village.upDateVillage();
        }
    }

    public void checkConstructing ( Village village , Scanner scanner  , String buildingKind ) {

        Map:
        while(true) {

            for(int line = 0 ; line < 30 ; line++) {
                for( int col = 0 ; col <30 ; col++ ) {

                    if( village.getMap().getCells()[line][col] .isEmptyOnGround() ) {

                        if ( line==0 || col==0 || line==29 || col==29 ) {
                            System.out.print(0);
                        }
                        else if((line == 14 && col == 15) || (line == 15 && col == 15) || (line == 15 && col == 14)){
                            System.out.print(0);
                        }
                        else
                            System.out.print(1);
                    }

                    else
                        System.out.print(0);
                }
                System.out.println();
            }

            String point = scanner.next() ;

            village.upDateVillage( ) ;

            point = point.replace("(" , "") ;
            point = point.replace(")" , "") ;
            String[] arr = point.split(",") ;
            int x = Integer.parseInt(arr[0]) - 1 ;
            int y = Integer.parseInt(arr[1]) - 1 ;


            if( !village.getMap().getCells()[y][x] .isEmptyOnGround()) {

                System.out.println("You can’t build this building here.Please choose another cell.");
                continue Map;
            }

            else if (village.getMap().getCells()[y][x] .isEmptyOnGround()) {

                if( buildingKind.equals("Barrack") ){

                    new Barrack() .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("Camp") ){

                    new Camp() .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("goldStorage") ){

                    new Storage("gold") .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("elixirStorage") ){

                    new Storage("elixir") .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("goldMine") ){

                    new Mine("gold") .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("elixirMine") ){

                    new Mine("elixir") .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("archerTower") ){

                    new ArcherTower() .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("Cannon") ){

                    new Cannon() .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("airDefense") ){

                    new AirDefense().startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("wizardTower") ){

                    new WizardTower().startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("Wall") ){

                    new Wall().startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                else if( buildingKind.equals("Trap") ){

                    new Trap() .startConstructing( village , x , y );
                    System.out.println("You have entered AvailableBuildings");
                    return;
                }

                return;
            }
        }
    }

    public void decreaseGold ( Village village , int amount ) {

        ArrayList ar = new ArrayList() ;

        for(Storage storage : village.getGoldStorages()){

            ar.add(storage.getCurrentCapacity());
        }

        Collections.sort(ar);

        for( int i = ar.size()-1 ; i>=0 ; i-- ){

            for( Storage storage : village.getGoldStorages() ){

                if( (int) ar.get(i) == storage.getCurrentCapacity() ) {

                    amount = storage.decreaseResource( amount , village );

                    if(amount == 0){
                        return;
                    }
                }
            }
        }

        if( amount != 0 ) {

            amount = village.getTownhall().getGoldStorage().decreaseResource( amount , village );
        }
    }

    public void decreaseElixir ( Village village , int amount ) {

        ArrayList ar = new ArrayList() ;

        for(Storage storage : village.getElixirStorages()){

            ar.add(storage.getCurrentCapacity());
        }

        Collections.sort(ar);

        for( int i = ar.size()-1 ; i>=0 ; i-- ){

            for( Storage storage : village.getElixirStorages() ){

                if( ar.get(i) .equals( storage.getCurrentCapacity() )) {

                    amount = storage.decreaseResource( amount , village );

                    if(amount == 0){
                        return;
                    }
                }
            }
        }

        if( amount != 0 ) {

            amount = village.getTownhall().getElixirStorage().decreaseResource( amount , village );
        }
    }

    public void decreaseBothResources (Village village , HashMap hashMap) {

        Set<Integer> keyset = hashMap.keySet();
        for( Integer i : keyset ){

            decreaseGold( village , i );
            decreaseElixir( village ,(int) hashMap.get(i) );
        }
    }


}