    package Application;

    import javafx.collections.ObservableList;
    import javafx.scene.Group;
    import javafx.scene.Node;
    import javafx.scene.image.ImageView;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Circle;

    public class Snake {
        private final static String BALL_IMAGE = "application/ball_bowling2.png";
        private GameViewManager game;
        private final static int SNAKE_RADIUS=10;
        private final static int BALL_RADIUS = 20;
        private ObservableList<Node> snake;
        private Group snakeBody;
        private ImageView[] balls;



        public Snake(GameViewManager game) {
            this.game = game;
            snakeBody = new Group();
            snake = snakeBody.getChildren();
            Circle head = new Circle();
            head.setCenterX(game.GAME_WIDTH/2);
            head.setCenterY(game.GAME_HEIGHT - 90);
            head.setRadius(10.0);
            head.setFill(Color.YELLOW);
            snake.add(head);

        }

        public Group getSnakeBody() {
            return snakeBody;
        }

        public ObservableList<Node> getSnake() {
            return snake;
        }

        public static int getSnakeRadius() {
            return SNAKE_RADIUS;
        }

        public static int getBallRadius() {
            return BALL_RADIUS;
        }

        public ImageView[] getBalls() {
            return balls;
        }

        public void createBalls()
        {
            this.balls = new ImageView[4];

            for (int i=0; i<this.balls.length; i++) {
                this.balls[i] = new ImageView(BALL_IMAGE);
                game.setNewElementPosition(getBalls()[i]);
                game.gamePane.getChildren().add(getBalls()[i]);
            }
        }

        public void moveSnake()
        {
            for (int i=0; i<balls.length; i++) {
            balls[i].setLayoutY(balls[i].getLayoutY()+7);
            balls[i].setRotate(balls[i].getRotate()+4);
        }
        }

        public void SetNewPosition()
        {
            for (int i=0; i<balls.length; i++) {
            if (balls[i].getLayoutY() > 900) {
                game.setNewElementPosition(balls[i]);
            }}

        }

        public void Grow()
        {		for (int i=0; i<balls.length; i++) {
            if (SNAKE_RADIUS + game.COIN_RADIUS > game.calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),balls[i].getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),balls[i].getLayoutY())) {
                game.setNewElementPosition(balls[i]);
                Circle head = new Circle();
                head.setCenterX(((Circle) snake.get(snake.size()-1)).getCenterX());
                head.setCenterY(((Circle) snake.get(snake.size()-1)).getCenterY()-15.0);
                head.setRadius(10.0);
                head.setFill(Color.YELLOW);
                snake.add(head);

            }
        }}



    }
