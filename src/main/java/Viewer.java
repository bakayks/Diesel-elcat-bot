import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class Viewer{
    private JFrame frame;
    private ModelBot modelBot;

    private static final String BOT_VERSION = "1.1";
    private static final String AUTHOR = "Bakai";
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;
    private JTextField textFieldInterval;


    private List publicationsName;
    private List publicationsLink;

    private JPanel addToUpPanel;

    private JLabel status;
    private JLabel allThemesAmount;


    public Viewer() throws Exception{
        modelBot = new ModelBot(this);
        Controller controller = new Controller(modelBot);
        //Панель авторизации
        JPanel authPanel = getAuthJPanel(controller);
        authPanel.setBounds(0, 0, 230, 150);
        authPanel.setBackground(Color.white);
        authPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Панель статистики
        JPanel statPanel = getStatJPanel();
        statPanel.setBounds(230, 0, 570, 150);
        statPanel.setBackground(Color.white);
        statPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Панель для установки времени и запуска авто-апа
        JPanel timePanel = getTimeJPanel();
        timePanel.setBounds(800, 0, 200, 150);
        timePanel.setBackground(Color.white);
        timePanel.setBorder(BorderFactory.createLineBorder(Color.black));

//
//        //Панель для просмотра информации тем
//        JPanel infoPanel = getTimeJPanel();
//        infoPanel.setBounds(500, 150, 500, 500);
//        infoPanel.setBackground(Color.white);
//        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Панель для добавления тем на ап
        JLabel jLabelInfo = new JLabel("Выберите темы для Авто-Апа:");
        jLabelInfo.setBounds(200,0,100,30);
        jLabelInfo.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));
        addToUpPanel = new JPanel();
        addToUpPanel.setBounds(0, 150, 500, 500);
        addToUpPanel.setBackground(Color.white);
        addToUpPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        addToUpPanel.add(jLabelInfo);

        frame = new JFrame("UpBot Diesel-Elcat.kg by "+AUTHOR+". Bot version-"+BOT_VERSION);
        ImageIcon img = new ImageIcon("src/main/resources/img/logo.png");
        frame.setIconImage(img.getImage());
        frame.setSize(1000, 650);
        frame.setMinimumSize(new Dimension(1000,650));
        frame.setLocation(50, 50);
        frame.add(addToUpPanel);
//        frame.add(infoPanel);
        frame.add(timePanel);
        frame.add(statPanel);
        frame.add(authPanel);
        frame.setVisible(true);
    }

    public JPanel getAuthJPanel(Controller controller){
        JLabel jLabelAuth = new JLabel("Авторизация:");
        jLabelAuth.setBounds(35,0,150,30);
        jLabelAuth.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));

        JLabel jLabelLogin = new JLabel("Логин");
        jLabelLogin.setBounds(10,40,60,30);
        jLabelLogin.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldLogin = new JTextField();
        textFieldLogin.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldLogin.setHorizontalAlignment(JTextField.LEFT);
        textFieldLogin.setBounds(75, 40, 145, 30);

        JLabel jLabelPassword = new JLabel("Пароль");
        jLabelPassword.setBounds(10,75,60,30);
        jLabelPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword = new JTextField();
        textFieldPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword.setHorizontalAlignment(JTextField.LEFT);
        textFieldPassword.setBounds(75, 75, 145, 30);

        JButton buttonSignIn = new JButton("Войти");
        buttonSignIn.setBounds(10, 110, 210, 30);
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
        JLabel jLabelLogin = new JLabel("Статистика");
        jLabelLogin.setBounds(210,0,150,30);
        jLabelLogin.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));

        JLabel statusLabel = new JLabel("Статус:");
        statusLabel.setBounds(10,40,130,30);
        statusLabel.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        status = new JLabel("Оффлайн");
        status.setBounds(10,70,130,30);
        status.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));

        JLabel allThemesLabel = new JLabel("Всего тем:");
        allThemesLabel.setBounds(140,40,130,30);
        allThemesLabel.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        allThemesAmount = new JLabel("0");
        allThemesAmount.setBounds(140,70,130,30);
        allThemesAmount.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jLabelLogin);
        panel.add(statusLabel);
        panel.add(allThemesLabel);
        panel.add(status);
        panel.add(allThemesAmount);
        return panel;
    }

    public JPanel getTimeJPanel(){
        JLabel jLabelInterval = new JLabel("Интервал:");
        jLabelInterval.setBounds(80,0,40,30);
        jLabelInterval.setFont(new java.awt.Font("Alergia", Font.BOLD, 18));

        JLabel jLabelKajdie = new JLabel("Каждые");
        jLabelInterval.setBounds(10,70,40,30);
        jLabelInterval.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));

        textFieldInterval = new JTextField();
        textFieldPassword.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));
        textFieldPassword.setHorizontalAlignment(JTextField.LEFT);
        textFieldPassword.setBounds(60, 70, 80, 30);

        JLabel jLabelMinut = new JLabel("минут.");
        jLabelInterval.setBounds(150,70,40,30);
        jLabelInterval.setFont(new java.awt.Font("Alergia", Font.ROMAN_BASELINE, 16));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jLabelInterval);
        panel.add(jLabelKajdie);
        panel.add(textFieldInterval);
        panel.add(jLabelMinut);
        return panel;
    }

    public void setUpdatePublicationList(String[] listOfPublications) throws Exception{
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        tableModel.addColumn("Список тем");
        for(int i = 0; i<listOfPublications.length; i++){
            tableModel.insertRow(0, new Object[] { listOfPublications[i]});
        }
        addToUpPanel.add(new JScrollPane(table));
        frame.setVisible(true);
    }

    public void setStatus(boolean bool){
        if(bool){
            status.setText("Онлайн");
        }else{
            status.setText("Оффлайн");
        }
    }

    public void setThemesAmount(int amount){
        allThemesAmount.setText(String.valueOf(amount));
    }

    public String getLogin(){
        return textFieldLogin.getText();
    }

    public String getPassword(){
        return textFieldPassword.getText();
    }

}