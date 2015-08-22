package be.pieterprovoost.wod;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Parser {

    final static Logger logger = Logger.getLogger(Parser.class);

    private Reader reader;
    private BasicDBObject tables;

    /**
     * Constructor.
     *
     * @param inputStream inputstream
     */
    public Parser(InputStream inputStream) {
        loadTables();
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Parses the input stream to a BasicDBObject.
     */
    public BasicDBObject parse() {
        BasicDBObject cast = new BasicDBObject();

        parsePrimaryHeader(cast);
        //parseCharacterEntries();
        //parseSecondaryHeader();
        //parseBiologicalHeader();
        //parseTaxonData();
        //parseProfileData();

        return cast;
    }

    /**
     * Loads the code tables from a json file.
     */
    private void loadTables() {
        InputStream is = this.getClass().getResourceAsStream("codes.json");
        try {
            String json = IOUtils.toString(is);
            tables = (BasicDBObject) JSON.parse(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Looks up a code in the tables and adds the necessary properties to the associated BasicDBObject.
     *
     * @param o the BasicDBObject
     * @param codes code sequence
     */
    private void lookupTable(BasicDBObject o, String... codes) {
        BasicDBObject node = tables;

        for (String code : codes) {
            if (node.containsField(code)) {
                node = (BasicDBObject) node.get(code);
            } else {
                return;
            }
        }

        if (node.containsField("properties")) {
            DBObject properties = (DBObject) node.get("properties");
            o.putAll(properties);
        }
    }

    /*

    private void parseProfileData(Cast cast) {

        logger.debug("Profile data");
        ProfileData profileData = new ProfileData();

        for (int l = 0; l < cast.getPrimaryHeader().getLevelNumber(); l++) {

            logger.debug("Level");
            Level level = new Level();

            // depth

            level.setDepth(readDouble());
            level.setDepthErrorCode(readInt(1));
            level.setOriginatorDepthErrorFlag(readInt(1));

            // values

            for (int v = 0; v < cast.getPrimaryHeader().getVariableNumber(); v++) {

                logger.debug("Variable");
                Value value = new Value();

                value.setValue(readDouble());
                value.setQualityControl(readInt(1));
                value.setOriginator(readInt(1));
                value.setCode(cast.getPrimaryHeader().getVariables().get(v).getCode());

                level.getValues().add(value);

            }

            profileData.getLevels().add(level);

        }

        cast.setProfileData(profileData);

    }

    private void parseSecondaryHeader(Cast cast) {

        logger.debug("Secondary header");
        SecondaryHeader secondaryHeader = new SecondaryHeader();

        int f1 = readInt(1);

        if (f1 == 0) {
            cast.setSecondaryHeader(secondaryHeader);
            return;
        }

        int f2 = readInt(f1);
        logger.debug("Bytes in secondary header: " + f2);

        int f3 = readInt(1);
        Integer entryNumber = readInt(f3);

        for (int e = 0; e < entryNumber; e++) {
            SecondaryHeaderEntry entry = new SecondaryHeaderEntry();
            int f5 = readInt(1);
            entry.setCode(readInt(f5));
            entry.setValue(readDouble());
            secondaryHeader.getEntries().add(entry);
        }

        cast.setSecondaryHeader(secondaryHeader);

    }

    private void parseBiologicalHeader(Cast cast) {

        logger.debug("Biological header");
        BiologicalHeader biologicalHeader = new BiologicalHeader();

        int f1 = readInt(1);

        if (f1 == 0) {
            cast.setBiologicalHeader(biologicalHeader);
            return;
        }

        int f2 = readInt(f1);
        int f3 = readInt(1);
        Integer entryNumber = readInt(f3);

        for (int e = 0; e < entryNumber; e++) {
            BiologicalHeaderEntry entry = new BiologicalHeaderEntry();
            int f5 = readInt(1);
            entry.setCode(readInt(f5));
            entry.setValue(readDouble());
            biologicalHeader.getEntries().add(entry);
        }

        cast.setBiologicalHeader(biologicalHeader);

    }

    private void parseTaxonData(Cast cast) {

        logger.debug("Taxon data");
        TaxonData taxonData = new TaxonData();

        int f1 = readInt(1);
        if (f1 == 0) {
            cast.setTaxonData(taxonData);
            return;
        }
        int taxaSetNumber = readInt(f1);

        for (int t = 0; t < taxaSetNumber; t++) {

            TaxaSet taxaSet = new TaxaSet();

            int f3 = readInt(1);
            int taxaSetEntryNumber = readInt(f3);

            for (int e = 0; e < taxaSetEntryNumber; e++) {

                TaxaSetEntry taxaSetEntry = new TaxaSetEntry();

                int f5 = readInt(1);
                taxaSetEntry.setCode(readInt(f5));
                taxaSetEntry.setValue(readDouble());
                taxaSetEntry.setQualityControl(readInt(1));
                taxaSetEntry.setOriginator(readInt(1));

                taxaSet.getEntries().add(taxaSetEntry);

            }

            taxonData.getTaxaSets().add(taxaSet);

        }

        cast.setTaxonData(taxonData);

    }

    private void parseCharacterEntries(Cast cast) {

        logger.debug("Character entries");

        int f1 = readInt(1);

        if (f1 == 0) {
            return;
        }

        int f2 = readInt(f1);
        logger.debug("Total bytes character data: " + f2);

        Integer entryNumber = readInt(1);
        logger.debug("Character data entries: " + entryNumber);

        for (int e = 0; e < entryNumber; e++) {

            int dataType = readInt(1);

            if (dataType == 1) {

                int f5 = readInt(2);
                cast.setOriginatorsCruise(readString(f5));

            } else if (dataType == 2) {

                int f5 = readInt(2);
                cast.setOriginatorsStationCode(readString(f5));

            } else if (dataType == 3) {

                int nameNumber = readInt(2);

                for (int n = 0; n < nameNumber; n++) {

                    logger.debug("Principal investigator");
                    PrincipalInvestigator principalInvestigator = new PrincipalInvestigator();

                    // variable code

                    int f6 = readInt(1);
                    principalInvestigator.setVariableCode(readInt(f6));

                    // investigator code

                    int f8 = readInt(1);
                    principalInvestigator.setInvestigatorCode(readInt(f8));

                    // add investigator

                    cast.getPrincipalInvestigators().add(principalInvestigator);

                }

            }

        }

    }

    */

    /**
     * Parses the primary header
     *
     * @param cast cast object
     */
    private void parsePrimaryHeader(BasicDBObject cast) {

        logger.debug("Primary header");
        BasicDBObject primaryHeader = new BasicDBObject();
        cast.put("primaryHeader", primaryHeader);

        primaryHeader.put("versionIdentifier", readString(1));

        int f2 = readInt(1);
        int f3 = readInt(f2);

        int f4 = readInt(1);
        primaryHeader.put("castNumber", readInt(f4));

        primaryHeader.put("countryCode", readString(2));

        int f7 = readInt(1);
        primaryHeader.put("cruiseNumber", readInt(f7));

        primaryHeader.put("year", readInt(4));
        primaryHeader.put("month", readInt(2));
        primaryHeader.put("day", readInt(2));

        primaryHeader.put("time", readDouble());

        primaryHeader.put("latitude", readDouble());
        primaryHeader.put("longitude", readDouble());

        int f15 = readInt(1);
        primaryHeader.put("levelNumber", readInt(f15));
        logger.debug("Number of levels: " + primaryHeader.getInt("levelNumber"));

        primaryHeader.put("profileType", readInt(1));

        primaryHeader.put("variableNumber", readInt(2));
        logger.debug("Number of variables: " + primaryHeader.getInt("variableNumber"));

        // variables

        BasicDBList variables = new BasicDBList();
        cast.put("variables", variables);

        for (int v = 0; v < primaryHeader.getInt("variableNumber"); v++) {

            logger.debug("Variable");
            BasicDBObject variable = new BasicDBObject();
            variables.add(variable);

            int f19 = readInt(1);
            lookupTable(variable, "variable", Integer.toString(readInt(f19)));

            variable.put("qc", readInt(1));

            int f22 = readInt(1);
            int metadataNumber = readInt(f22);

            // metadata

            for (int m = 0; m < metadataNumber; m++) {

                int f24 = readInt(1);
                Integer code = readInt(f24);
                Double value = readDouble();

                lookupTable(variable, "metadata", code.toString(), Integer.toString(value.intValue()));

            }

        }

    }

    /**
     * Reads a double.
     *
     * @return double
     */
    private Double readDouble() {
        String first = readString(1);
        if (!"-".equals(first)) {
            int signif = Integer.parseInt(first);
            int total = readInt(1);
            int prec = readInt(1);
            return readInt(total) / Math.pow(10, prec);
        } else {
            return null;
        }
    }

    /**
     * Reads a char array.
     *
     * @param bytes number of bytes to be read
     *
     * @return char array
     */
    private char[] readChar(int bytes) {
        char[] result = new char[bytes];
        try {
            for (int i = 0; i < bytes; i++) {
                int n = 10;
                while (n == 10) {
                    n = reader.read();
                }
                result[i] = (char) n;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("  Bytes read: " + new String(result));
        return result;
    }

    /**
     * Reads an integer.
     *
     * @param bytes number of bytes to be read
     *
     * @return integer
     */
    private int readInt(int bytes) {
        char[] digits = readChar(bytes);
        return Integer.parseInt(new String(digits).trim());
    }

    /**
     * Reads a string.
     *
     * @param bytes number of bytes to be read
     *
     * @return string
     */
    private String readString(int bytes) {
        char[] digits = readChar(bytes);
        return new String(digits);
    }

}
