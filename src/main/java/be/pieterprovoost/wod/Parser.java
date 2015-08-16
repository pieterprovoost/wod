package be.pieterprovoost.wod;

import be.pieterprovoost.wod.model.*;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Parser for ASCII records.
 */
public class Parser {

    final static Logger logger = Logger.getLogger(Parser.class);

    private Reader reader;

    /**
     * Constructor.
     *
     * @param inputStream ASCII record input stream
     */
    public Parser(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Parses a stream.
     *
     * @return cast
     */
    public Cast parse() {
        Cast cast = new Cast();
        parsePrimaryHeader(cast);
        parseCharacterEntries(cast);
        parseSecondaryHeader(cast);
        parseBiologicalHeader(cast);
        parseTaxonData(cast);
        parseProfileData(cast);
        return cast;
    }

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

    /**
     * Parses the secondary header
     *
     * @param cast cast
     */
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

    /**
     * Parses the biological header.
     *
     * @param cast cast
     */
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

    /**
     * Parses the taxon data.
     *
     * @param cast cast
     */
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

    /**
     * Parses character entries.
     *
     * @param cast cast
     */
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

    /**
     * Parses the primary header.
     *
     * @param cast cast
     */
    private void parsePrimaryHeader(Cast cast) {

        logger.debug("Primary header");
        PrimaryHeader primaryHeader = new PrimaryHeader();

        // version

        primaryHeader.setVersionIdentifier(readString(1));

        // bytes in profile

        int f2 = readInt(1);
        int profileBytes = readInt(f2);
        logger.debug("Bytes in profile: " + profileBytes);

        // cast number

        int f4 = readInt(1);
        primaryHeader.setCastNumber(readInt(f4));

        // country code

        primaryHeader.setCountryCode(readString(2));

        // cruise number

        int f7 = readInt(1);
        primaryHeader.setCruiseNumber(readInt(f7));

        // date

        primaryHeader.setYear(readInt(4));
        primaryHeader.setMonth(readInt(2));
        primaryHeader.setDay(readInt(2));

        // time

        primaryHeader.setTime(readDouble());

        // coordinates

        primaryHeader.setLatitude(readDouble());
        primaryHeader.setLongitude(readDouble());

        // number of levels

        int f15 = readInt(1);
        primaryHeader.setLevelNumber(readInt(f15));
        logger.debug("Number of levels: " + primaryHeader.getLevelNumber());

        // profile type

        primaryHeader.setProfileType(readInt(1));

        // number of variables

        primaryHeader.setVariableNumber(readInt(2));
        logger.debug("Number of variables: " + primaryHeader.getVariableNumber());

        // variables

        for (int v = 0; v < primaryHeader.getVariableNumber(); v++) {

            logger.debug("Variable");
            Variable variable = new Variable();

            // variable code

            int f19 = readInt(1);
            variable.setCode(readInt(f19));

            // quality control flag

            variable.setQualityControl(readInt(1));

            // number of metadata

            int f22 = readInt(1);
            variable.setMetadataNumber(readInt(f22));
            logger.debug("Number of metadata: " + variable.getMetadataNumber());

            // metadata

            for (int m = 0; m < variable.getMetadataNumber(); m++) {

                logger.debug("Metadata");
                Metadata metadata = new Metadata();

                // code

                int f24 = readInt(1);
                metadata.setCode(readInt(f24));

                // value

                metadata.setValue(readDouble());

                // add metadata

                variable.getMetadatas().add(metadata);

            }

            // add variable

            primaryHeader.getVariables().add(variable);

        }

        cast.setPrimaryHeader(primaryHeader);

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
