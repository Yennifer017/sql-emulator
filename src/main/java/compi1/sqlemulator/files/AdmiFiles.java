package compi1.sqlemulator.files;

import compi1.sqlemulator.files.models.FileProject;
import compi1.sqlemulator.files.models.OpenFile;
import compi1.sqlemulator.exceptions.*;
import compi1.sqlemulator.util.BinarySearch;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author yenni
 */
public class AdmiFiles {

    public static final String aceptedExtensions[] = {"csv"};
    private static final String EMPTY_NOTATION = "[none]";

    private List<FileProject> currentProject;
    private List<OpenFile> openFiles;
    private OpenFile currentFile;

    private UtilForFiles filesU;
    private UtilForDirectories directoryU;
    private CreatorFileIDE creatorFile;

    private JTree treeDisplay;
    private JPanel filesBar;
    private JTextPane displayContent;
    private JLabel labelForFileName;
    private CSVinterpretor csvInterpretor;

    private static final String COLUMN_FORMAT = "([a-z]|[A-Z]| _ )([a-z]|[A-Z]| _ |[0-9]| - | @ | + | [*] | #)*";
    private static final String CSV_COLUMNS_FORMAT = COLUMN_FORMAT + "(," + COLUMN_FORMAT + "+)*";

    public AdmiFiles(JTree treeDisplay, JPanel filesBar, JTextPane displayContent, JLabel labelForFileName) {
        this.treeDisplay = treeDisplay;
        this.filesU = new UtilForFiles();
        this.directoryU = new UtilForDirectories();
        currentProject = new LinkedList<>();
        openFiles = new ArrayList<>();
        this.filesBar = filesBar;
        this.displayContent = displayContent;
        this.labelForFileName = labelForFileName;
        creatorFile = new CreatorFileIDE();
        csvInterpretor = new CSVinterpretor(displayContent);
    }

    public void openProject() throws IOException, ProjectOpenException, DirectoryException, FileOpenException {
        openProject(directoryU.getPathFolder());
    }

    /**
     * Abre un proyecto que el usuario selecionara
     *
     * @param path
     * @throws compi1.sqlemulator.exceptions.ProjectOpenException
     * @throws compi1.sqlemulator.exceptions.DirectoryException
     * @throws java.io.IOException
     * @throws compi1.sqlemulator.exceptions.FileOpenException
     */
    public void openProject(String path)
            throws ProjectOpenException, DirectoryException, IOException, FileOpenException {
        if (!currentProject.isEmpty()) {
            throw new ProjectOpenException();
        }
        if (currentFile != null) {
            throw new FileOpenException();
        } else {
            //abrir el proyecto
            this.currentProject = directoryU.openProject(path);
            //inicializando el arbol
            DefaultMutableTreeNode firstNode = new DefaultMutableTreeNode(currentProject.get(0));
            DefaultTreeModel defaultTreeModel = new DefaultTreeModel(firstNode);
            treeDisplay.setModel(defaultTreeModel);

            DefaultMutableTreeNode currentParentModel = firstNode;
            //agregando archivos
            for (int i = 1; i < currentProject.size(); i++) {
                FileProject fileProject = currentProject.get(i);
                DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(fileProject);

                FileProject parentFileObj = (FileProject) currentParentModel.getUserObject();
                while (fileProject.getIdentation() <= parentFileObj.getIdentation()) {
                    currentParentModel = (DefaultMutableTreeNode) currentParentModel.getParent();
                    parentFileObj = (FileProject) currentParentModel.getUserObject();
                }
                defaultTreeModel.insertNodeInto(currentNode, currentParentModel,
                        currentParentModel.getChildCount());

                if (fileProject.getFile().isDirectory()) {
                    currentParentModel = currentNode;
                }
            }
            treeDisplay.revalidate();
            treeDisplay.repaint();
            filesU.saveFile(creatorFile.generateIDEfile(treeDisplay),
                    currentProject.get(0).getFile().getAbsolutePath()
                    + directoryU.getCarpetSeparator() + "settings.ide");
        }
    }

    /**
     * Abre un archivo al tener seleccionado un elemento del arbol de trabajo
     *
     * @throws java.io.IOException
     * @throws compi1.sqlemulator.exceptions.FileOpenException
     * @throws compi1.sqlemulator.exceptions.FileException
     * @throws compi1.sqlemulator.exceptions.FileExtensionException
     */
    public void openFileFromProject()
            throws IOException, FileOpenException, FileException, FileExtensionException {
        if (currentFile != null) {
            currentFile.setOpenContent(displayContent.getText());
        }
        DefaultMutableTreeNode selectedNode
                = (DefaultMutableTreeNode) treeDisplay.getLastSelectedPathComponent();
        if (selectedNode != null) {
            if (!(selectedNode.getUserObject() instanceof FileProject)) {
                throw new FileException();
            }
            FileProject selectedFileProject = (FileProject) selectedNode.getUserObject();
            File file = selectedFileProject.getFile();
            boolean isAceptedExtension = filesU.hasAceptedPath(aceptedExtensions, file);
            if (file.isFile() // y el archivo no esta abierto aun
                    && BinarySearch.search(openFiles, file.getAbsolutePath()) == -1
                    && isAceptedExtension) {
                openProjectFile(file);
            } else if (file.isFile() && isAceptedExtension) { //cuando ya esta abierto
                throw new FileOpenException();
            } else if (!isAceptedExtension && !file.isDirectory()) {
                throw new FileExtensionException();
            }
        }
    }

    private void openProjectFile(File file) throws IOException {
        String content = filesU.readTextFile(file.getAbsolutePath());
        currentFile = new OpenFile(file, content);
        openFiles.add(currentFile);
        Collections.sort(openFiles);
        displayContent.setText(filesU.readTextFile(file.getAbsolutePath()));
        labelForFileName.setText(file.getName());
        //anadir los botones
        currentFile.init(displayContent, labelForFileName, this);
        filesBar.add(currentFile);
    }

    /**
     * Ciera el proyecto actual
     *
     * @throws compi1.sqlemulator.exceptions.DirectoryException cuando no hay un
     * proyecto abierto
     */
    public void closeProject() throws DirectoryException {
        if (currentProject.isEmpty()) {
            throw new DirectoryException();
        } else {
            currentProject.clear();
            openFiles.clear();
            treeDisplay.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("root")));
            treeDisplay.revalidate();
            treeDisplay.repaint();
            closeOpenFiles();
        }
    }

    private void closeFileInProject() throws FileException {
        if (currentFile != null) {
            int position = BinarySearch.search(openFiles, currentFile.getFile().getAbsolutePath());
            if (position != -1) {
                openFiles.remove(position);
                filesBar.remove(currentFile);
                filesBar.revalidate();
                filesBar.repaint();
                currentFile = null;
                displayContent.setText("");
                labelForFileName.setText(EMPTY_NOTATION);
            } else {
                throw new FileException();
            }
        } else {
            throw new FileException();
        }
    }

    public void closeFile() throws FileException {
        if (!currentProject.isEmpty()) {
            closeFileInProject();
        } else if (currentFile != null) {
            closeOpenFiles();
        }
    }

    public void closeOpenFiles() {
        filesBar.removeAll();
        filesBar.revalidate();
        filesBar.repaint();
        openFiles.clear();
        currentFile = null;
        displayContent.setText("");
        labelForFileName.setText(EMPTY_NOTATION);
    }

    /**
     * Guarda el archivo actual
     *
     * @throws compi1.sqlemulator.exceptions.FileException
     */
    public void saveFile() throws FileException {
        if (currentFile != null) {
            filesU.saveFile(displayContent.getText(), currentFile.getFile());
        } else {
            throw new FileException();
        }
    }

    /**
     * Abre un archivo cuando no hay un proyecto abierto
     *
     * @throws compi1.sqlemulator.exceptions.ProjectOpenException cuando hay un
     * proyecto abierto
     * @throws java.io.IOException por cualquier excepcion extra
     * @throws compi1.sqlemulator.exceptions.FileExtensionException
     */
    public void openFile()
            throws ProjectOpenException, IOException, FileExtensionException {
        if (!currentProject.isEmpty() || currentFile != null) {
            throw new ProjectOpenException();
        } else {
            File file = new File(filesU.getPath("Archivos svc o txt",
                    aceptedExtensions));
            if (!filesU.hasAceptedPath(aceptedExtensions, file)) {
                throw new FileExtensionException();
            }
            String content = filesU.readTextFile(file.getAbsolutePath());
            displayContent.setText(content);
            this.labelForFileName.setText(file.getName());
            currentFile = new OpenFile(file, content);
        }
    }

    public String createProject() throws IOException, InvalidDataException {
        JOptionPane.showMessageDialog(null,
                "A continuacion selecciona el path donde sera guardado el proyecto");
        String rootPath = directoryU.getPathFolder();
        String nameProject = JOptionPane.showInputDialog("Ingresa el nombre del proyecto");
        if (nameProject.matches("([a-z]|[A-Z]| _ )([a-z]|[A-Z]| _ |[0-9]| - | @ | + | [*] | #)*")) {
            directoryU.createDirectory(rootPath, nameProject);
            rootPath += directoryU.getCarpetSeparator() + nameProject;
            JOptionPane.showMessageDialog(null,
                    "A continuacion seleccina los archivos que sean copiados al proyecto");
            String error = directoryU.copyFilesToPath(rootPath,
                    filesU.getFiles("Archivos csv", aceptedExtensions));
            if (!error.isEmpty()) {
                throw new InvalidDataException(error
                        + "\n Advertencia: la carpeta se ha creado, por favor ingresa los archivos manualmente");
            }
            return rootPath;
        } else {
            throw new InvalidDataException("El nombre de la carpeta no es valida");
        }
    }

    private String convertToAbsolutePath(String path) {
        try {
            path = path.replace(".", directoryU.getCarpetSeparator());
            DefaultTreeModel model = (DefaultTreeModel) treeDisplay.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            FileProject rootNode = (FileProject) root.getUserObject();
            String rootPath = rootNode.getFile().getAbsolutePath();
            rootPath = rootPath.substring(0, rootPath.length() - rootNode.getFile().getName().length());
            return rootPath + path + ".csv";
        } catch (Exception e) {
            return path;
        }
    }

    /**
     * Este metodo ayuda a trabajar sobre el archivo real del que se quiere
     * operar
     *
     * @param pathWithDots
     * @throws compi1.sqlemulator.exceptions.DirectoryException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void openFile(String pathWithDots) throws DirectoryException, FileNotFoundException, IOException {
        String path = pathWithDots.replace(".", directoryU.getCarpetSeparator());
        path += ".csv";
        if (!currentProject.isEmpty()) { //si esta abierto un proyecto
            String realPath = convertToAbsolutePath(pathWithDots);
            File file = new File(realPath);
            if (file.exists() && file.isFile()) {
                int index = BinarySearch.search(openFiles, file.getAbsolutePath());
                if (index == -1) {
                    openProjectFile(file);
                } else if (index >= 0) {
                    openFiles.get(index).executeAction(displayContent, labelForFileName, this);
                }
            } else {
                throw new FileNotFoundException();
            }
        } else if (currentProject.isEmpty() && currentFile != null) { //cuando solo es un archivo y esta abierto
            if (!currentFile.getFile().getName().equals(path)) {
                throw new FileNotFoundException();
            }
        } else { //cuando no hay un archivo abierto
            throw new DirectoryException();
        }
    }

    public void appendContent(String content) throws BadLocationException {
        StyledDocument doc = displayContent.getStyledDocument();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        doc.insertString(doc.getLength(), content, attributeSet);
    }

    public void setNewContent(String content) {
        displayContent.setText(content);
    }

    public void setCurrentFile(OpenFile currentFile) {
        this.currentFile = currentFile;
    }

    public OpenFile getCurrentFile() {
        return this.currentFile;
    }

    public CSVinterpretor getCSVinterpretor() {
        return this.csvInterpretor;
    }

    public String getCurrentDisplayTxt() {
        return this.displayContent.getText().replace("\t", "");
    }

    private String getRootFolderProject() throws DirectoryException {
        if (currentProject.isEmpty()) {
            throw new DirectoryException();
        }
        DefaultTreeModel model = (DefaultTreeModel) treeDisplay.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        FileProject rootNode = (FileProject) root.getUserObject();
        String rootPath = rootNode.getFile().getAbsolutePath();
        return rootPath;
    }

    public String saveNewFileInProject() throws InvalidDataException, IOException, DirectoryException {
        JOptionPane.showMessageDialog(null, """
                                            Selecciona la carpeta donde se guardara el archivo, esta tiene que estar dentro del proyecto actual, 
                                            
                                            IMPORTANTE!!!: guarda los archivos abiertos antes""");
        String rootFolder = directoryU.getPathFolder();
        if (rootFolder.contains(this.getRootFolderProject())) {
            String content = JOptionPane.showInputDialog(
                    "Ingresa las columnas que iran en el archivo, separado con comas y sin espacios");
            if (!content.matches(CSV_COLUMNS_FORMAT)) {
                throw new InvalidDataException("El formato de columnas es invalido");
            }
            String path = JOptionPane.showInputDialog(null, "Ingresa un nombre para guardar el archivo",
                    "Guardando un nuevo archivo", JOptionPane.QUESTION_MESSAGE);
            if (!path.matches(COLUMN_FORMAT)) {
                throw new InvalidDataException("El nombre del archivo es invalido");
            }

            filesU.saveAs(content, ".csv", rootFolder, path);
            return this.getRootFolderProject();
        } else {
            throw new InvalidDataException("La carpeta seleccionada, no esta dentro del proyecto");
        }
    }

    public boolean isOpenProject() {
        return !currentProject.isEmpty();
    }

    public String saveNewFile() throws InvalidDataException, IOException {
        JOptionPane.showMessageDialog(null, "Selecciona la carpeta donde se guardara el archivo,"
                + " toma en cuenta que tendraas que abirlo manualmente luego de haber sido guardado");
        String root = directoryU.getPathFolder();
        String content = JOptionPane.showInputDialog(
                "Ingresa las columnas que iran en el archivo, separado con comas y sin espacios");
        if (!content.matches(CSV_COLUMNS_FORMAT)) {
            throw new InvalidDataException("El formato de columnas es invalido");
        }
        String path = JOptionPane.showInputDialog(null, "Ingresa un nombre para guardar el archivo",
                "Guardando un nuevo archivo", JOptionPane.QUESTION_MESSAGE);
        if (!path.matches(COLUMN_FORMAT)) {
            throw new InvalidDataException("El nombre del archivo es invalido");
        }
        filesU.saveAs(content, ".csv", root, path);
        return root + directoryU.getCarpetSeparator() + path + ".csv";
    }

    public String createFolder() throws InvalidDataException, IOException, DirectoryException {
        if (!currentProject.isEmpty()) {
            JOptionPane.showMessageDialog(null, """
                                                Selecciona la carpeta donde se guardara el archivo, esta tiene que estar dentro del proyecto actual, 
                                                
                                                IMPORTANTE: guarda los archivos abiertos antes""");
            String rootFolder = directoryU.getPathFolder();
            if (rootFolder.contains(this.getRootFolderProject())) {
                String path = JOptionPane.showInputDialog(null, "Ingresa un nombre para la carpeta",
                        "Guardando una carpeta", JOptionPane.QUESTION_MESSAGE);
                if (!path.matches(COLUMN_FORMAT)) {
                    throw new InvalidDataException("El nombre del archivo es invalido");
                }
                directoryU.createDirectory(rootFolder, path);
                JOptionPane.showMessageDialog(null, "Carpeta creada exitosamente");
                return this.getRootFolderProject();
            } else {
                throw new InvalidDataException("La carpeta seleccionada, no esta dentro del proyecto");
            }
        } else {
            throw new InvalidDataException("No hay un proyecto abierto, no se puede crear una carpeta");
        }
    }

}
