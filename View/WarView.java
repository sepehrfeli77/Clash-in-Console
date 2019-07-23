package View;

import Controller.WarController;
import Enums.Resource;
import Exceptions.InvalidInput;
import Exceptions.InvalidPoint;
import Exceptions.InvalidSoldier;
import Exceptions.NotEnoughSoldier;
import GameModes.War;
import Models.Buildings.Building;
import Models.Buildings.DefensiveBuildings.*;
import Models.Buildings.TownBuildings.*;
import Models.Position.Map;
import Models.Position.Point;
import Models.Soldiers.*;
import Models.Village;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WarView {

    private Scanner scanner = null;

    private WarController warController;

    private ArrayList<Class> allSoldiersTypes = new ArrayList<>(0);
    private ArrayList<Class> allBuildingsTypes = new ArrayList<>(0);

    public WarView(Scanner scanner, WarController warController) {
        this.scanner = scanner;
        this.warController = warController;
        allSoldiersTypes.add(Archer.class);
        allSoldiersTypes.add(Giant.class);
        allSoldiersTypes.add(Guardian.class);
        allSoldiersTypes.add(Healer.class);
        allSoldiersTypes.add(WallBreaker.class);
        allSoldiersTypes.add(Dragon.class);

        allBuildingsTypes.add(AirDefense.class);
        allBuildingsTypes.add(ArcherTower.class);
        allBuildingsTypes.add(Cannon.class);
        allBuildingsTypes.add(Trap.class);
        allBuildingsTypes.add(WizardTower.class);
        allBuildingsTypes.add(Barrack.class);
        allBuildingsTypes.add(Camp.class);
        allBuildingsTypes.add(Mine.class);
        allBuildingsTypes.add(Storage.class);
        allBuildingsTypes.add(TownHall.class);
        allBuildingsTypes.add(Wall.class);

    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public WarController getWarController() {
        return warController;
    }

    public void setWarController(WarController warController) {
        this.warController = warController;
    }

    public void callSoldiers() {

        String input = scanner.nextLine() ;
        String regex = "Select (\\w+) (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        String soldierName;
        int number = 0;
        System.out.println("Start select");

        while (true) {

            input = scanner.nextLine();

            matcher = pattern.matcher(input);
            if (input.equals("End select"))
                break;

            if (!input.matches(regex)) {
                System.out.println("Invalid input.\nSelect <Soldier_Name> #Number");
                continue;
            }
            if (!matcher.find())
                continue;

            soldierName = matcher.group(1);
            number = Integer.parseInt(matcher.group(2));

            Soldier callingSoldier = null;

            for (Class soldierType : allSoldiersTypes) {
                if (soldierType.getSimpleName().equals(soldierName)) {
                    try {
                        callingSoldier = (Soldier) soldierType.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    try {
                        warController.callTroops(callingSoldier.getClass(), number);
                    } catch (NotEnoughSoldier notEnoughSoldier) {
                        System.out.println("not enough soldier");
                    }
                    break;
                }
            }
        }

        warController.startWar(warController.getWar().getWarMap());
        showWarMap();
    }

    public void showLoot() {
        HashMap<Resource, Integer> loot = warController.getWar().getLoot();

        for (Resource resource : loot.keySet()) {
            System.out.println(resource.toString() + " achieved : " + loot.get(resource));
        }

        HashMap<Resource, Integer> remainingResources = new HashMap<>(1);
        remainingResources.put(Resource.GOLD, 0);
        remainingResources.put(Resource.ELIXIR, 0);

        for (Building villageBuilding : warController.getWar().getVillageThings()) {
            if (!villageBuilding.isDestroyed()) {
                remainingResources.replace(Resource.GOLD, remainingResources.get(Resource.GOLD) + villageBuilding.getBuildingTime());
            }
            if (villageBuilding.getClass().equals(Storage.class)) {    // if the building is storage

                if (!villageBuilding.isDestroyed())
                    remainingResources.replace(((Storage) villageBuilding).getResource(), remainingResources.get(((Storage) villageBuilding).getResource()) + ((Storage) villageBuilding).getCurrentCapacity());
            }

            if (villageBuilding.getClass().equals(TownHall.class)) {

                remainingResources.replace(Resource.GOLD,
                        remainingResources.get(Resource.GOLD) +
                                ((TownHall) villageBuilding).getGoldStorage().getCurrentCapacity());

                remainingResources.replace(Resource.ELIXIR,
                        remainingResources.get(Resource.ELIXIR) +
                                ((TownHall) villageBuilding).getElixirStorage().getCurrentCapacity());

            }

        }
        System.out.println("");

        for (Resource resource : remainingResources.keySet()) {
            System.out.println(resource.toString() + " remaining on map : " + remainingResources.get(resource));
        }

    }

    public void showFightSoldiers(Class soldierType) {

        for (Soldier aliveSoldier : warController.getWar().getDeployedSoldiers()) {
            if (aliveSoldier.getClass().equals(soldierType))
            if (!aliveSoldier.isDestroyed()){
                System.out.println(soldierType.getSimpleName() + " : level " + aliveSoldier.getLevel()
                        + " in (" + (aliveSoldier.getPosition().getHeight() + 1 ) + ","
                        + ( aliveSoldier.getPosition().getWidth() + 1) +
                        ") with health  " + aliveSoldier.getHealth());
            }
        }

    }

    public void showAllFightingSoldiers() {

        for (Class soldierType : allSoldiersTypes) {
            showFightSoldiers(soldierType);
        }
    }

    public void showTheseTowers(Class towerType, boolean showIfThereIsNone) {

        for (Building villageBuilding : warController.getWar().getVillageThings()) {
            if (villageBuilding.getClass().equals(towerType)) {

                if (towerType.equals(Storage.class))
                    System.out.print(((Storage) villageBuilding).getResource() + " ");
                if (towerType.equals(Mine.class))
                    System.out.print(((Mine) villageBuilding).getResource() + " ");

                System.out.println(towerType.getSimpleName() + " : level " + villageBuilding.getLevel()
                        + " in (" + ( villageBuilding.getPosition().getHeight() + 1) + ","
                        + ( villageBuilding.getPosition().getWidth() + 1 ) +
                        ") with health  " + villageBuilding.getHealth());

            }
        }
        if (showIfThereIsNone)
            System.out.println("There is no " + towerType.getSimpleName() + " on map");

    }

    public void showRemainingBuildings() {

        for (Class buildingType : allBuildingsTypes) {
            showTheseTowers(buildingType, false);
        }
    }

    public void deployOneGroupOfSoldiers(String input) throws InvalidInput {

        String regex = new String("Put (\\w+) (\\d+) in (\\d+),(\\d+)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find())
            throw new InvalidInput();

        String soldierType = matcher.group(1);
        int soldiersNumber = Integer.parseInt(matcher.group(2));

        int height = Integer.parseInt(matcher.group(3)) - 1;
        int width = Integer.parseInt(matcher.group(4)) - 1;

        Class deployingSoldiersClass = null;

        for (Class soldierClass : allSoldiersTypes) {
            if (soldierClass.getSimpleName().equals(soldierType)) {
                deployingSoldiersClass = soldierClass;
                break;
            }

        }

        Point point = new Point(height, width);

        try {
            warController.deploySoldier(deployingSoldiersClass, point, soldiersNumber);
        } catch (InvalidPoint invalidPoint) {
            System.out.println("You can't deploy soldiers here.");
        } catch (InvalidSoldier invalidSoldier) {
            System.out.println("Not enough soldiers of this kind");
        }

    }

    public void showWarMap() {

        for (int i = 0; i < Map.getMapSize(); i++) {
            for (int j = 0; j < Map.getMapSize(); j++) {
                if (warController.getWar().getWarMap().getCell(i, j).isEmptyOnGround())
                    System.out.print("0 ");
                else
                    System.out.print("1 ");
            }
            System.out.println("");

        }
    }

    public void deploySoldiers(String input) {

        while (true) {
            if (input.equals("Go next turn"))
                break;
            else {
                try {
                    deployOneGroupOfSoldiers(input);
                } catch (InvalidInput invalidInput) {
                    System.out.println("Invalid input. \nPut <SoldierType> #Number in #Height,#Width ");
                }
            }

            input = scanner.nextLine();

        }
        warController.freeOccupiedCells();
        warController.getWar().passBattleTime();
    }

    public void manageWar() {

        String input ;

        callSoldiers();

        while (warController.warIsOn()) {

            input = scanner.nextLine();
            if (input.matches("Put .*")) {
                deploySoldiers(input);
                continue;
            }

            if (input.matches("status resources")) {
                showLoot();
                continue;
            }
            if (input.matches("status unit (.+)")) {

                Class unitType = null;
                input = input.replace("status unit ", "");

                for (Class soldierType : allSoldiersTypes) {
                    if (soldierType.getSimpleName().equals(input)){
                        unitType = soldierType;
                        break;
                    }
                }
                showFightSoldiers(unitType);
                continue;
            }

            if (input.matches("status units")) {
                showAllFightingSoldiers();
                continue;
            }

            if (input.matches("status tower (.+)")){
                Class towerType = null;

                input = input.replace("status tower ", "");

                for (Class buildingType : allBuildingsTypes) {
                    if (buildingType.getSimpleName().equals(input)){
                        towerType = buildingType;
                        break;
                    }
                }
                showTheseTowers(towerType, true);
                continue;
            }

            if (input.matches("status towers")){
                showRemainingBuildings();
                continue;
            }

            if (input.matches("status all")){
                showLoot();
                showAllFightingSoldiers();
                showRemainingBuildings();
                continue;
            }

            if (input.matches("Quit attack")){
                warController.getWar().setWarIsOn(false);
                break;
            }
            if (input.matches("turn \\d+")){

                int turnNumbers = Integer.parseInt(input.replace("turn " , ""));

                for (int i = 0; i < turnNumbers; i++) {
                    warController.getWar().passBattleTime();
                }
                continue;
            }

            else
                System.out.println("Invalid input");


        }

        warController.quitWar();

        System.out.println("The war ended with " +
                warController.getWar().getLoot().get(Resource.GOLD) + " golds " +
                warController.getWar().getLoot().get(Resource.ELIXIR) + " elixirs " +
                warController.getWar().getLoot().get(Resource.SCORE) + " scores " +
                "achieved !" );

    }


    public static void main(String[] args) {

        WarView WarView = new WarView(null, null);
        Scanner scanner = new Scanner(System.in);

        Map map = new Map();
        ArrayList<Soldier> soldiers = new ArrayList<>(0);
        WarController warController = new WarController();

        WarView warView = new WarView(scanner, warController);
        Village village = new Village();
        Camp camp = new Camp();
        ArrayList<Soldier> soldierArrayList = new ArrayList<>(0);
        for (int i = 0; i < 6; i++)
            soldierArrayList.add(new Archer());
        for (int i = 0; i < 6; i++)
            soldierArrayList.add(new Giant());

        camp.setArmy(soldierArrayList);
        ArrayList<Camp> villageCamps = new ArrayList<>(0);
        villageCamps.add(camp);
        village.setCamps(villageCamps);

        ArrayList<Building> villageThings = new ArrayList<>(1);

        Storage storage = new Storage("gold");
        storage.setCurrentCapacity(200);
        storage.setPosition(new Point(3,5));
        villageThings.add(storage);

        storage = new Storage("elixir");
        storage.setCurrentCapacity(10);
        storage.setPosition(new Point(3,23));
        villageThings.add(storage);


        warController.setAttackerVillage(village);
        War war = new War(new Map(), null);
        war.setVillageThings(villageThings);
        warController.setWar(war);

        warView.manageWar();
    }


}