package compi1.sqlemulator.util;

import compi1.sqlemulator.files.models.ColumnsCSV;
import compi1.sqlemulator.files.models.OpenFile;
import java.util.List;

/**
 *
 * @author yenni
 */
public class BinarySearch {

    public static int search(List<OpenFile> list, String elemento) {
        int inicio = 0;
        int fin = list.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int comparacion = elemento.compareTo(list.get(medio).getFile().getAbsolutePath());

            if (comparacion == 0) {
                return medio; //regresa la posicion del array
            } else if (comparacion < 0) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1;
    }
    
    public static int searchColumns(List<ColumnsCSV> list, String elemento) {
        int inicio = 0;
        int fin = list.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int comparacion = elemento.compareTo(list.get(medio).getNameColumn());

            if (comparacion == 0) {
                return medio; //regresa la posicion del array
            } else if (comparacion < 0) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1;
    }

}
