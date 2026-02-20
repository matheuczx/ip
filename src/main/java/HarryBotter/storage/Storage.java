package HarryBotter.storage;

import HarryBotter.task.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        f.getParentFile().mkdirs(); // create folders if missing
    }

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
