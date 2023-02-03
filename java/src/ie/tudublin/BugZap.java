package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet{

    float playerX, playerY, playerWidth;
    float bugX, bugY, bugWidth;
    int score = 0;
    float halfPlayer, halfBug;
    float playerSpeed = 7;

    public void settings()
    {
        size(500, 500);
    }
   
    public void setup() {
        colorMode(HSB);
        background(0);
        smooth();
       

        playerX = width *0.5f; // f casts to a float so we do not get an error
        playerY = height - 100; // 100 pixels from bottom of screen

        playerWidth = 50;
        halfPlayer = playerWidth * 0.5f;
        resetBug();
    }

    public void resetBug(){
        bugY = 50;
        bugWidth = 50;
        halfBug = bugWidth * 0.5f;
        bugX = random(halfBug, width - halfBug);
    }
   
    void drawPlayer(float x, float y, float w){
        noFill();
        stroke(255);
        rectMode(CENTER);
        rect(x, y, w, w);
        line(x, y - halfPlayer, x, y - (halfPlayer * 2));
    }

    void drawBug(float x, float y, float w){
        noFill();
        stroke(255);
        rectMode(CENTER);
        rect(x, y, w, w);
    }

    public void draw(){
        background(0);
        fill(255);
        text("Score: " + score, 50, 100);
        drawPlayer(playerX, playerY, playerWidth);
        drawBug(bugX, bugY, bugWidth);
        if(frameCount % 120 == 0){
            resetBug();
        }
    }

    public void keyPressed()
    {
        if (keyCode == LEFT || key == 'a')
        {
            if(playerX < width + halfPlayer)
            playerX -= playerSpeed;        
        }

        if (keyCode == RIGHT || key == 'd')
        {
            if (playerX < width - halfPlayer) {
                playerX += playerSpeed;
            }              
        }
     
        if (key == ' ')
        {
           
            line(playerX, playerY - halfPlayer, playerX, 0);

            if (playerX > bugX - halfBug && playerX < bugX + halfBug) {
                score++;
                resetBug();
            }
        }
    }  
}