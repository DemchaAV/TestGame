package org.example.testgame;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg2, enemy;
    @FXML
    Label labelPause, labelLose, scoreLabel;

    private int score = 0;


    @FXML
    public static final int BG_WIDTH = 712;
    private ParallelTransition parallelTransition;
    private TranslateTransition enemyTransition;
    public static boolean jump = false;
    public static boolean goDown = false;
    public static boolean isPause;
    public static boolean right = false;
    public static boolean left = false;


    @FXML
    private ImageView player_body;

    @FXML
    private ImageView player_head;
    @FXML
    private ImageView player_legs;

    private Player player = new Player(player_head,player_body,player_legs);
    @FXML ImageView player_view = player_head;



    @FXML
    private Group player_hitbox;


    private int playerSpeed = 3, jumpDownSpeed = 5;

    private void pauseGame(boolean status) {
        if (status) {
            playerSpeed = 0;
            jumpDownSpeed = 0;
            parallelTransition.pause();
            enemyTransition.pause();
        } else {
            playerSpeed = 3;
            jumpDownSpeed = 5;
            parallelTransition.play();
            enemyTransition.play();

        }
    }

    private void move(String move) {

        if (move.equals("right")) {
            player_head.setLayoutX(player_head.getLayoutX() + playerSpeed);
            player_body.setLayoutX(player_body.getLayoutX() + playerSpeed);
            player_legs.setLayoutX(player_legs.getLayoutX() + playerSpeed);
        } else if (move.equals("left")) {
            player_head.setLayoutX(player_head.getLayoutX() - playerSpeed);
            player_body.setLayoutX(player_body.getLayoutX() - playerSpeed);
            player_legs.setLayoutX(player_legs.getLayoutX() - playerSpeed);
        }

    }

    private void jumpUP(boolean statusJump) {
        if (statusJump) {
            player_head.setLayoutY(player_head.getLayoutY() - jumpDownSpeed);
            player_body.setLayoutY(player_body.getLayoutY() - jumpDownSpeed);
            player_legs.setLayoutY(player_legs.getLayoutY() - jumpDownSpeed);
        } else if (player_head.getLayoutY() <= 128f) {
            jump = false;
            player_head.setLayoutY(player_head.getLayoutY() + playerSpeed);
            player_body.setLayoutY(player_body.getLayoutY() + playerSpeed);
            player_legs.setLayoutY(player_legs.getLayoutY() + playerSpeed);
        }
    }
    private void jumpDown(boolean statusJump) {
        int speedDown = 7;
        if (statusJump) {
            System.out.println(speedDown);
            player_head.setLayoutY(player_head.getLayoutY() + speedDown);
            player_body.setLayoutY(player_body.getLayoutY() + speedDown);
            player_legs.setLayoutY(player_legs.getLayoutY() + speedDown);
        }
    }
    private void scoreCount(){
        score+=5;
        scoreLabel.setText(Integer.toString(score));
    }

    AnimationTimer timer = new AnimationTimer() {
        boolean jumpedOverEnemy = false;

        @Override
        public void handle(long l) {
            if (jump == true && player_head.getLayoutY() > 5f) {
                jumpUP(true);
            } else if (player_head.getLayoutY() <= 128f) {
                    jump = false;
                    jumpUP(false);

            }
            if (right && player_head.getLayoutX() < 100f) {
                move("right");
            }
            if (left && player_head.getLayoutX() > -45f) {
                move("left");

            }
            if (isPause && !labelPause.isVisible()) {
                pauseGame(true);
                labelPause.setVisible(true);
            } else if (!isPause && labelPause.isVisible()) {
                pauseGame(false);
                labelPause.setVisible(false);
            }
            if (player_legs.getBoundsInParent().intersects(enemy.getBoundsInParent())||player_body.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                labelLose.setVisible(true);
                pauseGame(true);
            }
            if(player_legs.getLayoutX()>enemyTransition.getNode().getTranslateX()+810){
               scoreCount();
            }
        }
    };


    @FXML
    void initialize() {
        TranslateTransition bg1Transition = new TranslateTransition(Duration.millis(5000), bg1);
        bg1Transition.setFromX(0);
        bg1Transition.setToX(BG_WIDTH * -1);
        bg1Transition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bg2Transition = new TranslateTransition(Duration.millis(5000), bg2);
        bg2Transition.setFromX(0);
        bg2Transition.setToX(BG_WIDTH * -1);
        bg2Transition.setInterpolator(Interpolator.LINEAR);

        enemyTransition = new TranslateTransition(Duration.millis(3500), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(BG_WIDTH * -1 - 100);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();


        parallelTransition = new ParallelTransition(bg1Transition, bg2Transition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();


        timer.start();


    }

}
