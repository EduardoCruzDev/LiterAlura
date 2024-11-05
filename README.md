Hello Folk! After 6 hours of cotinue developmente i've just finish a MVP of LiterAlura.



How to Implement

1.- Install PostgreSQL
2.- Make sure you have those environment variables configured

DB_HOST
DB_NAME
DB_USER
DB_PASSWORD

3.- Create a database in Install PostgreSQL ( Just Create , DO NOT TOUCH anything else )
4.- Reload dependences
5.- Clic Run


What the program do?

Here we are 7 Option ( in spanish )

1 - Buscar Libro Por Titulo 
Get all the Books or Authors with a just a word. It looks for all coincidences and save it on the DB

2 - Listar Libros Registrados
Return all the Books Stored in DB

3 - Listar Autores por Nombre
Write an Author name for searching on the DB

4 - Listar Autores registrados
List all the Author on the DB

5 - Lista Autores vivos en un determinado año
Write a year and the program will check if the Author was alive at that year

6 - Lista Libros por idioma
Choose a Language and it will search all books on that language

0 - Salir
Exit
