package filehandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Reads objects from file.
 * 
 * @author Sebastian Björkqvist
 */
public class ObjectReader {

    /**
     * Reads an object from a file.
     * 
     * Returns null if the object can't be read.
     * 
     * @param fileName The file name, including path.
     * @return The read object, or null if the reading failed.
     * @throws IllegalArgumentException if fileName is null or empty
     */
    public Object readObject(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can't be null or empty!");
        }
        Object object = null;

        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
        } catch (ClassNotFoundException ex) {
        }

        return object;
    }    
}
