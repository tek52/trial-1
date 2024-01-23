package edu.gatech.seclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 * <p>
 * This class is provided to interpret your grades via junit tests
 * and as a reminder, should NOT be posted in any public repositories,
 * even after the class has ended.
 */

@Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class MyStringTest {

    private MyStringInterface myString;

    @BeforeEach
    public void setUp() {
        myString = new MyString();
    }

    @AfterEach
    public void tearDown() {
        myString = null;
    }

    @Test
    // Description: First count number example in the interface documentation
    public void testCountAlphabeticWords1() {
        myString.setString("My numbers are 11, 96, and thirteen");
        assertEquals(5, myString.countAlphabeticWords());
    }

    @Test
    // Description: Counting alphabetic words in a string with numbers and special characters
    public void testCountAlphabeticWords2() {
        myString.setString("2024: A space odyssey, isn't it");
        assertEquals(7,myString.countAlphabeticWords());
    }

    @Test
    // Description: Counting alphabetic words in a string with no alphabetic words
    public void testCountAlphabeticWords3() {
        myString.setString("3244 -7 34521 78543 -23");
        assertEquals(0, myString.countAlphabeticWords());

    }

    @Test
    // Description: Counting alphabetic words with a null string
    public void testCountAlphabeticWords4() {
        assertThrows(IllegalArgumentException.class, () -> myString.setString(null));

    }

    @Test
    // Description: Setting a string including special and alphanumeric characters
    public void testSetString1() {
        myString.setString("OMSCS is a great program!");
        assertEquals("OMSCS is a great program!", myString.getString());
    }

    @Test
    // Description: Sample encryption 1
    public void testEncrypt1() {
        myString.setString("Cat & 5 DogS");
        assertEquals("1xU & S 65RJ", myString.encrypt(5, 3));
    }

    @Test
    // Description: Different parameters
    public void testEncrypt2() {
        myString.setString("Software Development Process Course");
        String encrypted = myString.encrypt(3,4);
        assertEquals("QU3jsod0 h0p0LUXO0Rj HdUu0gg eUmdg0", encrypted, "The encryption did not produce the expected output.");
    }

    @Test
    // Description: Encryption with boundary values for parameters
    public void testEncrypt3() {
        myString.setString("Boundary Test");
        assertEquals("AntmcZqx Sdrs", myString.encrypt(1,61));
    }

    @Test
    // Description: Encryption with a string including non-alphanumeric characters
    public void testEncrypt4() {
        myString.setString("Encrypt! 2024?");
        String expectedOutput = "ZmpUj8q! R5Rn?";
        assertEquals(expectedOutput, myString.encrypt(11, 5));
    }

    @Test
    // Description: Encryption method's error handling with an empty string
    public void testEncrypt5() {
        assertThrows(IllegalArgumentException.class, () -> {
            myString.setString("");
            myString.encrypt(3, 7);
        });
    }

    @Test
    // Description: Encryption with a null string
    public void testEncrypt6() {
        assertThrows(IllegalArgumentException.class, () -> myString.setString(null));
    }

    @Test
    // Description: First convert digits example in the interface documentation
    public void testConvertDigitsToNamesInSubstring1() {
        myString.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        myString.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", myString.getString());
    }

    @Test
    // Description: Tests the method's ability to accurately convert a substring containing only digits into their corresponding names within a longer string
    public void testConvertDigitsToNamesInSubstring2() {
        myString.setString("2024 Olympics will start on 23rd July");
        myString.convertDigitsToNamesInSubstring(1, 4);
        assertEquals("TwoZeroTwoFour Olympics will start on 23rd July", myString.getString());
    }

    @Test
    // Description: Tests the behavior of the method when the end index of the substring is out of bounds of the string.
    public void testConvertDigitsToNamesInSubstring3() {
        myString.setString("Example 1234");
        assertThrows(IllegalArgumentException.class, () -> myString.convertDigitsToNamesInSubstring(12, 15));
    }


    @Test
    // Description: Tests the method with a substring that does not contain any digits.
    public void testConvertDigitsToNamesInSubstring4() {
        myString.setString("Hello world 2024");
        myString.convertDigitsToNamesInSubstring(1, 5);
        assertEquals("Hello world 2024", myString.getString());
    }


    @Test
    // Description: Tests the method's behavior when the end index is out of bounds but the string consists only of digits.
    public void testConvertDigitsToNamesInSubstring5() {
        myString.setString("1234");
        assertThrows(IllegalArgumentException.class, () -> myString.convertDigitsToNamesInSubstring(1, 5));
    }
}
