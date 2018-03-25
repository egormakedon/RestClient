package com.epam.makedon.jersey.client;

import com.epam.makedon.jersey.client.model.CurrentArticle;
import com.epam.makedon.jersey.client.view.Frame;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        CurrentArticle.getInstance().addObserver(frame);
        frame.show();
    }
}