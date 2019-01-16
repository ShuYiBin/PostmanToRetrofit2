import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class JsonDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea jsonTextArea;

    private Project mProject;
    private Editor mEditor;

    public JsonDialog(Project project, Editor editor) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        mProject = project;
        mEditor = editor;

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Collection collection = parsePostman(jsonTextArea.getText());
        if(collection!=null) generateRxJavaCode(collection.getItem());

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private Collection parsePostman(String jsonString) {
        try{
            return new Gson().fromJson(jsonString, Collection.class);
        } catch (Exception e){
            System.out.println("Json parse failed.");
        }
        return null;
    }

    private void generateRxJavaCode(List<Collection.ItemBean> items) {
        for(Collection.ItemBean item : items) {
            int startOffset;
            if(items.indexOf(item) == 0) startOffset = mEditor.getDocument().getText().lastIndexOf("{")+1;
            else startOffset = mEditor.getDocument().getText().lastIndexOf(";")+1;
            String annotation = getAnotation(item);
            String method = getMethod(item);
            WriteCommandAction.runWriteCommandAction(mProject, ()-> mEditor.getDocument().insertString(startOffset, "\n    " + annotation + method));
        }
    }

    private String getAnotation(Collection.ItemBean item) {
        String result = "";
        if(!item.getRequest().getMethod().equalsIgnoreCase("GET")) result += "@FormUrlEncoded\n";
        result += "    @" + item.getRequest().getMethod();
        if(item.getRequest().getUrl() instanceof String) result += "(\"" + getApiPath((String)item.getRequest().getUrl(), false) + "\")";
        else if(item.getRequest().getUrl() instanceof LinkedTreeMap) {
            LinkedTreeMap url = (LinkedTreeMap)item.getRequest().getUrl();
            result += "(\"" + getApiPath(url.get("raw").toString(), false) + "\")";
        }
        return result + "\n";

    }

    private String getApiPath(String url, boolean hasDomain) {
        URI uri;
        try {
            if(!hasDomain) {
                uri = new URI(url);
                String domain = uri.getHost();
                url = url.replace("http://", "");
                url = url.replace("https://", "");
                url = url.replace(domain, "");
                return url;
            }
            return url;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getMethod(Collection.ItemBean item) {
        String result = "    Single<" + item.getName().trim() + "Response> " + item.getName().trim() + "(";
        if(item.getRequest().getMethod().equalsIgnoreCase("GET")) result = addQueryParams(item, result);
        else result = addFieldParams(item, result);
        return result;
    }

    private String addFieldParams(Collection.ItemBean item, String result) {
        //from Url-encoded
        if(item.getRequest().getBody().getUrlencoded()!=null) {
            for (Collection.ItemBean.RequestBean.BodyBean.UrlencodedBean urlencoded : item.getRequest().getBody().getUrlencoded()) {
                result += "@Field(\"" + urlencoded.getKey() + "\") " + "String " + urlencoded.getKey();
                if (item.getRequest().getBody().getUrlencoded().indexOf(urlencoded) != item.getRequest().getBody().getUrlencoded().size() - 1)
                    result += ", ";
            }
            return result + ");";
        }
        //from form-data
        if(item.getRequest().getBody().getFormdata()!=null) {
            for (Collection.ItemBean.RequestBean.BodyBean.FormdataBean formdata : item.getRequest().getBody().getFormdata()) {
                result += "@Field(\"" + formdata.getKey() + "\") " + "String " + formdata.getKey();
                if (item.getRequest().getBody().getFormdata().indexOf(formdata) != item.getRequest().getBody().getFormdata().size() - 1)
                    result += ", ";
            }
            return result + ");";
        }
        return result+");";
    }

    private String addQueryParams(Collection.ItemBean item, String result) {
        return result+");";
    }
}
