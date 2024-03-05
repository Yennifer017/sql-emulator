
package compi1.sqlemulator.files;

import compi1.sqlemulator.files.models.FileProject;
import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author yenni
 */
public class CreatorFileIDE {
    
    private static final String ROOT_FOLDER = "PROYECTO", FOLDER = "CARPETA", FILE = "archivo";
    private static final String OPEN_TAG = "<", CLOSE_TAG = ">", CLOSE_AUX_TAG = "/";
    private String content;
    public CreatorFileIDE() {
        content = "";
    }
    
    public String generateIDEfile(JTree treeFiles){
        content = "";
        DefaultTreeModel model = (DefaultTreeModel) treeFiles.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        
        FileProject rootNode = (FileProject) root.getUserObject();
        generateFolder(ROOT_FOLDER, rootNode.getFile(), 0);
        generateFromFolder(root);
        closeTagFile(ROOT_FOLDER, 0);
        return content;
    }
    
    private void generateFromFolder(DefaultMutableTreeNode folderNode){
        for (int i = 0; i < folderNode.getChildCount(); i++) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) folderNode.getChildAt(i);
            FileProject fileProject = (FileProject) currentNode.getUserObject();
            File currentFile = fileProject.getFile();
            if(currentFile.isDirectory()){
                generateFolder(FOLDER, currentFile, fileProject.getIdentation());
                generateFromFolder(currentNode);
                closeTagFile(FOLDER, fileProject.getIdentation());
            }else{
                generateFile(currentFile, fileProject.getIdentation());
            }
        }
    }
    
    private void generateFolder(String type, File file, int identation){
        content += OPEN_TAG + type + " nombre=" + entreComillas(file.getName()) + CLOSE_TAG + "\n";
    }
    
    private void closeTagFile(String type, int identation){
        content += OPEN_TAG + CLOSE_AUX_TAG + type + CLOSE_TAG + "\n";
    }
    
    private void generateFile(File file, int identation){
        content += OPEN_TAG + FILE + " nombre=" + entreComillas(file.getName()) 
                + " ubicacion=" + entreComillas(file.getAbsolutePath()) 
                + CLOSE_AUX_TAG + CLOSE_TAG + "\n";
    }
 
    
    private String comillas(){
        return "\"";
    }
    
    private String entreComillas(String contenido){
        return comillas() + contenido + comillas();
    }
    
}
