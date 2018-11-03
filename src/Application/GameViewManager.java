package Application;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ObservableList<Node> snake;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;	
	private AnimationTimer gameTimer;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "application/gamebg.jpeg";
//	private StackPane blocksPane;
	
	private final static String BALL_IMAGE = "application/ball_bowling2.png";
	private ImageView[] balls;
	private Random randomPositionGenerator;
	
	private ImageView coin,shield,block_destroyer,magnet,speedup,slomo,multiplier;
	private Rectangle wall;
	private Palette colors;

	private SmallInfoLabel coinLabel;
	private int coins;
	private final static String COIN_IMAGE 	= "application/coin.png";
	private final static String SHIELD_IMAGE 	= "application/icons8-shield-48.png";
	private final static String BLOCK_DESTROYER_IMAGE 	= "application/icons8-bulldozer-48.png";
	private final static String MAGNET_IMAGE 	= "application/icons8-magnetic-48.png";
	private final static String SPEEDUP_IMAGE 	= "application/icons8-voltage-64.png";
	private final static String SLOMO_IMAGE 	= "application/icons8-snail-48.png";
	private final static String MULTIPLIER_IMAGE 	= "application/icons8-diamond-48.png";


	private final static String BLOCK_IMAGE = "application/red_button07.png";
//	private ImageView[] blocks;
//	private GameRectangle[] blocks;
	private GameRectangle[] blocks;

	//private StackPane[] blocksPane;
	
	private final static int COIN_RADIUS = 12;
	private final static int SNAKE_RADIUS = 10;
	private final static int BALL_RADIUS = 20;
	private final static int BLOCK_RADIUS = 30;
	private final static int WALL_RADIUS = 30;


	
	GameViewManager() {
		initializeStage();
		createKeyListeners();
		randomPositionGenerator = new Random();
	}

	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}
				else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}
				else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
			}
		});
	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createSnake();
		colors = new Palette();
		createGameElements();
		createGameLoop();
		gameStage.setTitle("Snakes vs Blocks");
		gameStage.show();
	}
	
	private void createGameElements() {
		coin = new ImageView(COIN_IMAGE);
		coin.setFitHeight(25);
		coin.setFitWidth(25);
		//coin.setPreserveRatio(true);
		setNewElementPosition(coin);
		gamePane.getChildren().add(coin);
		
		coinLabel = new SmallInfoLabel("POINTS: 00");
		coinLabel.setLayoutX(460);
		coinLabel.setLayoutY(20);
		gamePane.getChildren().add(coinLabel);
		
		balls = new ImageView[4];
		for (int i=0; i<balls.length; i++) {
			balls[i] = new ImageView(BALL_IMAGE);
			setNewElementPosition(balls[i]);
			gamePane.getChildren().add(balls[i]);
		}
		
//		blocks = new ImageView[10];
//
//		for (int i=0; i<blocks.length; i++) {
//			blocks[i] = new ImageView(BLOCK_IMAGE);
//			blocks[i].setFitHeight(60);
//			blocks[i].setFitWidth(60);
//
//			blocks[i].setLayoutY(100);
//			blocks[i].setLayoutX(60 * i);
//
//			gamePane.getChildren().add(blocks[i]);
//		}

		blocks = new GameRectangle[10];

		for (int i=0; i<blocks.length; i++) {
			blocks[i] = new GameRectangle(i,gamePane,colors);

//			blocks[i].setHeight(60);
//			blocks[i].setHeight(60);

//			blocks[i].setLayoutY(100);
//			blocks[i].setLayoutX(60 * i);

//			blocks[i].createRectangle("20",gamePane);
//			gamePane.getChildren().add(blocks[i]);
		}

//		for (int i=0; i<blocks.length; i++) {
			wall = new Rectangle();

			wall.setWidth(15);
			wall.setFill(Color.WHITE);

			Random r = new Random();
			setNewWallPosition(wall);
            setNewWallDimension(wall);

			gamePane.getChildren().add(wall);
//		}

            shield = new ImageView(SHIELD_IMAGE);
            shield.setFitHeight(25);
            shield.setFitWidth(25);
            //coin.setPreserveRatio(true);
            setNewElementPosition(shield);
            gamePane.getChildren().add(shield);

            block_destroyer = new ImageView(BLOCK_DESTROYER_IMAGE);
            block_destroyer.setFitHeight(25);
            block_destroyer.setFitWidth(25);
            //coin.setPreserveRatio(true);
            setNewElementPosition(block_destroyer);
            gamePane.getChildren().add(block_destroyer);

            magnet = new ImageView(MAGNET_IMAGE);
            magnet.setFitHeight(25);
            magnet.setFitWidth(25);
            //coin.setPreserveRatio(true);
            setNewElementPosition(magnet);
            gamePane.getChildren().add(magnet);

            speedup = new ImageView(SPEEDUP_IMAGE);
            speedup.setFitHeight(25);
            speedup.setFitWidth(25);
            //coin.setPreserveRatio(true);
            setNewElementPosition(speedup);
            gamePane.getChildren().add(speedup);

            slomo = new ImageView(SLOMO_IMAGE);
            slomo.setFitHeight(25);
            slomo.setFitWidth(25);
            //coin.setPreserveRatio(true);
            setNewElementPosition(slomo);
            gamePane.getChildren().add(slomo);

            multiplier = new ImageView(MULTIPLIER_IMAGE);
            multiplier.setFitHeight(25);
            multiplier.setFitWidth(25);
            //coin.setPreserveRatio(true);
            setNewElementPosition(multiplier);
            gamePane.getChildren().add(multiplier);



	}
	
	private void moveGameElements() {
		
		coin.setLayoutY(coin.getLayoutY() + 5);

        shield.setLayoutY(shield.getLayoutY() + 5);

        block_destroyer.setLayoutY(block_destroyer.getLayoutY() + 5);

        magnet.setLayoutY(magnet.getLayoutY() + 5);

        speedup.setLayoutY(speedup.getLayoutY() + 5);

        slomo.setLayoutY(slomo.getLayoutY() + 5);

        multiplier.setLayoutY(multiplier.getLayoutY() + 5);


		
		for (int i=0; i<balls.length; i++) {
			balls[i].setLayoutY(balls[i].getLayoutY()+7);
			balls[i].setRotate(balls[i].getRotate()+4);
		}
		
		for (int i=0; i<blocks.length; i++) {
			blocks[i].setLayoutY(blocks[i].getLayoutY()+7);
		}

		for (int i=0; i<blocks.length; i++) {
			wall.setLayoutY(wall.getLayoutY()+0.5);
		}
 	}
	
	private void elementBelowScreen() {
		
		if (coin.getLayoutY() > 1200) {
			setNewElementPosition(coin);
		}

		if (wall.getLayoutY() > 1200) {
			setNewWallPosition(wall);
			setNewWallDimension(wall);
		}
		
		for (int i=0; i<balls.length; i++) {
			if (balls[i].getLayoutY() > 900) {
				setNewElementPosition(balls[i]);
			}
		}
		
		//Logic for placing blocks begins 
		int no_of_blocks = randomPositionGenerator.nextInt(10);
		
		int y_coordinate = randomPositionGenerator.nextInt(400);
		
		int[] x_coordinates = new int[10];
		boolean[] occupiedCoordinates = new boolean[10];
		
		for (int i=0; i<10; i++) {
			x_coordinates[i] = 60*i;
			occupiedCoordinates[i] = false;
		}
		
		boolean placeBlock = true;
		
		for (int i=0; i<blocks.length; i++) {
			if (blocks[i].getLayoutY() < 900) {
				placeBlock = false;
			}
		}
		
		if (placeBlock) {
			int x = 0;
			
			while (x < no_of_blocks) {
				int i = randomPositionGenerator.nextInt(10);
				if (occupiedCoordinates[i] == false) {
					blocks[i].setLayoutX(x_coordinates[i]);
					blocks[i].setLayoutY(y_coordinate);
					x++;
					occupiedCoordinates[i] = true;
				}
			}
			
		}
		
		placeBlock = false;
		//Logic for placing block ends

        //Logic for placing shield begins
        if (shield.getLayoutY() > 1200) {

            if(findChance(4,500)) {
                setNewElementPosition(shield);
            }

        }
        //Logic for placing shield ends

        if (block_destroyer.getLayoutY() > 1200) {

            if(findChance(4,1000)) {
                setNewElementPosition(block_destroyer);
            }
        }

        if (magnet.getLayoutY() > 1200) {

            if(findChance(4,500)) {
                setNewElementPosition(magnet);
            }
        }

        if (speedup.getLayoutY() > 1200) {

            if(findChance(4,500)) {
                setNewElementPosition(speedup);
            }
        }

        if (slomo.getLayoutY() > 1200) {

            if(findChance(4,20)) {
                setNewElementPosition(slomo);
            }
        }

        if (multiplier.getLayoutY() > 1200) {

            if(findChance(4,20)) {
                setNewElementPosition(multiplier);
            }
        }

    }
	
	private void setNewElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(450));
		image.setLayoutY(randomPositionGenerator.nextInt(100));
	}

	private boolean findChance(int freq, int high)
    {
        boolean placeElement = false;
        int chance = findNext(high);
        if(chance>0 && chance<freq)
        {
            placeElement = true;
        }

        return placeElement;
    }

	private void setNewWallPosition(Rectangle image) {
		image.setLayoutX(randomPositionGenerator.nextInt(450));
		image.setLayoutY(randomPositionGenerator.nextInt(100));
	}

	private void setNewWallDimension(Rectangle image) {

		Random r = new Random();
		int low = 20;
		int high = GAME_HEIGHT/2;
		wall.setHeight(r.nextInt(high - low) + low);
	}

	private int findNext(int high) {
        Random r = new Random();
        int low = 0;
        int num =  (r.nextInt(high - low) + low);
        return num;
    }
	
	private void createSnake() {
		Group snakeBody = new Group();
		snake = snakeBody.getChildren();
		
		Circle head = new Circle();
		head.setCenterX(GAME_WIDTH/2);
		head.setCenterY(GAME_HEIGHT - 90);
		head.setRadius(10.0);
		head.setFill(Color.YELLOW);
		snake.add(head);
		gamePane.getChildren().add(snakeBody);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				moveBackground();
				moveGameElements();
				elementBelowScreen();
				checkCollision();
				moveSnake();
			}
		};
		
		gameTimer.start();
	}
	
	private void moveSnake() {
		if (isLeftKeyPressed && !isRightKeyPressed) {
			if (((Circle) snake.get(snake.size()-1)).getCenterX() > 20) {
				for (int i=0; i<snake.size(); i++) {
					((Circle) snake.get(i)).setCenterX(((Circle) snake.get(i)).getCenterX() - 6);
				}
				
			}
		}
		
		if (!isLeftKeyPressed && isRightKeyPressed) {
			if (((Circle) snake.get(snake.size()-1)).getCenterX() < 580) {
				for (int i=0; i<snake.size(); i++) {
					((Circle) snake.get(i)).setCenterX(((Circle) snake.get(i)).getCenterX() + 6);
				}
			}
		}
		
	/*	if (!isLeftKeyPressed && !isRightKeyPressed) {
			
		} 

		if (isLeftKeyPressed && isRightKeyPressed) {
	
		}  */
		
		
	}
	
	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		
		for (int i=0; i<12; i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1,i%3,i/3);
			GridPane.setConstraints(backgroundImage2,i%3,i/3);
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);			
		}
		
		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1,gridPane2);
	}
	
	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 4.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 4.5);
		
		if (gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		
		if (gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
	
	private void checkCollision() {
	//	System.out.println(calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutY()));
		if (SNAKE_RADIUS + COIN_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),coin.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutY())) {
			setNewElementPosition(coin);
			
			coins++;
			String textToSet = "POINTS: ";
			if (coins <10) {
				textToSet = textToSet + "0";
			}
			coinLabel.setText(textToSet + coins);
			
		}
		for (int i=0; i<balls.length; i++) {
			if (SNAKE_RADIUS + COIN_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),balls[i].getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),balls[i].getLayoutY())) {
				setNewElementPosition(balls[i]);
				Circle head = new Circle();
				head.setCenterX(((Circle) snake.get(snake.size()-1)).getCenterX());
				head.setCenterY(((Circle) snake.get(snake.size()-1)).getCenterY()-15.0);
				head.setRadius(10.0);
				head.setFill(Color.YELLOW);
				snake.add(head);
				
			}
		}
		
		for (int i=0; i<blocks.length; i++) {
			if (SNAKE_RADIUS + BLOCK_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),blocks[i].getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),blocks[i].getLayoutY())) {
				//code elided
				
				
				
				
				
			}
		}
		
	}
	
	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
	}
}

