# PasswordManagerFX

**PasswordManagerFX** is a secure and user-friendly password management application built with JavaFX, utilizing PostgreSQL for robust data storage. It provides a simple graphical interface for managing your sensitive credentials, emphasizing security through encryption and an intuitive user experience.

## Features

* **Secure User Authentication:** Implements a robust login and registration system to ensure only authorized users can access their password data.
* **Password Storage:** Safely stores encrypted user passwords and associated details in a PostgreSQL database.
* **Data Encryption:** Encrypts sensitive password entries before storing them, providing an additional layer of security. (Specify your encryption method if you have one, e.g., AES, etc. If not, briefly explain how you secure the data).
* **User-Friendly Interface:** Designed with JavaFX and SceneBuilder for an intuitive and responsive graphical user interface.
* **PostgreSQL Integration:** Leverages the power and reliability of PostgreSQL for efficient and scalable data management.

## How to Use

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/sevsaylight/PasswordManagerFX.git]
    cd PasswordManagerFX
    ```

2.  **Configure Database Connection:**
    * Locate the `config.properties` file (or equivalent, see next section for creating one).
    * Update the database connection details (`db.url`, `db.user`, `db.password`) to match your PostgreSQL setup.

3.  **Open in your Preferred Java IDE (IntelliJ IDEA recommended):**
    * Open the project as a Maven or Gradle project (depending on your build system).
    * Ensure all dependencies (e.g., PostgreSQL JDBC driver, JavaFX SDK) are resolved. If you're using Maven, these should be in your `pom.xml`. If Gradle, in your `build.gradle`.

4.  **Compile and Run:**
    * Run the `Main` class (e.g., `com.example.sqlfx.Main`) as a Java Application.

### Application Usage

1.  **Login/Register:**
    * If you're a new user, click "Sign Up" to register a new account.
    * Enter your desired username and password.
    * If you have an existing account, enter your credentials on the login screen and click "Log in".

2.  **Dashboard/Password Management:**
    * After successful login, you will be directed to the dashboard (or password list screen).
    * Here you can add new password entries, view existing ones, edit, or delete them. (Provide more specific instructions if your app has unique features).

## Project Structure (Common for JavaFX Maven/Gradle)