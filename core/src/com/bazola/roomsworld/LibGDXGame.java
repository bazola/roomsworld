package com.bazola.roomsworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bazola.roomsworld.camera.SmoothCamera;

public class LibGDXGame extends ApplicationAdapter {

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
	
	public InputMultiplexer inputHandler = new InputMultiplexer();
	
	Texture img;
	
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

        this.hudCamera = new OrthographicCamera(HUD_WIDTH, HUD_HEIGHT);
        this.hudCamera.setToOrtho(false, HUD_WIDTH, HUD_HEIGHT);
        this.hudBatch = new SpriteBatch();
        this.hudStage = new Stage(new ScreenViewport(hudCamera), hudBatch);
        
        img = new Texture("badlogic.jpg");
        
        this.configureInputHandlers();
	}
	
	   private void configureInputHandlers() {
	        //this.inputHandler.addProcessor(this.buttonStage);
	        this.inputHandler.addProcessor(this.hudStage); 
	        this.inputHandler.addProcessor(this.stage);
	        //this.inputHandler.addProcessor(this.flagStage);
	        
	        /*
	        this.cameraPanner = new CameraPanner(this.camera);
	        this.cameraPanner.setEnabled(true);
	        //this.cameraPanner.setParallaxCamera(this.parallaxCamera);
	        
	        this.pinchZoomer = new PinchZoomer(this.camera);
	        this.pinchZoomer.setEnabled(true);
	        //this.pinchZoomer.setParallaxCamera(this.parallaxCamera);
	        
	        this.scrollWheelZoomer = new ScrollWheelZoomer(this.camera);
	        this.scrollWheelZoomer.setEnabled(true);
	        //this.scrollWheelZoomer.setParallaxCamera(this.parallaxCamera);
	        
	        this.inputHandler.addProcessor(new GestureDetector(this.cameraPanner));
	        this.inputHandler.addProcessor(new GestureDetector(this.pinchZoomer));
	        this.inputHandler.addProcessor(this.scrollWheelZoomer);
	        */
	        Gdx.input.setInputProcessor(this.inputHandler);
	    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        float delta = Gdx.graphics.getDeltaTime();
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        /*
        if (this.drawer != null) {
            this.drawer.draw(delta);
        }
        if (this.personDrawer != null) {
            this.personDrawer.draw(delta);
        }
        if (this.particleDrawer != null) {
            this.particleDrawer.draw(delta);
        }
        */
        
        batch.draw(img, 0, 0, STAGE_WIDTH, STAGE_HEIGHT);
        
        batch.end();
        //if (this.gameScreen != null) {
        //    if (this.gameScreen.getGameState() != GameState.PAUSED &&
        //        this.gameScreen.getGameState() != GameState.ENDED) {
                stage.act(Math.min(delta, 1 / 30f));
        //    }
        //}
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
		img.dispose();
	}
}
