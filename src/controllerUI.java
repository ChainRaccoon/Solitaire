import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class controllerUI {
    @FXML
    private StackPane pila1;


    private double ejeX;
    private double ejeY;

    @FXML
    public void iniciar(){
        hacerArrastrable(pila1);
    }

    private void hacerArrastrable(StackPane carta){
        carta.setOnMousePressed(event -> {
            ejeX = event.getSceneX() - carta.getLayoutX();
            ejeY = event.getSceneY() - carta.getLayoutY();
            carta.toFront();
        });

        carta.setOnMouseDragged(event -> {
            // Mover la carta siguiendo el cursor
            carta.setLayoutX(event.getSceneX() - ejeX);
            carta.setLayoutY(event.getSceneY() - ejeY);
        });
    }
}
