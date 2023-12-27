#JAVA ASSIGNMENT
# Java URL Database

This is a simple Java program that simulates a URL database using local variables. The program allows users to store URLs with unique short keys, retrieve short keys for given URLs, get the count of usage for a URL, list all URLs and counts in JSON format, and exit the program.

## How to Run

To start the program, run the following command in the command line:

java UrlDatabase

> storeurl google.com
URL stored with key: Key1

> get google.com
Key for URL google.com: Key1

> count google.com
Count for URL google.com: 1

> list
URLs and Counts (JSON format):
{ "Key1": {"url":"google.com","count":1} }

> exit
Exiting program...
