package HarryBotter.storage;

import HarryBotter.task.Deadline;
import HarryBotter.task.Event;
import HarryBotter.task.Task;
import HarryBotter.task.ToDo;
import java.io.*;
import java.util.ArrayList;

/**
 * Handles saving and loading tasks from a file on the hard disk.
 * The file stores tasks in a text format that can be reconstructed
 * into Task objects when the program starts.
 */

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        f.getParentFile().mkdirs(); // create folders if missing
    }
    /**
     * Loads tasks from the storage file.
     * If the file does not exist, an empty Task array is returned.
     *
     * @return an array of Task objects loaded from the file
     */
    public Task[] load() {
        File f = new File(filePath);

        // If file doesn't exist, return empty Task array
        if (!f.exists()) {
            return new Task[100]; // same as TaskManager
        }

        ArrayList<Task> temp = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Trim all parts
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                // Skip lines that don't even have type, status, and description
                if (parts.length < 3) continue;

                String type = parts[0];
                boolean done = parts[1].equals("1");
                String desc = parts[2];

                Task t;

                switch (type) {
                case "T":
                    t = new ToDo(desc);
                    break;
                case "D":
                    if (parts.length < 4) continue; // skip malformed deadline
                    t = new Deadline(desc, parts[3]);
                    break;
                case "E":
                    if (parts.length < 5) continue; // skip malformed event
                    t = new Event(desc, parts[3], parts[4]);
                    break;
                default:
                    continue; // skip unknown task type
                }

                t.updateStatus(done);
                temp.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            // Optionally: return empty array if file can't be read
            return new Task[100];
        }

        // Convert ArrayList to array of fixed size 100
        Task[] tasks = new Task[100];
        for (int i = 0; i < temp.size(); i++) {
            tasks[i] = temp.get(i);
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     * Each task is written in a structured text format that can
     * be reconstructed when loading.
     *
     * @param tasks the list of tasks to be saved
     * @throws IOException if an error occurs while writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Task t : tasks) {
            String line = "";
            if (t instanceof ToDo) line += "T";
            else if (t instanceof Deadline) line += "D";
            else if (t instanceof Event) line += "E";

            line += " | " + (t.isDone() ? "1" : "0") + " | " + t.getDescription();

            if (t instanceof Deadline) line += " | " + ((Deadline) t).getDueDate();
            if (t instanceof Event) {
                line += " | " + ((Event) t).getStartDateTime();
                line += " | " + ((Event) t).getEndDateTime();
            }
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}
