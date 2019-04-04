package dk.sdu.mmmi.cbse.common.data;

import java.awt.Image;
import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private Image[] sprites;
    private float dy, dx, x, y;
    private float radians;
    
}
