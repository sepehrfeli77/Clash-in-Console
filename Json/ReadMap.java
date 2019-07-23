package Json;


import Json.JsonModels.JsonBuilding;
import Json.JsonModels.JsonResources;
import Json.JsonModels.JsonSize;
import Json.JsonModels.JsonWall;
import Models.Buildings.DefensiveBuildings.*;
import Models.Buildings.TownBuildings.*;
import Models.Position.Map;
import Models.Position.Point;
import Models.Village;
import com.gilecode.yagson.YaGson;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;

public class ReadMap {
    public Map ReadAttackMap(String path) throws IOException {

        //Read file & convert it to string
        File f = new File(path);
        InputStream stream = new FileInputStream(f);
        StringBuilder json = new StringBuilder();
        int byteCode = stream.read();
        while (byteCode != -1) {
            json.append((char) byteCode);
            byteCode = stream.read();
        }
        stream.close();

        //Set json in jsonModels
        JsonParser parser = new JsonParser();
        Object object = parser.parse(json.toString());
        JsonObject jsonObject = (JsonObject) object;

        //Set size
        JsonArray sizes = jsonObject.get("size").getAsJsonArray();
        JsonSize size = new JsonSize();
        size.setHeight(sizes.get(0).getAsInt());
        size.setWidth(sizes.get(1).getAsInt());


        //Set walls
        JsonArray jsonWalls = jsonObject.get("walls").getAsJsonArray();
        ArrayList<JsonWall> walls = new ArrayList<JsonWall>();
        int wallsNumber = jsonWalls.size();

        for (int i = 0; i < wallsNumber; i++) {
            JsonWall wall = new JsonWall();
            wall.setLevel(jsonWalls.get(i).getAsJsonObject().get("level").getAsInt());
            wall.setX(jsonWalls.get(i).getAsJsonObject().get("x").getAsInt() - 1);
            wall.setY(jsonWalls.get(i).getAsJsonObject().get("y").getAsInt() - 1);
            walls.add(wall);
        }


        //Set resources
        JsonObject jsonResources = jsonObject.get("resources").getAsJsonObject();
        JsonResources resources = new JsonResources();
        resources.setGold(jsonResources.get("gold").getAsInt());
        resources.setElixir(jsonResources.get("elixir").getAsInt());

        //Set buildings
        JsonArray jsonBuilings = jsonObject.get("buildings").getAsJsonArray();
        ArrayList<JsonBuilding> buildings = new ArrayList<JsonBuilding>();
        int buildingsNumber = jsonBuilings.size();

        for (int i = 0; i < buildingsNumber; i++) {
            JsonBuilding building = new JsonBuilding();
            building.setType(jsonBuilings.get(i).getAsJsonObject().get("type").getAsInt());
            building.setLevel(jsonBuilings.get(i).getAsJsonObject().get("level").getAsInt());
            building.setX(jsonBuilings.get(i).getAsJsonObject().get("x").getAsInt() - 1);
            building.setY(jsonBuilings.get(i).getAsJsonObject().get("y").getAsInt() - 1);
            if (jsonBuilings.get(i).getAsJsonObject().has("amount")) {
                building.setAmount(jsonBuilings.get(i).getAsJsonObject().get("amount").getAsInt());
            }
            buildings.add(building);
        }




        //Set jsonModels in Map
        Map map = new Map();

        //Set size
        //Size setting ignored

        //Set resources
        Storage townHallGoldStorage = new Storage("gold");
        Storage townHallElixirStorage = new Storage("elixir");

        townHallGoldStorage.setCurrentCapacity(resources.getGold());
        townHallElixirStorage.setCurrentCapacity(resources.getElixir());

        //Set buildings
        for (JsonBuilding building : buildings) {
            Point point = new Point(building.getY(),building.getX());
            int level = building.getLevel();

            if (building.getType() == 1){
                Mine goldMine = new Mine("gold");
                goldMine.setPosition(point);
                goldMine.setLevel(level);

                map.locate(goldMine);
            }

            if (building.getType() == 2){
                Mine elixirMine = new Mine("elixir");
                elixirMine.setPosition(point);
                elixirMine.setLevel(level);

                map.locate(elixirMine);
            }

            if (building.getType() == 3){
                Storage goldStorage = new Storage("gold");
                goldStorage.setPosition(point);
                goldStorage.setLevel(level);
                goldStorage.setCurrentCapacity(building.getAmount());

                map.locate(goldStorage);
            }

            if (building.getType() == 4){
                Storage elixirStorage = new Storage("elixir");
                elixirStorage.setPosition(point);
                elixirStorage.setLevel(level);
                elixirStorage.setCurrentCapacity(building.getAmount());

                map.locate(elixirStorage);
            }

            if (building.getType() == 5){
                TownHall townHall = new TownHall();
                townHall.setPosition(point);
                townHall.setLevel(level);
                townHall.setGoldStorage(townHallGoldStorage);
                townHall.setElixirStorage(townHallElixirStorage);

                map.locate(townHall);
            }

            if (building.getType() == 6){
                Barrack barrack = new Barrack();
                barrack.setPosition(point);
                barrack.setLevel(level);

                map.locate(barrack);
            }

            if (building.getType() == 7){
                Camp camp = new Camp();
                camp.setPosition(point);
                camp.setLevel(level);

                map.locate(camp);
            }

            if (building.getType() == 8){
                ArcherTower archerTower = new ArcherTower();
                archerTower.setPosition(point);
                archerTower.setLevel(level);

                map.locate(archerTower);
            }

            if (building.getType() == 9){
                Cannon cannon =new Cannon();
                cannon.setPosition(point);
                cannon.setLevel(level);

                map.locate(cannon);
            }

            if (building.getType() == 10){
                AirDefense airDefense = new AirDefense();
                airDefense.setPosition(point);
                airDefense.setLevel(level);

                map.locate(airDefense);
            }

            if (building.getType() == 11){
                WizardTower wizardTower = new WizardTower();
                wizardTower.setPosition(point);
                wizardTower.setLevel(level);

                map.locate(wizardTower);
            }

            //TypeCode 12 is wall & initialize separate

            if (building.getType() == 13){
                Trap trap = new Trap();
                trap.setPosition(point);
                trap.setLevel(level);

                map.locate(trap);
            }

            //TypeCode 14 is GuardianGiant & it isn't necessary for phase1
        }

        //Set walls
        for (JsonWall jsonWall : walls) {
            Point point = new Point(jsonWall.getY(), jsonWall.getX());
            int level = jsonWall.getLevel();

            Wall wall = new Wall();
            wall.setPosition(point);
            wall.setLevel(level);

            map.locate(wall);
        }

        return map;
    }


    //name deleted
    public void Save(Village village, String path) throws IOException {
        YaGson yaGson = new YaGson();
        String string = yaGson.toJson(village);

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(string);

        writer.close();
    }

    public Village ReadOwnMap(String path) throws IOException {

        File f = new File(path);
        InputStream stream = new FileInputStream(f);
        StringBuilder json = new StringBuilder();
        int byteCode = stream.read();
        while (byteCode != -1) {
            json.append((char) byteCode);
            byteCode = stream.read();
        }
        stream.close();

        Village village = new YaGson().fromJson(json.toString(), Village.class);

        return village;
    }

    public static void main(String[] args) {
        ReadMap readMap = new ReadMap();

        try {
            Map map = readMap.ReadAttackMap("/home/mohsen/IdeaProjects/AP-Project/src/git-tuturial/src/Json/sample.json");
            System.out.println("salam");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("dtfeds");
    }
}