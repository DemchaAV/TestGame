package org.example.testgame;

import javafx.scene.image.ImageView;

public class Player extends ImageView {

    private ImageView body;
    private ImageView head;
    private ImageView legs;
    private  int speed;
    boolean jump = false;


    private int goUp = 5, goDown = 3;


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Player(ImageView body, ImageView head, ImageView legs) {
        this.body = body;
        this.head = head;
        this.legs = legs;
    }
    private void jumpUP(boolean statusJump) {
        if (statusJump) {
            head.setLayoutY(head.getLayoutY() - goUp);
            body.setLayoutY(body.getLayoutY() - goUp);
            legs.setLayoutY(legs.getLayoutY() - goUp);
        } else if (head.getLayoutY() <= 128f) {
            jump = false;
            head.setLayoutY(head.getLayoutY() + goDown);
            body.setLayoutY(body.getLayoutY() + goDown);
            legs.setLayoutY(legs.getLayoutY() + goDown);
        }
    }

    private void move(String move) {

        if (move.equals("right")) {
            head.setLayoutX(head.getLayoutX() + speed);
            body.setLayoutX(body.getLayoutX() + speed);
            legs.setLayoutX(legs.getLayoutX() + speed);
        } else if (move.equals("left")) {
            head.setLayoutX(head.getLayoutX() - speed);
            body.setLayoutX(body.getLayoutX() - speed);
            legs.setLayoutX(legs.getLayoutX() - speed);
        }

    }
    public void goDown(){

    }
}
