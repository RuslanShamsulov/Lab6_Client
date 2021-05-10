package FormingObjects;

import domain.Vehicle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class UpdateId {

    public void execute(LinkedList<Vehicle> LinkedList, String textFromScanner, Scanner scanner) {
        Iterator<Vehicle> iterator = LinkedList.iterator();
        FinderNumbers finder = new FinderNumbers();
        int id = finder.find(textFromScanner);

        while (iterator.hasNext()) {
            Vehicle currentVehicle = iterator.next();
            if (currentVehicle.getId() == id) {
                //         nesessaryID = id;
                //         LinkedList.remove(currentVehicle);
                //         break;
                //     }
                // }
                // Add add = new Add();
                // add.execute(LinkedList, scanner);
            }}}}


