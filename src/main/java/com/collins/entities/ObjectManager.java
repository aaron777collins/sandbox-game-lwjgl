package com.collins.entities;

import java.util.ArrayList;
import java.util.List;

import com.collins.terrains.Terrain;

public class ObjectManager {
    private static List<Entity> entities;
    private static List<Terrain> terrains;

    public static List<Terrain> getTerrains() {
        return terrains;
    }
    
    public static List<Entity> getEntities() {
        return entities;
    }

    public static void init() {
        entities = new ArrayList<Entity>();
        terrains = new ArrayList<Terrain>();
    }

    public static void update() {
        
        for (Entity entity : entities) {
            entity.update();
        }
    }
}
