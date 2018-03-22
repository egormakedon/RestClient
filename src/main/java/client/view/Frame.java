package client.view;

import client.Controller;
import client.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Frame implements Observer {
    private JFrame frame;
    private JPanel mainPanel;
    private Header header;

    private JLabel tittle;
    private JTextArea body;
    private JLabel date;

    private JLabel authorName;
    private JLabel surname;
    private JLabel country;

    private JLabel resourceName;
    private JLabel url;

    public Frame() {
        frame = new JFrame();
        mainPanel = new JPanel();
        header = new Header();

        tittle = new JLabel();
        body = new JTextArea();
        date = new JLabel();

        authorName = new JLabel();
        surname = new JLabel();
        country = new JLabel();

        resourceName = new JLabel();
        url = new JLabel();

        setFrame();
        setHeader();
        setButtonManager();
        setMainPanel();
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        Article article = CurrentArticle.getInstance().getArticle();

        tittle.setText("title: " + article.getTitle() + "; ");
        body.setText(article.getBody());
        date.setText("date: " + article.getDate().toString() + ";");

        Author author = article.getAuthor();
        authorName.setText("author name: " + author.getName() + "; ");
        surname.setText("author surname: " + author.getSurname() + "; ");
        country.setText("author country: " + author.getCountry() + "; ");

        Resource resource = article.getResource();
        resourceName.setText("resource name: " + resource.getName() + "; ");
        url.setText("url: " + resource.getUrl() + ";");
    }

    private void setFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Pascal Handbook");
        frame.setMinimumSize(new Dimension(1050, 700));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setHeader() {
        header.update();
        frame.add(header.getScrollPane(), BorderLayout.WEST);
    }

    private void setButtonManager() {
        JPanel panel = new JPanel();
        JButton addButton = new JButton();
        addButton.setText("add");

        JButton editButton = new JButton();
        editButton.setText("edit");

        JButton eraseButton = new JButton();
        eraseButton.setText("erase");

        panel.add(addButton);
        panel.add(editButton);
        panel.add(eraseButton);
        mainPanel.add(panel, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditingState editingState = EditingState.getInstance();
                if (editingState.getState().equals(EditingState.State.ENABLE)) {
                    return;
                }

                AddReferenceDialog dialog = new AddReferenceDialog(header);
                dialog.show();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CurrentArticle.getInstance().getArticle().getTitle().equals("")) {
                    return;
                }

                EditingState editingState = EditingState.getInstance();
                if (editingState.getState().equals(EditingState.State.ENABLE)) {
                    int id = CurrentArticle.getInstance().getArticle().getArticleId();

                    String answer = Controller.getInstance().update(id, body.getText());
                    editingState.setState(EditingState.State.DISABLE);
                    editButton.setText("edit");
                    body.setEnabled(false);
                    body.setBackground(Color.BLACK);
                    JOptionPane.showMessageDialog(frame, answer);
                } else if (editingState.getState().equals(EditingState.State.DISABLE)) {
                    editingState.setState(EditingState.State.ENABLE);
                    editButton.setText("save");
                    body.setEnabled(true);
                    body.setBackground(Color.GRAY);
                }
            }
        });

        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CurrentArticle.getInstance().getArticle().getTitle().equals("")) {
                    return;
                }

                EditingState editingState = EditingState.getInstance();
                if (editingState.getState().equals(EditingState.State.ENABLE)) {
                    return;
                }

                int result = JOptionPane.showConfirmDialog(frame, "Are you sure?");
                if (result == 0) {
                    int id = CurrentArticle.getInstance().getArticle().getArticleId();
                    String answer = Controller.getInstance().delete(id);
                    header.update();
                    JOptionPane.showMessageDialog(frame, answer);
                }
            }
        });
    }

    private void setMainPanel() {
        tittle.setFont(new Font("Times New Roman", Font.BOLD, 15));
        date.setFont(new Font("Times New Roman", Font.BOLD, 15));

        authorName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        surname.setFont(new Font("Times New Roman", Font.BOLD, 15));
        country.setFont(new Font("Times New Roman", Font.BOLD, 15));

        resourceName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        url.setFont(new Font("Times New Roman", Font.BOLD, 15));

        body.setFont(new Font("Times New Roman", Font.ITALIC, 11));
        body.setEnabled(false);
        body.setBackground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setSize(new Dimension(0, 50));
        titlePanel.setPreferredSize(new Dimension(0, 50));

        titlePanel.add(tittle);
        titlePanel.add(date);
        titlePanel.add(authorName);
        titlePanel.add(surname);
        titlePanel.add(country);
        titlePanel.add(resourceName);
        titlePanel.add(url);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(body), BorderLayout.CENTER);
        mainPanel.add(panel, BorderLayout.CENTER);
    }
}