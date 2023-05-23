# Table of content

● Introduction

● General Information

● Technologies

# Introduction

● Modify your code so that it:

○ Reads and writes data about projects and people associated with
projects from your database instead of text ﬁles. Your program
should not use any text ﬁles.
○ Capture information about new projects and add these to the
database.
○ Update information about existing projects.
○ Finalise existing projects. When a project is ﬁnalised the following
should happen:

■ An invoice should be generated for the client. This invoice
should contain the customer’s contact details and the total
amount that the customer must still pay. This amount is
calculated by subtracting the total amount paid to date from
the total fee for the project. If the customer has already paid
the full fee, an invoice should not be generated.

■ The project should be marked as “ﬁnalised” and the
completion date should be added.

○ Find all projects that still need to be completed from the database.
○ Find all projects that are past the due date from the database.
○ Find and select a project by entering either the project number or
project name.
● Besides meeting the above criteria, you should also do the following:

○ Include exception handling. Use try-catch blocks wherever
appropriate.
○ Remove all errors from your code. Take extra care to detect and
remove all logical and runtime errors.
○ Adequately refactor your code.
○ Document your code. Adhere to the style guide found here.
○ Use Javadoc to generate API documentation from documentation
comments for your program.
○ Follow the guidelines here to create a Readme ﬁle for this project.
● After receiving feedback from your mentor and improving your code
based on this feedback, add your program to GitHub.

# General Information

This project is a base version of what the final system will be able to do. In this program, the user will be asked for input to create an intial project object from which they will have the ability to make changes using a menu

# Technologies

● Java SE 18.0

● mySQL 8.0.30
