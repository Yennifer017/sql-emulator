package compi1.sqlemulator.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author yenni
 */
public class FilesUtil {

    public String readTextFile(String path) { //obtiene el texto contenido en un archivo y lo devuelve
        String texto = "";
        try {
            File archivo = new File(path); //creando el archivo
            FileReader lector = new FileReader(archivo); //lector del archivo
            BufferedReader buffer = new BufferedReader(lector); //para leer mas rapido el archivo
            String linea;
            while ((linea = buffer.readLine()) != null) {
                texto += linea + "\n";
            }
            buffer.close();
            lector.close();
        } catch (IOException | NullPointerException error) {
            System.out.println("No se ha podido abrir un archivo, excepcion controlada");
        }
        return texto;
    }

    public String getPath() throws IOException {
        try {
            JFileChooser buscador = new JFileChooser(); //creando el buscador de archivos
            buscador.showOpenDialog(null); //abrir el buscador
            return buscador.getSelectedFile().getAbsolutePath();
        } catch (NullPointerException e) {
            throw new IOException();
        }
    }

    public String getPath(String description, String[] aceptedExtensionFiles) {
        try {
            JFileChooser buscador = new JFileChooser(); //creando el buscador de archivos
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    description, aceptedExtensionFiles);
            buscador.setFileFilter(filter);
            buscador.showOpenDialog(null); //abrir el buscador
            return buscador.getSelectedFile().getAbsolutePath();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void saveFile(String texto, String ruta) { //reescribe un archivo a partir de un texto
        try {
            File archivo = new File(ruta); //obtiene el archivo de la ruta
            FileWriter escritor = new FileWriter(archivo, false);
            BufferedWriter buffer = new BufferedWriter(escritor);
            buffer.write(texto);
            buffer.close();
            escritor.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public void deleteFile(String path) {
        File archivo = new File(path);
        archivo.delete();
    }

    public void saveAs(String text, String extension) {
        String path = JOptionPane.showInputDialog(null, "Ingresa un nombre para guardar el archivo",
                "Guardando un nuevo archivo", JOptionPane.QUESTION_MESSAGE);
        if (path != null && !path.equals("")) {
            File file = new File(path + extension);
            if (!file.exists()) {
                this.saveFile(text, path + extension);
                JOptionPane.showMessageDialog(null, "Se ha guardado el archivo", ""
                        + "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int opcion = JOptionPane.showConfirmDialog(null, """
                        Se ha encontrado un archivo con el mismo nombre especificado.
                        ¿Deseas sobreescribirlo?""", "sobreescribiendo archivo...",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION);
                if (opcion == 0) {
                    this.saveFile(text, path + extension);
                    JOptionPane.showMessageDialog(null, "Se ha guardado el archivo", ""
                            + "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (path != null && path.equals("")) {
            JOptionPane.showMessageDialog(null, """
                                            No has ingresado un nombre de archivo valido,
                                            no se ha podido guardar, intentalo de nuevo.""",
                    "Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveFromExistentPath(String text, String path, String fileName) {
        int option = JOptionPane.showConfirmDialog(null, "¿Guardar el archivo "
                + fileName + "?", "Guardando archivo..",
                JOptionPane.CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION);
        if (option == 0) { //cuando confirma
            this.saveFile(text, path);
            JOptionPane.showMessageDialog(null, "Se ha guardado el archivo", ""
                    + "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
