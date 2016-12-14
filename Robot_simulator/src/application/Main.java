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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static Stage window;

	private Timeline timeline = new Timeline();
	private static double FPS = 0.01;
	public static double speed = 2.0;
	public static double distance=0.0;
	private boolean running = false;
	private boolean bounce = false;
	ArrayList<String> input;

	public String code1;
	private Pane root;

	File outFile = new File("command.txt");


	private Node robot;

	private double absAngle;
	
	private Button W = new Button("W");
	private Button S = new Button("S");
	private Button A = new Button("A");
	private Button D = new Button("D");

		private Label label1 = new Label();
		private Label label2 = new Label();

	private Parent createContent() throws Exception {

		// Parent root1= FXMLLoader.load(getClass().getResource("play.fxml"));
		// Pane root2 = (Pane) root1.lookup("#paneFrame");
		// Pane root = (Pane) root2.lookup("#panePlay");

		input = new ArrayList<String>();

		root = new Pane();

		// root.setPrefSize(800.0, 700.0);

		Rectangle rect1 = new Rectangle(910, 3, Color.BLACK);
		rect1.setTranslateY(0);
		
		Rectangle rect2 = new Rectangle(910, 3, Color.BLACK);
		rect2.setTranslateY(500);
		
		Rectangle rect3 = new Rectangle(3, 500, Color.BLACK);
		rect3.setTranslateX(0);	
		rect3.setTranslateY(0);
		
		Rectangle rect4 = new Rectangle(3, 500, Color.BLACK);
		rect4.setTranslateX(908);	
		rect4.setTranslateY(0);

		Image image = new Image("bat.png");
		Rectangle robot = new Rectangle(30, 40, Color.WHITE);
		ImagePattern imagePattern = new ImagePattern(image);
		robot.setTranslateX(200.0);
		robot.setTranslateY(300);
		robot.setFill(imagePattern);
		label1.setTextFill(Color.web("#0076a3"));
		label1.setFont(new Font("Arial", 20));
		label1.setTranslateX(300);
		label1.setTranslateY(520);


		label2.setTextFill(Color.web("#0076a3"));
		label2.setFont(new Font("Arial", 20));
		label2.setTranslateX(300);
		label2.setTranslateY(540);


		W.setTranslateX(100);
		W.setTranslateY(510);
		W.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if (input.contains("S")) {
					input.remove("S");

				} else
					input.add("W");
			}
		});

		S.setTranslateX(100);
		S.setTranslateY(550);
		S.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if (input.contains("W")) {
					input.remove("W");

				} else
					input.add("S");
			}
		});

		A.setTranslateX(60);
		A.setTranslateY(550);
		A.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if (!input.contains("A")) {
					// if(! input.contains("W") && ! input.contains("S")){
					input.add("A");
					// }
				}
			}
		});


		D.setTranslateX(140);
		D.setTranslateY(550);
		D.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if (!input.contains("D")) {
					// if(! input.contains("W") && ! input.contains("S")){
					input.add("D");
					// }
				}
			}
		});



		root.getChildren().add(W);
		root.getChildren().add(A);
		root.getChildren().add(S);
		root.getChildren().add(D);
		root.getChildren().add(label1);
		root.getChildren().add(label2);

		root.getChildren().add(robot);
		root.getChildren().add(rect1);
		root.getChildren().add(rect2);
		root.getChildren().add(rect3);
		root.getChildren().add(rect4);

		// lower the speed if want to increase the the movement velocity.
		KeyFrame frame = new KeyFrame(Duration.seconds(FPS), event -> {
			
			label1.setText("X: "+ Float.toString((float)(robot.getTranslateX())));
			label2.setText("Y: "+ Float.toString((float)(robot.getTranslateY())));

			
			if (robot.getBoundsInParent().intersects(rect1.getBoundsInParent())
					|| robot.getBoundsInParent().intersects(rect2.getBoundsInParent())
					|| robot.getBoundsInParent().intersects(rect3.getBoundsInParent())
					|| robot.getBoundsInParent().intersects(rect4.getBoundsInParent())) {

				bounce = true;

			}else{
				bounce = false;
			}
//			
//			if (CollisionDetectors.PolylineIntersection(robot, rect1) 
//					|| CollisionDetectors.PolylineIntersection(robot, rect2)
//					|| CollisionDetectors.PolylineIntersection(robot, rect3)
//					|| CollisionDetectors.PolylineIntersection(robot, rect4)){
//				bounce = true;
//			}else{
//				bounce = false;
//			}
			
			

			if (bounce) {
				//System.out.println(speed);

				if (input.contains("W")) {
					//System.out.println(speed);
					robot.setTranslateX(robot.getTranslateX() + speed/10.0 * Math.sin(absAngle * Math.PI / 180.0));
					robot.setTranslateY(robot.getTranslateY() - speed/10.0 * Math.cos(absAngle * Math.PI / 180.0));
				}
				

			}
			
			if (bounce) {
				//System.out.println(speed);

				if (input.contains("S")) {
					robot.setTranslateX(robot.getTranslateX() - speed/10.0 * Math.sin(absAngle * Math.PI / 180.0));
					robot.setTranslateY(robot.getTranslateY() + speed/10.0 * Math.cos(absAngle * Math.PI / 180.0));

				}
				
			}
			
			
			
//
//			if (!input.contains("W") && !input.contains("S")) {
//
//				speed = 0.0;
//			}


			try {
				FileWriter fWriter = new FileWriter(outFile, true);
				PrintWriter pWriter = new PrintWriter(fWriter);
			
			if(!bounce){
			if (input.contains("W")) {

				speed= 2.0;
				robot.setTranslateX(robot.getTranslateX() + speed * Math.sin(absAngle * Math.PI / 180.0));
				robot.setTranslateY(robot.getTranslateY() - speed * Math.cos(absAngle * Math.PI / 180.0));
				distance+=speed;
				if(!input.contains("A") && !input.contains("D")){
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
				pWriter.println(timeStamp + " W");
				}
			}
			
			if (input.contains("S")) {
                
				speed=2.0;
				robot.setTranslateX(robot.getTranslateX() - speed * Math.sin(absAngle * Math.PI / 180.0));
				robot.setTranslateY(robot.getTranslateY() + speed * Math.cos(absAngle * Math.PI / 180.0));
				distance+=speed;
				
				if(!input.contains("A") && !input.contains("D")){
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
				pWriter.println(timeStamp + " S");
				}
			}

			if (input.contains("A")) {
				if (input.contains("W") || input.contains("S")) {
					// robotSpeed*time/(radius + width/2)
					robot.getTransforms().add(new Rotate(-1, 15, 0));
					absAngle -= 1;
					
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
					if(input.contains("W")){
						pWriter.println(timeStamp + " WA " + absAngle);
					}else if(input.contains("S")){
						pWriter.println(timeStamp + " SA " + absAngle);
					}		
				} else {
					robot.getTransforms().add(new Rotate(-0.5, 7, 5));
					// radian distance calculate
					distance+=(15.0-7.0)*0.5;
					absAngle -= 0.5;
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
					pWriter.println(timeStamp + " A " + absAngle);
				}
			}

			if (input.contains("D")) {
				if (input.contains("W") || input.contains("S")) {
					robot.getTransforms().add(new Rotate(1, 15, 0));
					absAngle += 1;
					
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
					if(input.contains("W")){
						pWriter.println(timeStamp + " WD " + absAngle);
					}else if(input.contains("S")){
						pWriter.println(timeStamp + " SD " + absAngle);
					}

				} else {
					robot.getTransforms().add(new Rotate(0.5, 23, 5));
					distance+=(23.0-15.0)*0.5;
					absAngle += 0.5;
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
					pWriter.println(timeStamp + " D " + absAngle);
				}
			}
			}
			
			
			pWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//System.out.println(distance);

		});

		timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		return root;
	}

	private Node initRobot() {
		Rectangle rect = new Rectangle(20, 38, Color.GREEN);
		rect.setTranslateX(200.0);
		rect.setTranslateY(600.0 - 39);
		return rect;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("open.fxml"));
		primaryStage.setTitle("Robot simulator");

		window = primaryStage;
		window.setScene(new Scene(root, 600.0, 400.0));

		MainScreen(root);

		window.show();
	}

	public void MainScreen(Parent root) {
		// TODO Auto-generated method stub

	}

	public void start_Btn() throws Exception {

		Scene scene = new Scene(createContent(), 900.0, 570.0);
		key_press(scene);
		timeline.play();
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

	public void key_press(Scene scene) {
		// TODO Auto-generated method stub

		scene.setOnKeyPressed(event -> {

			String code = event.getCode().toString();



				if (!input.contains(code)) {
					if ("S".equals(code)) {
						if (input.contains("W")) {
							input.remove("W");

						} else{
							input.add(code);

						}
					}

					if ("W".equals(code)) {
						if (input.contains("S")) {
							input.remove("S");

						} else{
							input.add(code);

						}
					}

				}
				if ("A".equals(code) && !input.contains("A")) {
					// if(! input.contains("W") && ! input.contains("S")){
					input.add(code);

					// }
				}

				if ("D".equals(code) && !input.contains("D")) {
					// if(! input.contains("W") && ! input.contains("S")){
					input.add(code);

					// }
				}
				

			

		});

		scene.setOnKeyReleased(event1 -> {

			if (input.contains("A")) {
				input.remove("A");
			}

			if (input.contains("D")) {
				input.remove("D");
			}
		});

	}
	
	public void inBound(){
		
        if (robot.getTranslateX() < 0) {
            robot.setTranslateX(0);

        } else if (robot.getTranslateX() > 800 - 50) {
            robot.setTranslateX(800 - 50);

        }

        if (robot.getTranslateY() < 0) {
            robot.setTranslateY(0);
        } else if (robot.getTranslateY() > 400 - 50) {
            robot.setTranslateY(400 - 50);

        }
	}
	

	public void quit() {
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}