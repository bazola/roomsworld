package com.bazola.roomsworld;

import com.bazola.roomsworld.gamemodel.RoomWorld;

public class PersonDrawer {
    
    private final LibGDXGame libGDXGame;

    private final RoomWorld world;
    
    private final int playerWidth = 16;
    private final int playerHeight = 16;
    
    /*
    private ShaderProgram shaderCombo;
    private ShaderProgram shaderSlime;
    private ShaderState shaderState = ShaderState.NONE;
    private float colorOffset = 0;
    private boolean shouldColorOffsetMoveUp = true;
    private float time = 0;
    private boolean shouldTimeMoveUp = true;
    */
    
    public PersonDrawer(LibGDXGame libGDXGame, RoomWorld world) {
        this.libGDXGame = libGDXGame;
        this.world = world;
        
        /*
        String vertexShader = Gdx.files.internal("vertex.glsl").readString();
        String fragmentShader = Gdx.files.internal("shader.glsl").readString();
        this.shaderCombo = new ShaderProgram(vertexShader,fragmentShader);
        this.shaderCombo.begin();
        this.shaderCombo.setUniformi("u_texture1", 1);
        this.shaderCombo.end();

        this.libGDXGame.yellowOrangeGradient.bind(1);
        
        this.shaderSlime = new ShaderProgram(vertexShader, fragmentShader);
        this.shaderSlime.begin();
        this.shaderSlime.setUniformi("u_texture1", 2);
        this.shaderSlime.end();
        
        this.libGDXGame.greenGradient.bind(2);
        
        //now we need to reset glActiveTexture to zero!!!! since sprite batch does not do this for us
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        */
    }
    
    public void draw(float deltaTime) {
        this.drawPlayer(deltaTime);
        
        //shader could be anything after the player is drawn
        //so it needs to be reset
        //this.shaderState = ShaderState.NONE;
        //this.libGDXGame.batch.setShader(null);
        
        //this.drawEnemies(deltaTime);
        
        //this.drawPlayerParticles(deltaTime);
    }
    
    /*
    private void drawEnemies(float delta) {
        for (EnemyWorm enemy : this.world.enemies) {
            if (enemy.enemy == null) {
                enemy.setAnimatedEnemy(new AnimatedEnemy(this.libGDXGame.wormAnimations, enemy.x, enemy.y));
            }
            
            this.libGDXGame.batch.draw(enemy.enemy.getRegionForTime(delta),
                                       enemy.x,
                                       enemy.y,
                                       enemy.width,
                                       enemy.height);
                                       
        }
    }
    */
    
    private void drawPlayer(float delta) {
        if (this.world.player.character == null) {
            this.world.player.character = new AnimatedCharacter(this.libGDXGame.playerAnimations);
        }
        
        //this.setShaderForPlayer(this.world.player);
        
        
        this.libGDXGame.batch.draw(this.world.player.character.getRegionForTime(delta),
                                   this.world.player.getXPos(),
                                   this.world.player.getYPos(),
                                   this.playerWidth,
                                   this.playerHeight);
                                   
    }
}
