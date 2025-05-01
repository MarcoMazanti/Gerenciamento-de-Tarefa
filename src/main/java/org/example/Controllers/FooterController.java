package org.example.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class FooterController implements Initializable {
    public ImageView fotoCriador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Circle clip = new Circle(50, 50, 50);
        fotoCriador.setClip(clip);
    }

    public void linkedIn(MouseEvent mouseEvent) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("www.linkedin.com/in/marco-aurélio-consoni-mazanti");
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void gitHub(MouseEvent mouseEvent) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://github.com/MarcoMazanti");
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
