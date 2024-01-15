# FilmQueryProject
![Project Logo](https://s3.amazonaws.com/nightjarprod/content/uploads/sites/249/2022/09/13235630/VidiotsVideoStoreRenderingZJ-e1668482351696.jpeg)

# Description
The FilmQueryProject is a command-line application that allows users to retrieve and display film data. It provides a menu-based interface where users can choose to look up films by ID, search keywords, or exit the application. The project encapsulates JDBC code within the com.skilldistillery.filmquery.database.DatabaseAccessorObject class, and user input and display output are handled in methods of the com.skilldistillery.filmquery.app.FilmQueryApp class.

# Technologies Used
- Java
- JDBC
- MySQL

# Lessons Learned
1. JDBC Implementation: The project demonstrates the use of JDBC to connect to a MySQL database and retrieve film-related data.

2. Encapsulation: The code encapsulates JDBC code within the DatabaseAccessorObject class, promoting code organization and maintainability.

3. Menu-Driven Interface: The application features a menu-driven interface, providing users with options to interact with the database.

4. Object-Oriented Programming: The project emphasizes the use of object-oriented principles by defining entities like Film and Actor and implementing interfaces like DatabaseAccessor.

5. Exception Handling: The code includes exception handling to manage potential SQL and runtime exceptions during database interactions, however, I do probably need more work on understanding which exceptions to throw, where to throw them, and how to.

6. User Interface: The application employs a simple user interface, allowing users to input film IDs or search specific keywords and view relevant film details.