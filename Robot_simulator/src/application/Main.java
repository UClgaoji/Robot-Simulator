package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static Stage window;

	private Timeline timeline = new Timeline();
	private static double FPS = 0.01;
	public static double speed = 2.0;
	public static double distance = 0.0;
	public static double battery = 100.0;
	public int j = 0;
	public int m=1;
	private boolean bounce = false;
	private boolean disable = true;
	private boolean show = false;
	private boolean recharged = false;
	
	private boolean jokerd=false;
	private boolean joker1d=false;
	private boolean joker2d=false;
	
	ArrayList<String> input;

	ArrayList<Double> X_pos = new ArrayList<Double>();
	ArrayList<Double> Y_pos = new ArrayList<Double>();
	ArrayList<Double> Ang = new ArrayList<Double>();
	ArrayList<Double> angleb = new ArrayList<Double>();
	
	public List<Node> bullet = new ArrayList<>();
	

	public String code1;
	private Pane root;

	File outFile = new File("command.txt");

	public TextArea text_area;
	public Button btn0;
	public Button start_btn;
	public Button dem_btn;

	private double absAngle;

	private Button W = new Button("W");
	private Button S = new Button("S");
	private Button A = new Button("A");
	private Button D = new Button("D");

	private Label label1 = new Label();
	private Label label2 = new Label();
	private Label label3 = new Label();
	private Label label4 = new Label();
	private Label label5 = new Label();
	
	public MediaPlayer mediaPlayer;
	public MediaPlayer mediaPlayer1;
	public MediaPlayer mediaPlayer2;


	private Parent createContent() throws Exception {



		input = new ArrayList<String>();

		root = new Pane();



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
		
		Rectangle rect5 = new Rectangle(25, 300, Color.BLACK);
		rect5.setTranslateX(150);
		rect5.setTranslateY(200);
		
		Rectangle rect6 = new Rectangle(25, 300, Color.BLACK);
		rect6.setTranslateX(300);
		rect6.setTranslateY(0);
		
		Rectangle rect7 = new Rectangle(25, 300, Color.BLACK);
		rect7.setTranslateX(450);
		rect7.setTranslateY(200);
		
		Rectangle rect8 = new Rectangle(300, 25, Color.BLACK);
		rect8.setTranslateX(450);
		rect8.setTranslateY(200);
		
		Rectangle rect9 = new Rectangle(25, 175, Color.BLACK);
		rect9.setTranslateX(750);
		rect9.setTranslateY(200);
		
		Rectangle rect10 = new Rectangle(190, 25, Color.BLACK);
		rect10.setTranslateX(580);
		rect10.setTranslateY(350);
		
	    Image img = new Image("joker.png");
	    ImageView joker = new ImageView(img);
	    joker.setTranslateX(500);
	    joker.setTranslateY(350);
	    

	    ImageView joker1 = new ImageView(img);
	    joker1.setTranslateX(200);
	    joker1.setTranslateY(300);
	    

	    ImageView joker2 = new ImageView(img);
	    joker2.setTranslateX(800);
	    joker2.setTranslateY(200);
	  
	    Image img1 = new Image("batsign.png");
	    ImageView bat = new ImageView(img1);
	    bat.setTranslateX(750);
	    bat.setTranslateY(40);
	    
	    Image img2= new Image("ucl.png");
	    ImageView ucl = new ImageView(img2);
	    ucl.setTranslateX(630);
	    ucl.setTranslateY(220);
		
		


		Image image = new Image(Xml.cartype + ".png");
		Rectangle robot = new Rectangle(Xml.width,Xml.length, Color.WHITE);
		ImagePattern imagePattern = new ImagePattern(image);
		

		
		robot.setTranslateX(30.0);
		robot.setTranslateY(400);
		robot.setFill(imagePattern);

		label1.setTextFill(Color.web("#0076a3"));
		label1.setFont(new Font("Arial", 20));
		label1.setTranslateX(300);
		label1.setTranslateY(520);

		label2.setTextFill(Color.web("#0076a3"));
		label2.setFont(new Font("Arial", 20));
		label2.setTranslateX(300);
		label2.setTranslateY(540);

		label3.setTextFill(Color.web("#0076a3"));
		label3.setFont(new Font("Arial", 20));
		label3.setTranslateX(450);
		label3.setTranslateY(520);

		label4.setTextFill(Color.web("#0076a3"));
		label4.setFont(new Font("Arial", 20));
		label4.setTranslateX(450);
		label4.setTranslateY(540);
		
		label5.setTextFill(Color.web("#0076a3"));
		label5.setFont(new Font("Arial", 20));
		label5.setTranslateX(600);
		label5.setTranslateY(540);

		W.setTranslateX(100);
		W.setTranslateY(510);


		S.setTranslateX(100);
		S.setTranslateY(550);


		A.setTranslateX(60);
		A.setTranslateY(550);


		D.setTranslateX(140);
		D.setTranslateY(550);
		



		root.getChildren().add(W);
		root.getChildren().add(A);
		root.getChildren().add(S);
		root.getChildren().add(D);
		root.getChildren().add(label1);
		root.getChildren().add(label2);
		root.getChildren().add(label3);
		root.getChildren().add(label4);
		root.getChildren().add(label5);
		
		root.getChildren().add(robot);
		root.getChildren().add(rect1);
		root.getChildren().add(rect2);
		root.getChildren().add(rect3);
		root.getChildren().add(rect4);
		root.getChildren().add(rect5);
		root.getChildren().add(rect6);
		root.getChildren().add(rect7);	
		root.getChildren().add(rect8);
		root.getChildren().add(rect9);
		root.getChildren().add(rect10);
		
		root.getChildren().add(joker);
		root.getChildren().add(joker1);
		root.getChildren().add(joker2);
		root.getChildren().add(bat);
		root.getChildren().add(ucl);
		


		// lower the speed if want to increase the the movement velocity.
		KeyFrame frame = new KeyFrame(Duration.seconds(FPS), event -> {

			label1.setText("X: " + Float.toString((float) (robot.getTranslateX())));
			label2.setText("Y: " + Float.toString((float) (robot.getTranslateY())));
			
		



			if (! show) {
				
				label3.setText("Distance: " + Float.toString((float) (distance)));
				label4.setText("Speed: " + Float.toString((float) (speed)));
				label5.setText("Battery: " + battery);
				

				if (robot.getBoundsInParent().intersects(rect1.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect2.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect3.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect4.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect5.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect6.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect7.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect8.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect9.getBoundsInParent())
						|| robot.getBoundsInParent().intersects(rect10.getBoundsInParent())) {

					bounce = true;
					mediaPlayer.play();

				} else {
					bounce = false;
				}
				
				
				if ((robot.getBoundsInParent().intersects(joker.getBoundsInParent()) && !jokerd)
						|| (robot.getBoundsInParent().intersects(joker1.getBoundsInParent())&& !joker1d)
						|| (robot.getBoundsInParent().intersects(joker2.getBoundsInParent())&& !joker2d)) {

					
					battery-=0.1;

				} else {

				}
				
				if (robot.getBoundsInParent().intersects(bat.getBoundsInParent())) {

					battery+=0.2;
					recharged=true;

					
					
				} else {

				}
				
				// 
				if(battery>10.0 && recharged){
				mediaPlayer2.pause();	
				}
				else if(battery<10.0 && battery>=0.0){
					mediaPlayer2.play();
					
				}
				else if(battery<0.0){
					input.clear();
					battery=0.0;
					lose();
					timeline.stop();
					mediaPlayer2.stop();
				}


				if (bounce) {
					


					input.clear();

					robot.setTranslateX(X_pos.get(X_pos.size() - 5));
					robot.setTranslateY(Y_pos.get(Y_pos.size() - 5));
					absAngle = absAngle - (Ang.get(Ang.size() - 1) - Ang.get(Ang.size() - 5));
					robot.getTransforms().add(new Rotate(-(Ang.get(Ang.size() - 1) - Ang.get(Ang.size() - 5)), 15, 0));

					for (int i = 0; i < 4; i++) {
						X_pos.remove(X_pos.size() - 1);
						Y_pos.remove(Y_pos.size() - 1);
						Ang.remove(Ang.size() - 1);
					}

					mediaPlayer.setOnEndOfMedia(new Runnable() {
					    @Override
					    public void run() {
					        // actions here e.g.:
					        mediaPlayer.pause();
					    }
					});

				}

				if (!input.contains("W") && !input.contains("S")) {

					speed = 0.0;
					X_pos.add(robot.getTranslateX());
					Y_pos.add(robot.getTranslateY());
					Ang.add(absAngle);
				}

				try {
					FileWriter fWriter = new FileWriter(outFile, true);
					PrintWriter pWriter = new PrintWriter(fWriter);

					if (!bounce) {
						if (input.contains("W")) {

							speed = Xml.speed;
							robot.setTranslateX(robot.getTranslateX() + speed * Math.sin(absAngle * Math.PI / 180.0));
							robot.setTranslateY(robot.getTranslateY() - speed * Math.cos(absAngle * Math.PI / 180.0));
							distance += speed;
							battery -=0.01;

							X_pos.add(robot.getTranslateX());
							Y_pos.add(robot.getTranslateY());
							Ang.add(absAngle);

							if (!input.contains("A") && !input.contains("D")) {
								String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
								pWriter.println(timeStamp + " W " + robot.getTranslateX() + " " + robot.getTranslateY()
										+ " " + absAngle);
							}
						}

						if (input.contains("S")) {

							speed = Xml.speed;
							robot.setTranslateX(robot.getTranslateX() - speed * Math.sin(absAngle * Math.PI / 180.0));
							robot.setTranslateY(robot.getTranslateY() + speed * Math.cos(absAngle * Math.PI / 180.0));
							distance += speed;
							battery -=0.01;

							X_pos.add(robot.getTranslateX());
							Y_pos.add(robot.getTranslateY());
							Ang.add(absAngle);

							if (!input.contains("A") && !input.contains("D")) {
								String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
								pWriter.println(timeStamp + " S " + robot.getTranslateX() + " " + robot.getTranslateY()
										+ " " + absAngle);
							}
						}

						if (input.contains("A")) {
							if (input.contains("W") || input.contains("S")) {
								// robotSpeed*time/(radius + width/2)
								robot.getTransforms().add(new Rotate(-1.5, Xml.width/2.0, 0));
								absAngle -= 1.5;
								battery -=0.01;

								X_pos.add(robot.getTranslateX());
								Y_pos.add(robot.getTranslateY());
								Ang.add(absAngle);

								String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
								if (input.contains("W")) {
									pWriter.println(timeStamp + " WA " + robot.getTranslateX() + " "
											+ robot.getTranslateY() + " " + absAngle);
								} else if (input.contains("S")) {
									pWriter.println(timeStamp + " SA " + robot.getTranslateX() + " "
											+ robot.getTranslateY() + " " + absAngle);
								}
							} else {
								robot.getTransforms().add(new Rotate(-1.0, Xml.length/4.0, Xml.length/5.0));
								// radian distance calculate
								distance += 2.0 * 1.0;
								absAngle -= 1;
								battery -=0.01;

								X_pos.add(robot.getTranslateX());
								Y_pos.add(robot.getTranslateY());
								Ang.add(absAngle);

								String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
								pWriter.println(timeStamp + " A " + robot.getTranslateX() + " " + robot.getTranslateY()
										+ " " + absAngle);
							}
						}

						if (input.contains("D")) {
							if (input.contains("W") || input.contains("S")) {
								robot.getTransforms().add(new Rotate(1.5, Xml.width/2.0, 0));
								absAngle += 1.5;
								battery -=0.01;

								X_pos.add(robot.getTranslateX());
								Y_pos.add(robot.getTranslateY());
								Ang.add(absAngle);

								String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
								if (input.contains("W")) {
									pWriter.println(timeStamp + " WD " + robot.getTranslateX() + " "
											+ robot.getTranslateY() + " " + absAngle);
								} else if (input.contains("S")) {
									pWriter.println(timeStamp + " SD " + robot.getTranslateX() + " "
											+ robot.getTranslateY() + " " + absAngle);
								}

							} else {
								robot.getTransforms().add(new Rotate(1.0, Xml.width-Xml.width/4.0, Xml.length/5.0));
								distance += 2.0 * 1.0;
								absAngle += 1.0;
								battery-=0.01;


								X_pos.add(robot.getTranslateX());
								Y_pos.add(robot.getTranslateY());
								Ang.add(absAngle);

								String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
								pWriter.println(timeStamp + " D " + robot.getTranslateX() + " " + robot.getTranslateY()
										+ " " + absAngle);
							}
						}
						
						if(robot.getBoundsInParent().intersects(ucl.getBoundsInParent())){
							win();
							timeline.stop();
						}
						


						if(input.contains("F")){
							
							bullet.add(spawnBullet(robot.getTranslateX(),robot.getTranslateY()));
							battery-=0.01;
							angleb.add(absAngle);
	
							
						}
						
						int c=0;
						for(Node bullets:bullet){
							bullets.setTranslateX(bullets.getTranslateX() + 4.0 * Math.sin(angleb.get(c) * Math.PI / 180.0));
							bullets.setTranslateY(bullets.getTranslateY() - 4.0 * Math.cos(angleb.get(c) * Math.PI / 180.0));
							
							
							if(bullets.getBoundsInParent().intersects(joker.getBoundsInParent())){
								root.getChildren().remove(joker);
								jokerd=true;
							}else if(bullets.getBoundsInParent().intersects(joker1.getBoundsInParent())){
								root.getChildren().remove(joker1);
								joker1d=true;
							}else if(bullets.getBoundsInParent().intersects(joker2.getBoundsInParent())){
								root.getChildren().remove(joker2);
								joker2d=true;
							}
							
							if(	bullets.getBoundsInParent().intersects(rect1.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect2.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect3.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect4.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect5.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect6.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect7.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect8.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect9.getBoundsInParent())
									|| bullets.getBoundsInParent().intersects(rect10.getBoundsInParent())){
								root.getChildren().remove(bullet.get(c));
							}

							
							c++;
						}
						
					}

					pWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			} else{
				
				if(j<Txt.X_posd.size()-1){
				j++;
				
				robot.setTranslateX(Txt.X_posd.get(j));
				robot.setTranslateY(Txt.Y_posd.get(j));
				robot.getTransforms()
						.add(new Rotate(Txt.Angd.get(j) - ((j - 1 >= 0) ? Txt.Angd.get(j - 1) : 0.0), 15, 0));
				}else{
					//
				}
			}

		});

		timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("open.fxml"));
		primaryStage.setTitle("Robot simulator");

		window = primaryStage;
		window.setScene(new Scene(root, 600.0, 400.0));
		
		String musicFile = "dark.mp3";     // For example

		Media sound1 = new Media(new File(musicFile).toURI().toString());
		mediaPlayer1 = new MediaPlayer(sound1);
		mediaPlayer1.play();

		MainScreen(root);

		window.show();
	}

	public void MainScreen(Parent root) {


		start_btn = (Button) root.lookup("#start_btn");
		text_area = (TextArea) root.lookup("#xml");
		btn0 = (Button) root.lookup("#haha");
		dem_btn = (Button) root.lookup("#show");

		start_btn.setDisable(disable);
		dem_btn.setDisable(disable);

		btn0.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Xml.xml(text_area);
				disable = false;
				start_btn.setDisable(disable);
				dem_btn.setDisable(disable);
			}
		});

		dem_btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Txt.txt();
				//disable = false;
				//start_btn.setDisable(disable);
				show = true;
				try {
					start_Btn();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(Txt.Y_posd.get(0));
			}
		});

	}
	
    private Node spawnBullet(double x, double y) {
        Rectangle rect = new Rectangle(5, 5, Color.RED);
  
        rect.setTranslateX(x);
        rect.setTranslateY(y);

        root.getChildren().add(rect);
        return rect;
   }
    
    private void win(){
    	String win = "YOU WIN";

        HBox hBox = new HBox();
        hBox.setTranslateX(300);
        hBox.setTranslateY(250);
        root.getChildren().add(hBox);

        for (int i = 0; i < win.toCharArray().length; i++) {
            char letter = win.charAt(i);

            Text text = new Text(String.valueOf(letter));
            
            text.setFont(Font.font ("Verdana", 60));
            text.setFill(Color.ORANGE);
            text.setOpacity(0);

            hBox.getChildren().add(text);

            FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
            ft.setToValue(1);
            ft.setDelay(Duration.seconds(i * 0.15));
            ft.play();
        }
    }
    
    private void lose(){
    	String win = "YOU LOSE";

        HBox hBox = new HBox();
        hBox.setTranslateX(300);
        hBox.setTranslateY(250);
        root.getChildren().add(hBox);

        for (int i = 0; i < win.toCharArray().length; i++) {
            char letter = win.charAt(i);

            Text text = new Text(String.valueOf(letter));
            
            text.setFont(Font.font ("Verdana", 60));
            text.setFill(Color.RED);
            text.setOpacity(0);

            hBox.getChildren().add(text);

            FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
            ft.setToValue(1);
            ft.setDelay(Duration.seconds(i * 0.15));
            ft.play();
        }
    }
	
	

	public void start_Btn() throws Exception {

		Scene scene = new Scene(createContent(), 900.0, 570.0);
		key_press(scene);
		timeline.play();
		window.setScene(scene);
		window.setResizable(false);
		window.show();

		String musicFile = "metal.mp3";     // For example
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		
		String musicFile1 = "battery.mp3";     // For example
		Media sound2 = new Media(new File(musicFile1).toURI().toString());
		mediaPlayer2 = new MediaPlayer(sound2);
		mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
		
		if(bounce){
			mediaPlayer.play();
		}
		

	}

	public void key_press(Scene scene) {
	

		scene.setOnKeyPressed(event -> {

			String code = event.getCode().toString();

			if (!input.contains(code)) {
				if ("S".equals(code)) {
					if (input.contains("W")) {
						input.remove("W");

					} else {
						input.add(code);

					}
				}

				if ("W".equals(code)) {
					if (input.contains("S")) {
						input.remove("S");

					} else {
						input.add(code);

					}
				}

			}
			
			if ("A".equals(code) && !input.contains("A")) {

				input.add(code);

			}

			if ("D".equals(code) && !input.contains("D")) {

				input.add(code);

				
			}
			
			if ("F".equals(code) && !input.contains("F")){
				input.add(code);
			}

		});

		scene.setOnKeyReleased(event1 -> {

			if (input.contains("A")) {
				input.remove("A");
			}

			if (input.contains("D")) {
				input.remove("D");
			}
			
			if (input.contains("F")) {
				input.remove("F");
			}
		});

	}

	public void quit() {
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}