package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{

    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/objects/potion_red", (int)(gp.tileSize / 1.5), (int)(gp.tileSize / 1.5));
        description = "[Red Potion]\nHeals you by " + value + ".";
    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = value + " health have been recovered by \ndrinking the " + name + ".";

        entity.life += value;

        gp.playSE(2);
    }
}
