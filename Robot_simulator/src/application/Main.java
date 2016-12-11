package application;
	


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	public static Stage window;
	
	private Timeline timeline = new Timeline();
	private static double speed = 0.01;
	private boolean running = false;
	ArrayList<String> input;
	
	private Pane root;
	
    File outFile = new File("test.txt");
	
	
	
	
    private List<Node> cars = new ArrayList<>();

    private Node robot;
    
    public enum Direction{ //pre defined states!
        UP, DOWN, LEFT, RIGHT, Stop
    }
    
    private Direction direction;
    
    private double absAngle;
	
	private Parent createContent() throws Exception{
		

		
//		Parent root1= FXMLLoader.load(getClass().getResource("play.fxml"));
//		Pane root2 = (Pane) root1.lookup("#paneFrame");
//		Pane root = (Pane) root2.lookup("#panePlay");
		
	    input = new ArrayList<String>();
		
		root= new Pane();
		
        root.setPrefSize(646.0, 473.0);
        
        
        Rectangle rect1 = new Rectangle(270, 38, Color.BLACK);
        rect1.setTranslateY(200);
        
        
        robot = initRobot();
        root.getChildren().add(robot);
        root.getChildren().add(rect1);
        
        
      //lower the speed if want to increase the the movement velocity.
        KeyFrame frame = new KeyFrame(Duration.seconds(speed), event -> {
        	
        	if (robot.getBoundsInParent().intersects(rect1.getBoundsInParent())){
        		if(input.contains("W")){
           		 robot.setTranslateX(robot.getTranslateX() - 2*Math.sin(absAngle*Math.PI/180.0));
           		 robot.setTranslateY(robot.getTranslateY() + 2*Math.cos(absAngle*Math.PI/180.0));
            		input.clear();
        		}
        		
        		if(input.contains("S")){
            		 robot.setTranslateX(robot.getTranslateX() + 2*Math.sin(absAngle*Math.PI/180.0));
             		 robot.setTranslateY(robot.getTranslateY() - 2*Math.cos(absAngle*Math.PI/180.0));
        			input.clear();
        		}
        		
        		if(input.contains("A")){
          		  robot.getTransforms().add(new Rotate(0.5,0,0));
          		  absAngle += 0.5;
          		 input.clear();

        		}
        		
        		if(input.contains("D")){
          		  robot.getTransforms().add(new Rotate(-0.5,0,0));
          		  absAngle -= 0.5;
            	 input.clear();

          		}

        	}


        	if(!input.contains("W") && !input.contains("S"))
        		robot.setTranslateY(robot.getTranslateY());


        	 if(input.contains("W")){
        		 robot.setTranslateX(robot.getTranslateX() + 2*Math.sin(absAngle*Math.PI/180.0));
        		 robot.setTranslateY(robot.getTranslateY() - 2*Math.cos(absAngle*Math.PI/180.0));
    		}

        	  
        	  if(input.contains("S")){
         		 robot.setTranslateX(robot.getTranslateX() - 2*Math.sin(absAngle*Math.PI/180.0));
         		 robot.setTranslateY(robot.getTranslateY() + 2*Math.cos(absAngle*Math.PI/180.0));
     		}
        	  
        	  if(input.contains("A")){
        		  if(input.contains("W") || input.contains("S")){
        			  //robotSpeed*time/(radius + width/2)
        			  robot.getTransforms().add(new Rotate(-1,10,0));
        			  absAngle -= 1;
        		  }else{
        		  robot.getTransforms().add(new Rotate(-0.5,0,0));
        		  absAngle -= 0.5;
        		  }
        	  }
        	  
        	  if(input.contains("D")){
        		  if(input.contains("W") || input.contains("S")){
        			  robot.getTransforms().add(new Rotate(1,10,0));
        			  absAngle += 1;
        			  
        		  }else{
        		  robot.getTransforms().add(new Rotate(0.5,38,0));
        		  absAngle += 0.5;
        		  }
        	  }
 

        	
        });

        timeline.getKeyFrames().addAll(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        return root;
    }
	
	


   

    private Node initRobot() {
        Rectangle rect = new Rectangle(20, 38, Color.GREEN);
        rect.setTranslateX(200.0);
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
        	
        	String code = event.getCode().toString();
        	
            try {
				FileWriter fWriter = new FileWriter(outFile, true);
				PrintWriter pWriter = new PrintWriter(fWriter);


        	

        	if(! input.contains(code)){
        		if("S".equals(code)){
        			if(input.contains("W")){
        				input.remove("W");
        				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        				pWriter.println(timeStamp + " robot stop");

        			}
        			else 
        				input.add(code);
        		}
        		
        		if("W".equals(code)){
        			if(input.contains("S")){
        				input.remove("S");

        			}
        			else
        				input.add(code);
        		}
        		

        	}
    		if("A".equals(code) && !input.contains("A")){
    			//if(! input.contains("W") && ! input.contains("S")){
    			input.add(code);
    			//}
    		}
    		
    		if("D".equals(code) && !input.contains("D")){
    			//if(! input.contains("W") && ! input.contains("S")){
    			input.add(code);
    			//}
    		}
			pWriter.close();
        	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	

        	
        });
        
        scene.setOnKeyReleased(event1 -> {
        	
        	if(input.contains("A")){
        		input.remove("A");
        	}
        	
        	if(input.contains("D")){
        		input.remove("D");
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