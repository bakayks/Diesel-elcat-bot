import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class Viewer  {
    private static final String BOT_VERSION = "1.1";
    private static final String AUTHOR = "IhAcKeArB";
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;

    public Viewer() throws Exception{
        ModelBot modelBot = new ModelBot(this);
        Controller controller = new Controller(modelBot);

        //Панель авторизации
        JPanel authPanel = getAuthJPanel(controller);
        authPanel.setBounds(0, 0, 220, 110);

        //Панель статистики
        JPanel statPanel = getStatJPanel();
        statPanel.setBounds(220, 0, 780, 110);

        JFrame frame = new JFrame("UpBot Diesel-Elcat.kg by "+AUTHOR+". Bot version-"+BOT_VERSION);
        ImageIcon img = new ImageIcon("src/main/resources/img/logo.png");
        frame.setIconImage(img.getImage());
        frame.setSize(1000, 650);
        frame.setLocation(50, 50);
        frame.add(authPanel);
        frame.add(statPanel);
        frame.setVisible(true);
    }

    public JPanel getAuthJPanel(Controller controller){
        JLabel jLabelLogin = new JLabel("Логин");
        jLabelLogin.setBounds(5,5,70,30);
        jLabelLogin.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldLogin = new JTextField();
        textFieldLogin.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldLogin.setHorizontalAlignment(JTextField.LEFT);
        textFieldLogin.setBounds(75, 5, 145, 30);

        JLabel jLabelPassword = new JLabel("Пароль");
        jLabelPassword.setBounds(5,35,70,30);
        jLabelPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword = new JTextField();
        textFieldPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword.setHorizontalAlignment(JTextField.LEFT);
        textFieldPassword.setBounds(75, 35, 145, 30);

        JButton buttonSignIn = new JButton("Войти");
        buttonSignIn.setBounds(5, 70, 215, 30);
        buttonSignIn.addActionListener(controller);
        buttonSignIn.setActionCommand("Войти");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jLabelLogin);
        panel.add(jLabelPassword);
        panel.add(textFieldLogin);
        panel.add(textFieldPassword);
        panel.add(buttonSignIn);

        return panel;
    }

    public JPanel getStatJPanel(){
        JLabel jLabelLogin = new JLabel("Статистика:");
        jLabelLogin.setBounds(250,0,280,30);
        jLabelLogin.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jLabelLogin);
        return panel;
    }

    public String getLogin(){
        return textFieldLogin.getText();
    }

    public String getPassword(){
        return textFieldPassword.getText();
    }
}
