
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private ModelBot modelBot;

    public Controller(ModelBot modelBot) throws Exception{
        this.modelBot = modelBot;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        modelBot.doAction(e.getActionCommand());
    }

    public ModelBot getModel() {
        return modelBot;
    }
}
