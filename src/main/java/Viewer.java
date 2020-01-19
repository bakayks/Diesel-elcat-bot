import javax.swing.*;
import java.awt.*;

public class Viewer{
    private static final String BOT_VERSION = "1.1";
    private static final String AUTHOR = "IhAcKeArB";
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;

    private JLabel status;
    private JLabel allThemesAmount;


    public Viewer() throws Exception{
        ModelBot modelBot = new ModelBot(this);
        Controller controller = new Controller(modelBot);

        //Панель авторизации
        JPanel authPanel = getAuthJPanel(controller);
        authPanel.setBounds(0, 0, 220, 150);

        //Панель статистики
        JPanel statPanel = getStatJPanel();
        statPanel.setBounds(225, 0, 780, 150);

        JFrame frame = new JFrame("UpBot Diesel-Elcat.kg by "+AUTHOR+". Bot version-"+BOT_VERSION);
        ImageIcon img = new ImageIcon("src/main/resources/img/logo.png");
        frame.setIconImage(img.getImage());
        frame.setSize(1000, 650);
        frame.setMinimumSize(new Dimension(1000,650));
        frame.setLocation(50, 50);
        frame.add(statPanel);
        frame.add(authPanel);
        frame.setVisible(true);
    }

    public JPanel getAuthJPanel(Controller controller){
        JLabel jLabelAuth = new JLabel("Авторизация:");
        jLabelAuth.setBounds(35,0,150,30);
        jLabelAuth.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));

        JLabel jLabelLogin = new JLabel("Логин");
        jLabelLogin.setBounds(5,50,70,30);
        jLabelLogin.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldLogin = new JTextField();
        textFieldLogin.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldLogin.setHorizontalAlignment(JTextField.LEFT);
        textFieldLogin.setBounds(75, 50, 145, 30);

        JLabel jLabelPassword = new JLabel("Пароль");
        jLabelPassword.setBounds(5,85,70,30);
        jLabelPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword = new JTextField();
        textFieldPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword.setHorizontalAlignment(JTextField.LEFT);
        textFieldPassword.setBounds(75, 85, 145, 30);

        JButton buttonSignIn = new JButton("Войти");
        buttonSignIn.setBounds(5, 120, 215, 30);
        buttonSignIn.addActionListener(controller);
        buttonSignIn.setActionCommand("Войти");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jLabelLogin);
        panel.add(jLabelPassword);
        panel.add(jLabelAuth);
        panel.add(textFieldLogin);
        panel.add(textFieldPassword);
        panel.add(buttonSignIn);

        return panel;
    }

    public JPanel getStatJPanel(){
        JLabel jLabelLogin = new JLabel("Статистика:");
        jLabelLogin.setBounds(315,0,150,30);
        jLabelLogin.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));

        status = new JLabel("Статус: Оффлайн");
        status.setBounds(0,40,195,30);
        status.setBackground(Color.red);
        status.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        status.revalidate();

        allThemesAmount = new JLabel("Всего тем: 0");
        allThemesAmount.setBounds(195,40,195,30);
        allThemesAmount.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        allThemesAmount.revalidate();


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jLabelLogin);
        panel.add(status);
        panel.add(allThemesAmount);
        return panel;
    }

    public void setStatus(boolean bool){
        if(bool){
            status.setText("Статус: Онлайн");
            status.setBackground(Color.green);
        }else{
            status.setText("Статус: Оффлайн");
            status.setBackground(Color.red);
        }
    }

    public void setThemesAmount(int amount){
        allThemesAmount.setText("Всего тем: "+amount);
    }

    public String getLogin(){
        return textFieldLogin.getText();
    }

    public String getPassword(){
        return textFieldPassword.getText();
    }
}