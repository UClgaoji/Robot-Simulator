package application;

import javafx.animation.PauseTransition;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;

import javafx.stage.Stage;

import javafx.util.Duration;

public class PauseTDemo extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      String[] imageNames =
      {
         "elephant.jpg",
         "giraffe.jpg",
         "monkey1.jpg",
         "monkey2.jpg",
         "tiger.jpg"
      };
      ImageView[] iv = new ImageView[imageNames.length];
      for (int i = 0; i < imageNames.length; i++)
         iv[i] = new ImageView(new Image("file:res/" + imageNames[i]));

      Group root = new Group();
      Scene scene = new Scene(root, 0, 0, Color.BLACK);

      PauseTransition pt = new PauseTransition();
      pt.setDuration(new Duration(6000));
      pt.setOnFinished(e ->
                       {
                          int index = (int) rnd(imageNames.length);
                          root.getChildren().clear();
                          iv[index].setX(rnd((scene.getWidth() - 
                                             iv[index].getImage().getWidth())));
                          iv[index].setY(rnd((scene.getHeight() - 
                                             iv[index].getImage().getHeight())));
                          root.getChildren().add(iv[index]);
                          pt.play();
                       });

      primaryStage.setTitle("PauseTransition Demo");
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setFullScreen(true);

      pt.play();

   }

   public double rnd(double limit)
   {
      return Math.random() * limit;
   }
}