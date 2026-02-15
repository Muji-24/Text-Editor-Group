package data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import dal.HashCalculator;
import dal.DatabaseConnection;

import java.sql.Connection;

public class DataLayerTest {

    @Test
    @DisplayName("Test HashCalculator: MD5 Integrity")
    void testMD5Hash_Integrity() {
        String input1 = "Arabic Text Editor Test";
        String input2 = "Arabic Text Editor Test";
        String input3 = "Different Text";

        String hash1 = "";
        String hash2 = "";
        String hash3 = "";
        try {
            hash1 = HashCalculator.calculateHash(input1);
            hash2 = HashCalculator.calculateHash(input2);
            hash3 = HashCalculator.calculateHash(input3);
        } catch (Exception e) {
            fail("Hash calculation failed: " + e.getMessage());
        }

        assertNotNull(hash1, "Hash should not be null");
        assertEquals(hash1, hash2, "Same input should produce same MD5 hash");
        assertNotEquals(hash1, hash3, "Different input should produce different MD5 hashes");
        assertEquals(32, hash1.length(), "MD5 hash should be 32 characters long");
    }

    @Test
    @DisplayName("Test DatabaseConnection: Singleton Property")
    void testDatabaseConnection_Singleton() {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        assertNotNull(instance1, "DatabaseConnection instance should not be null");
        assertSame(instance1, instance2, "DatabaseConnection should follow Singleton pattern (same instance)");
    }

    @Test
    @DisplayName("Test DatabaseConnection: Connection Retrieval")
    void testDatabaseConnection_GetConnection() {
        DatabaseConnection dbInstance = DatabaseConnection.getInstance();
        Connection connection = dbInstance.getConnection();

        assertNotNull(connection, "Database connection should not be null (ensure MariaDB is running)");
        try {
            assertFalse(connection.isClosed(), "Database connection should be open");
        } catch (Exception e) {
            fail("Exception while checking connection status: " + e.getMessage());
        }
    }
}
