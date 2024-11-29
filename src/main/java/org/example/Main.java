package org.example;

import org.example.archive.Archive;
import org.example.exceptions.BadChoiceException;
import org.example.products.book.Genre;
import org.example.products.magazine.Periodicity;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            String firstChoice = "";
            String secondChoice = "";
            boolean validChoice = false;
            boolean secondValidChoice = false;

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

            if (firstChoice.equals("x")) {
                exitProgram = true;
                System.out.println("Non dimenticare di mettermi 10, saluti!");
                continue;
            }

            Archive archive = Archive.getInstance();

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
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Definisci ISBN");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchBookIsbn(isbn);
                            System.out.println("Errore: ISBN già esistente, inseriscine un altro.");
                        } catch (Exception e) {
                            if (e.getMessage().contains("not found")) {
                                validIsbn = true;
                            } else {
                                System.out.println("Inserisci un valore numerico valido.");
                                scanner.nextLine();
                            }
                        }
                    }

                    System.out.println("Definisci titolo");
                    String title = scanner.nextLine();

                    System.out.println("Definisci anno di pubblicazione");
                    int year = 0;
                    boolean validYear = false;
                    while (!validYear) {
                        try {
                            year = scanner.nextInt();
                            scanner.nextLine();
                            validYear = true;
                        } catch (Exception e) {
                            System.out.println("Inserisci un anno valido.");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Definisci numero pagine");
                    int pages = 0;
                    boolean validPages = false;
                    while (!validPages) {
                        try {
                            pages = scanner.nextInt();
                            scanner.nextLine();
                            validPages = true;
                        } catch (Exception e) {
                            System.out.println("Inserisci un numero di pagine valido.");
                            scanner.nextLine();
                        }
                    }

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

                    try {
                        archive.addBook(isbn, title, year, pages, author, genre);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (secondChoice.equals("b")) {
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Inserisci codice ISBN:");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchBookIsbn(isbn);
                            validIsbn = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro ISBN? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validIsbn = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("c")) {
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Inserisci codice ISBN:");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.removeBookIsbn(isbn);
                            validIsbn = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro ISBN? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validIsbn = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("d")) {
                    System.out.println("Inserisci anno di pubblicazione:");
                    int year = 0;
                    boolean validYear = false;
                    while (!validYear) {
                        try {
                            year = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchBookForYear(year);
                            validYear = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro anno? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validYear = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("e")) {
                    boolean validAuthor = false;
                    while (!validAuthor) {
                        System.out.println("Inserisci autore:");
                        String author = scanner.nextLine();
                        try {
                            archive.searchBookForAuthor(author);
                            validAuthor = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro autore? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validAuthor = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("f")) {
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Inserisci ISBN del libro da aggiornare:");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchBookIsbn(isbn);
                            validIsbn = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro ISBN? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validIsbn = true;
                            }
                        }
                    }

                    System.out.println("Inserisci nuovo titolo (premi invio per lasciare invariato):");
                    String newTitle = scanner.nextLine();
                    System.out.println("Inserisci nuovo anno di pubblicazione (inserisci -1 per lasciare invariato):");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci nuovo numero di pagine (inserisci -1 per lasciare invariato):");
                    int newPages = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci nuovo autore (premi invio per lasciare invariato):");
                    String newAuthor = scanner.nextLine();

                    Genre newGenre = null;
                    boolean validGenre = false;
                    while (!validGenre) {
                        try {
                            System.out.println("Inserisci nuovo genere (lascia vuoto per lasciare invariato):");
                            String input = scanner.nextLine().toUpperCase();
                            if (!input.isEmpty()) {
                                newGenre = Genre.valueOf(input);
                            }
                            validGenre = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Scegli un genere valido.");
                        }
                    }

                    boolean updated = archive.updateBook(isbn, newTitle.isEmpty() ? null : newTitle, newYear, newPages, newAuthor.isEmpty() ? null : newAuthor, newGenre);
                    if (updated) {
                        System.out.println("Libro aggiornato con successo.");
                    } else {
                        System.out.println("Errore durante l'aggiornamento del libro.");
                    }
                }
            }


            // PARTE DELLE RIVISTE

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
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Definisci ISBN");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchMagazineIsbn(isbn);
                            System.out.println("Errore: ISBN già esistente, inseriscine un altro.");
                        } catch (Exception e) {
                            if (e.getMessage().contains("not found")) {
                                validIsbn = true;
                            } else {
                                System.out.println("Inserisci un valore numerico valido.");
                                scanner.nextLine();
                            }
                        }
                    }

                    System.out.println("Definisci titolo");
                    String title = scanner.nextLine();

                    System.out.println("Definisci anno di pubblicazione");
                    int year = 0;
                    boolean validYear = false;
                    while (!validYear) {
                        try {
                            year = scanner.nextInt();
                            scanner.nextLine();
                            validYear = true;
                        } catch (Exception e) {
                            System.out.println("Inserisci un anno valido.");
                            scanner.nextLine();
                        }
                    }

                    System.out.println("Definisci numero pagine");
                    int pages = 0;
                    boolean validPages = false;
                    while (!validPages) {
                        try {
                            pages = scanner.nextInt();
                            scanner.nextLine();
                            validPages = true;
                        } catch (Exception e) {
                            System.out.println("Inserisci un numero di pagine valido.");
                            scanner.nextLine();
                        }
                    }

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

                    try {
                        archive.addMagazine(isbn, title, year, pages, periodicity);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (secondChoice.equals("b")) {
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Inserisci codice ISBN:");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchMagazineIsbn(isbn);
                            validIsbn = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro ISBN? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validIsbn = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("c")) {
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Inserisci codice ISBN:");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.removeMagazineIsbn(isbn);
                            validIsbn = true;
                            System.out.println("Rivista rimossa con successo.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro ISBN? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validIsbn = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("d")) {
                    boolean validPeriodicity = false;
                    while (!validPeriodicity) {
                        System.out.println("Inserisci periodicità (WEEKLY, MONTHLY, SEMIANNUALY):");
                        try {
                            String input = scanner.nextLine().toUpperCase();
                            Periodicity periodicity = Periodicity.valueOf(input);
                            archive.searchMagazineForPeriodicity(periodicity);
                            validPeriodicity = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Inserisci una periodicità valida.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un'altra periodicità? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validPeriodicity = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("e")) {
                    boolean validYear = false;
                    while (!validYear) {
                        System.out.println("Inserisci anno di pubblicazione:");
                        try {
                            int year = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchMagazineForYear(year);
                            validYear = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro anno? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validYear = true;
                            }
                        }
                    }
                } else if (secondChoice.equals("f")) {
                    boolean validIsbn = false;
                    int isbn = 0;
                    while (!validIsbn) {
                        try {
                            System.out.println("Inserisci ISBN della rivista da aggiornare:");
                            isbn = scanner.nextInt();
                            scanner.nextLine();
                            archive.searchMagazineIsbn(isbn);
                            validIsbn = true;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Vuoi provare con un altro ISBN? (s/n)");
                            String retry = scanner.nextLine().toLowerCase();
                            if (retry.equals("n")) {
                                validIsbn = true;
                            }
                        }
                    }

                    System.out.println("Inserisci nuovo titolo (premi invio per lasciare invariato):");
                    String newTitle = scanner.nextLine();
                    System.out.println("Inserisci nuovo anno di pubblicazione (inserisci -1 per lasciare invariato):");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci nuovo numero di pagine (inserisci -1 per lasciare invariato):");
                    int newPages = scanner.nextInt();
                    scanner.nextLine();

                    Periodicity newPeriodicity = null;
                    boolean validPeriodicity = false;
                    while (!validPeriodicity) {
                        try {
                            System.out.println("Inserisci nuova periodicità (lascia vuoto per lasciare invariato):");
                            String input = scanner.nextLine().toUpperCase();
                            if (!input.isEmpty()) {
                                newPeriodicity = Periodicity.valueOf(input);
                            }
                            validPeriodicity = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Inserisci una periodicità valida.");
                        }
                    }

                    boolean updated = archive.updateMagazine(isbn, newTitle.isEmpty() ? null : newTitle, newYear, newPages, newPeriodicity);
                    if (updated) {
                        System.out.println("Rivista aggiornata con successo.");
                    } else {
                        System.out.println("Errore durante l'aggiornamento della rivista.");
                    }
                }
            }



            // STAMPA TOTALE


            if (firstChoice.equals("c")) {
                archive.printAll();
            }

            System.out.println("\nVuoi tornare al menu principale? (digita 's' per sì o 'n' per uscire)");
            String continueChoice = scanner.nextLine().toLowerCase();
            if (continueChoice.equals("n")) {
                exitProgram = true;
                System.out.println("Arrivederci.");
            }
        }

        scanner.close();
    }
}
