package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    // This class will handle every environmental mechanic such as Lighting, Rain, Fog etc.

    GamePanel gp;
    Lighting Lighting;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;

    }

    public void setup() {

        Lighting = new Lighting(gp); // The number shouldn't be lager than the screen width or screen height
    }

    public void update() {
        Lighting.update();
    }

    public void draw(Graphics2D g2) {

        Lighting.draw(g2);
    }
}
