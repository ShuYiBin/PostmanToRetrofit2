import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

public class Retrofit2Generator extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        JsonDialog jsonDialog = new JsonDialog(project, editor);
        jsonDialog.pack();
        jsonDialog.setTitle("Postman collection");
        jsonDialog.setSize(600, 400);
        jsonDialog.setVisible(true);
    }
}
