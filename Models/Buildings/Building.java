package Models.Buildings;

import Models.Entity;
import Models.Position.Map;
import Models.Village;

import java.util.HashMap;

public abstract class Building extends Entity {

    protected HashMap<Integer, Integer> mineBuildingCost = new HashMap<>();

    protected int buildingCost; // gold , elixir
    protected int buildingTime;

    protected int upgradeCost;  // gold
    protected int upgradeTime;

    private int size;
    private int hitScore;
    private boolean isActive = false;

    private boolean onUpgrade = false;
    private boolean onConstruct = true;

    public boolean isOnUpgrade() {
        return onUpgrade;
    }

    public void setOnUpgrade(boolean onUpgrade) {
        this.onUpgrade = onUpgrade;
    }

    public boolean isOnConstruct() {
        return onConstruct;
    }

    public void setOnConstruct(boolean onConstruct) {
        this.onConstruct = onConstruct;
    }

    public HashMap<Integer, Integer> getMineBuildingCost() {
        return mineBuildingCost;
    }

    public void setMineBuildingCost(HashMap<Integer, Integer> mineBuildingCost) {
        this.mineBuildingCost = mineBuildingCost;
    }

    public int getBuildingCost() {
        return buildingCost;
    }

    public void setBuildingCost(int buildingCost) {
        this.buildingCost = buildingCost;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public int getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(int upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(int buildingTime) {
        this.buildingTime = buildingTime;
    }

    public int getSize() {

        return size;
    }

    public void setSize(int size) {

        this.size = size;
    }

    public int getHitScore() {
        return hitScore;
    }

    public void setHitScore(int hitScore) {
        this.hitScore = hitScore;
    }

    @Override
    public void upgrade() {

    }

    public abstract void passOneTurn(Map map);

    public void startUpGrading(Village village) {

    }

}
