    package Application;

    import javafx.collections.ObservableList;
    import javafx.scene.Group;
    import javafx.scene.Node;
    import javafx.scene.control.Label;
    import javafx.scene.image.ImageView;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Circle;
    import javafx.scene.text.Font;

    public class Snake {
        private final static String BALL_IMAGE = "application/ball_bowling2.png";
        private GameViewManager game;
        private final static int SNAKE_RADIUS=10;
        private final static int BALL_RADIUS = 20;
        private ObservableList<Node> snake;
        private Group snakeBody;
        private Label Value;
//        private ImageView[] balls;

        public Label getValue() {
            return Value;
        }

        public void setValue(Label label) {
            this.Value = label;
        }


        public Snake(GameViewManager game) {
            this.game = game;
            snakeBody = new Group();
            snake = snakeBody.getChildren();
            Circle Head = new Circle();
            Head.setCenterX(game.GAME_WIDTH/2);
            Head.setCenterY(game.GAME_HEIGHT - 90);
            Head.setRadius(10.0);
            Head.setFill(Color.YELLOW);

            snake.add(Head);

            for (int i = 0; i <3; i++) {
                Circle head = new Circle();
                head.setFill(Color.YELLOW);
                head.setCenterX(((Circle) snake.get(i)).getCenterX());
                head.setCenterY(((Circle) snake.get(i)).getCenterY() - 15.0);
                head.setRadius(10.0);
                if (i == 2)
                    head.setFill(Color.RED);
                snake.add(head);

            }



            this.setNextValue();
            this.AlignLabel();
            Value.setText(Integer.toString(4));
//            game.getGamePane().getChildren().add(Value);

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

        public void setNextValue()
        {
            /* Sets value of label*/
            Label text;
            text = new Label(Integer.toString(computeValue()));
            text.setTextFill(Color.WHITE);
//        text.setMinWidth(100);
//        text.setMinHeight(100);
            text.setFont(new Font("Cambria", 18));
            text.setStyle("-fx-font-weight: bold");
            setValue(text);
        }

        public void changeText()
        {
            this.getValue().setText(Integer.toString(computeValue()));
        }

        public int computeValue() {
            return snake.size();
        }

        public void AlignLabel()
        {
            Circle head = ((Circle) snake.get(snake.size() - 1));
//            this.setNextValue();

            this.changeText();
            Value.setLayoutX(head.getCenterX()-5);
            Value.setLayoutY(head.getCenterY()-Ball.getImage_height());

        }

//        public ImageView[] getBalls() {
//            return balls;
//        }






//        public void SetNewPosition()
//        {
//            for (int i=0; i<balls.length; i++) {
//            if (balls[i].getLayoutY() > 900) {
//                game.setNewElementPosition(balls[i]);
//            }}
//
//        }

        public void Grow()
        {

            int BALL_RADIUS = Ball.getRadius();
            ImageView ball = game.getBallImage();
            if (SNAKE_RADIUS + BALL_RADIUS > game.calculateDistance(((Circle) snake.get(snake.size()-1)).getCenterX(),ball.getLayoutX(),((Circle) snake.get(snake.size()-1)).getCenterY(),ball.getLayoutY())) {
              //  game.setNewElementPosition(game.ball.getImage());//how?
                Circle head = new Circle();
                head.setCenterX(((Circle) snake.get(snake.size()-1)).getCenterX());
                head.setCenterY(((Circle) snake.get(snake.size()-1)).getCenterY()-15.0);
                head.setRadius(10.0);
                head.setFill(Color.YELLOW);
                snake.add(head);

            }
        }

        public int getLength()
        {
            return snake.size();
        }



    }
