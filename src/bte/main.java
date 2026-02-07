package bte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class main extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem newItem, openItem, saveItem, exitItem;
    JFileChooser fileChooser;

    public main() {
        // Constructor just sets up the UI
        setupUI();
    }

    private void setupUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bed - Untitled");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);

        // Menu Setup
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
        
        fileChooser = new JFileChooser(".");
        this.setVisible(true);
    }

    // NEW METHOD: Open a specific file automatically
    public void openFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String s1 = "", s2 = "";
                while ((s1 = br.readLine()) != null) {
                    s2 += s1 + "\n";
                }
                textArea.setText(s2);
                br.close();
                this.setTitle("Bed - " + file.getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage());
            }
        } else {
            // If file doesn't exist, we just start with a blank screen and the title set
            this.setTitle("Bed - " + file.getName() + " (New File)");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                openFile(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } 
        else if (e.getSource() == saveItem) {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(textArea.getText());
                    bw.close();
                    this.setTitle("Bed - " + file.getName());
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            }
        }
        else if (e.getSource() == exitItem) System.exit(0);
        else if (e.getSource() == newItem) {
            textArea.setText("");
            this.setTitle("Bed - Untitled");
        }
    }

    public static void main(String[] args) {
        main editor = new main();
        
        // CHECK ARGUMENTS
        if (args.length > 0) {
            // If user typed "xed filename.txt", args[0] is "filename.txt"
            editor.openFile(args[0]);
        }
    }
}