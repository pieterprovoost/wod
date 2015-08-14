package be.pieterprovoost.wod;

import be.pieterprovoost.wod.model.Cast;
import be.pieterprovoost.wod.model.Metadata;
import be.pieterprovoost.wod.model.PrimaryHeader;
import be.pieterprovoost.wod.model.Variable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Parser for ASCII records.
 */
public class Parser {

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
        cast.setPrimaryHeader(parsePrimaryHeader());
        return cast;
    }

    /**
     * Parses the primary header.
     *
     * @return primary header
     */
    private PrimaryHeader parsePrimaryHeader() {

        PrimaryHeader primaryHeader = new PrimaryHeader();

        // version

        primaryHeader.setVersionIdentifier(readString(1));

        // bytes in profile

        int f2 = readInt(1);
        int profileBytes = readInt(f2);

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
        primaryHeader.setMonth(readInt(2));

        // time

        primaryHeader.setTime(readDouble());

        // coordinates

        primaryHeader.setLatitude(readDouble());
        primaryHeader.setLongitude(readDouble());

        // number of levels

        int f15 = readInt(1);
        primaryHeader.setLevelNumber(readInt(f15));

        // profile type

        primaryHeader.setProfileType(readInt(1));

        // number of variables

        primaryHeader.setVariableNumber(readInt(2));

        // variables

        for (int v = 0; v < primaryHeader.getVariableNumber(); v++) {

            Variable variable = new Variable();

            // variable code

            int f19 = readInt(1);
            variable.setCode(readInt(f19));

            // quality control flag

            variable.setQualityControl(readInt(1));

            // number of metadata

            int f22 = readInt(1);
            variable.setMetadataNumber(readInt(f22));

            // metadata

            for (int m = 0; m < variable.getMetadataNumber(); m++) {

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

        return primaryHeader;
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
                result[i] = (char) reader.read();
            }
        } catch (Exception e) {
        }
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

    /**
     * Reads a byte array.
     *
     * @param bytes number of bytes to be read.
     *
     * @return byte array
     */
    private byte[] read(int bytes) {
        byte[] result = new byte[bytes];
        try {
            for (int i = 0; i < bytes; i++) {
                result[i] = (byte) reader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
