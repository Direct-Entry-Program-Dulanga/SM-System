package controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {

    public static final int NAV_ICON_NONE = 0;
    public static final int NAV_ICON_BACK = 1;
    public static final int NAV_ICON_HOME = 2;

    public ImageView imgNav;
    public ImageView imgMinimize;
    public ImageView imgClose;
    public AnchorPane pneAppBar;
    public Label lblTitle;
    public AnchorPane pneStage;
    private double xMousePos;
    private double yMousePos;

    public void initialize() {
        initWindow();
    }

    public void navigate(String title, String url, int icon) {
        try {
            imgNav.setVisible(true);

            /* Let's set the icon */
            switch (icon) {
                case NAV_ICON_NONE:
                    imgNav.setVisible(false);
                    break;
                case NAV_ICON_HOME:
                    imgNav.setImage(new Image("/view/assets/icons/home.png"));
                    break;
                case NAV_ICON_BACK:
                    imgNav.setImage(new Image("/view/assets/icons/back.png"));
                    break;
            }
            Parent root = FXMLLoader.load(this.getClass().getResource(url));
            pneStage.getChildren().clear();
            pneStage.getChildren().add(root);
            lblTitle.setText(title);
            Stage primaryStage = (Stage) (pneStage.getScene().getWindow());

            Platform.runLater(() -> {
                primaryStage.sizeToScene();
                primaryStage.centerOnScreen();
//                    Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
//                    primaryStage.setX((visualBounds.getWidth() - primaryStage.getWidth())/2);
//                    primaryStage.setY((visualBounds.getHeight() - primaryStage.getHeight())/2);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initWindow() {
        lblTitle.setMouseTransparent(true);
        imgNav.setVisible(false);

        Platform.runLater(() -> {
            lblTitle.setText(((Stage) (imgClose.getScene().getWindow())).getTitle());
        });

        pneAppBar.setOnMousePressed(event -> {
            xMousePos = event.getX();
            yMousePos = event.getY();
        });

        pneAppBar.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                Window mainWindow = imgMinimize.getScene().getWindow();
                mainWindow.setX(event.getScreenX() - xMousePos);
                mainWindow.setY(event.getScreenY() - yMousePos);
            }
        });

        imgClose.setOnMouseEntered(event -> imgClose.setImage(new Image("/view/assets/icons/close-hover.png")));
        imgClose.setOnMouseExited(event -> imgClose.setImage(new Image("/view/assets/icons/close.png")));
        imgClose.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).close());

        imgMinimize.setOnMouseEntered(event -> imgMinimize.setImage(new Image("/view/assets/icons/minimize-hover.png")));
        imgMinimize.setOnMouseExited(event -> imgMinimize.setImage(new Image("/view/assets/icons/minimize.png")));
        imgMinimize.setOnMouseClicked(event -> ((Stage) (imgClose.getScene().getWindow())).setIconified(true));
    }

}
