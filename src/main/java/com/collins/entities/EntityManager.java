package com.collins.entities;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private static List<Entity> entities;

    public static List<Entity> getEntities() {
        return entities;
    }

    public static void init() {
        entities = new ArrayList<Entity>();
    }

    public static void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }
}
