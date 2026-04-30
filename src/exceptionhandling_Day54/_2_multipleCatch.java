package exceptionhandling_Day54;

public class _2_multipleCatch {
    public static void main(String[] args) {
        try {
            String str = args[0];           // Might be ArrayIndexOutOfBounds
            int num = Integer.parseInt(str); // Might be NumberFormatException
            int result = 100 / num;          // Might be ArithmeticException
            System.out.println("Result: " + result);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No argument provided!");

        } catch (NumberFormatException e) {
            System.out.println("Argument is not a valid number!");

        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");

        } catch (Exception e) {
            // Catch-all: must be LAST (most general)
            System.out.println("Something went wrong: " + e.getMessage());
        }

        //Multi-catch
        // Handle multiple exceptions with same logic
        try {
            // Some risky code
            String str = null;
            int[] arr = new int[5];
            arr[10] = Integer.parseInt(str);

        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            // Handle both the same way
            System.out.println("Null or bounds error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Format error");
        }
    }



/*
IMPORTANT RULES FOR MULTIPLE CATCH:
→ More specific exceptions must come BEFORE more general ones
→ catch(Exception e) must always be LAST if present
→ Child class before parent class
→ Compiler error if unreachable catch block detected

WRONG ORDER (compile error):
catch (Exception e) { ... }     // Too general — catches everything
catch (IOException e) { ... }   // UNREACHABLE — compiler error!

CORRECT ORDER:
catch (IOException e) { ... }   // Specific first
catch (Exception e) { ... }     // General last


RULES FOR MULTI-CATCH:
→ Use pipe | to separate exception types
→ Variable e is effectively final (cannot reassign)
→ Cannot catch parent and child in same multi-catch
→ Exception types must not be related (no inheritance)
*/
}
