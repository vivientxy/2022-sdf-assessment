## SDF 2022 Assessment - Task 01

To run the code:
```
java -cp java.MailMerge.App <CSV file> <template file>
```

## Description

This code will take in 2 command line arguments:
1. CSV file, containing the header row which specifies the variables in each column
2. Template file, containing the email template with variables denoted by a prefix and suffix of "__" that corresponds with the variables listed in CSV file

This code will take the template from the template file, replace all variables with data listed in the CSV file, and print out the email to console.

Note that variable names in the CSV file and template file has to match exactly in order for the code to work.

## Java Version

This project runs on Java 21