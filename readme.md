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
    git clone [https://github.com/YourUsername/PasswordManagerFX.git](https://github.com/YourUsername/PasswordManagerFX.git)
    cd PasswordManagerFX
    ```
    (Replace `YourUsername` with your actual GitHub username and `PasswordManagerFX` with your repository name)

2.  **Database Setup (PostgreSQL):**
    * Ensure you have PostgreSQL installed and running on your system.
    * Create a new database for this application (e.g., `password_manager_db`).
    * Execute the following SQL script to create the necessary `users` and `passwords` (or `password_entries` as per your design) tables:

        ```sql
        -- SQL script for 'users' table
        CREATE TABLE users (
            id SERIAL PRIMARY KEY,
            username VARCHAR(50) UNIQUE NOT NULL,
            password VARCHAR(255) NOT NULL -- Store hashed/encrypted password here
        );

        -- SQL script for 'password_entries' table (Example, adjust based on your actual table)
        CREATE TABLE password_entries (
            id SERIAL PRIMARY KEY,
            user_id INTEGER NOT NULL REFERENCES users(id),
            service_name VARCHAR(255) NOT NULL,
            username_or_email VARCHAR(255),
            encrypted_password TEXT NOT NULL, -- Store encrypted password text
            notes TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
        ```
        * **Note:** Adjust table and column names (`users`, `password_entries`, `service_name`, `username_or_email`, `encrypted_password`, `notes`) to match your actual database schema. The `password` column in `users` should ideally store a **hashed** password, not plain text.

3.  **Configure Database Connection:**
    * Locate the `config.properties` file (or equivalent, see next section for creating one).
    * Update the database connection details (`db.url`, `db.user`, `db.password`) to match your PostgreSQL setup.

4.  **Open in your Preferred Java IDE (IntelliJ IDEA recommended):**
    * Open the project as a Maven or Gradle project (depending on your build system).
    * Ensure all dependencies (e.g., PostgreSQL JDBC driver, JavaFX SDK) are resolved. If you're using Maven, these should be in your `pom.xml`. If Gradle, in your `build.gradle`.

5.  **Compile and Run:**
    * Run the `Main` class (e.g., `com.example.sqlfx.Main`) as a Java Application.

### Application Usage

1.  **Login/Register:**
    * If you're a new user, click "Kayıt Ol" to register a new account.
    * Enter your desired username and password.
    * If you have an existing account, enter your credentials on the login screen and click "Giriş Yap".

2.  **Dashboard/Password Management:**
    * After successful login, you will be directed to the dashboard (or password list screen).
    * Here you can add new password entries, view existing ones, edit, or delete them. (Provide more specific instructions if your app has unique features).

## Project Structure (Common for JavaFX Maven/Gradle)