package prac;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UrlDatabase {
    private static Map<String, UrlEntry> database = new HashMap<>();
    private static int uniqueKeyCounter = 1;

    public static void main(String[] args) {
        System.out.println("Java URL Database - Type 'exit' to exit.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                break;
            } else if (command.startsWith("storeurl")) {
                String url = command.substring("storeurl".length()).trim();
                storeUrl(url);
            } else if (command.startsWith("get")) {
                String url = command.substring("get".length()).trim();
                getUrl(url);
            } else if (command.startsWith("count")) {
                String url = command.substring("count".length()).trim();
                getCount(url);
            } else if (command.equalsIgnoreCase("list")) {
                listUrls();
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }

        scanner.close();
    }

    private static void storeUrl(String url) {
        String uniqueKey = generateUniqueKey();
        database.put(uniqueKey, new UrlEntry(url, 0));
        System.out.println("URL stored with key: " + uniqueKey);
    }

    private static void getUrl(String url) {
        for (Map.Entry<String, UrlEntry> entry : database.entrySet()) {
            UrlEntry urlEntry = entry.getValue();
            if (urlEntry.getUrl().equals(url)) {
                urlEntry.incrementCount();
                System.out.println("Key for URL " + url + ": " + entry.getKey());
                return;
            }
        }
        System.out.println("URL not found.");
    }

    private static void getCount(String url) {
        for (UrlEntry urlEntry : database.values()) {
            if (urlEntry.getUrl().equals(url)) {
                System.out.println("Count for URL " + url + ": " + urlEntry.getCount());
                return;
            }
        }
        System.out.println("URL not found.");
    }

    private static void listUrls() {
        System.out.println("URLs and Counts (JSON format):");
        System.out.print("{ ");
        boolean first = true;
        for (Map.Entry<String, UrlEntry> entry : database.entrySet()) {
            if (!first) {
                System.out.print(", ");
            }
            UrlEntry urlEntry = entry.getValue();
            System.out.print("\"" + entry.getKey() + "\": {\"url\":\"" + urlEntry.getUrl() + "\",\"count\":" + urlEntry.getCount() + "}");
            first = false;
        }
        System.out.println(" }");
    }

    private static String generateUniqueKey() {
        return "Key" + uniqueKeyCounter++;
    }

    private static class UrlEntry {
        private final String url;
        private int count;

        public UrlEntry(String url, int count) {
            this.url = url;
            this.count = count;
        }

        public String getUrl() {
            return url;
        }

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }
    }
}
