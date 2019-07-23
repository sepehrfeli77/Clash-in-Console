package com.company;
import Controller.WarController;
import Functions.*;
import GameModes.War;
import Models.Buildings.TownBuildings.*;
import Models.Soldiers.Soldier;
import Models.Village;
import Models.Position.Map;
import View.WarView;
import Json.ReadMap;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Main {


    public static ArrayList<String> showBuildings ( Village village ){

        ArrayList <String> buildings = new ArrayList<>();
        int lastNumberOfBuilding = 1 ;


        for( int i = 1 ; i<= village.getAirDefenses().size() ; i++ ) {

            if( village.getAirDefenses().get(i-1) .isActive() ){

                System.out.println( lastNumberOfBuilding + ": Air Defense " + i) ;
                buildings.add("airDefense " + i);

                lastNumberOfBuilding++ ;
            }
        }

        for( int i = 1 ; i<= village.getArcherTowers().size() ; i++ ) {

            if( village.getArcherTowers().get(i-1) .isActive() ){

                System.out.println( lastNumberOfBuilding + ": Archer Tower " + i) ;
                buildings.add("archerTower " + i);

                lastNumberOfBuilding++ ;
            }
        }

        for( int i = 1 ; i<= village.getBarracks().size() ; i++ ) {

            if( village.getBarracks().get(i-1).isActive() ){

                System.out.println(lastNumberOfBuilding + ": Barrack " + i);
                buildings.add("Barrack " + i);
                lastNumberOfBuilding++;
            }
        }


        for( int i = 1 ; i<= village.getCamps().size() ; i++ ) {

            if( village.getCamps().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Camp " + i) ;
                buildings.add("Camp " + i);

                lastNumberOfBuilding++ ;
            }
        }

        for( int i = 1 ; i<= village.getCannons().size() ; i++ ) {

            if( village.getCannons().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Cannon " + i) ;
                buildings.add("Cannon " + i);

                lastNumberOfBuilding++ ;
            }
        }

        for( int i = 1 ; i<= village.getElixirMines().size() ; i++ ) {

            if( village.getElixirMines().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Elixir mine " + i) ;
                buildings.add("elixirMine " + i);
                lastNumberOfBuilding++ ;
            }
        }


        for(int i = 1; i<= village.getElixirStorages().size() ; i++ ) {

            if( village.getElixirStorages().get(i-1).isActive() ){

                System.out.println(lastNumberOfBuilding + ": Elixir storage " + i) ;
                buildings.add("elixirStorage " + i);
                lastNumberOfBuilding++ ;
            }
        }


        for( int i = 1 ; i<= village.getGoldMines().size() ; i++ ) {

            if( village.getGoldMines().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Gold mine " + i) ;
                buildings.add("goldMine " + i);
                lastNumberOfBuilding++ ;
            }
        }


        for(int i = 1; i<= village.getGoldStorages().size() ; i++ ) {

            if( village.getGoldStorages().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Gold storage " + i) ;
                buildings.add("goldStorage " + i);
                lastNumberOfBuilding++ ;
            }
        }

        System.out.println( lastNumberOfBuilding + ": Town hall");
        buildings.add("townHall");
        lastNumberOfBuilding++ ;

        for( int i = 1 ; i<= village.getTraps().size() ; i++ ) {

            if( village.getTraps().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Trap " + i) ;
                buildings.add("Trap " + i);

                lastNumberOfBuilding++ ;
            }
        }

        for( int i = 1 ; i<= village.getWalls().size() ; i++ ) {

            if( village.getWalls().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Wall " + i) ;
                buildings.add("Wall " + i);

                lastNumberOfBuilding++ ;
            }
        }

        for( int i = 1 ; i<= village.getWizardTowers().size() ; i++ ) {

            if( village.getWizardTowers().get(i-1).isActive() ){

                System.out.println( lastNumberOfBuilding + ": Wizard Tower " + i) ;
                buildings.add("wizardTower " + i);

                lastNumberOfBuilding++ ;
            }
        }

        System.out.println( lastNumberOfBuilding + ": Back") ;
        buildings.add("Back");

        return buildings;
    }

    public static void showTotalResources(Village village ) {

        System.out.println("Gold: " + village.getTotalGoldStorage().getCurrentCapacity());

        System.out.println("Elixir: " + village.getTotalElixirStorage().getCurrentCapacity());

        System.out.println("Score: " + village.getTownhall().getScore());

    }


    public static void main(String[] args) {

        Scanner  scanner = new Scanner( System.in ) ;
        Village village = new Village();

        System.out.println("1. New Game\n2. Load Game");

        int startGame = scanner.nextInt();
        if( startGame == 1 ) {

            village = new Village() ;
        }

        else  { //load game
            System.out.println("enter your file address");
            scanner.skip("\n");
            String gamePath = scanner.nextLine();
            System.out.println(gamePath);

            ReadMap loadMap = new ReadMap();
            try {
                village = loadMap.ReadOwnMap(gamePath);
            } catch (IOException e) {
                System.out.println("there is no valid file in this location");
            }


        }

        // new game
        String input ;

        main:
        while( true ) {
            input = scanner.nextLine() ;

            if( input.matches( "showBuildings" ) ) {

                village.upDateVillage( ) ;

                ArrayList<String> building = showBuildings( village );

                int buildingNum = scanner.nextInt() ;

                if( building.get(buildingNum-1) .equals("townHall") ){

                    village.upDateVillage( ) ;
                    new Menu().townHallMenu(village , scanner);
                }

                else if( building.get(buildingNum - 1) .matches("Barrack [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().barrackMenu(village , scanner , village.getBarracks().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("Camp [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().campMenu(village , scanner , village.getCamps().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("goldMine [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().minesMenu(village , scanner , village.getGoldMines().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("elixirMine [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().minesMenu(village , scanner , village.getElixirMines().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("goldStorage [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().storagesMenu(village , scanner , village.getGoldStorages().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("elixirStorage [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().storagesMenu(village , scanner , village.getGoldStorages().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("archerTower [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().defensiveBuildingMenu(village , scanner , village.getArcherTowers().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("Cannon [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().defensiveBuildingMenu(village , scanner , village.getCannons().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("airDefense [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().defensiveBuildingMenu(village , scanner , village.getAirDefenses().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("wizardTower [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().defensiveBuildingMenu(village , scanner , village.getWizardTowers().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("Wall [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().defensiveBuildingMenu(village , scanner , village.getWalls().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("Trap [\\d]+") ) {

                    village.upDateVillage( ) ;

                    String[] array = building.get(buildingNum - 1).split(" ") ;
                    new Menu().defensiveBuildingMenu(village , scanner , village.getTraps().get( Integer.parseInt(array[1]) -1 ));
                }

                else if( building.get(buildingNum - 1) .matches("Back") ) {

                    village.upDateVillage();
                    System.out.println("You have entered village");
                    continue main;
                }

            }

            else if( input.matches( "resources" ) ) {

                village.upDateVillage( ) ;
                showTotalResources( village );
            }

            else if ( input.matches( "turn [\\d]+" ) ) {

                String[] array = input.split(" ") ;
                int deltaT = Integer.parseInt( array[1] ) ;

                new Functions().skipForward( village , deltaT );

                continue ;
            }

            else if( input.matches( "showMenu" ) ) {

                village.upDateVillage( ) ;
                System.out.println("showBuildings\nresources\nattack");
            }

            else if ( input.matches("attack") ) {

                village.upDateVillage() ;

                Map warMap = new Map();

                warMap = new Menu().attackMenu( village , scanner);

                if (warMap == null)
                    break ;

                War war = new War(warMap, new ArrayList<Soldier>(0));
                WarController warController = new WarController(war, village);
                WarView warView = new WarView(scanner, warController);

                warView.manageWar();




            }

            else if( input.matches("save [a-zA-Z\\d./:]+") )
                break;
        }

        String[] parts = input.split(" ");
        String path = parts[1];

        ReadMap saveMap = new ReadMap();
        try {
            saveMap.Save(village,path);
        } catch (IOException e) {
            System.out.println("address is invalid");
        }

    }
}
