import java.util.ArrayList;
import java.util.Scanner;

public class Series {
    private ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void runApplication() {
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("LATEST SERIES - 2025");
        System.out.println("***********************************************");
        System.out.println("Enter (1) to launch menu or any other key to exit");

        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series.");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");

            int choice = getIntInput(">> ");
            switch (choice) {
                case 1:
                    CaptureSeries();
                    break;
                case 2:
                    SearchSeries();
                    break;
                case 3:
                    UpdateSeries();
                    break;
                case 4:
                    DeleteSeries();
                    break;
                case 5:
                    SeriesReport();
                    break;
                case 6:
                    exit = true;
                    ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("*********************");

        String seriesId = getStringInput("Enter the series id: ");
        String seriesName = getStringInput("Enter the series name: ");

        int ageRestriction;
        while (true) {
            try {
                ageRestriction = getIntInput("Enter the series age restriction: ");
                if (ageRestriction < 2 || ageRestriction > 18) {
                    System.out.println("You have entered an incorrect series age!!!");
                    System.out.println("Please re-enter the series age >> ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.println("Please re-enter the series age >> ");
            }
        }

        int numberOfEpisodes = getIntInput("Enter the number of episodes for " + seriesName + ": ");

        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, ageRestriction, numberOfEpisodes);
        seriesList.add(newSeries);
        System.out.println("Series processed successfully!!!");
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }

    public SeriesModel SearchSeries(String seriesId) {
        for (SeriesModel series : seriesList) {
            if (series.getSeriesId().equals(seriesId)) {
                return series;
            }
        }
        return null;
    }

    public void SearchSeries() {
        System.out.print("\nEnter the series id to search: ");
        String seriesId = scanner.nextLine();

        SeriesModel foundSeries = SearchSeries(seriesId);
        if (foundSeries != null) {
            System.out.println("---");
            System.out.println(foundSeries.toString());
            System.out.println("---");
        } else {
            System.out.println("---");
            System.out.println("Series with Series Id: " + seriesId + " was not found!");
            System.out.println("---");
        }
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }

    public boolean UpdateSeries(String seriesId, String newName, int newAgeRestriction, int newEpisodes) {
        SeriesModel seriesToUpdate = SearchSeries(seriesId);
        if (seriesToUpdate != null) {
            seriesToUpdate.setSeriesName(newName);
            seriesToUpdate.setAgeRestriction(newAgeRestriction);
            seriesToUpdate.setNumberOfEpisodes(newEpisodes);
            return true;
        }
        return false;
    }

    public void UpdateSeries() {
        System.out.print("\nEnter the series id to update: ");
        String seriesId = scanner.nextLine();

        SeriesModel foundSeries = SearchSeries(seriesId);
        if (foundSeries == null) {
            System.out.println("Series not found!");
            return;
        }

        String newName = getStringInput("Enter the series name: ");

        int newAgeRestriction;
        while (true) {
            try {
                newAgeRestriction = getIntInput("Enter the age restriction: ");
                if (newAgeRestriction < 2 || newAgeRestriction > 18) {
                    System.out.println("You have entered an incorrect series age!!!");
                    System.out.println("Please re-enter the series age >> ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.println("Please re-enter the series age >> ");
            }
        }

        int newEpisodes = getIntInput("Enter the number of episodes: ");

        boolean updated = UpdateSeries(seriesId, newName, newAgeRestriction, newEpisodes);
        if (updated) {
            System.out.println("Series updated successfully!");
        } else {
            System.out.println("Failed to update series.");
        }
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }

    public boolean DeleteSeries(String seriesId) {
        SeriesModel seriesToDelete = SearchSeries(seriesId);
        if (seriesToDelete != null) {
            seriesList.remove(seriesToDelete);
            return true;
        }
        return false;
    }

    public void DeleteSeries() {
        System.out.print("\nEnter the series id to delete: ");
        String seriesId = scanner.nextLine();

        System.out.print("Are you sure you want to delete series " + seriesId + " from the system? Yes (y) to delete: ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            boolean deleted = DeleteSeries(seriesId);
            if (deleted) {
                System.out.println("---");
                System.out.println("Series with Series Id: " + seriesId + " WAS deleted!");
                System.out.println("---");
            } else {
                System.out.println("Series not found!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }

    public void SeriesReport() {
        System.out.println("\nSERIES REPORT - 2025");
        System.out.println("====================");

        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }

        for (int i = 0; i < seriesList.size(); i++) {
            System.out.println("\nSeries " + (i + 1));
            System.out.println("---");
            System.out.println(seriesList.get(i).toString());
            System.out.println("---");
        }
        System.out.println("\nEnter (1) to launch menu or any other key to exit");
    }

    public void ExitSeriesApplication() {
        System.out.println("Exiting application...");
        scanner.close();
        System.exit(0);
    }

    // Helper methods
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}