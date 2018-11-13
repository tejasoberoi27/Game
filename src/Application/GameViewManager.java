package Application;

import com.sun.org.apache.xpath.internal.operations.Mult;
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

	private float gameSpeedFactor;
	AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	
	static final int GAME_WIDTH = 600;
	static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
//	private ObservableList<Node> snake;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;	
	private AnimationTimer gameTimer;
	private TranslateTransition translate;

	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String BACKGROUND_IMAGE = "application/gamebg.jpeg";
//	private StackPane blocksPane;
	

//	private ImageView[] balls;
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

	private SmallInfoLabel coinLabel;
	private int coins;
	private final static String COIN_IMAGE 	= "application/coin.png";

	private final static String BLOCK_DESTROYER_IMAGE 	= "application/icons8-bulldozer-48.png";




	private final static String BLOCK_IMAGE = "application/red_button07.png";
	private ArrayList<Component> ComponentList = new ArrayList<Component>();
//	private ImageView[] blocks;
//	private GameRectangle[] blocks;
	private GameRectangle[] blocks;

	//private StackPane[] blocksPane;
	
	final static int COIN_RADIUS = 12;
//	private final static int SNAKE_RADIUS = 10;
	final static int BALL_RADIUS = 20;
	private final static int BLOCK_RADIUS = 30;
	private final static int WALL_RADIUS = 30;
	private final static int SPEEDUP_RADIUS = 20;


	
	GameViewManager() {
		initializeStage();
		createKeyListeners();
		randomPositionGenerator = new Random();
		gameSpeedFactor = 1;
	}

	public float getGameSpeedFactor() {
		return gameSpeedFactor;
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
		System.err.println("1");
		createSnake();
		System.err.println("2");
		colors = new Palette();
		createGameElements();
		System.err.println("3");
//		translate = new TranslateTransition();
		createGameLoop();
		System.err.println("4");
		gameStage.setTitle("Snakes vs Blocks");
		System.err.println("5");
		gameStage.show();
		System.err.println("6");
	}
	
	private void createGameElements() {


		int test =0;



        blocks = new GameRectangle[10];
        for (int i=0; i<blocks.length; i++) {
			System.err.println(test++);
        	blocks[i] = new GameRectangle(i,gamePane,colors);
			System.err.println("this"+test++); }

        int no_of_blocks = randomPositionGenerator.nextInt(15);

        if (no_of_blocks >= 10) no_of_blocks = 10;


//		int y_coordinate = randomPositionGenerator.nextInt(400);
        int y_coordinate = 0;

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
                System.err.println("running");
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


        ball = new Ball(randomPositionGenerator.nextInt(5),this);
        ComponentList.add(ball);
        gamePane.getChildren().add(ball.getImage());
//        wall.setWidth(15);
//        wall.setFill(Color.WHITE);
//
//
//        setNewWallPosition(wall);
//        System.err.println(test++);
//        setNewWallDimension(wall);
		wall = new Wall(this);
		ComponentList.add(wall);
        gamePane.getChildren().add(wall.getRectangle());
//		}

        shield = new Shield(5,this);
        //coin.setPreserveRatio(true);
       // setNewElementPosition(shield);
        gamePane.getChildren().add(shield.getImage());
        ComponentList.add(shield);

        block_destroyer = new ImageView(BLOCK_DESTROYER_IMAGE);
        block_destroyer.setFitHeight(25);
        block_destroyer.setFitWidth(25);
        //coin.setPreserveRatio(true);
       // setNewElementPosition(block_destroyer);
        gamePane.getChildren().add(block_destroyer);

        magnet = new Magnet(5,this);
        //coin.setPreserveRatio(true);
    //    setNewElementPosition(magnet);
        gamePane.getChildren().add(magnet.getImage());
        ComponentList.add(magnet);

//        speedup = new ImageView(SPEEDUP_IMAGE);
//        speedup.setFitHeight(25);
//        speedup.setFitWidth(25);
        //coin.setPreserveRatio(true);
       // setNewElementPosition(speedup);
		speedUp = new SpeedUp(5,this);
        gamePane.getChildren().add(speedUp.getImage());

        slomo = new ImageView(SLOMO_IMAGE);
        slomo.setFitHeight(25);
        slomo.setFitWidth(25);
        //coin.setPreserveRatio(true);
       // setNewElementPosition(slomo);
        gamePane.getChildren().add(slomo);

        multiplier = new Multiplier(5,this);
        //coin.setPreserveRatio(true);
      //  setNewElementPosition(multiplier);
        gamePane.getChildren().add(multiplier.getImage());
        ComponentList.add(multiplier);
	}
	
	private void moveGameElements() {

	    moveToken(coin);
        moveToken(shield);
        moveToken(block_destroyer);
        moveToken(magnet);
        moveToken(speedup);
        moveToken(slomo);
        moveToken(multiplier);



//        shield.setLayoutY(shield.getLayoutY() +  gameSpeedFactor * 5);
//
//        block_destroyer.setLayoutY(block_destroyer.getLayoutY() +  gameSpeedFactor * 5);
//
//        magnet.setLayoutY(magnet.getLayoutY() +  gameSpeedFactor * 5);
//
//        speedup.setLayoutY(speedup.getLayoutY() +  gameSpeedFactor * 5);
//
//        slomo.setLayoutY(slomo.getLayoutY() +  gameSpeedFactor * 5);
//
//        multiplier.setLayoutY(multiplier.getLayoutY() +  gameSpeedFactor * 5);


		
//		for (int i=0; i<balls.length; i++) {
//			balls[i].setLayoutY(balls[i].getLayoutY()+7);
//			balls[i].setRotate(balls[i].getRotate()+4);
//		}

//        public void moveSnake()
//        {
          if (ball.isActive()) {
              ball.getImage().setLayoutY(ball.getImage().getLayoutY()+gameSpeedFactor*5);
              ball.getImage().setRotate(ball.getImage().getRotate()+gameSpeedFactor*5);
          }


		
		for (int i=0; i<blocks.length; i++) {
//			int a =(int) blocks[i].getLayoutY();
//			System.out.println(a);

			blocks[i].setLayoutY(blocks[i].getLayoutY()+ gameSpeedFactor * 5);
//			blocks[i].setLayoutY(blocks[i].getLayoutY()+20);

//			translate.setNode(blocks[i]);
//			translate.setByY(7);
//			blocks[i].setLayoutY(blocks[i].getLayoutY()+7);
		}

//		for (int i=0; i<blocks.length; i++) {
			wall.setLayoutY(wall.getLayoutY()+ gameSpeedFactor * 5);
//		}
 	}

    private void moveToken(ImageView Token) {
	    if (Token != null) {
            Token.setLayoutY(Token.getLayoutY() +  gameSpeedFactor * 5);
        }
    }

    private void elementBelowScreen() {



        int no_of_blocks = randomPositionGenerator.nextInt(15);

        if (no_of_blocks >= 10) no_of_blocks = 10;


//		int y_coordinate = randomPositionGenerator.nextInt(400);
        int y_coordinate = 0;

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
                System.err.println("running");
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

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                generateToken();
            }
        };

        timer.schedule(task, 1000);

//		if (coin.getLayoutY() > 1200) {
//			setNewElementPosition(coin);
//		}

        if (wall.getLayoutY() > 1200) {
            setNewWallPosition(wall);
            setNewWallDimension(wall);
        }

//		for (int i=0; i<balls.length; i++) {
//			if (balls[i].getLayoutY() > 900) {
//				setNewElementPosition(balls[i]);
//			}
//		}
//		player.SetNewPosition();

//                    for (int i=0; i<balls.length; i++) {
//            if (balls[i].getImage().getLayoutY() > 900) {
//                setNewElementPosition(balls[i].getImage());
//            }}

        //Logic for placing blocks begins


        //Logic for placing shield begins
//        if (shield.getLayoutY() > 1200) {
//
//            if(findChance(10,500)) {
//                setNewElementPosition(shield);
//            }


        //Logic for placing shield ends

//        if (block_destroyer.getLayoutY() > 1200) {
//
//            if(findChance(10,1000)) {
//                setNewElementPosition(block_destroyer);
//            }
//        }

//        if (magnet.getLayoutY() > 1200) {
//
//            if(findChance(10,500)) {
//                setNewElementPosition(magnet);
//            }
//        }

//        if (speedup.getLayoutY() > 1200) {
//
//            if(findChance(10,500)) {
//                setNewElementPosition(speedup);
//            }
//        }
//
//        if (slomo.getLayoutY() > 1200) {
//
//            if(findChance(10,500)) {
//                setNewElementPosition(slomo);
//            }
//        }
//
//        if (multiplier.getLayoutY() > 1200) {
//
//            if(findChance(10,500)) {
//                setNewElementPosition(multiplier);
//            }
//        }

    }



    public void generateToken() {

	    int choice = randomPositionGenerator.nextInt(12);

	    if (choice==0)
        {
            initializeToken(shield);
        }
        else if (choice == 1) {
            initializeToken(slomo);
        }
        else if (choice == 2) {
            initializeToken(speedup);
        }
        else if (choice == 3) {
            initializeToken(magnet);
        }
        else if (choice == 4) {
            initializeToken(block_destroyer);
        }
        else if (choice == 5) {
            initializeToken(magnet);
        }
        else if (choice >=6 && choice <=8) {
            initializeToken(ball.getImage());
        }
        else if (choice >=9 && choice <=10) {
            initializeToken(coin);
        }
        else {
            //do nothing
        }


    }

    public void initializeToken(ImageView i) {
	    if (i != null) {
            i.setFitWidth(25);
            i.setFitHeight(25);
            i.setLayoutY(0);
            i.setLayoutX(randomPositionGenerator.nextInt(500));
        }

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

//	private void setNewWallPosition(Rectangle image) {
//		image.setLayoutX(randomPositionGenerator.nextInt(GAME_WIDTH));
//		image.setLayoutY(10);
////		image.setLayoutY(randomPositionGenerator.nextInt(100));
//	}

//	private void setNewWallDimension(Rectangle image) {
//
//		Random r = new Random();
//		int low = 20;
//		int high = GAME_HEIGHT/2;
//		wall.setHeight(r.nextInt(high - low) + low);
//	}

	private int findNext(int high) {
        Random r = new Random();
        int low = 0;
        int num =  (r.nextInt(high - low) + low);
        return num;
    }

    private int findNextInt(int low,int high) {
        Random r = new Random();
        int num =  (r.nextInt(high - low) + low);
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
				//checkCollision();
				moveSnake();
			}
		};
		
		gameTimer.start();
	}
	
	private void moveSnake() {
//			ObservableList<Node> snake = player.getSnake();
		if (isLeftKeyPressed && !isRightKeyPressed) {
			if (((Circle) player.getSnake().get(player.getSnake().size()-1)).getCenterX() > 20) {
				for (int i=0; i<player.getSnake().size(); i++) {
					((Circle) player.getSnake().get(i)).setCenterX(((Circle) player.getSnake().get(i)).getCenterX() - gameSpeedFactor * 6);
				}
				
			}
		}
		
		if (!isLeftKeyPressed && isRightKeyPressed) {
			if (((Circle) player.getSnake().get(player.getSnake().size()-1)).getCenterX() < 580) {
				for (int i=0; i<player.getSnake().size(); i++) {
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

		SpeedUp speedupToken = new SpeedUp(5,"SPEEDUP", speedup);
	//	System.out.println(calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutY()));
		 int SNAKE_RADIUS = player.getSnakeRadius();
		 ObservableList<Node> snake = player.getSnake();
		if (SNAKE_RADIUS + COIN_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),coin.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),coin.getLayoutY())) {
			setNewElementPosition(coin);
			
			coins++;
			String textToSet = "POINTS: ";
			if (coins <10) {
				textToSet = textToSet + "0";
			}
			coinLabel.setText(textToSet + coins);
			
		}


//		for (int i=0; i<balls.length; i++) {
//			if (SNAKE_RADIUS + COIN_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),balls[i].getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),balls[i].getLayoutY())) {
//				setNewElementPosition(balls[i]);
//				Circle head = new Circle();
//				head.setCenterX(((Circle) snake.get(snake.size()-1)).getCenterX());
//				head.setCenterY(((Circle) snake.get(snake.size()-1)).getCenterY()-15.0);
//				head.setRadius(10.0);
//				head.setFill(Color.YELLOW);
//				snake.add(head);
//
//			}
//		}

		player.Grow();
		
		for (int i=0; i<blocks.length; i++) {
			if (SNAKE_RADIUS + BLOCK_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),blocks[i].getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),blocks[i].getLayoutY())) {
				//code elided
				
				
				
				
				
			}
		}

		if (SNAKE_RADIUS + SPEEDUP_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),speedup.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),speedup.getLayoutY())) {
			gameSpeedFactor = 3;

			Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    gameSpeedFactor = 1;
                }
            };

            timer.schedule(task,speedupToken.getValue()*1000);


        }

        if (SNAKE_RADIUS + SPEEDUP_RADIUS > calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),slomo.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),slomo.getLayoutY())) {
            gameSpeedFactor = 0.5f;

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    gameSpeedFactor = 1;
                }
            };

            timer.schedule(task,speedupToken.getValue()*1000);


        }

		
	}
	
	double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
	}
}

