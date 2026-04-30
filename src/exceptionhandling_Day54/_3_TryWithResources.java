package exceptionhandling_Day54;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _3_TryWithResources implements AutoCloseable {
    static void main(String[] args) {
        // OLD WAY — manual finally for cleanup
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("file.txt"));
            String line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close(); // Must close in finally
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // NEW WAY — try-with-resources (cleaner!)
        try (BufferedReader br2 = new BufferedReader(new FileReader("file.txt"))) {
            String line = br2.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // br.close() called AUTOMATICALLY — even if exception occurs!

/*
HOW TRY-WITH-RESOURCES WORKS:
→ Resource must implement AutoCloseable interface
→ close() is called automatically at end of try block
→ close() called even if exception thrown
→ Multiple resources: closed in REVERSE order of declaration
→ Cleaner, less error-prone than manual finally

MULTIPLE RESOURCES:
try (
    Connection con = DriverManager.getConnection(url);
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery()
) {
    // Use resources
} catch (SQLException e) {
    e.printStackTrace();
}
// rs closed first, then stmt, then con (reverse order)
*/
    }

    @Override
    public void close() throws Exception {

    }
}
