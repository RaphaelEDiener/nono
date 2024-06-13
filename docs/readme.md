# Project Nonogram Documentation

## User Documentation

### TUI

### API

## Development Documentation

### Compiling from source

To compile from source use the script file with the format your operating system supports.

| OS      | File Name |
| ------- | --------- |
| Linux   | `run.sh`  |
| Windows | `run.bat` |

These scripts compile the project.
In case you are getting compilation errors, check that your JDK version is up-to-date.
The version for this project is `21`.

The scripts will compile and run the program.
Afterwards you will find the `.jar` file inside the projects root directory.

### Sources used during Development

- [Oracle Java documentation](https://docs.oracle.com/en/java/javase/22/)
- [Ansi codes](https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797)

### Design

#### Console support

According to the java documentation,
there are no ways of interacting with the operating system
or any environment for that matter without using libraries.
I want to keep the project free of libraries.
Luckily imediate/canonical mode exists.
So before starting the application, 
we can need to set the mode to canonical: `stty -icanon`.
Problem: windows command prompt.
Also, I don't like the fact that the user has to set this up.
Ideally the application would do so on startup.
BUT java is not able to do actually usefull programming.
No native of operating system depend functions anywhere.

So three options in order of increasing workload:
- User does `stty -icanon`
- Java FFI
- Creating a terminal from scratch

The options increase in resource usage on the users pc,
as well as in effort for this project.
I want to ceep it small with having a potential switch in mind.

For the moment we'll go with option 1.
