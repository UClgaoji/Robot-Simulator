package application;
	


import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;


public class Main extends Application {
	
	public static Stage window;
	
	private Timeline timeline = new Timeline();
	private static double speed = 0.01;
	private boolean running = false;
	ArrayList<String> input;
	
	private Pane root;
	
	
	
	
    private List<Node> cars = new ArrayList<>();

    private Node robot;
    
    public enum Direction{ //pre defined states!
        UP, DOWN, LEFT, RIGHT, Stop
    }
    
    private Direction direction;
	
	private Parent createContent() throws Exception{
		

		
//		Parent root1= FXMLLoader.load(getClass().getResource("play.fxml"));
//		Pane root2 = (Pane) root1.lookup("#paneFrame");
//		Pane root = (Pane) root2.lookup("#panePlay");
		
	    input = new ArrayList<String>();
		
		root= new Pane();
		
        root.setPrefSize(646.0, 473.0);
        
        robot = initRobot();
        root.getChildren().add(robot);
        
        
      //lower the speed if want to increase the the movement velocity.
        KeyFrame frame = new KeyFrame(Duration.seconds(speed), event -> {


        	
          switch (direction) {
          case UP:

    		robot.setTranslateY(robot.getTranslateY() - 4);
    		break;
    		
          case DOWN:
        	  
        	  
        	  robot.setTranslateY(robot.getTranslateY() + 4);
        	  break;
        	  
		default:
			break;
    	}
        	
        	
        });

        timeline.getKeyFrames().addAll(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        return root;
    }
	
	


   

    private Node initRobot() {
        Rectangle rect = new Rectangle(38, 38, Color.GREEN);
        rect.setTranslateY(473.0 - 39);
        return rect;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

    	Parent root = FXMLLoader.load(getClass().getResource("open.fxml"));
    	primaryStage.setTitle("Robot simulator");
    	
    	window = primaryStage;
    	window.setScene(new Scene(root,600.0, 400.0));
    	
    	MainScreen(root);


        primaryStage.show();
    }

    public void MainScreen(Parent root) {
		// TODO Auto-generated method stub
    	
		
	}
    
    public void start_Btn() throws Exception{
    	
    	Scene scene = new Scene(createContent(),646.0, 473.0);
    	key_press(scene);
    	direction=Direction.Stop;
    	timeline.play();
    	window.setScene(scene);
    	window.show();
    }

	public void key_press(Scene scene) {
		// TODO Auto-generated method stub
		
        scene.setOnKeyPressed(event -> {
//        	if(event.getCode()==KeyCode.W){
//        		robot.setTranslateY(robot.getTranslateY() - 20);
//        	}
//        	
//        	if(event.getCode()==KeyCode.S){
//        		robot.setTranslateY(robot.getTranslateY() + 20);
//        	}
//        
//        	if(event.getCode()==KeyCode.A){
//        		robot.setTranslateX(robot.getTranslateX() - 20);
//        	}
//        	
//        	if(event.getCode()==KeyCode.D){
//        		robot.setTranslateX(robot.getTranslateX() + 20);
//        	}
        	
        	 switch (event.getCode()) {
             case W:

                     direction = Direction.UP;
                 break;

             case S:

                     direction = Direction.DOWN;
                 break;
        	 }
        	
        });
		
		
	}
	
	public void quit(){
		System.exit(0);
	}

	public static void main(String[] args) {
        launch(args);
    }
}