package com.bazola.roomsworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bazola.roomsworld.camera.SmoothCamera;
import com.bazola.roomsworld.gamemodel.TileType;

public class LibGDXGame extends Game {

    public static float STAGE_WIDTH;
    public static float STAGE_HEIGHT;
    
    public static float HUD_WIDTH;
    public static float HUD_HEIGHT;
    
	public SmoothCamera camera;
	public Stage stage;
	public SpriteBatch batch;
	    
	public OrthographicCamera hudCamera;
	public SpriteBatch hudBatch;
	public Stage hudStage;
	
	public RoomDrawer drawer;
	public PersonDrawer personDrawer;
	private GameScreen gameScreen;
	
	public InputMultiplexer inputHandler = new InputMultiplexer();

	public Texture alpha;
	
	public ObjectMap<TileType, TextureRegion> atlasTextures;
	public ObjectMap<ImageType, TextureRegion> animationTextures;
	
	public ObjectMap<AnimationType, Animation<TextureRegion>> playerAnimations = new ObjectMap<AnimationType, Animation<TextureRegion>>();
	
	@Override
	public void create () {
        LibGDXGame.STAGE_WIDTH = 100;
        LibGDXGame.STAGE_HEIGHT = 100;
        
        LibGDXGame.HUD_WIDTH = 600;
        LibGDXGame.HUD_HEIGHT = 600;
        
        this.camera = new SmoothCamera(STAGE_WIDTH, STAGE_HEIGHT);
        this.camera.setToOrtho(false, STAGE_WIDTH, STAGE_HEIGHT);
        this.batch = new SpriteBatch();
        this.stage = new Stage(new ScreenViewport(camera), batch);
        
        this.camera.setZoom(0.4f);

        this.hudCamera = new OrthographicCamera(HUD_WIDTH, HUD_HEIGHT);
        this.hudCamera.setToOrtho(false, HUD_WIDTH, HUD_HEIGHT);
        this.hudBatch = new SpriteBatch();
        this.hudStage = new Stage(new ScreenViewport(hudCamera), hudBatch);
        
        this.loadAssets();
        
        this.configureInputHandlers();
        
        this.startGame();
	}
	
    private void loadAssets()
    {
        this.alpha = new Texture("alpha.png");
        
        this.atlasTextures = this.loadAtlasTextures();
        this.animationTextures = this.loadAnimationTextures();
        
        this.playerAnimations.put(AnimationType.PLAYER_RIGHT, AnimationLoader.playerMoveRight(this.animationTextures));
        this.playerAnimations.put(AnimationType.PLAYER_LEFT, AnimationLoader.playerMoveLeft(this.animationTextures));
        this.playerAnimations.put(AnimationType.PLAYER_UP, AnimationLoader.playerMoveUp(this.animationTextures));
        this.playerAnimations.put(AnimationType.PLAYER_DOWN, AnimationLoader.playerMoveDown(this.animationTextures));
        this.playerAnimations.put(AnimationType.NONE, AnimationLoader.none(this));
    }
    
    private ObjectMap<TileType, TextureRegion> loadAtlasTextures() {
        ObjectMap<TileType, TextureRegion> textures = new ObjectMap<TileType, TextureRegion>();
        TextureAtlas atlas = new TextureAtlas("roomsworld01.atlas");
        for (TileType type : TileType.values()) {
            AtlasRegion region = atlas.findRegion(type.path);
            TextureRegion textureRegion = region;
            textures.put(type, textureRegion);
        }
        return textures;
    }
    
    private ObjectMap<ImageType, TextureRegion> loadAnimationTextures() {
        ObjectMap<ImageType, TextureRegion> textures = new ObjectMap<ImageType, TextureRegion>();
        TextureAtlas atlas = new TextureAtlas("roomsPlayer01.atlas");
        for (ImageType type : ImageType.values()) {
            AtlasRegion region = atlas.findRegion(type.path);
            TextureRegion textureRegion = region;
            textures.put(type, textureRegion);
        }
        return textures;
    }

    private ObjectMap<AnimationType, Animation<TextureRegion>> loadSlashAnimations() {
        ObjectMap<AnimationType, Animation<TextureRegion>> slashAnimations = new ObjectMap<AnimationType, Animation<TextureRegion>>();
        TextureAtlas atlas = new TextureAtlas("slash.atlas");
        for (AnimationType type : AnimationType.values()) {
            AtlasRegion region = atlas.findRegion(type.path);
            TextureRegion textureRegion = region;
            slashAnimations.put(type, new Animation<TextureRegion>(1f, textureRegion));
        }
        return textures;
    }
    
    private void configureInputHandlers() {
        this.inputHandler.addProcessor(this.hudStage); 
        this.inputHandler.addProcessor(this.stage);
        
        Gdx.input.setInputProcessor(this.inputHandler);
    }
    
    public void startGame() {
        this.drawer = null;
        this.stage.clear();
        this.hudStage.clear();
        
        this.gameScreen = new GameScreen(this);
        this.setScreen(this.gameScreen);
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        float delta = Gdx.graphics.getDeltaTime();
        
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        
        if (this.drawer != null) {
            this.drawer.draw(delta);
        }
        if (this.personDrawer != null) {
            this.personDrawer.draw(delta);
        }

        batch.end();
        if (this.gameScreen != null) {
        //    if (this.gameScreen.getGameState() != GameState.PAUSED &&
        //        this.gameScreen.getGameState() != GameState.ENDED) {
                this.gameScreen.update(Math.min(delta, 1/30f));
                stage.act(Math.min(delta, 1 / 30f));
        //    }
        }
        stage.draw();
        
        hudCamera.update();
        hudBatch.setProjectionMatrix(hudCamera.combined);
        hudBatch.begin();
        //hudBatch.draw(bg, 0, 0, STAGE_WIDTH, STAGE_HEIGHT);
        hudBatch.end();
        hudStage.act(Math.min(delta, 1 / 30f));
        hudStage.draw(); 
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
