package org.example;

import org.example.archive.Archive;
import org.example.exceptions.BadChoiceException;
import org.example.products.book.Genre;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstChoice = "";
        String secondChoice = "";
        String thirdChoice = "";
        boolean validChoice = false;
        boolean secondValidChoice = false;
        boolean thirdValidChoice = false;

        // STEP 1 SELEZIONA INTERESSAMENTO

        while (!validChoice) {
            try {
                System.out.println("Benvenuto, " +
                        "\nDigita A per i libri " +
                        "\nDigita B per le riviste " +
                        "\nDigita C per una stampa totale");
                firstChoice = scanner.nextLine().toLowerCase();

                if (!firstChoice.equals("a") && !firstChoice.equals("b") && !firstChoice.equals("c")) {
                    throw new BadChoiceException("Effettua una scelta corretta");
                }
                validChoice = true;
            } catch (BadChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        // STEP 2 LIBRO SELEZIONATO

        if (firstChoice.equals("a")) {


            while(!secondValidChoice) {

                try {
                    System.out.println("Digita A per aggiungere un elemento" +
                            "\nDigita B per ricercare un libro tramite ISBN" +
                            "\nDigita C per rimuovere un libro" +
                            "\nDigita D per ricercare un libro tramite anno di pubblicazione" +
                            "\nDigita E per ricercare un libro tramite autore" +
                            "\nDigita F per aggiornare un libro esistente");
                secondChoice = scanner.nextLine().toLowerCase();
                if(!secondChoice.equals("a") && !secondChoice.equals("b") && !secondChoice.equals("d") && !secondChoice.equals("e") && !secondChoice.equals("f"))
                {throw new BadChoiceException("Effettua una scelta corretta");}
                secondValidChoice = true;
                } catch (BadChoiceException e) {
                    System.out.println(e.getMessage());
                }
                }

        }

        // STEP 3 GESTIONE SCELTA

        if(secondChoice.equals("a")) {
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
            boolean validGenre = false;
            Genre genre = null;
            while(!validGenre) {
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
                    if (!genre.equals(Genre.ADVENTURE) && !genre.equals(Genre.CLASSIC) && !genre.equals(Genre.FICTION) && !genre.equals(Genre.FANTASY) && !genre.equals(Genre.DYSTOPIAN) && !genre.equals(Genre.HISTORICAL) && !genre.equals(Genre.PSYCOLOGICAL) && !genre.equals(Genre.ROMANCE)) {
                        throw new BadChoiceException("Scegli un genere tra quelli dell'elenco");
                    }
                    validGenre = true;
                } catch (BadChoiceException e){
                    System.out.println(e.getMessage());
                }
            }
Archive archive = new Archive();
            archive.addBook(isbn, title, year, pages, author, genre);
        } else if (secondChoice.equals("b")) {
            System.out.println("Inserisci codice ISBN");
            int isbn = scanner.nextInt();
            Archive archive = new Archive();
            archive.searchBookIsbn(isbn);
        } else if (secondChoice.equals("c")) {
            System.out.println("Inserisci codice ISBN");
            int isbn = scanner.nextInt();
            Archive archive = new Archive();
            archive.removeBookIsbn(isbn);
        } else if (secondChoice.equals("d")) {
            System.out.println("Inserisci anno di pubblicazione");
            int year = scanner.nextInt();
            Archive archive = new Archive();
            archive.searchBookForYear(year);
        } else if (secondChoice.equals("e")) {
            System.out.println("Inserisci autore");
            String author = scanner.nextLine();
            Archive archive = new Archive();
            archive.searchBookForAuthor(author);
        } else if (secondChoice.equals("f")) {
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

            Archive archive = new Archive();
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


        //_____________________________________________________________

        else if (firstChoice.equals("b")) {
            System.out.println("Digita A per aggiungere un elemento" +
                    "\nDigita B per ricercare una rivista tramite ISBN" +
                    "\nDigita C per rimuovere una rivista" +
                    "\nDigita D per ricercare una rivista per periodicità" +
                    "\nDigita E per ricercare una rivista per anno di pubblicazione" +
                    "\nDigita F per aggiornare una rivista esistente");
        } else if (firstChoice.equals("c")) {
            System.out.println("Ecco la stampa totale di libri e riviste.");
        }

        scanner.close();
    }
}
