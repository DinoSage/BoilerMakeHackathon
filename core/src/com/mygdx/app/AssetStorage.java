package com.mygdx.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

//This singleton class keeps track of all assets and manages them
public class AssetStorage {

    //-------SINGLETON SETUP-------
    private static AssetStorage instance = null;

    //Gets instance of asset storage
    public static AssetStorage getInstance(){
        if(instance == null)
            instance = new AssetStorage();
        return instance;
    }

    //The private constructor of singleton & setup of class
    private AssetStorage(){
        manager = new AssetManager();
    }

    //---------------------------------------
    //The main asset manager that loads and unloads assets
    public final AssetManager manager;

    //Asset Storage / Manager loading states
    public boolean render = false;

    //Instance variables of certain / current assets - Main Variables
    public Skin skin;

    //Should always be rendering for screens that implement this
    public void render(){
        render = manager.update();
    }

    //Load Methods----------------------

    //Method for loading the assets for "loading"
    public void startLoad(){
        manager.load(AppConstants.SKIN_FILE_PATH, Skin.class);
        manager.finishLoading();

        skin = manager.get(AppConstants.SKIN_FILE_PATH);
    }

    //Queues the assets to be rendered
    public boolean queue(String file, Class type){
        if(Gdx.files.internal(file).exists()){
            manager.load(file, type);
            return true;
        } else {
            return false;
        }
    }

    //Getter method for accessing assets by file path
    public <T> T get(String file){
        if(manager.isLoaded(file)){
            return (manager.get(file));
        } else {
            return null;
        }
    }

    //To unload an asset and save space
    public void unload(String file){
        if(manager.isLoaded(file)){
            manager.unload(file);
        }
    }

    //Private Methods
    /*private Array<TextureRegion> getRegions(String commonFileName, String atlasName){
        TextureAtlas atlas = new TextureAtlas(atlasName);
        Array<TextureAtlas.AtlasRegion> regions = atlas.getRegions();

        ArrayList<TextureRegion> textures = new ArrayList<>();
        for(TextureAtlas.AtlasRegion region : regions){
            if(region.name.contains(commonFileName)){
                textures.add(new TextureRegion(region.getTexture()));
            }
        }

        TextureRegion[] arr= textures.toArray(new TextureRegion[0]);
        Array<TextureRegion> textureRegions = new Array<>(arr);
        return textureRegions;
    }*/
}
