package bte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class main extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, viewMenu;
    JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem;
    JFileChooser fileChooser;

    Font uiFont = new Font("Segoe UI", Font.PLAIN, 25);
    Font editorFont = new Font("Monospaced", Font.PLAIN, 25);

    public main() {
        setupUI();
    }

    private void setupUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Bed - Untitled");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(editorFont);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        createMenuBar();
        this.setJMenuBar(menuBar);

        fileChooser = new JFileChooser(".");
        this.setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        fileMenu.setFont(uiFont);
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newItem = new JMenuItem("New");
        newItem.setFont(uiFont);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(this);

        openItem = new JMenuItem("Open...");
        openItem.setFont(uiFont);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openItem.addActionListener(this);

        saveItem = new JMenuItem("Save");
        saveItem.setFont(uiFont);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(this);

        saveAsItem = new JMenuItem("Save As...");
        saveAsItem.setFont(uiFont);
        saveAsItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        saveAsItem.addActionListener(this); 
        exitItem = new JMenuItem("Quit");
        exitItem.setFont(uiFont);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(this); 
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        editMenu = new JMenu("Edit");
        editMenu.setFont(uiFont);
        editMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setFont(uiFont);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.setFont(uiFont);
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        cutItem.addActionListener(e -> textArea.cut());

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.setFont(uiFont);
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        copyItem.addActionListener(e -> textArea.copy());

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.setFont(uiFont);
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        pasteItem.addActionListener(e -> textArea.paste());

        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.setFont(uiFont);
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        selectAllItem.addActionListener(e -> textArea.selectAll());

        editMenu.add(undoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(selectAllItem);

        viewMenu = new JMenu("View");
        viewMenu.setFont(uiFont);

        JCheckBoxMenuItem wordWrapItem = new JCheckBoxMenuItem("Word Wrap");
        wordWrapItem.setFont(uiFont);
        wordWrapItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
        wordWrapItem.addActionListener(e -> {
            textArea.setLineWrap(wordWrapItem.isSelected());
            textArea.setWrapStyleWord(wordWrapItem.isSelected());
        });
        viewMenu.add(wordWrapItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
    }

    public void openFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                textArea.read(br, null);
                br.close();
                this.setTitle("Bed - " + file.getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage());
            }
        }
    }

    public void saveFile(String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            textArea.write(bw);
            bw.close();
            this.setTitle("Bed - " + new File(path).getName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                openFile(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == saveItem) {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                saveFile(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == saveAsItem) {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                saveFile(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        } else if (e.getSource() == newItem) {
            textArea.setText("");
            this.setTitle("Bed - Untitled");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            setUIFont(new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN, 20));

        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            main editor = new main();
            if (args.length > 0) {
                editor.openFile(args[0]);
            }
        });
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}