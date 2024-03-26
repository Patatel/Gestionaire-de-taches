import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Action {
    public static void add(TaskList tasks){
        int number;
        String description;
        int statu;
        String date;

        // Demander à l'utilisateur d'entrer les infos de la tache
        Scanner addScan = new Scanner(System.in);
        System.out.print("Donner le numéro de la nouvelle tache :");
        number = Integer.parseInt(addScan.nextLine());
        System.out.print("Donner la description de la nouvelle tache :");
        description = addScan.nextLine();
        System.out.print("Donner le statu de la nouvelle tache (0=enCours / 1=fini) :");
        statu = Integer.parseInt(addScan.nextLine());
        System.out.print("Donner la date limite de la nouvelle tache (Format=jj/MM/aaaa) :");
        date = addScan.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date limite = null;
        try {
            limite = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        Task task = new Task(number,description,statu,limite);
        tasks.addList(task);

        System.out.print("Vous avez ajouter la tâche "+task.getNumber()+" - "+task.getDescrption()+" au statu "+ task.getStatu());

        addScan.close();
    }

    public static int delete(TaskList tasks) {
        int suppTask;
        int rtn = 0;

        //Affiche la liste des tasks
        System.out.println("Liste des tâches");
        for (Task task : tasks.getList()){
            task.display();
        }
        // Demander à l'utilisateur de choisir le numéro de la task a supprimer
        Scanner suppScan = new Scanner(System.in);
        System.out.print("Donner le numéro de la tâche a supprimer :");
        suppTask = Integer.parseInt(suppScan.nextLine());

        int i = 0;
        for (Task task : tasks.getList()){
            i = i+1;
            if (task.getNumber() == suppTask) {
                tasks.remove(i);
                System.out.println("Tâche supprimée avec succès !");
                rtn = 1;
            }
        }
        return rtn;
    }

    public static void finishTasks(TaskList tasks){
        //Affiche la liste des tasks
        System.out.println("Liste des tâches terminé");
        for (Task task : tasks.getList()){
            if (task.getStatu() == 1){
                task.display();
            }
        }
    }

    public static void saveInFile(TaskList tasks) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"));
            for (Task task : tasks.getList()) {
                writer.println(task.getNumber() + "," + task.getDescrption() + "," + task.getStatu() + "," + task.getLimite());
            }
            System.out.println("Liste des tâches enregistrée dans le fichier 'tasks.txt'.");
    }

    public static void importTask(TaskList tasks){
        tasks.clear();
        File file = new File("taches.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String ligne;
                // Lire chaque ligne du fichier
                while ((ligne = br.readLine()) != null) {
                    // Séparer les informations de la tâche sur la base des espaces
                    String[] infosTache = ligne.split(" ");
                    //gérer les info de la tache
                    int num = Integer.parseInt(infosTache[0]);
                    String desc = infosTache[1];
                    int statu = Integer.parseInt(infosTache[2]);
                    String date = infosTache[3];
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date limite = null;
                    try {
                        limite = formatter.parse(date);
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    // Créer un objet Tache avec les informations de la ligne
                    Task task = new Task(num,desc,statu,limite);
                    // Ajouter la tâche à la liste
                    tasks.addList(task);
                }
            } catch (IOException e) {
                System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            }

            // Affichage des tâches enregistrées
            System.out.println("Tâches enregistrées :");
            for (Task task : tasks.getList()) {
                System.out.println("Numéro: " + task.getNumber() + ", Description: " + task.getDescrption());
            }
        } else {
            System.out.println("Le fichier 'taches.txt' n'existe pas.");
        }
    }

    public static void dateBetween(TaskList tasks){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Demander à l'utilisateur de saisir deux dates
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la première date (format dd/MM/yyyy) : ");
        String dateun = scanner.nextLine();
        Date dateDebut = null;
        try {
            dateDebut = formatter.parse(dateun);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Entrez la deuxième date (format dd/MM/yyyy) : ");
        String datedeux = scanner.nextLine();
        Date dateFin = null;
        try {
            dateFin = formatter.parse(datedeux);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        //Affiche la liste des tasks
        System.out.println("Liste des tâches limité entre les dates");
        for (Task task : tasks.getList()){
            Date limite = task.getLimite();
            if (limite.compareTo(dateDebut) > 0 | limite.compareTo(dateFin) < 0 ){
                task.display();
            }
        }

        scanner.close();
    }
}
