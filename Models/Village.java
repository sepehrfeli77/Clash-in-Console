package Models;

import Enums.Resource;
import Models.Buildings.Building;
import Models.Buildings.DefensiveBuildings.*;
import Models.Buildings.TownBuildings.*;
import Models.Position.Map;
import Models.Soldiers.Soldier;


import java.util.ArrayList;

public class Village {

    public Village() {

        this.getMap().getCell(14, 14).getOnGroundThings().add(this.townhall);

        this.getGoldStorages().add(new Storage("gold"));
        this.getElixirStorages().add(new Storage("elixir"));

        this.getGoldMines().add(new Mine("gold"));
        this.getElixirMines().add(new Mine("elixir"));

        this.getTotalGoldStorage().setMaxCapacity(10500);
        this.getTotalGoldStorage().setCurrentCapacity(10000);

        this.getTotalElixirStorage().setMaxCapacity(5020);
        this.getTotalElixirStorage().setCurrentCapacity(1000);

        this.getMap().getCell(13, 13).getOnGroundThings().add(this.goldMines.get(0));
        this.getGoldMines().get(0).setOnConstruct(false);
        this.getGoldMines().get(0).setActive(true);

        this.getMap().getCell(13, 16).getOnGroundThings().add(this.elixirMines.get(0));
        this.getElixirMines().get(0).setOnConstruct(false);
        this.getElixirMines().get(0).setActive(true);

        this.getMap().getCell(16, 13).getOnGroundThings().add(this.goldStorages.get(0));
        this.getGoldStorages().get(0).setOnConstruct(false);
        this.getGoldStorages().get(0).setActive(true);

        this.getMap().getCell(16, 16).getOnGroundThings().add(this.elixirStorages.get(0));
        this.getElixirStorages().get(0).setOnConstruct(false);
        this.getElixirStorages().get(0).setActive(true);

        this.getTownhall().setActive(true);

        this.getElixirStorages().get(0).setActive(true);
        this.getGoldStorages().get(0).setActive(true);

        this.getElixirMines().get(0).setActive(true);
        this.getGoldMines().get(0).setActive(true);
    }

    private ArrayList<Storage> goldStorages = new ArrayList<>();
    private ArrayList<Storage> elixirStorages = new ArrayList<>();

    private ArrayList<Mine> goldMines = new ArrayList<>();
    private ArrayList<Mine> elixirMines = new ArrayList<>();

    private ArrayList<Barrack> barracks = new ArrayList<>();
    private ArrayList<Camp> camps = new ArrayList<>();

    private ArrayList<AirDefense> airDefenses = new ArrayList<>();
    private ArrayList<ArcherTower> archerTowers = new ArrayList<>();
    private ArrayList<WizardTower> wizardTowers = new ArrayList<>();
    private ArrayList<Cannon> cannons = new ArrayList<>();
    private ArrayList<Trap> traps = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    private TownHall townhall = new TownHall();

    private Map map = new Map();

    private Storage totalGoldStorage = new Storage("gold");

    private Storage totalElixirStorage = new Storage("elixir");


    public ArrayList<Storage> getGoldStorages() {
        return goldStorages;
    }

    public void setGoldStorages(ArrayList<Storage> goldSrorages) {
        this.goldStorages = goldSrorages;
    }


    public ArrayList<Storage> getElixirStorages() {
        return elixirStorages;
    }

    public void setElixirStorages(ArrayList<Storage> elixirStorages) {
        this.elixirStorages = elixirStorages;
    }


    public ArrayList<Mine> getGoldMines() {
        return goldMines;
    }

    public void setGoldMines(ArrayList<Mine> goldMines) {
        this.goldMines = goldMines;
    }


    public ArrayList<Mine> getElixirMines() {
        return elixirMines;
    }

    public void setElixirMines(ArrayList<Mine> elixirMines) {
        this.elixirMines = elixirMines;
    }


    public ArrayList<Barrack> getBarracks() {
        return barracks;
    }

    public void setBarracks(ArrayList<Barrack> barracks) {
        this.barracks = barracks;
    }


    public ArrayList<Camp> getCamps() {
        return camps;
    }

    public void setCamps(ArrayList<Camp> camps) {
        this.camps = camps;
    }


    public ArrayList<AirDefense> getAirDefenses() {
        return airDefenses;
    }

    public void setAirDefenses(ArrayList<AirDefense> airDefenses) {
        this.airDefenses = airDefenses;
    }


    public ArrayList<ArcherTower> getArcherTowers() {
        return archerTowers;
    }

    public void setArcherTowers(ArrayList<ArcherTower> archerTowers) {
        this.archerTowers = archerTowers;
    }


    public ArrayList<WizardTower> getWizardTowers() {
        return wizardTowers;
    }

    public void setWizardTowers(ArrayList<WizardTower> wizardTowers) {
        this.wizardTowers = wizardTowers;
    }


    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public void setCannons(ArrayList<Cannon> cannons) {
        this.cannons = cannons;
    }


    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public void setTraps(ArrayList<Trap> traps) {
        this.traps = traps;
    }


    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }


    public TownHall getTownhall() {
        return townhall;
    }

    public void setTownhall(TownHall townhall) {
        this.townhall = townhall;
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Storage getTotalGoldStorage() {
        return totalGoldStorage;
    }

    public void setTotalGoldStorage(Storage totalGoldStorage) {
        this.totalGoldStorage = totalGoldStorage;
    }


    public Storage getTotalElixirStorage() {
        return totalElixirStorage;
    }

    public void setTotalElixirStorage(Storage totalElixirStorage) {
        this.totalElixirStorage = totalElixirStorage;
    }


    public void upDateVillage() {

        boolean hasEmptySpace = false;

        for (Mine goldmine : goldMines) {

            if (goldmine.isActive() == true) {

                for (Storage goldstorage : goldStorages) {

                    if (goldstorage.isActive() == true) {

                        if (goldstorage.isFull() == false) {

                            goldstorage.increaseResource(goldmine, this);
                            hasEmptySpace = true;
                        }
                    }


                }

                if (hasEmptySpace == false) {
                    this.getTownhall().getGoldStorage().increaseResource(goldmine, this);
                }

                hasEmptySpace = false;
            }

        }

        hasEmptySpace = false;
        for (Mine elixirMine : elixirMines) {

            if (elixirMine.isActive() == true) {

                for (Storage elixirstorage : elixirStorages) {

                    if (elixirstorage.isActive() == true) {

                        if (elixirstorage.isFull() == false) {

                            elixirstorage.increaseResource(elixirMine, this);
                            hasEmptySpace = true;
                        }
                    }
                }

                if (hasEmptySpace == false) {

                    this.getTownhall().getElixirStorage().increaseResource(elixirMine, this);
                }

                hasEmptySpace = false;
            }
        }

        // building & upgrading :
        ArrayList finishedCostructing = new ArrayList();
        for (Building building : this.getTownhall().getInLineConstructions()) {

            if (building.getBuildingTime() != 0) {
                building.setBuildingTime(building.getBuildingTime() - 1);
                continue;
            } else if (building.getBuildingTime() == 0) {

                if (building.isOnUpgrade() == true) {

                    building.upgrade();
                } else if (building.isOnConstruct() == true) {

                    building.setActive(true);
                    building.setOnConstruct(false);
                }

                for (Labor l : this.getTownhall().getLabors()) {

                    if (l.getWorkPlace() == building) {

                        l.setWorkPlace(null);
                        l.setFree(true);
                        break;
                    }
                }


                for (int i = 0; i < this.getTownhall().getInLineConstructions().size(); i++) {
                    if (this.getTownhall().getInLineConstructions().get(i).equals(building)) {
                        finishedCostructing.add(i);
                        break;
                    }
                }
            }
        }
        if (finishedCostructing.size() != 0) {

            for (int i = finishedCostructing.size() - 1; i >= 0; i--) {

                this.getTownhall().getInLineConstructions().remove( this.getTownhall().getInLineConstructions().get( (int)finishedCostructing.get(i) ));

            }
        }


        // building soldiers
        ArrayList finishedBuildingSoldiers = new ArrayList();
        barrackSearch:
        for (Barrack barrack : this.getBarracks()) {

            if (barrack.isActive() == true) {

                if (barrack.getLineOfSoldiers().size() == 0)
                    break ;

                if (barrack.getLineOfSoldiers().get(0).getBuildingTime() != 0) {

                    barrack.getLineOfSoldiers().get(0).setBuildingTime(barrack.getLineOfSoldiers().get(0).getBuildingTime() - 1);
                    continue barrackSearch;
                } else if (barrack.getLineOfSoldiers().get(0).getBuildingTime() == 0) {

                    findingEmptyCamp:
                    for (Camp camp : this.getCamps()) {

                        if (camp.getCurrentCapacity() != 0) {

                            camp.getArmy().add(barrack.getLineOfSoldiers().get(0));
                            camp.setCurrentCapacity(camp.getCurrentCapacity() - 1);

                            barrack.getLineOfSoldiers().remove( barrack.getLineOfSoldiers().get(0) );

                            break findingEmptyCamp;
                        }
                    }
                }
            }
        }
    }

    public int availableNumberOf(Class soldierType) {

        int soldierNum = 0;

        for (Camp villageCamp : camps) {
            for (Soldier campSoldier : villageCamp.getArmy()) {
                if (campSoldier.getClass().equals(soldierType)) {
                    soldierNum++;
                }
            }
        }
        return soldierNum;
    }

    public void hostSoldier(Soldier soldier){

        for (Camp villageCamp : getCamps()) {
            if (villageCamp.getMaxCapacity() - villageCamp.getCurrentCapacity() > 0)
                villageCamp.getArmy().add(soldier);

        }
    }

    public Soldier callSoldier(Class soldierType) {

        for (Camp villageCamp : camps) {
            for (Soldier campSoldier : villageCamp.getArmy()) {
                if (campSoldier.getClass().equals(soldierType)) {
                    villageCamp.getArmy().remove(campSoldier);
                    return campSoldier;
                }
            }
        }

        return null;
    }

    public void collectResource(Resource resource, int amount){

        if (resource.equals(Resource.GOLD)){


            for (Storage storage: goldStorages) {
                int emptySpace = storage.getMaxCapacity() - storage.getCurrentCapacity();

                if (emptySpace >= amount ){
                    storage.setCurrentCapacity(storage.getCurrentCapacity() + amount);
                    totalGoldStorage.setCurrentCapacity(totalGoldStorage.getCurrentCapacity() + amount);
                    amount -= emptySpace;
                }
                else {
                    storage.setCurrentCapacity(storage.getMaxCapacity());
                    totalGoldStorage.setCurrentCapacity(totalGoldStorage.getCurrentCapacity() + emptySpace);
                    amount -= emptySpace;
                }
                if (amount <= 0)
                    break;

            }
        }

        if (resource.equals(Resource.ELIXIR)){


            for (Storage storage: elixirStorages) {
                int emptySpace = storage.getMaxCapacity() - storage.getCurrentCapacity();

                if (emptySpace >= amount ){
                    storage.setCurrentCapacity(storage.getCurrentCapacity() + amount);
                    totalElixirStorage.setCurrentCapacity(totalElixirStorage.getCurrentCapacity() + amount);
                    amount -= emptySpace;
                }
                else {
                    storage.setCurrentCapacity(storage.getMaxCapacity());
                    totalElixirStorage.setCurrentCapacity(totalElixirStorage.getCurrentCapacity() + emptySpace);
                    amount -= emptySpace;
                }
                if (amount <= 0)
                    break;

            }
        }

    }

    public ArrayList<Integer> totalCapacityLeft(){

        ArrayList<Integer> arrayList = new ArrayList<>(0);
        int capacity = 0;
        for (Storage storage : getGoldStorages()) {
            capacity += (storage.getMaxCapacity() - storage.getMaxCapacity());
        }

        arrayList.add(capacity);
        capacity = 0;

        for (Storage storage : getElixirStorages()) {
            capacity += (storage.getMaxCapacity() - storage.getMaxCapacity());
        }
        arrayList.add(capacity);
        return arrayList;
    }

}