# Project Nonogram Documentation

## User Documentation

### TUI

### API

## Development Documentation

### Compiling from source

To compile from source use the script file with the format your operating system supports.

| OS      | File Name |
|---------|-----------|
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
- [Unicode list Wikipedia](https://en.wikipedia.org/wiki/List_of_Unicode_characters)
- [HTTP Specification](https://www.rfc-editor.org/rfc/rfc9110.html)
- [htmx docs]()

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

After some more experimentation, I conclude that this sadly wasn't possible.
Java has no sane way of interacting with the operating system or the console.
If you want to go beyond simple log dumping, you will be bitterly disappointed.

I tried getting input from stdin and giving output,
but the encoding creates more trouble than it's worth.

Surely I could've spend more time on it and I would have learned more about java and encodings.
But I want to finish this project.

After getting the ok from Mr. Didas I've tried working with a library as an intermediary.
JCurses didn't work and was not maintained.
That was my last try.

#### Web is the way

What do you do when dependencies ain't working? Throw more dependencies at it!
Based on that moto, I've included the browser as the dependency.
Java offers a full http client implementation out of the box, 
but I don't want to read tons of more docs.
I've already read enough for this project.
So I'm using the web-sockets (also included) to make it from scratch.
This enables me to also support alternative modes of connecting, 
where the client can decide what format my response has.

If I am very bored or want another shot at console apps, I can reuse the server now.
But the primary target is the browser.

To my surprise, working with HTTP was quite enjoyable.
No corner cases or anything.
Just straight text exchanged between server and client.

As for the interactivity: It's HTMX!
I don't want or need much more than the element replacement logic upon keypress.
Maybe I'll strip the js file to a limited version which only supports that.
But that's a problem for future me/us.

I also don't take advantage of the more rich rendering abilities of the browser.
It's still just a single character stream.
Another thing for when I have too much free time.

I've also cut some corners.
The current data model is not to my liking.
I wiped it out quickly, but that means it is quite stateful.
I'm sure I can make it more fluent if I think about it,
but that's to much work to bother right now.
Same for the granularity of control when creating the html.
It's just exactly what we need.
Definitely not pretty.

Yeah.
Logic comes next.

But before logic, I've wanted to make the little server "complete".
Currently it just spins out when getting a request it can't handle.
So I've introduced some defaults for the Server class which now can
handle favicon and 404 requests.

#### Game Design

We've decided on the following game states:

- Main menu
- Solving
- Creating

#### Remarks on terse code

The java collections library is as terrible as I've remembered.
Most of the "game" can be reduced (pun intended) to a simple map-filter-reduce sequence.
Reading and writing from disc?
-> Read, map, done or map, write, done respectively.
Checking for solved?
-> Pre-scan, split, max-reduce, done.
Yet even the most basic of operations for streaming/mapping/filtering is not implemented for
the vast number of types inside java.
This leads to the increased reliance of slow and eager for loops instead of lazy fast iterators.

Java is even after 30 years of existence in a worse state of usability 
and less coherent, than the much younger Ocaml/Zig/Gleam.
What a shame.



