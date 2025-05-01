package org.example.Controllers.PopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorInsertController {
    @FXML
    public Label textoAviso;

    public void setAviso(String aviso) {
        textoAviso.setText(aviso);
    }
}
