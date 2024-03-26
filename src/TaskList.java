import java.util.ArrayList;

public class TaskList {
        private final ArrayList<Task> tasks = new ArrayList<>();
        public void addList(Task task){
            tasks.add(task);
        }
        public ArrayList<Task> getList(){
            return tasks;
        }
        public ArrayList<Task> remove(int nb){ return remove(nb); }
        public ArrayList<Task> clear(){ return clear(); }
}
