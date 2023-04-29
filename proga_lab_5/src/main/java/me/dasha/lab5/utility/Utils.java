package me.dasha.lab5.utility;

import me.dasha.lab5.collectionClasses.SpaceMarine;

/**
 * this class to represent the elements of a class SpaceMarine
 */
public class Utils {
    /**
     * check exist value SpaceMarine
     * @param Id
     * @return false or true
     */
    public static boolean checkExist(Integer Id) {
        for (SpaceMarine spaceMarine: CollectionManager.getStack()) {
            return spaceMarine.getId().equals(Id);//TODO переопределить equals в SpaceMarine
        }
        return false;
    }
}
