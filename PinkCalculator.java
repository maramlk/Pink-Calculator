import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PinkCalculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JPanel panel;
    Font font = new Font("Segoe UI", Font.PLAIN, 28);

    JButton[] numberButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, clrButton;

    String operator = "";
    double num1 = 0, num2 = 0;

    Color pink = new Color(255, 192, 203);       // light pink
    Color darkerPink = new Color(255, 160, 180); // for hover or highlights
    Color peach = new Color(255, 105, 180);      // equals button

    PinkCalculator() {
        frame = new JFrame("Pink Calculator");
        frame.setSize(360, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(pink);

        textfield = new JTextField();
        textfield.setBounds(10, 10, 320, 80);
        textfield.setFont(font);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setEditable(false);
        textfield.setBackground(Color.WHITE);
        textfield.setForeground(Color.DARK_GRAY);
        frame.add(textfield);

        panel = new JPanel(new GridBagLayout());
        panel.setBounds(10, 100, 320, 500);
        panel.setBackground(pink);
        frame.add(panel);

        // Create buttons
        addButton = createButton("+");
        subButton = createButton("-");
        mulButton = createButton("×");
        divButton = createButton("÷");
        decButton = createButton(".");
        equButton = createButton("=");
        clrButton = createButton("AC");

        equButton.setBackground(peach);
        equButton.setForeground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
        }

        // === Layout ===
        // Row 0
        addToPanel(panel, divButton, 0, 0, 1, 1);
        addToPanel(panel, mulButton, 1, 0, 1, 1);
        addToPanel(panel, subButton, 2, 0, 1, 1);
        addToPanel(panel, addButton, 3, 0, 1, 1); // AC spans 2 rows

        // Row 1
        addToPanel(panel, clrButton, 3, 1, 1, 2);

        // Row 2
        addToPanel(panel, numberButtons[7], 0, 1, 1, 1);
        addToPanel(panel, numberButtons[8], 1, 1, 1, 1);
        addToPanel(panel, numberButtons[9], 2, 1, 1, 1);

        // Row 3
        addToPanel(panel, numberButtons[4], 0, 2, 1, 1);
        addToPanel(panel, numberButtons[5], 1, 2, 1, 1);
        addToPanel(panel, numberButtons[6], 2, 2, 1, 1);

        // Row 4
        addToPanel(panel, numberButtons[1], 0, 3, 1, 1);
        addToPanel(panel, numberButtons[2], 1, 3, 1, 1);
        addToPanel(panel, numberButtons[3], 2, 3, 1, 1);
        addToPanel(panel, equButton, 3, 3, 1, 2); // = spans 2 rows

        // Row 5
        addToPanel(panel, numberButtons[0], 0, 4, 2, 1); // 0 spans 2 columns
        addToPanel(panel, decButton, 2, 4, 1, 1);

        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setFocusable(false);
        btn.setBackground(darkerPink);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.addActionListener(this);
        return btn;
    }

    private void addToPanel(JPanel panel, JButton btn, int x, int y, int w, int h) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(btn, gbc);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
                return;
            }
        }

        if (e.getSource() == decButton && !textfield.getText().contains(".")) {
            textfield.setText(textfield.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "+";
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "-";
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "*";
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = "/";
            textfield.setText("");
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());
            double result = 0;
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num2 != 0 ? num1 / num2 : Double.NaN; break;
            }
            textfield.setText(String.valueOf(result));
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
    }

    public static void main(String[] args) {
        new PinkCalculator();
    }
}

