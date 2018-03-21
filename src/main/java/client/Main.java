package client;

import client.model.CurrentArticle;
import client.view.Frame;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        CurrentArticle.getInstance().addObserver(frame);
        frame.show();
    }
}