package Models.Soldiers;

import Enums.Field;
import Models.Position.Map;
import Models.Soldiers.Soldier;

import java.util.ArrayList;

public class Healer extends Soldier {

    public Healer() {

        setMovementField(Field.GROUND);
        setBuildingCost(175);
        setBuildingTime(30);
        setMaxHealth(200);
        setHealth(getMaxHealth());
        setEffectRadius(10);
        setDamage(25);
        setSpeed(2);
    }

    public ArrayList alliesInRange (Map soldierMap ) { //TODO

        ArrayList allies = new ArrayList() ;

        return allies ;
    }

    public void heal ( ArrayList< Soldier > allies ) { //TODO

    }

    @Override
    public void passOneTurn(Map map) {

    }

    @Override
    public void upgrade() {

        this.setDamage(this.getDamage() +1);

    }


    @Override
    public void move(Map map) {

    }
}
