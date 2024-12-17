package cipher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    // Using this process to invoke the constructor,
    static JFileChooser fileInput = new JFileChooser();

    JRadioButton encryption = new JRadioButton("Encryption");
    JRadioButton decryption = new JRadioButton("Decryption");
    JRadioButton brutforce = new JRadioButton("BrutForce");

    String path = "Path: ";
    JLabel fileNameLabel = new JLabel(path);
    JTextField shift = new JTextField("0");

    public GUI() {
        super("Cipher from Maksim Isakov");
        this.setSize(500, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel container = new JPanel(new GridLayout(3, 1, 5 ,5));
        this.add(container);

        JPanel workingMode = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fileChooser = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel keySet = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Layout order
        container.add(workingMode);
        container.add(fileChooser);
        container.add(keySet);

        //workingMode
        JLabel workingModeLabel = new JLabel("Working mode:");
        workingMode.add(workingModeLabel);
        ButtonGroup group = new ButtonGroup();
        group.add(encryption);
        group.add(decryption);
        group.add(brutforce);
        encryption.setSelected(true);
        workingMode.add(encryption);
        workingMode.add(decryption);
        workingMode.add(brutforce);

        //keySet
        JLabel keyLabel = new JLabel("Key (numbers only):");
        keySet.add(keyLabel);
        shift.setPreferredSize(new Dimension( 100, 24 ));
        keySet.add(shift);
        JButton startButton = new JButton("Do some MAGIC!");
        keySet.add(startButton);
        startButton.addActionListener(new DoSomeMagicListener());

        //fileChooser
        JButton inputFileChooser = new JButton("Select file to read");
        fileChooser.add(inputFileChooser);
        inputFileChooser.addActionListener(new FileChooserListener());
        fileChooser.add(fileNameLabel);
    }

    class FileChooserListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            fileInput.showSaveDialog(null);
            fileNameLabel.setText(path + fileInput.getSelectedFile().getAbsolutePath());
        }
    }

    class DoSomeMagicListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String result = null;
            int key = Integer.parseInt(shift.getText());
            String text = FileHandler.readFile(fileInput.getSelectedFile());
            if (encryption.isSelected()) {
                result = Cipher.encrypt(text, key);
            } else if (decryption.isSelected()) {
                result = Cipher.decrypt(text, key);
            } else if (brutforce.isSelected()) {
                result = Cipher.brutForce(text);
            }
            if (result == null)  {
                JOptionPane.showMessageDialog(null, "Something go wrong!", "Help", JOptionPane.PLAIN_MESSAGE);
            } else {
                FileHandler.writeFile(result);
                JOptionPane.showMessageDialog(null, "Task complete! Result file in same directory as original file.", "Help", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
}
