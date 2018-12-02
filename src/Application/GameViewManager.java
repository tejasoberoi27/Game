package Application;

//import com.sun.org.apache.xpath.internal.operations.Mult;
//import com.sun.org.apache.xpath.internal.operations.Mult;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;

import java.io.*;

import static java.lang.Math.abs;

public class GameViewManager {

	private double[] discretePositions = {50.0, 100.0, 150.0, 200.0, 250.0, 300.0, 350.0, 400.0, 450.0, 500.0};
	private Color[] blockColors = {Color.RED, Color.ORANGERED, Color.YELLOW, Color.ORANGE, Color.DARKORANGE};
	private ArrayList<Double> blockPositions;
	private float gameSpeedFactor;
	private boolean roknaHai;
	private int wallFlag;
	private double currentTime;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	private boolean isWall_left;
	private boolean isWall_right;
	private ArrayList<Wall> walls_Present = new ArrayList<Wall>() ;



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
	private Random randomPositionGenerator;

	//	private ImageView coin,shield,block_destroyer,magnet,speedup,slomo,multiplier;
//	private Rectangle wall;
	private Wall wall;
	private Palette colors;
	private Snake player;
	private Ball ball;
	private Shield shield;
	private BlockDestroyer blockDestroyer;
	private Magnet magnet;
	private SpeedUp speedUp;
	private Multiplier multiplier;
	private SloMo slomo;
	private Coin coin;


	private SmallInfoLabel coinLabel;
	private int coins;
//	private final static String COIN_IMAGE 	= "application/coin.png";

//	private final static String BLOCK_DESTROYER_IMAGE 	= "application/icons8-bulldozer-48.png";


	private final static String BLOCK_IMAGE = "application/red_button07.png";
	private ArrayList<Component> ComponentList = new ArrayList<Component>();
	//	private ImageView[] blocks;
//	private Block[] blocks;
	private Block[] blocks;
	private final double BLOCK_RADIUS = 20;

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
		blockPositions = new ArrayList<>();
		gameSpeedFactor = 1;
		wallFlag = 0;
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
		coins=0;
		createBackground();
//		System.err.println("1");
		createSnake();
//		System.err.println("2");
		colors = new Palette();
		createGameElements();
		create();
//		System.err.println("3");
//		translate = new TranslateTransition();
		createGameLoop();
//		System.err.println("4");
		gameStage.setTitle("Snakes vs Blocks");
//		System.err.println("5");
		gameStage.show();
//		System.err.println("6");
	}

	//int flag = 0;


	public AnchorPane getGamePane() {
		return gamePane;
	}

	private void create() {


		int no_of_blocks = randomPositionGenerator.nextInt(20);

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
					blocks[i].updateValue();
					blocks[i].setLayoutX(x_coordinates[i]);
					blocks[i].setLayoutY(y_coordinate);
					blockPositions.add((double) x_coordinates[i]);
					x++;
					occupiedCoordinates[i] = true;
				}
			}

			wallFlag = 0;

		}

		placeBlock = false;

		//Logic for placing block ends

//		if (flag == 0) {
//			timer.schedule(task, 1000,2000);
//			flag = 1;
//		}


	}

	private void createWalls() {

		double wallGroupHeight = 0.0;

		if (blockPositions.size() > 0) {
			for (int i=0; i<randomPositionGenerator.nextInt(blockPositions.size()); i++) {
				int index = randomPositionGenerator.nextInt(blockPositions.size());
				if (i==0) {
					Wall wall = new Wall(this);
					wall.getRectangle().setLayoutX(blockPositions.get(index) + 30.0);
					gamePane.getChildren().add(wall.getRectangle());
					activeComponentsList.add(wall);
					wallGroupHeight = wall.getRectangle().getHeight();
					this.walls_Present.add(wall);
				}
				else {
					Wall wall = new Wall(this);
					wall.getRectangle().setHeight(wallGroupHeight);
					wall.getRectangle().setLayoutX(blockPositions.get(index) + 30.0);
					gamePane.getChildren().add(wall.getRectangle());
					activeComponentsList.add(wall);
					this.walls_Present.add(wall);
				}

			}

		}




	}

	private void createGameElements() {


		blocks = new Block[10];
		for (int i = 0; i < blocks.length; i++) {

			blocks[i] = new Block(i, gamePane, colors,this,player);
		}

		coinLabel = new SmallInfoLabel("POINTS: 0");
		coinLabel.setLayoutX(460);
		coinLabel.setLayoutY(20);
		gamePane.getChildren().add(coinLabel);

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
//			System.out.println(activeComponentsList.size());
			Component component = activeComponentsList.get(i);

//			if (component.getName().e)
			component.move();
//			System.out.println(component);
		}
	}


	int second = 0;
	double timeElapsedSinceBlock = 0;

	private void elementBelowScreen() {

		boolean allBlocksBelowScreen = true;
		int repBlock = 0;

		//System.out.println("below screen size" + activeComponentsList.size());

		for (int i = 0; i < activeComponentsList.size() ; i++) {
			Component current = activeComponentsList.get(i);
//			if (activeComponentsList.get(i).getY() > GAME_HEIGHT) {
//				if(activeComponentsList.get(i).getClass()!=wall.getClass())
////				activeComponentsList.remove(i);
//				activeComponentsList.remove(i);
//
//			}
			if (current.getY() > GAME_HEIGHT) {
//				activeComponentsList.remove(i);
					if(current.getClass()==Coin.class||current.getClass()==Ball.class )
					{gamePane.getChildren().remove(((Token) current).getValue());}
					activeComponentsList.remove(current);
				if(current.getClass() == Wall.class)
				{
					walls_Present.remove(current);
				}


			}
		}

		for (int i=0; i<blocks.length; i++) {
			if (blocks[i].getLayoutY() < GAME_HEIGHT) {
				allBlocksBelowScreen = false;
				repBlock = i;
				break;
			}

			else {
				if (!blocks[i].isVisible()) {
					blocks[i].setVisible(true);
				}
				for (int j=0; j<blockPositions.size(); j++) {
					if (blocks[i].getLayoutX() == blockPositions.get(j)) {
						blockPositions.remove(j);
					}
				}
			}

		}

		if (allBlocksBelowScreen) {
			create();

			if (wallFlag == 0) {
				createWalls();
				wallFlag = 1;
			}

		}

		else if (blocks[repBlock].getLayoutY() > GAME_HEIGHT - 20) {
			roknaHai = true;
			timeElapsedSinceBlock = System.currentTimeMillis();
		}

//		if (blocks[0].getLayoutY() > GAME_HEIGHT) {
//			create();
//			createWalls();
//		}

//		if (blocks[0].getLayoutY() > GAME_HEIGHT - 50) {
//			roknaHai = true;
//			timeElapsedSinceBlock = System.currentTimeMillis();
//		}

		double time = System.currentTimeMillis() - currentTime;
		time = time/1000;

		if (second != (int)time && !roknaHai) {
			generateToken();
			second = (int)time;
		}
		else if (roknaHai) {
			if (System.currentTimeMillis() - timeElapsedSinceBlock > 450) {
				roknaHai = false;
			}
		}

//
	}



	public void generateToken() {
//		System.out.println("TOKEN CREATED");
		int choice = randomPositionGenerator.nextInt(100);

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
		} else if (choice >49 && choice<60) {
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
		} else if (choice >= 5 && choice <= 80) {
			int i = randomPositionGenerator.nextInt(3);

			for (int x = 0; x < i; x++) {
				Ball ball = new Ball(5, this);
				ball.setNextValue();
				gamePane.getChildren().add(ball.getImage());
				gamePane.getChildren().add(ball.getValue());
				initializeToken(ball);
				activeComponentsList.add(ball);
			}

		} else if (choice >= 81 && choice <= 100) {
			Coin coin = new Coin(findNextInt(1,3), this);
			coin.setNextValue();
			gamePane.getChildren().add(coin.getImage());
			gamePane.getChildren().add(coin.getValue());
			initializeToken(coin);
			activeComponentsList.add(coin);
		} else {
			//do nothing
		}


	}

	public void initializeToken(Token i) {

//            i.setFitWidth(25);
//            i.setFitHeight(25);
		if(i.getClass()!= Ball.class&& i.getClass()!=Coin.class)
		{
			System.out.println("FOUND");
		}

		ImageView icon = i.getImage();
		icon.setLayoutY(0);
		icon.setLayoutX(discretePositions[randomPositionGenerator.nextInt(discretePositions.length)]);
		Label increment = i.getValue();
		if(increment!=null) {
			System.out.println("text" + increment.getText());


//			increment.setLayoutX(i.getImage().getLayoutX() + i.getRadius() / 2);
//			increment.setLayoutY(i.getImage().getLayoutY() + i.getRadius() / 2);

			increment.setLayoutX(icon.getLayoutX() + icon.getFitWidth()/2 -5);
			increment.setLayoutY(icon.getLayoutY() - 0.8*Ball.getImage_height());


		}

//			if(i.isActive())
//				i.toggle();
	}

//	void setNewElementPosition(ImageView image) {
////		image.setLayoutX(randomPositionGenerator.nextInt(450));
//
//		int x, y;
//		x = findNextInt(0, 450);
//		y = findNextInt(100, 150);
////		int ctr = 0;
////		while (Math.abs(image.getLayoutY() - blocks[0].getLayoutY()) < 40) {
////			x = findNextInt(0, 450);
////			y = findNextInt(100, 150);
////			if (ctr++ > 10) {
////				break;
////			}
//
//		image.setLayoutX(x);
//		image.setLayoutY(y);
//
////		image.setLayoutY(randomPositionGenerator.nextInt(100));
////		}
//	}

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

	public int findNextInt(int low, int high) {
		Random r = new Random();
		int num = (r.nextInt(high - low) + low);
		return num;
	}

	private void createSnake() {
		player = new Snake(this);
		gamePane.getChildren().add(player.getSnakeBody());
		gamePane.getChildren().add(player.getValue());

	}

	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				moveBackground();
				moveGameElements();
				elementBelowScreen();
				try {
					checkCollision();
				}catch (ConcurrentModificationException e)
				{

//					System.out.println("caught "+currentTime);
				}

//					checkCollision();

				moveSnake();
			}
		};

		gameTimer.start();
	}

	private void moveSnake() {
		ObservableList<Node> snake = player.getSnake();

		isWall_left = false;
		isWall_right = false;

		int j=0;
		for (Wall element:walls_Present) {
			System.out.println(j++);

			double upperbound = element.getY();
			double lowerbound = upperbound + ((Wall) element).getHeight();
			Circle snake_head = (Circle) snake.get(snake.size() - 1);

//			double posY = snake_head.getLayoutY();
//			double posY = GAME_HEIGHT - 90;
			double posY = snake_head.getCenterY();



			double Wall_X = element.getX();
			double centreX = snake_head.getCenterX();

			System.out.println("lb "+lowerbound);
			System.out.println("ub "+ upperbound);
			System.out.println("snh "+ posY);
			System.out.println("Centre "+centreX );
			System.out.println("Wall X "+ Wall_X);
//			System.out.println("posX"+ posX);

			if (posY <= lowerbound && posY >= upperbound) {
				System.out.println("inside");
				if ((abs(Wall_X - centreX) < 20)) {
					if (Wall_X <= centreX) {
						isWall_left = true;
						isWall_right = false;
						System.out.println("wall left");
					}
					else
					{
						isWall_right = true;
						isWall_left = false;
						System.out.println("wall right");
					}
				}

		}

			System.out.println("Size = "+j);






	/*	if (!isLeftKeyPressed && !isRightKeyPressed) {

		}

		if (isLeftKeyPressed && isRightKeyPressed) {

		}  */


		}

		if (isLeftKeyPressed && !isRightKeyPressed && !isWall_left) {
//			if(isWall)
			if (((Circle) player.getSnake().get(player.getSnake().size() - 1)).getCenterX() > 20) {
				for (int i = 0; i < player.getSnake().size(); i++) {
					((Circle) player.getSnake().get(i)).setCenterX(((Circle) player.getSnake().get(i)).getCenterX() - gameSpeedFactor * 4);
				}
				player.getValue().setLayoutX(player.getValue().getLayoutX() - gameSpeedFactor * 4);
			}
		}

		if (!isLeftKeyPressed && isRightKeyPressed && !isWall_right) {
			if (((Circle) player.getSnake().get(player.getSnake().size() - 1)).getCenterX() < 580) {
				for (int i = 0; i < player.getSnake().size(); i++) {
					((Circle) player.getSnake().get(i)).setCenterX(((Circle) player.getSnake().get(i)).getCenterX() + gameSpeedFactor * 4);
				}
				player.getValue().setLayoutX(player.getValue().getLayoutX() + gameSpeedFactor * 4);
			}
		}
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
		gridPane1.setLayoutY(gridPane1.getLayoutY() + gameSpeedFactor * 0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + gameSpeedFactor * 0.5);

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
		ObservableList<Node> snake = player.getSnake();
		//	icon = ((Token) element).getImage();

		//	if (SNAKE_RADIUS + radius > calculateDistance(((Circle) snake.get(snake.size() - 1)).getCenterX(), icon.getLayoutX(), ((Circle) snake.get(snake.size() - 1)).getCenterY(), icon.getLayoutY())) {



		for (int i = 0; i < blocks.length; i++) {
			if (Math.sqrt(2) * BLOCK_RADIUS + SNAKE_RADIUS > calculateDistance(((Circle) snake.get(snake.size() - 1)).getCenterX(), blocks[i].getLayoutX(), ((Circle) snake.get(snake.size() - 1)).getCenterY(), blocks[i].getLayoutY()) && blocks[i].isVisible()) {
				blocks[i].setVisible(false);
				PlayBurst(blocks[i].getBoundsInParent());
				int block_value = Integer.valueOf(blocks[i].getText().getText());

				if (snake.size() > block_value && block_value <= 5) {
					for (int j = 0; j < block_value; j++) {
						snake.remove(0);
						player.AlignLabel();
					}
				}


//					ImageView explosion = new ImageView("Application/explosion.gif");
//					explosion.setFitHeight(25);
//					explosion.setFitWidth(25);
//					explosion.setLayoutY(blocks[i].getLayoutY());
//					explosion.setLayoutX(blocks[i].getLayoutX());
//					gamePane.getChildren().add(explosion);
//					gamePane.getChildren().remove(explosion);
			}
		}

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


			//ObservableList<Node> snake = player.getSnake();
			if (element instanceof Token) {

				icon = ((Token) element).getImage();
//				System.out.println("STATUS = " + (Magnet.isActive()));
				if (element instanceof Coin && Magnet.isActive()) {
//					System.out.println("radius increased");
					radius = 300;
				}
//				if (Magnet.isActive())
//				{
//					System.out.println("RADIUS INCREASED");
//					radius = 10*radius;
//				}

//				System.out.println("rad = " + element.getClass() + " " + radius);

				if (SNAKE_RADIUS + radius > calculateDistance(((Circle) snake.get(snake.size() - 1)).getCenterX(),
						icon.getLayoutX(), ((Circle) snake.get(snake.size() - 1)).getCenterY(), icon.getLayoutY())) {

					((Token) element).getImage().setVisible(false);
					if (((Token) element).getValue() != null)
						((Token) element).getValue().setVisible(false);
					activeComponentsList.remove(element);

					if (element instanceof SloMo) {
						setGameSpeedFactor(0.5f);
						Timer timer = new Timer();
						TimerTask task = new TimerTask() {
							@Override
							public void run() {
								setGameSpeedFactor(1);
							}
						};
						timer.schedule(task, element.getValueInt() * 1000);

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

						timer.schedule(task, element.getValueInt() * 1000);
					}

					if (element instanceof Ball) {

						int increment = Integer.parseInt(((Ball) element).getValue().getText());
						((Circle) snake.get(snake.size() - 1)).setFill(Color.YELLOW);
						for (int i = 0; i < increment; i++) {
							Circle head = new Circle();
							head.setFill(Color.YELLOW);
							head.setCenterX(((Circle) snake.get(snake.size() - 1)).getCenterX());
							head.setCenterY(((Circle) snake.get(snake.size() - 1)).getCenterY() - 15.0);
							head.setRadius(10.0);
							if (i == increment - 1)
								head.setFill(Color.RED);
							snake.add(head);
							player.AlignLabel();
						}
					}

					if (element instanceof Coin) {
//						System.out.println("It's a coin");
						if (Multiplier.isActive()) {
							coins += 2;
						} else {
							coins++;
						}
//						System.out.println(coins);
						String textToSet = "POINTS: ";
//						if (coins <10) {
//							textToSet = textToSet + "0";
//
//						}
						textToSet = textToSet + (Integer.toString(coins));
						coinLabel.setText(textToSet);
//						System.out.println(textToSet);
					}

					if (element instanceof Magnet) {

						Magnet.setIsActiveTrue();
						Timer timer = new Timer();
						TimerTask task1 = new TimerTask() {
							@Override
							public void run() {
//								System.out.println("inside" + Magnet.isActive());
								Magnet.setIsActiveFalse();
//								timer.cancel();
							}
						};

						timer.schedule(task1, element.getValueInt() * 1000);
					}

					if (element instanceof Multiplier) {
						Timer timer = new Timer();
						TimerTask task1 = new TimerTask() {
							@Override
							public void run() {
/*								sSystem.out.println(Multiplier.isActive());*/
//								Multiplier.setIsActiveFalse();
							}
						};
						Multiplier.setIsActiveTrue();
						timer.schedule(task1, element.getValueInt() * 1000);
					}



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



//	private void checkCollision() {
//
//	}

    public void PlayBurst(Bounds bounds) {
        double x = bounds.getMaxX() + bounds.getMinX();
        x /= 2;
        double y = bounds.getMaxY() + bounds.getMinY();
        y /= 2;
        BurstAnimation(x, y);
    }

    public void BurstAnimation(double x, double y) {
        KeyFrame kf = new KeyFrame(Duration.millis(2), new BurstAnimationHandler(x, y));
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(1);
        timeline.play();
    }

    private class BurstAnimationHandler implements EventHandler<ActionEvent> {

        final double x;
        final double y;
        final long duration = java.time.Duration.ofSeconds(1).toNanos();
        final int side = 60;
        final double radius = Math.sqrt(2) * side;
        final Rectangle[] rectangles = new Rectangle[50];
        final ArrayList<Long> delays = new ArrayList<>();
        final ArrayList<Double> angles = new ArrayList<Double>();

        public BurstAnimationHandler(double x, double y) {
            // TODO Auto-generated constructor stub
            this.x = x;
            this.y = y;
        }

        @Override
        public void handle(ActionEvent event) {
            // TODO Auto-generated method stub

            for (int i = 0; i < 50; i++) {
                int rand = randomPositionGenerator.nextInt(blockColors.length);
                rectangles[i] = new Rectangle(10, 10, blockColors[rand]);
                angles.add(2 * Math.random() * Math.PI);
                delays.add((long) (Math.random() * duration));
            }
            gamePane.getChildren().addAll(rectangles);

            AnimationTimer Burst = new AnimationTimer() {
                int k = 0;

                @Override
                public void handle(long now) {
                    // TODO Auto-generated method stub
                    k++;
                    // System.out.println(k);
                    if (k < 10) {
                        for (int i = 0; i < 50; i++) {
                            Rectangle rect = rectangles[i];
                            long time = (now - delays.get(i)) % duration;
                            double d = time * radius / duration;
                            rect.setTranslateX(Math.cos(angles.get(i)) * d + x);
                            rect.setTranslateY(Math.sin(angles.get(i)) * d + y);
                            rect.setOpacity((duration - time) / (double) duration);

                        }
                    } else {
                        this.stop();
                        gamePane.getChildren().removeAll(rectangles);
                    }

                }
            };
            Burst.start();
        }
    }

		double calculateDistance ( double x1, double x2, double y1, double y2){
			return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		}

	public void setGameSpeedFactor(float gameSpeedFactor) {
		this.gameSpeedFactor = gameSpeedFactor;
	}

	public int getCoins() {
		return coins;
	}

	public ImageView getBallImage()
	{
		return ball.getImage();
	}

	public Random getRandomPositionGenerator() {
		return randomPositionGenerator;
	}
}



