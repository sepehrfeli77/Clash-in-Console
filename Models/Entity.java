package Models;

import Models.Position.Map;
import Models.Position.Point;

public class Entity {

    private int level;
    private Point position;
    private int maxHealth;
    private int health;
    private boolean isDestroyed = false;
    private int typeCode;

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
    public void upgrade(){

    }

    public void getDamaged(int receivedDamage){
        health -= receivedDamage ;

        if (health <= 0){
            isDestroyed = true;
        }
    }

    public void passOneTurn(Map map){
    };


}
