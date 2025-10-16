import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM extends JFrame {
    private double balance = 1000.0;
    private final int PIN = 1234;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ATM() {
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(pinPanel(), "PIN");
        mainPanel.add(menuPanel(), "MENU");

        add(mainPanel);
        cardLayout.show(mainPanel, "PIN");
    }

    private JPanel pinPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // light blue background

        JLabel label = new JLabel("Enter PIN", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JPasswordField pinField = new JPasswordField();
        pinField.setFont(new Font("Arial", Font.PLAIN, 16));
        pinField.setHorizontalAlignment(JTextField.CENTER);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 123, 255));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);

        loginBtn.addActionListener(e -> {
            try {
                int enteredPin = Integer.parseInt(String.valueOf(pinField.getPassword()));
                if (enteredPin == PIN) {
                    cardLayout.show(mainPanel, "MENU");
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter digits only!");
            }
        });

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBackground(new Color(240, 248, 255));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        centerPanel.add(pinField);
        centerPanel.add(loginBtn);

        panel.add(label, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel menuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel heading = new JLabel("ATM Menu", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        JButton checkBtn = createStyledButton("Check Balance", new Color(0, 153, 51));
        JButton depositBtn = createStyledButton("Deposit", new Color(0, 102, 204));
        JButton withdrawBtn = createStyledButton("Withdraw", new Color(204, 102, 0));
        JButton exitBtn = createStyledButton("Exit", new Color(200, 0, 0));

        checkBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, String.format("Current Balance: ₹%.2f", balance))
        );

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(this, "Deposited successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(this, "Withdrawn successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(heading);
        panel.add(checkBtn);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(exitBtn);

        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM().setVisible(true));
    }
}

