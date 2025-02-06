package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    // This class will handle every environmental mechanic such as lighting, Rain, Fog etc.

    GamePanel gp;
    public Lighting lighting;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;

    }

    public void setup() {

        lighting = new Lighting(gp); // The number shouldn't be lager than the screen width or screen height
    }

    public void update() {
        lighting.update();
    }

    public void draw(Graphics2D g2) {

        lighting.draw(g2);
    }
}
