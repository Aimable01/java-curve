Here are the solutions for the three tasks using **Java** and **regular expressions**.

---

### **1. Java Program to Validate Email Address**
A valid email address has the format `username@domain.com`.  
We can use a regex to validate it.

#### **Code:**
```java
import java.util.regex.*;

public class EmailValidator {
    public static void main(String[] args) {
        String email = "example@gmail.com"; // Test email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        
        if (Pattern.matches(regex, email)) {
            System.out.println("Valid Email Address");
        } else {
            System.out.println("Invalid Email Address");
        }
    }
}
```

#### **Explanation**:
- `^[a-zA-Z0-9._%+-]+` → Starts with alphanumeric and allowed special characters.
- `@[a-zA-Z0-9.-]+` → Followed by the "@" symbol and domain name.
- `\\.[a-zA-Z]{2,6}$` → Ends with a dot followed by 2-6 letters (e.g., `.com`, `.net`).

---

### **2. Java Program to Validate Rwandan Mobile Telephone Number**
We check for:
- **Local format**: Numbers starting with `07`.
- **International format**: Numbers starting with `+2507`.

#### **Code:**
```java
import java.util.regex.*;

public class RwandanPhoneValidator {
    public static void main(String[] args) {
        String phoneNumber = "+250784567890"; // Test number
        
        String localRegex = "^07\\d{8}$";      // Local format: Starts with "07" and has 10 digits.
        String internationalRegex = "^\\+2507\\d{8}$"; // International format: Starts with "+2507"

        if (Pattern.matches(localRegex, phoneNumber)) {
            System.out.println("Local number format");
        } else if (Pattern.matches(internationalRegex, phoneNumber)) {
            System.out.println("International number format");
        } else {
            System.out.println("Invalid phone number format");
        }
    }
}
```

#### **Explanation**:
- `^07\\d{8}$`: Starts with `07` followed by exactly 8 digits (local format).
- `^\\+2507\\d{8}$`: Starts with `+2507` followed by exactly 8 digits (international format).

---

### **3. Java Program to Validate Strong Password**
A strong password:
- Has at least **8 characters**.
- Contains **at least one uppercase letter**.
- Contains **at least one lowercase letter**.
- Contains **at least one digit**.
- Contains **at least one special character**.

#### **Code:**
```java
import java.util.regex.*;

public class StrongPasswordValidator {
    public static void main(String[] args) {
        String password = "Strong@123"; // Test password
        
        // Regex for a strong password
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        
        if (Pattern.matches(regex, password)) {
            System.out.println("Strong Password");
        } else {
            System.out.println("Weak Password");
        }
    }
}
```

#### **Explanation**:
- `(?=.*[a-z])`: At least one lowercase letter.
- `(?=.*[A-Z])`: At least one uppercase letter.
- `(?=.*\\d)`: At least one digit.
- `(?=.*[@$!%*?&])`: At least one special character.
- `[A-Za-z\\d@$!%*?&]{8,}`: Ensures a minimum of **8 characters**.

---

### **Output Examples**
1. **Email Validator**:
    - Input: `example@gmail.com` → Output: `Valid Email Address`
    - Input: `invalid-email` → Output: `Invalid Email Address`

2. **Rwandan Phone Validator**:
    - Input: `0784567890` → Output: `Local number format`
    - Input: `+250784567890` → Output: `International number format`
    - Input: `123456` → Output: `Invalid phone number format`

3. **Strong Password Validator**:
    - Input: `Strong@123` → Output: `Strong Password`
    - Input: `weakpass` → Output: `Weak Password`

---

These programs use **regular expressions** to ensure the inputs meet specific validation rules. You can customize the regex patterns further if needed.