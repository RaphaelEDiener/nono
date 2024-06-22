# Nonogram in Java

## Project Description

The goal is a generator and solver for so-called nonograms.

The numbers in each row or column describe the length of contiguous black areas 
in the respective row or column.
Black areas in the respective row or column.

A program is to be created with which you can generate nonograms yourself by
clicking on the respective pixels. 
Furthermore, it should be possible to obtain the numbers
for a nonogram and to try to find the solution yourself. 
In doing so time should be displayed 
and the number of clicks until the correct solution is found be counted.

The final submission should...    
- [ ] be tagged with "`Abgabe`".
- [ ] contain a development protocol in form of a pdf.
 
## Project Guidelines

- Project language is english.
- > 90% test coverage at all times
- Everything has to be documented in code
- No trowing of exceptions! Only returning!
- No side-effects are allowed, expect when handling IO.
- IO should be contained in a single file and not pollute throughout the project!
- Every `if` has an `else`.
  - Guard clauses are the exception to this rule
- No method should be called on any class. Only on interfaces.
- No `get`'ters or `set`'ters
- The documentation lives in `/docs`
- The code lives in `/src`
- The tests live in `/tests`
- The compilation output belongs into the ignored `/bin` folder.
- The documentation in external files or inside the `/docs` folder should be done with `Markdown` 
  and only for the submission be automatically transformed into a `PDF`.
- Ask one another for help instead of ChatGPT
- JDK version 21 or newer

