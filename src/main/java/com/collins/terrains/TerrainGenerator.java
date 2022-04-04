package com.collins.terrains;

import java.util.List;
import java.util.Random;

import com.collins.display.Loader;
import com.collins.display.ModelData;
import com.collins.display.OBJFileLoader;
import com.collins.display.Models.RawModel;
import com.collins.display.Models.TexturedModel;
import com.collins.display.textures.ModelTexture;
import com.collins.entities.Entity;
import com.collins.entities.ObjectManager;
import com.collins.entities.Tree;
import com.collins.entities.Grass;


import org.joml.Vector3f;

public class TerrainGenerator {

    public static void generateTerrain(Loader loader) {

		List<Terrain> terrains = ObjectManager.getTerrains();

		ModelTexture terrainTexture = new ModelTexture(loader.loadTexture("grass"));
		terrainTexture.setShineDamper(10);
		terrainTexture.setReflectivity(1f);

		terrains.add(new Terrain(-1,-1, loader, terrainTexture));
		terrains.add(new Terrain(0,-1, loader, terrainTexture));
		terrains.add(new Terrain(0,0, loader, terrainTexture));
		terrains.add(new Terrain(-1,0, loader, terrainTexture));

		ModelData plantData = OBJFileLoader.loadOBJ("plant");
        RawModel rawPlantModel = loader.loadToVAO(plantData.getVertices(), plantData.getTextureCoords(), plantData.getNormals(), plantData.getIndices());
        TexturedModel grass = new TexturedModel(rawPlantModel, new ModelTexture(loader.loadTexture("tallgrass")));
		ModelTexture grassTexture = grass.getTexture();
		grassTexture.setShineDamper(10);
		grassTexture.setReflectivity(1f);
		grassTexture.setHasTransparency(true);
		grassTexture.setUseFakeLighting(true);

		ModelData highDemPlantData = OBJFileLoader.loadOBJ("high-dem-plant");
        RawModel rawhighDemPlantModel = loader.loadToVAO(highDemPlantData.getVertices(), highDemPlantData.getTextureCoords(), highDemPlantData.getNormals(), highDemPlantData.getIndices());
		TexturedModel tree = new TexturedModel(rawhighDemPlantModel, new ModelTexture(loader.loadTexture("treeSide")));
		ModelTexture treeTexture = tree.getTexture();
		treeTexture.setShineDamper(10);
		treeTexture.setReflectivity(1f);
		treeTexture.setHasTransparency(true);
		treeTexture.setUseFakeLighting(true);

        List<Entity> entities = ObjectManager.getEntities();
		Random random = new Random();

		for (int i = 0; i< 15000; i++) {
			float x = random.nextFloat() * Terrain.getSize() * (random.nextFloat() - 0.5f)*2;
			float y = 0;//random.nextFloat() * 5;
			float z = random.nextFloat() * Terrain.getSize() * (random.nextFloat() - 0.5f)*2;
			float ry = random.nextFloat()*180f;
			entities.add(new Grass(grass, new Vector3f(x, y, z), 0, ry, 0f, random.nextFloat()*1f + 1));
		}

		for (int i = 0; i< 2500; i++) {
			float x = random.nextFloat() * Terrain.getSize() * (random.nextFloat() - 0.5f)*2;
			float y = 0;//random.nextFloat() * 5;
			float z = random.nextFloat() * Terrain.getSize() * (random.nextFloat() - 0.5f)*2;
			float ry = random.nextFloat()*180f;
			entities.add(new Tree(tree, new Vector3f(x, y, z), 0, ry, 0f, random.nextFloat()*2f+4));
		}
    }

}
