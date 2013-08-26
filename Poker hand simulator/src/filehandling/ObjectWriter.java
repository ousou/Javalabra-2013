package filehandling;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Writes objects to file.
 * 
 * @author Sebastian Björkqvist
 */
public class ObjectWriter {

    /**
     * Writes the given object to the file name given.
     * 
     * @param object Object to write
     * @param fileName File name, including path.
     * @return true if writing succeeded, false if it didn't.
     * @throws IllegalArgumentException if fileName is null or empty
     * @throws IllegalArgumentException if Object is null
     */
    public boolean writeObject(Object object, String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can't be null or empty!");
        }
        if (object == null) {
            throw new IllegalArgumentException("Object can't be null!");            
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }    
    
}
