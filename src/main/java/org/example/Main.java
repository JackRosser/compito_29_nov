package org.example;

import org.example.archive.Archive;
import org.example.exceptions.BadChoiceException;
import org.example.products.book.Genre;
import org.example.products.magazine.Periodicity;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false; // Variabile per controllare l'uscita dal programma

        while (!exitProgram) {
            String firstChoice = "";
            String secondChoice = "";
            boolean validChoice = false;
            boolean secondValidChoice = false;

            // STEP 1: MENU PRINCIPALE
            while (!validChoice) {
                try {
                    System.out.println("Benvenuto, " +
                            "\nDigita A per i libri " +
                            "\nDigita B per le riviste " +
                            "\nDigita C per una stampa totale" +
                            "\nDigita X per uscire");
                    firstChoice = scanner.nextLine().toLowerCase();

                    if (!firstChoice.equals("a") && !firstChoice.equals("b") && !firstChoice.equals("c") && !firstChoice.equals("x")) {
                        throw new BadChoiceException("Effettua una scelta corretta");
                    }
                    validChoice = true;
                } catch (BadChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Controllo per uscire dal programma
            if (firstChoice.equals("x")) {
                exitProgram = true;
                System.out.println("Grazie per aver usato il nostro programma. Arrivederci!");
                continue;
            }

            Archive archive = Archive.getInstance(); // Inizializza l'archivio

            // GESTIONE LIBRI
            if (firstChoice.equals("a")) {
                while (!secondValidChoice) {
                    try {
                        System.out.println("Digita A per aggiungere un libro" +
                                "\nDigita B per ricercare un libro tramite ISBN" +
                                "\nDigita C per rimuovere un libro" +
                                "\nDigita D per ricercare un libro tramite anno di pubblicazione" +
                                "\nDigita E per ricercare un libro tramite autore" +
                                "\nDigita F per aggiornare un libro esistente");
                        secondChoice = scanner.nextLine().toLowerCase();

                        if (!secondChoice.equals("a") && !secondChoice.equals("b") && !secondChoice.equals("c") &&
                                !secondChoice.equals("d") && !secondChoice.equals("e") && !secondChoice.equals("f")) {
                            throw new BadChoiceException("Effettua una scelta corretta");
                        }
                        secondValidChoice = true;
                    } catch (BadChoiceException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (secondChoice.equals("a")) {
                    System.out.println("Definisci ISBN");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Definisci titolo");
                    String title = scanner.nextLine();
                    System.out.println("Definisci anno di pubblicazione");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Definisci numero pagine");
                    int pages = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Definisci autore");
                    String author = scanner.nextLine();
                    Genre genre = null;
                    boolean validGenre = false;

                    while (!validGenre) {
                        try {
                            System.out.println("Scegli genere tra" +
                                    "\n" + Genre.ADVENTURE +
                                    "\n" + Genre.CLASSIC +
                                    "\n" + Genre.FICTION +
                                    "\n" + Genre.FANTASY +
                                    "\n" + Genre.DYSTOPIAN +
                                    "\n" + Genre.HISTORICAL +
                                    "\n" + Genre.PSYCOLOGICAL +
                                    "\n" + Genre.ROMANCE);
                            String input = scanner.nextLine().toUpperCase();
                            genre = Genre.valueOf(input);
                            validGenre = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Scegli un genere valido tra quelli elencati.");
                        }
                    }

                    archive.addBook(isbn, title, year, pages, author, genre);
                } else if (secondChoice.equals("b")) {
                    System.out.println("Inserisci codice ISBN:");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    archive.searchBookIsbn(isbn);
                } else if (secondChoice.equals("c")) {
                    System.out.println("Inserisci codice ISBN:");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    archive.removeBookIsbn(isbn);
                } else if (secondChoice.equals("d")) {
                    System.out.println("Inserisci anno di pubblicazione:");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    archive.searchBookForYear(year);
                } else if (secondChoice.equals("e")) {
                    System.out.println("Inserisci autore:");
                    String author = scanner.nextLine();
                    archive.searchBookForAuthor(author);
                } else if (secondChoice.equals("f")) {
                    // Codice per aggiornare un libro
                    System.out.println("Inserisci ISBN del libro da aggiornare:");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Inserisci il nuovo titolo (lascia vuoto per mantenere invariato):");
                    String newTitle = scanner.nextLine();

                    System.out.println("Inserisci il nuovo anno di pubblicazione (digita 0 per mantenere invariato):");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Inserisci il nuovo numero di pagine (digita 0 per mantenere invariato):");
                    int newPages = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Inserisci il nuovo autore (lascia vuoto per mantenere invariato):");
                    String newAuthor = scanner.nextLine();

                    Genre newGenre = null;
                    boolean validGenre = false;

                    while (!validGenre) {
                        try {
                            System.out.println("Scegli il nuovo genere (lascia vuoto per mantenere invariato):" +
                                    "\n" + Genre.ADVENTURE +
                                    "\n" + Genre.CLASSIC +
                                    "\n" + Genre.FICTION +
                                    "\n" + Genre.FANTASY +
                                    "\n" + Genre.DYSTOPIAN +
                                    "\n" + Genre.HISTORICAL +
                                    "\n" + Genre.PSYCOLOGICAL +
                                    "\n" + Genre.ROMANCE);
                            String input = scanner.nextLine().toUpperCase();

                            if (input.isEmpty()) {
                                validGenre = true;
                            } else {
                                newGenre = Genre.valueOf(input);
                                validGenre = true;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Scegli un genere valido tra quelli dell'elenco.");
                        }
                    }

                    boolean updated = archive.updateBook(
                            isbn,
                            newTitle.isEmpty() ? null : newTitle,
                            newYear == 0 ? -1 : newYear,
                            newPages == 0 ? -1 : newPages,
                            newAuthor.isEmpty() ? null : newAuthor,
                            newGenre
                    );

                    if (updated) {
                        System.out.println("Libro aggiornato con successo.");
                    } else {
                        System.out.println("Errore: libro con ISBN " + isbn + " non trovato.");
                    }
                }
            }

            // GESTIONE RIVISTE
            if (firstChoice.equals("b")) {
                while (!secondValidChoice) {
                    try {
                        System.out.println("Digita A per aggiungere una rivista" +
                                "\nDigita B per ricercare una rivista tramite ISBN" +
                                "\nDigita C per rimuovere una rivista" +
                                "\nDigita D per ricercare una rivista per periodicità" +
                                "\nDigita E per ricercare una rivista per anno di pubblicazione" +
                                "\nDigita F per aggiornare una rivista esistente");
                        secondChoice = scanner.nextLine().toLowerCase();

                        if (!secondChoice.equals("a") && !secondChoice.equals("b") && !secondChoice.equals("c") &&
                                !secondChoice.equals("d") && !secondChoice.equals("e") && !secondChoice.equals("f")) {
                            throw new BadChoiceException("Effettua una scelta corretta");
                        }
                        secondValidChoice = true;
                    } catch (BadChoiceException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (secondChoice.equals("a")) {
                    System.out.println("Definisci ISBN");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Definisci titolo");
                    String title = scanner.nextLine();
                    System.out.println("Definisci anno di pubblicazione");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Definisci numero pagine");
                    int pages = scanner.nextInt();
                    scanner.nextLine();
                    Periodicity periodicity = null;
                    boolean validPeriodicity = false;

                    while (!validPeriodicity) {
                        try {
                            System.out.println("Scegli periodicità tra:" +
                                    "\n" + Periodicity.WEEKLY +
                                    "\n" + Periodicity.MONTHLY +
                                    "\n" + Periodicity.SEMIANNUALY);
                            String input = scanner.nextLine().toUpperCase();
                            periodicity = Periodicity.valueOf(input);
                            validPeriodicity = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Scegli una periodicità valida tra quelle elencate.");
                        }
                    }

                    archive.addMagazine(isbn, title, year, pages, periodicity);
                } else if (secondChoice.equals("b")) {
                    System.out.println("Inserisci codice ISBN della rivista:");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    archive.searchMagazineIsbn(isbn);
                } else if (secondChoice.equals("c")) {
                    System.out.println("Inserisci codice ISBN della rivista da rimuovere:");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    archive.removeMagazineIsbn(isbn);
                } else if (secondChoice.equals("d")) {
                    Periodicity periodicity = null;
                    boolean validPeriodicity = false;

                    while (!validPeriodicity) {
                        try {
                            System.out.println("Scegli periodicità per la ricerca:" +
                                    "\n" + Periodicity.WEEKLY +
                                    "\n" + Periodicity.MONTHLY +
                                    "\n" + Periodicity.SEMIANNUALY);
                            String input = scanner.nextLine().toUpperCase();
                            periodicity = Periodicity.valueOf(input);
                            validPeriodicity = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Scegli una periodicità valida tra quelle elencate.");
                        }
                    }

                    archive.searchMagazineForPeriodicity(periodicity);
                } else if (secondChoice.equals("e")) {
                    System.out.println("Inserisci anno di pubblicazione:");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    archive.searchMagazineForYear(year);
                } else if (secondChoice.equals("f")) {
                    System.out.println("Inserisci ISBN della rivista da aggiornare:");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Inserisci il nuovo titolo (lascia vuoto per mantenere invariato):");
                    String newTitle = scanner.nextLine();

                    System.out.println("Inserisci il nuovo anno di pubblicazione (digita 0 per mantenere invariato):");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Inserisci il nuovo numero di pagine (digita 0 per mantenere invariato):");
                    int newPages = scanner.nextInt();
                    scanner.nextLine();

                    Periodicity newPeriodicity = null;
                    boolean validPeriodicity = false;

                    while (!validPeriodicity) {
                        try {
                            System.out.println("Scegli la nuova periodicità (lascia vuoto per mantenere invariata):" +
                                    "\n" + Periodicity.WEEKLY +
                                    "\n" + Periodicity.MONTHLY +
                                    "\n" + Periodicity.SEMIANNUALY);
                            String input = scanner.nextLine().toUpperCase();

                            if (input.isEmpty()) {
                                validPeriodicity = true;
                            } else {
                                newPeriodicity = Periodicity.valueOf(input);
                                validPeriodicity = true;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Scegli una periodicità valida tra quelle elencate.");
                        }
                    }

                    boolean updated = archive.updateMagazine(
                            isbn,
                            newTitle.isEmpty() ? null : newTitle,
                            newYear == 0 ? -1 : newYear,
                            newPages == 0 ? -1 : newPages,
                            newPeriodicity
                    );

                    if (updated) {
                        System.out.println("Rivista aggiornata con successo.");
                    } else {
                        System.out.println("Errore: rivista con ISBN " + isbn + " non trovata.");
                    }
                }
            }

            // STAMPA TOTALE
            if (firstChoice.equals("c")) {
                archive.printAll();
            }

            // Chiedi se tornare al menu principale
            System.out.println("\nVuoi tornare al menu principale? (digita 's' per sì o 'n' per uscire)");
            String continueChoice = scanner.nextLine().toLowerCase();
            if (continueChoice.equals("n")) {
                exitProgram = true;
                System.out.println("Mi aspetto un 10 per tutto questo, arrivederci.");
            }
        }

        scanner.close();
    }
}
