package Functions;

import Enums.Resource;
import Json.ReadMap;
import Models.Buildings.Building;
import Models.Buildings.TownBuildings.Barrack;
import Models.Buildings.TownBuildings.Camp;
import Models.Buildings.TownBuildings.Mine;
import Models.Buildings.TownBuildings.Storage;
import Models.Labor;
import Models.Position.Map;
import Models.Soldiers.*;
import Models.Village;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {


    public void townHallMenu (Village village , Scanner scanner ) {

        townHalMenu:
        while ( true ) {

            System.out.println("1. Info\n2. Available buildings\n3. Status\n4. Back");

            int input = scanner.nextInt() ;

            if( input == 1 ) { //Info

                village.upDateVillage( ) ;

                TownHallInfo:
                while ( true ){

                    System.out.println("1. Overall info\n2. Upgrade info\n3. Back");

                    int infoInput = scanner.nextInt() ;

                    if( infoInput == 1 ) {

                        village.upDateVillage( ) ;

                        System.out.println( "Level: " + village.getTownhall().getLevel());
                        System.out.println( "Health: " + village.getTownhall().getHealth() );
                        System.out.println("\n1. Back");

                        String back = scanner.next();
                        if(back.equals("1")){

                            village.upDateVillage( ) ;
                            continue townHalMenu;
                        }

                    }

                    else if( infoInput == 2 ) {

                        village.upDateVillage( ) ;

                        System.out.println("Upgrade cost: 500 Gold");
                        System.out.println("\n1. Upgrade\n2.Back");

                        String upgradeInput = scanner.next();

                        if( upgradeInput.equals("2") ){

                            village.upDateVillage( ) ;
                            continue townHalMenu;
                        }

                        else if( upgradeInput .equals( "1" ) ) {

                            village.upDateVillage( ) ;

                            System.out.println("Do you want to upgradeTownHall for 500 golds? [Y/N]");

                            upgradeInput = scanner.next();

                            if( upgradeInput .equals("N") ) {

                                village.upDateVillage( ) ;
                                continue townHalMenu;
                            }

                            else if( upgradeInput .equals("Y") ) {

                                village.upDateVillage( ) ;

                                if( village.getTotalGoldStorage().getCurrentCapacity() < 500 ) {

                                    System.out.println("You don’t have enough resources.");
                                    continue townHalMenu;
                                }

                                else {

                                    village.getTownhall().startUpGrading( village);
                                }
                            }
                        }
                    }

                    else if( infoInput == 3 ) {

                        village.upDateVillage( ) ;
                        continue townHalMenu;
                    }
                }
            }

            else if( input == 2 ) { // Available buildings

                village.upDateVillage( ) ;

                AvailableBuilding:
                while ( true ) {

                    int i = 1 ;
                    ArrayList<String> availables = new ArrayList<>() ;

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 300 ) {

                        System.out.println(i + ". Air Defense");
                        availables.add("airDefense") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 300 ) {

                        System.out.println(i + ". Archer Tower");
                        availables.add("archerTower") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 200 ) {

                        System.out.println(i + ". Barrack");
                        availables.add("Barrack") ;
                        i++;
                    }


                    if(village.getTotalGoldStorage().getCurrentCapacity() >= 200 ) {

                        System.out.println(i + ". Camp");
                        availables.add("Camp");
                        i++;

                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 400 ) {

                        System.out.println(i + ". Cannon");
                        availables.add("Cannon") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 100 && village.getTotalElixirStorage().getCurrentCapacity() >= 3 ) {
                        System.out.println(i + ". Elixir mine");
                        availables.add("elixirMine") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 200 ) {
                        System.out.println(i + ". Elixir storage");
                        availables.add("elixirStorage") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 150 && village.getTotalElixirStorage().getCurrentCapacity() >= 5 ) {
                        System.out.println(i + ". Gold mine");
                        availables.add("goldMine") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 200 ) {
                        System.out.println(i + ". Gold storage");
                        availables.add("goldStorage") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 100 ) {

                        System.out.println(i + ". Trap");
                        availables.add("Trap") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 100 ) {

                        System.out.println(i + ". Wall");
                        availables.add("Wall") ;
                        i++;
                    }

                    if( village.getTotalGoldStorage().getCurrentCapacity() >= 500 ) {

                        System.out.println(i + ". Wizard Tower");
                        availables.add("wizardTower") ;
                        i++;
                    }

                    System.out.println("\n" + i + ". Back");
                    availables.add("back");


                    int availableBuildingsInput = scanner.nextInt() ;

                    if( availables.get(availableBuildingsInput - 1).equals("back") ){

                        village.upDateVillage( ) ;
                        continue townHalMenu ;
                    }


                    else if( availables.get(availableBuildingsInput - 1).equals("airDefense") ) {

                        village.upDateVillage( ) ;

                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Air Defense for 300 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "airDefense" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("archerTower") ) {

                        village.upDateVillage( ) ;

                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Archer Tower for 300 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "archerTower" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("Barrack") ) {

                        village.upDateVillage( ) ;

                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Barrack for 200 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "Barrack" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("Camp") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Camp for 200 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "Camp" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("Cannon") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Cannon for 400 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "Cannon" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("goldStorage") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Gold Storage for 200 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "goldStorage" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("elixirStorage") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Elixir Storage for 200 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "elixirStorage" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("elixirMine") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Elixir Mine for 100 gold and 3 elixir? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "elixirMine" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("goldMine") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Elixir Mine for 150 gold and 5 elixir? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "goldMine" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("Trap") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Trap for 100 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "Trap" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("Wall") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Wall for 100 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "Wall" );
                            }
                        }
                    }

                    else if( availables.get(availableBuildingsInput - 1).equals("wizardTower") ) {

                        village.upDateVillage( ) ;
                        boolean hasFreeLabor = false ;

                        for(Labor l : village.getTownhall().getLabors() ) {
                            if(l.isFree() == true) {
                                hasFreeLabor = true ;
                                break ;
                            }
                        }

                        if( hasFreeLabor == false ) {

                            System.out.println("You don’t have any worker to build this building.");
                        }

                        else {

                            System.out.println("Do you want to build Wizard Tower for 500 gold? [Y/N]" ) ;

                            String barrackBuild = scanner.next();
                            if( barrackBuild.equals("N") ) {

                                village.upDateVillage( ) ;
                                continue AvailableBuilding ;
                            }

                            else if( barrackBuild.equals("Y") ){

                                village.upDateVillage( ) ;
                                new Functions().checkConstructing( village , scanner , "wizardTower" );
                            }
                        }
                    }
                }
            }

            else if( input == 3 ) { // status

                village.upDateVillage( ) ;

                for(Building b : village.getTownhall().getInLineConstructions()) {
                    if (b.getBuildingTime() == 0)
                        continue ;
//                    String[] array = b.getClass().getName() .split("[.]");
//                    System.out.println( array[3] + " " + b.getBuildingTime() ) ;

                    System.out.println( b.getClass().getSimpleName() + " " + b.getBuildingTime() );
                }

                System.out.println("\n1. Back");

                int statusInput = scanner.nextInt();
                if(statusInput == 1){

                    village.upDateVillage( ) ;
                    continue townHalMenu;
                }
            }

            else if( input == 4 ) {

                village.upDateVillage( ) ;

                System.out.println("You have entered village");
                return;
            }
        }
    }

    public void barrackMenu (Village village , Scanner scanner , Barrack barrack ) {

        BarrackMenu:
        while( true ) {

            System.out.println("1. Info\n2. Build soldiers\n3. Status\n\n4. Back");
            int input = scanner.nextInt() ;

            if( input == 1 ) { // info

                village.upDateVillage( ) ;

                BarrackInfo:
                while ( true ){

                    System.out.println("1. Overall info\n2. Upgrade info\n3. Back");

                    int infoInput = scanner.nextInt() ;

                    if( infoInput == 1 ) {

                        village.upDateVillage( ) ;

                        System.out.println( "Level: " + barrack.getLevel());
                        System.out.println( "Health: " + barrack.getHealth() );
                        System.out.println("\n1. Back");

                        String back = scanner.next();
                        if(back.equals("1")){

                            village.upDateVillage( ) ;
                            continue BarrackInfo;
                        }
                    }

                    else if( infoInput == 2 ) {

                        village.upDateVillage( ) ;

                        System.out.println("Upgrade cost: 100 Gold");
                        System.out.println("\n1. Upgrade\n2.Back");

                        String upgradeInput = scanner.next();

                        if( upgradeInput.equals("2") ){

                            village.upDateVillage( ) ;
                            continue BarrackMenu;
                        }

                        else if( upgradeInput .equals( "1" ) ) {

                            village.upDateVillage( ) ;

                            if( barrack.getLevel() == village.getTownhall().getLevel() ){

                                System.out.println("You cant upgrade your barrack because it's level is equals to townHall");
                                continue BarrackMenu;
                            }

                            else{

                                System.out.println("Do you want to upgrade Barrack for 100 golds? [Y/N]");

                                upgradeInput = scanner.next();

                                if( upgradeInput .equals("N") ) {

                                    village.upDateVillage( ) ;
                                    continue BarrackMenu;
                                }

                                else if( upgradeInput .equals("Y") ) {

                                    village.upDateVillage( ) ;

                                    if( village.getTotalGoldStorage().getCurrentCapacity() < 100 ) {

                                        System.out.println("You don’t have enough resources.");
                                        continue BarrackMenu;
                                    }

                                    else {

                                        barrack.startUpGrading(village);
                                    }
                                }
                            }


                        }
                    }

                    else if( infoInput == 3 ) {

                        village.upDateVillage( ) ;
                        continue BarrackMenu;
                    }
                }
            }

            else if( input == 2 ) { // build soldiers

                village.upDateVillage( ) ;

                BuildSoldier:
                while(true) {

                    int i = 1 ;

                    if( village. getTotalElixirStorage(). getCurrentCapacity() >= 75 ){

                        System.out.println( i + ". Archer A x"+ (int) (village. getTotalElixirStorage(). getCurrentCapacity() / 75) );
                    }
                    else
                        System.out.println( i + ". Archer U" );
                    i++;


                    if( village. getTotalElixirStorage(). getCurrentCapacity() >= 200 ){

                        System.out.println( i + ". Dragon A x"+ (int) (village. getTotalElixirStorage(). getCurrentCapacity() / 200) );
                    }
                    else
                        System.out.println( i + ". Dragon U" );
                    i++;


                    if( village. getTotalElixirStorage(). getCurrentCapacity() >= 150 ){

                        System.out.println( i + ". Giant A x"+ (int) (village. getTotalElixirStorage(). getCurrentCapacity() / 150) );
                    }
                    else
                        System.out.println( i + ". Giant U" );
                    i++;


                    if( village. getTotalElixirStorage(). getCurrentCapacity() >= 50 ){

                        System.out.println( i + ". Guardian A x"+ (int) (village. getTotalElixirStorage(). getCurrentCapacity() / 50) );
                    }
                    else
                        System.out.println( i + ". Guardian U" );
                    i++;

                    if( village. getTotalElixirStorage(). getCurrentCapacity() >= 175 ){

                        System.out.println( i + ". Healer A x"+ (int) (village. getTotalElixirStorage(). getCurrentCapacity() / 175) );
                    }
                    else
                        System.out.println( i + ". Healer U" );
                    i++;


                    if( village. getTotalElixirStorage(). getCurrentCapacity() >= 40 ){

                        System.out.println( i + ". Wall Breaker A x"+ (int) (village. getTotalElixirStorage(). getCurrentCapacity() / 40) );
                    }
                    else
                        System.out.println( i + ". Wall Breaker U" );
                    i++;

                    System.out.println( i + ". Back" );


                    int currentCapacity = 0;
                    int maxCapacity = 0 ;

                    for( Camp c : village.getCamps() ) {

                        currentCapacity += c.getCurrentCapacity() ;
                    }


                    int soldierInput = scanner.nextInt() ;

                    if( soldierInput == 1 ) { // Archer

                        village.upDateVillage( ) ;

                        if( village. getTotalElixirStorage(). getCurrentCapacity() < 75 ) {

                            System.out.println("You can’t build this soldier.");
                        }

                        else {

                            System.out.println("How many of this soldier do you want to build?");

                            int numOfSoldier = scanner.nextInt();

                            village.upDateVillage( ) ;

                            if( numOfSoldier*75 > village. getTotalElixirStorage(). getCurrentCapacity() ) {

                                System.out.println("You don’t have enough resources.");
                                continue BarrackMenu;
                            }

                            else {

                                if( currentCapacity < numOfSoldier ) {

                                    System.out.println("Your camps dont have enugh space.");
                                    continue BarrackMenu;
                                }

                                for( int j = 1 ; j<=numOfSoldier ; j++ ){

                                    barrack.getLineOfSoldiers().add(new Archer()) ;
                                    new Functions().decreaseElixir(village , new Archer().getBuildingCost());
                                }
                            }
                        }
                    }

                    else if( soldierInput == 2 ) { // Dragon

                        village.upDateVillage( ) ;

                        if( village. getTotalElixirStorage(). getCurrentCapacity() < 200 ) {

                            System.out.println("You can’t build this soldier.");
                        }

                        else {

                            System.out.println("How many of this soldier do you want to build?");

                            int numOfSoldier = scanner.nextInt();

                            village.upDateVillage( ) ;

                            if( numOfSoldier*200 > village. getTotalElixirStorage(). getCurrentCapacity() ) {

                                System.out.println("You don’t have enough resources.");
                                continue BuildSoldier;
                            }

                            else {

                                if( currentCapacity < numOfSoldier ) {

                                    System.out.println("Your camps dont have enugh space.");
                                    continue BarrackMenu;
                                }

                                for( int j = 1 ; j<=numOfSoldier ; j++ ){

                                    barrack.getLineOfSoldiers().add(new Dragon()) ;
                                    new Functions().decreaseElixir(village , new Dragon().getBuildingCost());
                                }
                            }
                        }
                    }

                    if( soldierInput == 3 ) { // Giant

                        village.upDateVillage( ) ;

                        if( village. getTotalElixirStorage(). getCurrentCapacity() < 150 ) {

                            System.out.println("You can’t build this soldier.");
                        }

                        else {

                            System.out.println("How many of this soldier do you want to build?");

                            int numOfSoldier = scanner.nextInt();

                            village.upDateVillage( ) ;

                            if( numOfSoldier*150 > village. getTotalElixirStorage(). getCurrentCapacity() ) {

                                System.out.println("You don’t have enough resources.");
                                continue BuildSoldier;
                            }

                            else {

                                if( currentCapacity < numOfSoldier ) {

                                    System.out.println("Your camps dont have enugh space.");
                                    continue BarrackMenu;
                                }

                                for( int j = 1 ; j<=numOfSoldier ; j++ ){

                                    barrack.getLineOfSoldiers().add(new Giant()) ;
                                    new Functions().decreaseElixir(village , new Giant().getBuildingCost());
                                }
                            }
                        }
                    }

                    if( soldierInput == 4 ) { // Guardean

                        village.upDateVillage( ) ;

                        if( village. getTotalElixirStorage(). getCurrentCapacity() < 50 ) {

                            System.out.println("You can’t build this soldier.");
                        }

                        else {

                            System.out.println("How many of this soldier do you want to build?");

                            int numOfSoldier = scanner.nextInt();

                            village.upDateVillage( ) ;

                            if( numOfSoldier*50 > village. getTotalElixirStorage(). getCurrentCapacity() ) {

                                System.out.println("You don’t have enough resources.");
                                continue BuildSoldier;
                            }

                            else {

                                if( currentCapacity < numOfSoldier ) {

                                    System.out.println("Your camps dont have enugh space.");
                                    continue BarrackMenu;
                                }

                                for( int j = 1 ; j<=numOfSoldier ; j++ ){

                                    barrack.getLineOfSoldiers().add(new Guardian()) ;
                                    new Functions().decreaseElixir(village , new Guardian().getBuildingCost());
                                }
                            }
                        }
                    }

                    if( soldierInput == 5 ) { // Healer

                        village.upDateVillage( ) ;

                        if( village. getTotalElixirStorage(). getCurrentCapacity() < 175 ) {

                            System.out.println("You can’t build this soldier.");
                        }

                        else {

                            System.out.println("How many of this soldier do you want to build?");

                            int numOfSoldier = scanner.nextInt();

                            village.upDateVillage( ) ;

                            if( numOfSoldier*175 > village. getTotalElixirStorage(). getCurrentCapacity() ) {

                                System.out.println("You don’t have enough resources.");
                                continue BuildSoldier;
                            }

                            else {

                                if( currentCapacity < numOfSoldier ) {

                                    System.out.println("Your camps dont have enugh space.");
                                    continue BarrackMenu;
                                }

                                for( int j = 1 ; j<=numOfSoldier ; j++ ){

                                    barrack.getLineOfSoldiers().add(new Healer()) ;
                                    new Functions().decreaseElixir(village , new Healer().getBuildingCost());
                                }
                            }
                        }
                    }

                    if( soldierInput == 6 ) { // wall breaker

                        village.upDateVillage( ) ;

                        if( village. getTotalElixirStorage(). getCurrentCapacity() < 40 ) {

                            System.out.println("You can’t build this soldier.");
                        }

                        else {

                            System.out.println("How many of this soldier do you want to build?");

                            int numOfSoldier = scanner.nextInt();

                            village.upDateVillage( ) ;

                            if( numOfSoldier*40 > village. getTotalElixirStorage(). getCurrentCapacity() ) {

                                System.out.println("You don’t have enough resources.");
                                continue BuildSoldier;
                            }

                            else {

                                if( currentCapacity < numOfSoldier ) {

                                    System.out.println("Your camps dont have enugh space.");
                                    continue BarrackMenu;
                                }

                                for( int j = 1 ; j<=numOfSoldier ; j++ ){

                                    barrack.getLineOfSoldiers().add(new WallBreaker()) ;
                                    new Functions().decreaseElixir(village , new WallBreaker().getBuildingCost());
                                }

                                continue BarrackMenu;
                            }
                        }
                    }

                    else if( soldierInput == 7 ) {

                        village.upDateVillage( ) ;
                        continue BarrackMenu;
                    }

                }

            }

            else if( input == 3 ) { // status

                village.upDateVillage( ) ;



                for( Soldier soldier : barrack.getLineOfSoldiers() ) {

                    if( soldier.getBuildingCost() == 50 ) {
                        System.out.println("Guardian " + soldier.getBuildingTime());
                    }

                    else if( soldier.getBuildingCost() == 150 ) {
                        System.out.println("Giant " + soldier.getBuildingTime());
                    }

                    else if( soldier.getBuildingCost() == 200 ) {
                        System.out.println("Dragon " + soldier.getBuildingTime());
                    }

                    else if( soldier.getBuildingCost() == 75 ) {
                        System.out.println("Archer " + soldier.getBuildingTime());
                    }

                    else if( soldier.getBuildingCost() == 40 ) {
                        System.out.println("Wall breaker " + soldier.getBuildingTime());
                    }

                    else if( soldier.getBuildingCost() == 175 ) {
                        System.out.println("Healer " + soldier.getBuildingTime());
                    }


                }

                continue BarrackMenu;
            }

            else if( input == 4 ) {

                village.upDateVillage( ) ;
                System.out.println("You have entered village");
                return ;
            }
        }
    }

    public void campMenu ( Village village , Scanner scanner , Camp camp ) {

        CampMenu:
        while(true) {

            System.out.println("1.Info\n2. Soldiers\n\n3. Back");

            int input = scanner.nextInt() ;

            if( input == 1 ) { // info

                village.upDateVillage( ) ;

                CampInfo:
                while ( true ){

                    System.out.println("1. Overall info\n2. Upgrade info\n3. Capacity info\n\n4. Back");

                    int infoInput = scanner.nextInt() ;

                    if( infoInput == 1 ) { // overall info

                        village.upDateVillage( ) ;

                        System.out.println( "Level: " + camp.getLevel());
                        System.out.println( "Health: " + camp.getHealth() );
                        System.out.println("\n1. Back");

                        String back = scanner.next();
                        if(back.equals("1")){

                            village.upDateVillage( ) ;
                            continue CampInfo;
                        }
                    }

                    else if( infoInput == 2 ) { // upGrade info

                        village.upDateVillage( ) ;

                        System.out.println("you cant upGrade Camp");
                        System.out.println("\n1. Back");

                        String upgradeInput = scanner.next();

                        if( upgradeInput.equals("1") ){

                            village.upDateVillage( ) ;
                            continue CampInfo;
                        }
                    }

                    else if( infoInput == 3 ) { //capacity info

                        village.upDateVillage( ) ;

                        int currentCapacity = 0;
                        int maxCapacity = 0 ;

                        for( Camp c : village.getCamps() ) {

                            currentCapacity += c.getCurrentCapacity() ;
                            maxCapacity += c.getMaxCapacity() ;
                        }


                        System.out.println("Your camps capacity is " + currentCapacity +" / " + maxCapacity);

                        System.out.println("\n1. Back");

                        int capacityInput = scanner.nextInt();

                        if( capacityInput == 1 ) {

                            village.upDateVillage( ) ;
                            continue CampInfo;
                        }
                    }

                    else if( infoInput == 4 ) {

                        village.upDateVillage( ) ;
                        continue CampMenu;
                    }
                }
            }

            else if(  input == 2) { //soldiers

                village.upDateVillage( ) ;

                int guardian = 0;
                int giant = 0;
                int dragon =0;
                int archer =0;
                int wallBreaker =0;
                int healer =0;

                for( Soldier soldier : camp.getArmy() ) {

                    if( soldier.getBuildingCost() == 50 ) {
                        guardian++;
                    }

                    else if( soldier.getBuildingCost() == 150 ) {
                        giant++;
                    }

                    else if( soldier.getBuildingCost() == 200 ) {
                        dragon++;
                    }

                    else if( soldier.getBuildingCost() == 75 ) {
                        archer++;
                    }

                    else if( soldier.getBuildingCost() == 40 ) {
                        wallBreaker++;
                    }

                    else if( soldier.getBuildingCost() == 175 ) {
                        healer++;
                    }
                }

                if(archer != 0 ) {
                    System.out.println("Archer x" + archer);
                }

                if(dragon != 0 ) {
                    System.out.println("Dragon x" + dragon);
                }

                if(giant != 0 ) {
                    System.out.println("Giant x" + giant);
                }

                if(guardian != 0 ) {
                    System.out.println("Guardian x" + guardian);
                }

                if(healer != 0 ) {
                    System.out.println("Healer x" + healer);
                }

                if(wallBreaker != 0 ) {
                    System.out.println("Wall Breaker x" + wallBreaker);
                }
            }


            else if( input == 3 ) {

                village.upDateVillage( ) ;

                System.out.println("You have entered village");
                return ;
            }
        }

    }

    public void minesMenu (Village village , Scanner scanner , Mine mine) {

        MineMenu:
        while(true) {

            System.out.println("1. Info\n2. Mine\n\n3. Back");

            int mineMenuInput = scanner.nextInt() ;

            if( mineMenuInput == 1 ) {

                village.upDateVillage( ) ;

                MineInfo:
                while ( true ){

                    System.out.println("1. Overall info\n2. Upgrade info\n\n3. Back");

                    int infoInput = scanner.nextInt() ;

                    if( infoInput == 1 ) {

                        village.upDateVillage( ) ;

                        System.out.println( "Level: " + mine.getLevel());
                        System.out.println( "Health: " + mine.getHealth() );
                        System.out.println("\n1. Back");

                        String back = scanner.next();
                        if(back.equals("1")) {

                            village.upDateVillage();
                            continue MineInfo;
                        }
                    }

                    else if( infoInput == 2 ) {

                        village.upDateVillage( ) ;

                        System.out.println("Upgrade cost: 100 Gold");
                        System.out.println("\n1. Upgrade\n2.Back");

                        String upgradeInput = scanner.next();

                        if( upgradeInput.equals("2") ){

                            village.upDateVillage( ) ;
                            continue MineInfo;
                        }

                        else if( upgradeInput .equals( "1" ) ) {

                            village.upDateVillage( ) ;

                            if( mine.getLevel() == village.getTownhall().getLevel() ){

                                System.out.println("You can't upgrade your mine because it's level is equals to townHall");
                                continue MineMenu;
                            }

                            else {

                                System.out.println("Do you want to upgrade Mine for 100 golds? [Y/N]");

                                upgradeInput = scanner.next();

                                if( upgradeInput .equals("N") ) {

                                    village.upDateVillage( ) ;
                                    continue MineMenu;
                                }

                                else if( upgradeInput .equals("Y") ) {

                                    village.upDateVillage( ) ;

                                    if( village.getTotalGoldStorage().getCurrentCapacity() < 100 ) {

                                        System.out.println("You don’t have enough resources.");
                                        continue MineMenu;
                                    }

                                    else {

                                        mine.startUpGrading( village );
                                    }
                                }
                            }
                        }
                    }

                    else if( infoInput == 3 ) {

                        village.upDateVillage( ) ;
                        continue MineMenu;
                    }
                }
            }

            else if( mineMenuInput == 2 ) { // mine

                village.upDateVillage( ) ;

                System.out.println("this option is not handled");
            }

            else if( mineMenuInput == 3 ) {

                village.upDateVillage( ) ;

                System.out.println("You have entered village");
                return;
            }
        }
    }

    public void storagesMenu (Village village , Scanner scanner , Storage storage) {

        StorageMenu:
        while (true) {

            System.out.println("1. Info\n\n2. Back ");

            int storagemenuInput = scanner.nextInt();

            if( storagemenuInput == 1 ) {

                village.upDateVillage( ) ;

                StorageInfo:
                while(true) {

                    System.out.println("1. Overall info\n2. Upgrade info\n3. Sources info\n4. Upgrade\n\n5. Back");

                    int infoInput = scanner.nextInt() ;

                    if( infoInput == 1 ) {

                        village.upDateVillage( ) ;

                        System.out.println( "Level: " + storage.getLevel());
                        System.out.println( "Health: " + storage.getHealth() );
                        System.out.println("\n1. Back");

                        String back = scanner.next();
                        if(back.equals("1")) {

                            village.upDateVillage();
                            continue StorageInfo;
                        }
                    }

                    else if( infoInput == 2 ) { //upgrade info

                        village.upDateVillage( ) ;

                        System.out.println("Upgrade cost: 100 Gold");
                        System.out.println("\n1. Upgrade\n2.Back");

                        String upgradeInput = scanner.next();

                        if( upgradeInput.equals("2") ){

                            village.upDateVillage( ) ;
                            continue StorageInfo;
                        }

                        else if( upgradeInput .equals( "1" ) ) {

                            village.upDateVillage( ) ;

                            if( storage.getLevel() == village.getTownhall().getLevel() ){

                                System.out.println("You can't upgrade your storage because it's level is equals to townHall");
                                continue StorageMenu;
                            }

                            else {

                                System.out.println("Do you want to upgrade Storage for 100 golds? [Y/N]");

                                upgradeInput = scanner.next();

                                if( upgradeInput .equals("N") ) {

                                    village.upDateVillage( ) ;
                                    continue StorageMenu;
                                }

                                else if( upgradeInput .equals("Y") ) {

                                    village.upDateVillage( ) ;

                                    if( village.getTotalGoldStorage().getCurrentCapacity() < 100 ) {

                                        System.out.println("You don’t have enough resources.");
                                        continue StorageMenu;
                                    }

                                    else {

                                        storage.startUpGrading( village );
                                    }
                                }
                            }
                        }
                    }

                    else if(infoInput == 3){ //sources info

                        village.upDateVillage( ) ;

                        int currentCapacity = 0;
                        int maxCapacity = 0 ;

                        if(storage.getResource() == Resource.GOLD) {

                            for( Storage s : village.getGoldStorages()) {

                                currentCapacity += s.getCurrentCapacity() ;
                                maxCapacity += s.getMaxCapacity() ;
                            }

                            System.out.println("Your gold storages capacity is " + currentCapacity +" / " + maxCapacity);
                        }

                        else if(storage.getResource() == Resource.ELIXIR) {

                            for( Storage s : village.getElixirStorages()) {

                                currentCapacity += s.getCurrentCapacity() ;
                                maxCapacity += s.getMaxCapacity() ;
                            }

                            System.out.println("Your elixir storages capacity is " + currentCapacity +" / " + maxCapacity);
                        }


                        System.out.println("\n1. Back");

                        int capacityInput = scanner.nextInt();

                        if( capacityInput == 1 ) {

                            village.upDateVillage( ) ;
                            continue StorageInfo;
                        }

                    }

                    else if( infoInput == 4 ) { //upgrade

                        village.upDateVillage( ) ;

                        System.out.println("this key is not handled\nfor upgrading, please go to Upgrade info");

                        continue StorageInfo;
                    }

                    else if( infoInput == 5 ) {

                        village.upDateVillage( ) ;
                        continue StorageMenu;
                    }
                }
            }

            else if( storagemenuInput == 2 ) {

                village.upDateVillage( ) ;

                System.out.println("You have entered village");
                return;
            }
        }
    }

    public void defensiveBuildingMenu (Village village , Scanner scanner , Building building  ) {

        dbMenu:
        while ( true ) {

            System.out.println("1. Info\n2. Target\n\n3. Back");

            int dbinput = scanner.nextInt() ;

            if( dbinput== 1 ){ // info

                village.upDateVillage( ) ;

                dbInfo:
                while( true ){

                    System.out.println("1. Overall info\n2. Upgrade info\n3. Attack info\n\n4. Back");

                    int dbInfoInput = scanner.nextInt() ;

                    if( dbInfoInput == 1 ) { //overall info

                        village.upDateVillage( ) ;

                        System.out.println( "Level: " + building.getLevel());
                        System.out.println( "Health: " + building.getHealth() );
                        System.out.println("\n1. Back");

                        String back = scanner.next();
                        if(back.equals("1")) {

                            village.upDateVillage();
                            continue dbInfo;
                        }
                    }

                    else if( dbInfoInput == 2 ) { //upGrade info

                        village.upDateVillage( ) ;

                        System.out.println("Upgrade cost: " + building.getUpgradeCost() + " Gold");
                        System.out.println("\n1. Upgrade\n2.Back");

                        String upgradeInput = scanner.next();

                        if( upgradeInput.equals("2") ){

                            village.upDateVillage( ) ;
                            continue dbInfo;
                        }

                        else if( upgradeInput .equals( "1" ) ) {

                            village.upDateVillage( ) ;

                            if( building.getLevel() == village.getTownhall().getLevel() ){

                                System.out.println("You can't upgrade your building because it's level is equals to townHall");
                                continue dbMenu;
                            }

                            else {

                                System.out.println("Do you want to upgrade this building for " + building.getUpgradeCost()+ " golds? [Y/N]");

                                upgradeInput = scanner.next();

                                if( upgradeInput .equals("N") ) {

                                    village.upDateVillage( ) ;
                                    continue dbMenu;
                                }

                                else if( upgradeInput .equals("Y") ) {

                                    village.upDateVillage( ) ;

                                    if( village.getTotalGoldStorage().getCurrentCapacity() < building.getUpgradeCost() ) {

                                        System.out.println("You don’t have enough resources.");
                                        continue dbMenu;
                                    }

                                    else {

                                        building.startUpGrading( village );
                                    }
                                }
                            }
                        }
                    }

                    else if( dbInfoInput == 3 ) {// attack info

                        village.upDateVillage( ) ;
///////////////////////////TODO
                    }

                    else if( dbInfoInput == 4 ){

                        village.upDateVillage( ) ;
                       continue dbMenu;
                    }
                }
            }

            else if ( dbinput == 2 ) { //Target

                village.upDateVillage( ) ;
//////////////////////////TODO
            }

            else if( dbinput==3 ){ //back

                village.upDateVillage( ) ;

                System.out.println("You have entered village");
                return;
            }
        }
    }

    public Map attackMenu(Village village, Scanner scanner) {

        attackmenu:
        while(true) {

            System.out.println("1. Load map");

            int i = 2 ;
            // load attacked Map & i++

            System.out.println(i + ". Back");

            int mapNum = scanner.nextInt();

            if( mapNum == 1 ){

                village.upDateVillage();

                System.out.println("Enter map path: ");
                scanner.skip("\n");

                String newMapPath = scanner.nextLine();

                ReadMap readAttackMap = new ReadMap();

                try {
                    return readAttackMap.ReadAttackMap(newMapPath);

                } catch (IOException e) {
                    System.out.println("There is no valid file in this location");
                }

            }
            if (mapNum == 2){
                return null;
            }
        }
    }
}