package Application;

//import com.sun.org.apache.xpath.internal.operations.Mult;
//import com.sun.org.apache.xpath.internal.operations.Mult;
import javafx.animation.TranslateTransition;
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

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class GameViewManager {

	private double[] discretePositions = {50.0, 100.0, 150.0, 200.0, 250.0, 300.0, 350.0, 400.0, 450.0, 500.0};
	private float gameSpeedFactor;
	private boolean roknaHai;
	private double currentTime;
	AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;


	static final int GAME_WIDTH = 600;
	static final int GAME_HEIGHT = 800;

	private Stage menuStage;


	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private AnimationTimer gameTimer;
	private TranslateTransition translate;

	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "application/gamebg.jpeg";
	Random randomPositionGenerator;

	//	private ImageView coin,shield,block_destroyer,magnet,speedup,slomo,multiplier;
//	private Rectangle wall;
	private Wall wall;
	private Palette colors;
	private Snake player;
	Ball ball;
	Shield shield;
	BlockDestroyer blockDestroyer;
	Magnet magnet;
	SpeedUp speedUp;
	Multiplier multiplier;
	SloMo slomo;
	Coin coin;

	private SmallInfoLabel coinLabel;
	private int coins;
//	private final static String COIN_IMAGE 	= "application/coin.png";

//	private final static String BLOCK_DESTROYER_IMAGE 	= "application/icons8-bulldozer-48.png";


	private final static String BLOCK_IMAGE = "application/red_button07.png";
	private ArrayList<Component> ComponentList = new ArrayList<Component>();
	//	private ImageView[] blocks;
//	private GameRectangle[] blocks;
	private GameRectangle[] blocks;

	//private StackPane[] blocksPane;

//	final static int COIN_RADIUS = 12;
//	private final static int SNAKE_RADIUS = 10;
//	final static int BALL_RADIUS = 20;
//	private final static int BLOCK_RADIUS = 30;
//	private final static int WALL_RADIUS = 30;
//	private final static int SPEEDUP_RADIUS = 20;

	private ArrayList<Component> activeComponentsList;

	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			//wall.newWall();
			roknaHai = false;

		}
	};


	GameViewManager() {
		initializeStage();
		createKeyListeners();
		roknaHai = false;
		randomPositionGenerator = new Random();
		gameSpeedFactor = 1;
		currentTime = System.currentTimeMillis();
		activeComponentsList = new ArrayList<Component>();
	}

	public float getGameSpeedFactor() {
		return gameSpeedFactor;
	}

	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode() == KeyCode.RIGHT) {
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
		System.err.println("1");
		createSnake();
		System.err.println("2");
		colors = new Palette();
		createGameElements();
		create();
		System.err.println("3");
//		translate = new TranslateTransition();
		createGameLoop();
		System.err.println("4");
		gameStage.setTitle("Snakes vs Blocks");
		System.err.println("5");
		gameStage.show();
		System.err.println("6");
	}

	//int flag = 0;
	private void create() {


		int no_of_blocks = randomPositionGenerator.nextInt(15);

		if (no_of_blocks >= 10) no_of_blocks = 10;


//		int y_coordinate = randomPositionGenerator.nextInt(400);
		int y_coordinate = 20;

		int[] x_coordinates = new int[10];
		boolean[] occupiedCoordinates = new boolean[10];

		for (int i = 0; i < 10; i++) {
			x_coordinates[i] = 60 * i;
			occupiedCoordinates[i] = false;
		}

		boolean placeBlock = true;

		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].getLayoutY() < 900) {
				placeBlock = false;
			}
		}

		if (placeBlock) {
			int x = 0;

			while (x < no_of_blocks) {
				int i = randomPositionGenerator.nextInt(10);
				//	System.err.println("running");
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

//		if (flag == 0) {
//			timer.schedule(task, 1000,2000);
//			flag = 1;
//		}


	}


	private void createGameElements() {


		blocks = new GameRectangle[10];
		for (int i = 0; i < blocks.length; i++) {

			blocks[i] = new GameRectangle(i, gamePane, colors);
		}

//        ball = new Ball(randomPositionGenerator.nextInt(5),this);
//        ComponentList.add(ball);
//        gamePane.getChildren().add(ball.getImage());
//        wall.setWidth(15);
//        wall.setFill(Color.WHITE);
//
//
//        setNewWallPosition(wall);
//        System.err.println(test++);
//        setNewWallDimension(wall);
//		wall = new Wall(this);
//		ComponentList.add(wall);
//        gamePane.getChildren().add(wall.getRectangle());
//        activeComponentsList.add(wall);
////		}

//        shield = new Shield(5,this);
//        //coin.setPreserveRatio(true);
//       // setNewElementPosition(shield);
//        gamePane.getChildren().add(shield.getImage());
//        ComponentList.add(shield);

//		blockDestroyer = new BlockDestroyer(5,this);
////        block_destroyer.setFitHeight(25);
////        block_destroyer.setFitWidth(25);
//		//coin.setPreserveRatio(true);
//		// setNewElementPosition(block_destroyer);
//
//		gamePane.getChildren().add(blockDestroyer.getImage());
////		Token i = blockDestroyer;
////		i.getImage().setLayoutY(0);
////		i.getImage().setLayoutX(randomPositionGenerator.nextInt(500));
////		generateToken();
//		ComponentList.add(blockDestroyer);
//
//        magnet = new Magnet(5,this);
//        //coin.setPreserveRatio(true);
//    //    setNewElementPosition(magnet);
//        gamePane.getChildren().add(magnet.getImage());
//        ComponentList.add(magnet);

//        speedup = new ImageView(SPEEDUP_IMAGE);
//        speedup.setFitHeight(25);
//        speedup.setFitWidth(25);
		//coin.setPreserveRatio(true);
		// setNewElementPosition(speedup);
//		speedUp = new SpeedUp(5,this);
//        gamePane.getChildren().add(speedUp.getImage());
//
//        slomo = new SloMo(5,this);
//        //coin.setPreserveRatio(true);
//       // setNewElementPosition(slomo);
//        gamePane.getChildren().add(slomo.getImage());
//        ComponentList.add(slomo);
//
//        multiplier = new Multiplier(5,this);
//        //coin.setPreserveRatio(true);
//      //  setNewElementPosition(multiplier);
//        gamePane.getChildren().add(multiplier.getImage());
//        ComponentList.add(multiplier);

//		coin = new Coin(5,this);
//		//coin.setPreserveRatio(true);
//		//  setNewElementPosition(multiplier);
//		gamePane.getChildren().add(coin.getImage());
//		ComponentList.add(coin);
	}

	private void moveGameElements() {

//

		for (int i = 0; i < blocks.length; i++) {
//			int a =(int) blocks[i].getLayoutY();
//			System.out.println(a);

			blocks[i].setLayoutY(blocks[i].getLayoutY() + gameSpeedFactor * 5);
//
		}
		MoveToken();
//
	}

	//
	private void MoveToken() {
		for (int i = 0; i < activeComponentsList.size(); i++) {
			System.out.println(activeComponentsList.size());
			Component component = activeComponentsList.get(i);

//			if (component.getName().e)
			component.move();
			System.out.println(component);
		}
	}


	int second = 0;
	double timeElapsedSinceBlock = 0;

	private void elementBelowScreen() {


		//System.out.println("below screen size" + activeComponentsList.size());

		for (int i = 0; i < activeComponentsList.size(); i++) {
			Component current = activeComponentsList.get(i);
//			if (activeComponentsList.get(i).getY() > GAME_HEIGHT) {
//				if(activeComponentsList.get(i).getClass()!=wall.getClass())
////				activeComponentsList.remove(i);
//				activeComponentsList.remove(i);
//
//			}
			if (current.getY() > GAME_HEIGHT && current != null && wall != null) {
				if (current.getClass() != wall.getClass())
//				activeComponentsList.remove(i);
					activeComponentsList.remove(current);
			}
		}

		if (blocks[0].getLayoutY() > GAME_HEIGHT) {
			create();
		}

		if (blocks[0].getLayoutY() > GAME_HEIGHT - 50) {
			roknaHai = true;
			timeElapsedSinceBlock = System.currentTimeMillis();
		}

		double time = System.currentTimeMillis() - currentTime;
		time = time / 1000;

		if (second != (int) time && !roknaHai) {
			generateToken();
			second = (int) time;
		} else if (roknaHai) {
			if (System.currentTimeMillis() - timeElapsedSinceBlock > 250) {
				roknaHai = false;
			}
		}

//
	}


	public void generateToken() {
		System.out.println("TOKEN CREATED");
		int choice = randomPositionGenerator.nextInt(12);

		if (choice == 0) {
			Shield shield = new Shield(5, this);
			gamePane.getChildren().add(shield.getImage());
			initializeToken(shield);
			activeComponentsList.add(shield);
		} else if (choice == 1) {
			SloMo slomo = new SloMo(5, this);
			gamePane.getChildren().add(slomo.getImage());
			initializeToken(slomo);
			activeComponentsList.add(slomo);
		} else if (choice == 2) {
			SpeedUp speedUp = new SpeedUp(5, this);
			gamePane.getChildren().add(speedUp.getImage());
			initializeToken(speedUp);
			activeComponentsList.add(speedUp);
		} else if (choice == 3) {
			Magnet magnet = new Magnet(5, this);
			gamePane.getChildren().add(magnet.getImage());
			initializeToken(magnet);
			activeComponentsList.add(magnet);
		} else if (choice == 4) {
			BlockDestroyer blockDestroyer = new BlockDestroyer(5, this);
			gamePane.getChildren().add(blockDestroyer.getImage());
			initializeToken(blockDestroyer);
			activeComponentsList.add(blockDestroyer);
		} else if (choice == 5) {
			Multiplier multiplier = new Multiplier(5, this);
			gamePane.getChildren().add(multiplier.getImage());
			initializeToken(multiplier);
			activeComponentsList.add(multiplier);
		} else if (choice >= 6 && choice <= 8) {
			int i = randomPositionGenerator.nextInt(3);

			for (int x = 0; x < i; x++) {
				Ball ball = new Ball(5, this);
				gamePane.getChildren().add(ball.getImage());
				initializeToken(ball);
				activeComponentsList.add(ball);
			}

		} else if (choice >= 9 && choice <= 10) {
			Coin coin = new Coin(5, this);
			gamePane.getChildren().add(coin.getImage());
			initializeToken(coin);
			activeComponentsList.add(coin);
		} else {
			//do nothing
		}


	}

	public void initializeToken(Token i) {

//            i.setFitWidth(25);
//            i.setFitHeight(25);
		i.getImage().setLayoutY(0);
		i.getImage().setLayoutX(discretePositions[randomPositionGenerator.nextInt(discretePositions.length)]);

//			if(i.isActive())
//				i.toggle();
	}

	void setNewElementPosition(ImageView image) {
//		image.setLayoutX(randomPositionGenerator.nextInt(450));

		int x, y;
		x = findNextInt(0, 450);
		y = findNextInt(100, 150);
//		int ctr = 0;
//		while (Math.abs(image.getLayoutY() - blocks[0].getLayoutY()) < 40) {
//			x = findNextInt(0, 450);
//			y = findNextInt(100, 150);
//			if (ctr++ > 10) {
//				break;
//			}

		image.setLayoutX(x);
		image.setLayoutY(y);

//		image.setLayoutY(randomPositionGenerator.nextInt(100));
//		}
	}

	private boolean findChance(int freq, int high) {
		boolean placeElement = false;
		int chance = findNext(high);
		if (chance > 0 && chance < freq) {
			placeElement = true;
		}

		return placeElement;
	}


	private int findNext(int high) {
		Random r = new Random();
		int low = 0;
		int num = (r.nextInt(high - low) + low);
		return num;
	}

	private int findNextInt(int low, int high) {
		Random r = new Random();
		int num = (r.nextInt(high - low) + low);
		return num;
	}

	private void createSnake() {
		player = new Snake(this);
		gamePane.getChildren().add(player.getSnakeBody());
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
//			ObservableList<Node> snake = player.getSnake();
		if (isLeftKeyPressed && !isRightKeyPressed) {
			if (((Circle) player.getSnake().get(player.getSnake().size() - 1)).getCenterX() > 20) {
				for (int i = 0; i < player.getSnake().size(); i++) {
					((Circle) player.getSnake().get(i)).setCenterX(((Circle) player.getSnake().get(i)).getCenterX() - gameSpeedFactor * 6);
				}

			}
		}

		if (!isLeftKeyPressed && isRightKeyPressed) {
			if (((Circle) player.getSnake().get(player.getSnake().size() - 1)).getCenterX() < 580) {
				for (int i = 0; i < player.getSnake().size(); i++) {
					((Circle) player.getSnake().get(i)).setCenterX(((Circle) player.getSnake().get(i)).getCenterX() + gameSpeedFactor * 6);
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

		for (int i = 0; i < 12; i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
			GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);
		}

		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}

	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + gameSpeedFactor * 5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + gameSpeedFactor * 5);

		if (gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}

		if (gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}

	private void checkCollision() {
//
////		SpeedUp speedupToken = new SpeedUp(5,"SPEEDUP", speedup);
//	//	System.out.println(calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutY()));
		int SNAKE_RADIUS = player.getSnakeRadius();
//		 ObservableList<Node> snake = player.getSnake();
//		 ImageView coin = coin.getImage();
//		if (SNAKE_RADIUS + COIN_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),coin.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutY())) {
//			setNewElementPosition(coin);
//
//			coins++;
//			String textToSet = "POINTS: ";
//			if (coins <10) {
//				textToSet = textToSet + "0";
//			}
//			coinLabel.setText(textToSet + coins);
//
//		}
//
//
//
//		player.Grow();
//
//		for (int i=0; i<blocks.length; i++) {
//			if (SNAKE_RADIUS + BLOCK_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),blocks[i].getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),blocks[i].getLayoutY())) {
//				//code elided
//
//			}
//		}
//
//		if (SNAKE_RADIUS + SPEEDUP_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),speedup.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),speedup.getLayoutY())) {
//			gameSpeedFactor = 3;
//
//			Timer timer = new Timer();
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    gameSpeedFactor = 1;
//                }
//            };
//
//            timer.schedule(task,speedupToken.getValue()*1000);
//
//
//        }
//
//        if (SNAKE_RADIUS + SPEEDUP_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),slomo.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),slomo.getLayoutY())) {
//            gameSpeedFactor = 0.5f;
//
//            Timer timer = new Timer();
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    gameSpeedFactor = 1;
//                }
//            };
//
//            timer.schedule(task,speedupToken.getValue()*1000);
//        }
//
//

		for (Component element : activeComponentsList
				) {

			int radius = element.getRadius();
			ImageView icon;
			ObservableList<Node> snake = player.getSnake();
			if (element instanceof Token) {

				icon = ((Token) element).getImage();

				if (SNAKE_RADIUS + radius > calculateDistance(((Circle) snake.get(snake.size() - 1)).getCenterX(), icon.getLayoutX(), ((Circle) snake.get(snake.size() - 1)).getCenterY(), icon.getLayoutY())) {
					((Token) element).getImage().setVisible(false);
					if (element instanceof SloMo) {
						setGameSpeedFactor(0.5f);
						Timer timer = new Timer();
						TimerTask task = new TimerTask() {
							@Override
							public void run() {
								setGameSpeedFactor(1);
							}
						};
						timer.schedule(task, element.getValue() * 1000);

					}
					if (element instanceof SpeedUp) {
						setGameSpeedFactor(2.0f);

						Timer timer = new Timer();
						TimerTask task = new TimerTask() {
							@Override
							public void run() {
								gameSpeedFactor = 1;
							}
						};

						timer.schedule(task, element.getValue() * 1000);
					}

					if (element instanceof Ball) {

						for (int i = 0; i < element.getValue(); i++) {
							Circle head = new Circle();
							head.setCenterX(((Circle) snake.get(snake.size() - 1)).getCenterX());
							head.setCenterY(((Circle) snake.get(snake.size() - 1)).getCenterY() - 15.0);
							head.setRadius(10.0);
							head.setFill(Color.YELLOW);
							snake.add(head);
						}
					}

					if(element instanceof Coin)
					{
						coins++;
						String textToSet = "POINTS: ";
						if (coins <10) {
							textToSet = textToSet + "0";
						}
						coinLabel.setText(textToSet + coins);
					}
				}
			}

		}

//			if (element instanceof SpeedUp) {
//				ImageView speedup = ((SloMo) element).getImage();
//				if (SNAKE_RADIUS + radius > calculateDistance(((Circle) snake.get(snake.size() - 1)).getCenterX(), speedup.getLayoutX(), ((Circle) snake.get(snake.size() - 1)).getCenterY(), speedup.getLayoutY())) {
//					setGameSpeedFactor(3);
//
//					Timer timer = new Timer();
//					TimerTask task = new TimerTask() {
//						@Override
//						public void run() {
//							gameSpeedFactor = 1;
//						}
//					};
//
//					timer.schedule(task, element.getValue() * 1000);
//
//
//				}



	}
//	private void checkCollision() {
//
//	}

		double calculateDistance ( double x1, double x2, double y1, double y2){
			return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		}

	public void setGameSpeedFactor(float gameSpeedFactor) {
		this.gameSpeedFactor = gameSpeedFactor;
	}
}

