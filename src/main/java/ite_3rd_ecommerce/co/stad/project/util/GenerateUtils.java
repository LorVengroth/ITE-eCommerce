package ite_3rd_ecommerce.co.stad.project.util;


import java.util.Random;

public class GenerateUtils {

    // generate product code
    public static String generateProductCode() {
        Random random = new Random();

        // Generate a random integer between 0 (inclusive) and 10000 (exclusive)
        int randomDigits = random.nextInt(10000);

        // %04d formats the integer to be exactly 4 digits, padding with zeros on the left
        return String.format("ITE-3RD-%04d", randomDigits);
    }

    // generate slug based on name
    public static String generateSlug(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        // 1. Convert to lowercase and strip whitespace from both ends
        String slug = text.toLowerCase().strip();

        // 2. Remove all special characters (keep only alphanumeric, spaces, and hyphens)
        slug = slug.replaceAll("[^\\w\\s-]", "");

        // 3. Replace spaces, underscores, and consecutive hyphens with a single hyphen
        slug = slug.replaceAll("[\\s_-]+", "-");

        // 4. Remove any remaining leading or trailing hyphens
        slug = slug.replaceAll("^-+|-+$", "");

        return slug;
    }

}

