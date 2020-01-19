import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;




import java.util.HashMap;

public class ModelBot {
    private Viewer viewer;
    private boolean isAuthorized;

    private WebClient webClient;
    private HtmlPage homePage;

    private HtmlPage pageMyPublication;

    public ModelBot(Viewer viewer) throws Exception{
        this.viewer = viewer;
        webClient = new WebClient(BrowserVersion.FIREFOX_45);
        homePage = webClient.getPage("http://diesel.elcat.kg/");
        System.out.println("Открываем страницу"+homePage.getTitleText());
    }

    public void logoutAcc() throws Exception{
        HtmlElement divLogout = (HtmlElement) homePage.getElementById("user_navigation");
        HtmlAnchor htmlAnchorElement = (HtmlAnchor) divLogout.getElementsByTagName("a").get(3);
        homePage = webClient.getPage(htmlAnchorElement.getHrefAttribute());
        isAuthorized = false;
        System.out.println("Выход: "+homePage.getTitleText());
    }

    public void doAction(String command){
        switch (command){
            case "Войти":
                try {
                    if(isAuthorized){
                        logoutAcc();
                    }
                    submittingAuthorisationForm(viewer.getLogin(), viewer.getPassword());
                }catch (Exception ex){
                    System.out.println(ex);
                }

        }
    }

    public void submittingAuthorisationForm(String login, String password) throws Exception {
        //Загружаем форму
        homePage = webClient.getPage("https://diesel.elcat.kg/index.php?app=core&module=global&section=login");
        System.out.println(homePage.getTitleText());
        DomElement form1 = homePage.getElementById("login");
        HtmlForm form =(HtmlForm) form1;
        //Загружаем ввод логина и пароля, присваиваем им значения
        HtmlTextInput textInputLogin = form.getInputByName("ips_username");
        textInputLogin.setValueAttribute(login);
        HtmlPasswordInput textInputPassword = form.getInputByName("ips_password");
        textInputPassword.setValueAttribute(password);
        //Загружаем кнопку и нажимаем ее для авторизации. После нажатия переходим на другую страницу
        HtmlSubmitInput button = form.getInputByValue("Войти");
        homePage = button.click();
        System.out.println(homePage.getTitleText());
        if(homePage.getTitleText().equals("Diesel Forum")){
            isAuthorized = true;
        }
        System.out.println("Авторизовано:"+isAuthorized);
    }

    public HashMap<String, String> getPublications() throws Exception {
        HashMap<String, String> publicationHashList = new HashMap<String, String>();
        pageMyPublication = webClient.getPage("http://diesel.elcat.kg/index.php?app=core&module=search&do=user_activity&search_app_filters[forums][searchInKey]=&userMode=title");
        HtmlTable scriptsDiv = (HtmlTable) pageMyPublication.getElementById("forum_table");
        System.out.println(pageMyPublication.getTitleText());
        List<HtmlElement> elementsIdPublications = scriptsDiv.getElementsByTagName("h4");
        for(HtmlElement htmlElement : elementsIdPublications){
            String linkName = htmlElement.getElementsByTagName("a").get(0).getTextContent();
            HtmlAnchor htmlAnchorElement = (HtmlAnchor) htmlElement.getElementsByTagName("a").get(0);
            String link = htmlAnchorElement.getHrefAttribute();
            System.out.println(linkName+" "+link);
            publicationHashList.put(linkName, link);
        }
        return publicationHashList;
    }

    public Boolean isAuth(){
        return isAuthorized;
    }
}
