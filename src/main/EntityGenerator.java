package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {

        Entity obj = switch (itemName) {
            case OBJ_Axe.objName -> new OBJ_Axe(gp);
            case OBJ_Boots.objName -> new OBJ_Boots(gp);
            case OBJ_Coin_Bronze.objName -> new OBJ_Coin_Bronze(gp);
            case OBJ_Heart.objName -> new OBJ_Heart(gp);
            case OBJ_Key.objName -> new OBJ_Key(gp);
            case OBJ_Lantern.objName -> new OBJ_Lantern(gp);
            case OBJ_Potion_Red.objName -> new OBJ_Potion_Red(gp);
            case OBJ_Shield_Blue.objName -> new OBJ_Shield_Blue(gp);
            case OBJ_Shield_Wood.objName -> new OBJ_Shield_Wood(gp);
            case OBJ_Sword_Normal.objName -> new OBJ_Sword_Normal(gp);
            case OBJ_Tent.objName -> new OBJ_Tent(gp);
            case OBJ_Door.objName -> new OBJ_Door(gp);
            case OBJ_Chest.objName -> new OBJ_Chest(gp);
            case OBJ_ManaCrystal.objName -> new OBJ_ManaCrystal(gp);
            case OBJ_Fireball.objName -> new OBJ_Fireball(gp);
            case OBJ_Rock.objName -> new OBJ_Rock(gp);
            case OBJ_Pickaxe.objName -> new OBJ_Pickaxe(gp);
            case OBJ_Door_Iron.objName -> new OBJ_Door_Iron(gp);
            default -> null;
        };

        return obj;
    }
}
