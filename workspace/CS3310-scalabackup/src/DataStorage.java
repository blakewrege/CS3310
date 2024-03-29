

import java.io.*;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * This class handles all of our BST-related things.
 * As well as reading / writing to the Backup file
 */
public class DataStorage {
	private short rootPointer;
    private short n;
    private short nextEmptyPointer;
    private short searchCount;
    private BSTNode[] countries;
    private UIoutput log;
	private boolean success;

    public DataStorage() {
        this.rootPointer = -1;
        this.n = 0;
        this.nextEmptyPointer = 0;
        this.countries = new BSTNode[300];
    }

    /**
     * Used by Setup.
     * Creates a new DataStorage object with space for 300 BSTNodes
     *
     * @param log
     */
    public DataStorage(UIoutput log) {
        this.rootPointer = -1;
        this.n = 0;
        this.nextEmptyPointer = 0;
        this.countries = new BSTNode[300];
        this.log = log;
    }

    /**
     * This exists just in case we know how big we want our array to be
     *
     * @param size
     * @param log
     */
    public DataStorage(short size, UIoutput log) {
        this.rootPointer = -1;
        this.n = 0;
        this.nextEmptyPointer = 0;
        this.countries = new BSTNode[size];
        this.log = log;
    }

    /**
     * TODO
     * Used by UserApp
     * Reads in the Backup file and recreates the BST
     *
     * @param inputFile
     * @param log
     */
    public DataStorage(String inputFile, UIoutput UIoutput) {
        this.countries = new BSTNode[300];
        this.log = UIoutput;
        //String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            UIoutput.displayThis("-->> OPENED Backup file");

            createBSTArray(br);

            UIoutput.displayThis("-->> CLOSED Backup file");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new array of BSTNodes from the Backup file
     * @param br
     */
    private void createBSTArray(BufferedReader br) {
        String line;
        String[] lineParts;
        int i;
        Country c;

        try {
            line = br.readLine();
            lineParts = line.split(",");
            this.rootPointer = Short.parseShort(lineParts[0]);
            this.n = Short.parseShort(lineParts[1]);
            this.nextEmptyPointer = Short.parseShort(lineParts[2]);

            for (i = 0; i < n; i++) {
                line = br.readLine();
                lineParts = line.split(",");

                c = new Country();
                c.setCode(lineParts[2]);
                c.setId(Short.parseShort(lineParts[3]));
                c.setName(lineParts[4]);
                c.setContinent(lineParts[5]);
                c.setArea(Integer.parseInt(lineParts[6]));
                c.setPopulation(Long.parseLong(lineParts[7]));
                c.setLifeExpectancy(Float.parseFloat(lineParts[8]));

                countries[i] = new BSTNode(Short.parseShort(lineParts[0]),
                        Short.parseShort(lineParts[1]),
                        lineParts[2], c);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public short getRootPointer() {
        return rootPointer;
    }

    public void setRootPointer(short rootPointer) {
        this.rootPointer = rootPointer;
    }

    public short getN() {
        return n;
    }

    public void setN(short n) {
        this.n = n;
    }

    public short getNextEmptyPointer() {
        return nextEmptyPointer;
    }

    public void setNextEmptyPointer(short nextEmptyPointer) {
        this.nextEmptyPointer = nextEmptyPointer;
    }

    public short getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(short searchCount) {
        this.searchCount = searchCount;
    }

    /**
     * Insert countries into the BST
     * @param c
     * @param output
     */
    public void insert(Country c, boolean output) {
        BSTNode node = new BSTNode(c.getCode(), c);
        short i;
        short parent;
        char direction;
        int compare;
        //String message = "";
        this.searchCount = 1;

        // if the tree is empty
        if (this.rootPointer == -1) {
            countries[0] = node;
            this.rootPointer = 0;
            this.n = 1;
            this.nextEmptyPointer = 1;

            // the tree isn't empty
        } else {
            i = this.rootPointer;
            parent = 0;
            direction = 0;

            while (i != -1) {
                parent = i;
                compare = c.getCode().compareTo(countries[i].getKeyValue());

                if (compare < 0) {
                    this.searchCount += 1;
                    i = countries[i].getLeftPointer();
                    direction = 'L';

                } else if (compare > 0) {
                    this.searchCount += 1;
                    i = countries[i].getRightPointer();
                    direction = 'R';

                } else {
                    // keys are the same
                    // shouldn't happen in this assignment
                    System.out.println("Key already exists in tree: " + c.getCode());
                    System.exit(1);
                }
            }

            if (direction == 'L') {
                countries[nextEmptyPointer] = node;
                countries[parent].setLeftPointer(n);
                this.n += 1;
                this.nextEmptyPointer += 1;

            }

            if (direction == 'R') {
                countries[nextEmptyPointer] = node;
                countries[parent].setRightPointer(n);
                this.n += 1;
                this.nextEmptyPointer += 1;
            }
        }

        // we don't want our inserts performed by Setup to be logged
        if (output) {
         //   this.UIoutput.displayThis(c, 'I', c.getCode(), this.searchCount, true);
        }
    }

    /**
     * Calls select() to find the country we want to static delete, then
     * sets its ID to 0.
     * @param code
     */
    public void delete(String code, UIoutput UIoutput) {
        Country country;
        country = this.select(code, false);
        //String message;

        if (country == null) {
            // we didn't find what we were looking for
          //  this.UIoutput.displayThis(null, 'D', code, this.searchCount, false);

        } else {
            // we found the country, now tombstone it
            country.setId((short) 0);
          //  this.UIoutput.displayThis(country, 'D', code, this.searchCount, true);
        }
    }

    /**
     * Search the BST for a given country code using a BST search algorithm
     * @param code country code we're looking for
     * @param output whether or not to log the result
     * @return
     */
    public Country select(String code, boolean output) {
        int i = this.rootPointer;
        int compare = code.compareTo(this.countries[i].getKeyValue());
        this.searchCount = 1;
        Country country;
        while (i != -1 && (compare != 0)) {
            if (compare < 0) {
                i = this.countries[i].getLeftPointer();
            } else {
                i = this.countries[i].getRightPointer();
            }

            this.searchCount += 1;
            try {
                compare = code.compareTo(this.countries[i].getKeyValue());
            } catch (ArrayIndexOutOfBoundsException e) {
                compare = 0;
            }
        }

        if (i == -1) {
            // country not found
            country = null;
            success = false;
            //this.log.display_this(null, 'S', code, this.searchCount, false);
        } else if (countries[i].getRestOfData().getId() != 0){
            // country was found
            country = countries[i].getRestOfData();
            success = true;
            //this.log.display_this(country, 'S', code, this.searchCount, true);
        } else {
            // country exists, but was tombstoned
            country = null;
            success = false;
            //this.log.display_this(null, 'S', code, this.searchCount, false);
        }

        if (output) {
  //          this.log.display_this(country, 'S', code, this.searchCount, success);
        }

        return country;
    }

    /**
     * Write the header for the ShowAll function
     * Call the In-Order Traversal method
     * Write the footer line
     */
    public void showAll() {
        this.log.displayThis("A");
 //       this.log.print_S_A_Header();
        iot(this.rootPointer);
 //       this.log.display_this("===========================\r\n");
    }

    /**
     * Uses recursion to perform an in-order traversal of our BST
     * @param rootPointer
     * @return
     */
    private void iot(int rootPointer) {
        if (rootPointer != -1) {
            iot(countries[rootPointer].getLeftPointer());
            //System.out.println(countries[rootPointer].getRestOfData().toString());

//            this.log.displayThis(countries[rootPointer].getRestOfData(), 'A', countries[rootPointer].getKeyValue(), (short) 0, true);

            iot(countries[rootPointer].getRightPointer());
        }
    }

    /**
     * Open the Backup file for writing, and save the BST to it (in CSV form).
     * Then close the Backup file
     * @param bst
     */
    public void finish_up(DataStorage bst) {
        String backupFile = "Backup.csv";
        String header = bst.rootPointer + "," + n + "," + bst.nextEmptyPointer + "\r\n";
        String line = "";
        int i;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(backupFile, false));
            this.log.displayThis("-->> OPENED Backup file");
            bw.write(header);
            for (i = 0; i < bst.n; i++) {
                line += bst.countries[i].getLeftPointer();
                line += ",";
                line += bst.countries[i].getRightPointer();
                line += ",";
                line += bst.countries[i].getKeyValue();
                line += ",";
                line += bst.countries[i].getRestOfData().getId();
                line += ",";
                line += bst.countries[i].getRestOfData().getName();
                line += ",";
                line += bst.countries[i].getRestOfData().getContinent();
                line += ",";
                line += bst.countries[i].getRestOfData().getArea();
                line += ",";
                line += bst.countries[i].getRestOfData().getPopulation();
                line += ",";
                line += bst.countries[i].getRestOfData().getLifeExpectancy();
                line += "\r\n";
                bw.write(line);
                line = "";
            }
            log.displayThis("-->> CLOSED Backup file");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
