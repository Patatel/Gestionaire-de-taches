import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main() throws IOException {
        TaskList tasks = new TaskList();
        int choice;

        do {
            System.out.println("Menu :");
            System.out.println("1 - Ajouter une tâche");
            System.out.println("2 - Supprimer une tâche");
            System.out.println("3 - Afficher la liste des tâches finis");
            System.out.println("4 - Enregistrer la liste des tâches dans un fichier");
            System.out.println("5 - Charger la liste des tâches depuis un fichier");
            System.out.println("6 - Afficher la liste des tâches comprise entre deux dates");
            System.out.println("7 - Quitter");

            // Création d'un objet Scanner pour lire l'entrée utilisateur
            Scanner mainScan = new Scanner(System.in);
            // Demander à l'utilisateur de choisir son action
            System.out.print("Donner le numéro de l'action a effectuer :");
            choice = Integer.parseInt(mainScan.nextLine());
            mainScan.close();

            //Ajouter tache
            if (choice == 1){
                Action.add(tasks);
                //Supprimer tache
            }else if (choice == 2) {
                int rtn = 0;
                rtn = Action.delete(tasks);
                if (rtn == 0){
                    System.out.println("Aucune tâche trouvée avec ce numéro.");
                }

                //Afficher taches fini
            }else if (choice == 3) {
                Action.finishTasks(tasks);

                //Enregistrer dans un fichier
            }else if (choice == 4) {
                Action.saveInFile(tasks);

                //Charger depuis un fichier
            }else if (choice == 5) {
                Action.importTask(tasks);

                //Afficher entre dates
            }else if (choice == 6) {
                Action.dateBetween(tasks);

                //Quitter
            }else if (choice == 7) {
                System.exit(0);
            }else{
                System.out.println("Le numéro d'action n'existe pas");
            }
            mainScan.close();
        }while (choice != 7);
    }
}