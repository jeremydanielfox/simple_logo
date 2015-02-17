
#Slogo: Design Document
Team 13: Peter Moseley (plm17),  Jason Novack (jbn15), Jeremy Fox (jdf37), Nathan Prabhu (ncp9)
###Introduction:
Our team will be building a highly flexible version integrated development environment from which to run SLOGO code. Our goal is to create a product that will be open to future extensions. To do so, we will be adhering to the MVC framework, keeping the backend (model) and frontend (view) as separated as possible. Our code will support a variety of features and customizations, including multiple user languages, a display window of the turtle and lines drawn, previous commands, user defined variables, and more. Our program will be open to extension so that, if desired, one could add more and more components that are relevant to an IDE without changing the existing design of the program.

###Overview:
Check SLogo_Design.png for UML Document.
The program is separated into two main portions, frontend and backend. The frontend will handle displaying all necessary graphical resources and getting user input. The backend will handle interpreting user input and applying commands to the turtle. It will also keep an up to date list of user-made commands, variables, and previously used commands.

The program will not use a controller, so the model will be called directly from the view. However, there are very few points at which the back and front end have access to each other. The model will be initialized from the view at start-up, and will be subsequently called via an update function each time the user enters input. This update function with then go through the backend and return a list of objects to the frontend that contain data to be displayed. We will take advantage of binding in a couple of different places in order to streamline communication between the front and backend. Firstly, the view will use binding with the database in order to keep an accurate display, and allow editing, of the map of variables, user-defined commands, and previously entered commands. We will also take advantage of binding when creating the resourcebundle, so that the language can be changed easily. 

####Front-end:
The front end will contain a View class that will serve as the container class for the front end. The View will then contain a Display, which contains everything that is pictured on screen. The View will also contain a ViewManager class, which will manipulate front-end-only data, representing a smaller MVC separation on the front end. The Display class will contain all the visual components, which will be separated into the MenuBar, which will contain options and settings, the Feed, which will allow for text to be input and sent to the parser, and the workspace, which will contain all of the display objects relevant to be updated, such as the TurtleDisplay and the PromptHistory. Finally, the Feed will contain a copy of the Model so that it can send the text put in by the user to the model.


####Back-end:
The model will be called and given a string input each time the user enters new commands. This string input will be passed from the model to a parser. The parser will convert this string into a list of graphs, using the idea of an abstract syntax tree to structure the graphs, and then be passed into the interpreter. The parser will also add the original input string to the list of previously used commands. The interpreter will then go through each graph and when it reaches a command with proper parameters it will call the command using a visitor design pattern. The call to command will return an object with information needed to update the turtle and/or view. It may also be passed to the database in order to add a new variable or command. It will also be added to a list which will be passed back to the front end in order to update the interface.


###User Interface:
The primary user interface component of our design is the Feed, a text prompt through which the user can issue commands to control the turtle, draw lines, clear the screen, define commands, and create variables. The Feed will contain an enter button which will enter the text upon click. The Feed will also contain an add button that will allow the user to select additional strings to enter into the Feed, such as strings representing user defined variables and commands.

The component the user will communicate with next most is the menu bar, which will contain drop down menus entitled File, Edit, View, Options, and Help. Each of these menus will contain buttons the user can use.

Finally, the view will also contain a scrollable list of previously issued commands. Clicking on any of these commands will cause it to be sent immediately to the parser and then executed.


###Design Details:
Front-End:
View
Model myModel (Back-End)
Display myDisplay
MenuBar myMenu
File (Quit, Load/Save User Command Files to be done late)
Edit (Undo/Redo and Cut/Copy/Paste to be done later)
View (Choose Background Color, Choose Pen Color)
Options (Choose Language)
Help (Show Help)
Workspace myWorkspace //extendable to a list of workspaces to show different perspectives/environments
TurtleView myTurtleView
Group //Contains all turtle and other Node Objects on screen
addNode(), moveNode(), clear()
Feed myFeed //handles events on enter press or pressing the run button to send commands to the back-end
TextBox myTextBox
Button runButton
Button addFunctionButton
getText()
ScrollableList myHistory //list of clickable commands that can be run again
ScrollableList myVariables //list of clickable variables that can be editted in a pop up menu



**Model**
In a nutshell, the model takes in the input feed as a String "feed" and returns a List< TurtleData>. The process starts in the Model class. It calls updateModel on "feed." 

*Parser*
The "feed" is then sent to the Parser. The Parser has two jobs in its parse method. Firstly, it must translate all tokens in the feed to the singular key from the ResourceBundle. Secondly, it must create a list of syntax trees (in Graph form) for the interpreter. A syntax tree contains a set of commands, mathops, booleans, and iterators from the feed, organized into a traversable network. For each command in the feed, a separate syntax tree is made. For example, fd 50 rt 90 has two commands, and they would each get their own syntax tree. A syntax tree will always begin with a Commandable (to be discussed later).

The reason for creating this tree before computation is so that we can check for any errors in the feed beforehand. If errors are found, they will be thrown up the stack trace to a try/catch block in the view that will then create the appropriate error message in the GUI. This strategy increases efficiency by not unnecessarily executing commands in a feed that would eventually break. 

As syntax trees are being created, any user-defined variables or commands will be made into trees themselves, but they will not be sent to the interpreter. Instead they will be sent to the database to be called upon when needed. 

Also during syntax tree creation, when each syntax tree is being created, a list of Commandables is being created. Commandables represent any potential movement to the turtle, i.e. turtle commands or control structures. To clarify, only control structures starting a feed or called after a turtle command ends are stored - not a control structure within a command. Certain tokens in the feed will map to the appropriate Commandable to be added. For example, "forward" corresponds to a Translator(isPositive = true) and "left" corresponds to a Rotator(isClockwise = false). 

*Interpreter*
The Interpreter's interpret method traverses the syntax trees, with the intent to execute each corresponding command on the turtle. Once it has unravelled the required information, each Commandable will be left with its appropriate parameters. For example, the syntax tree of "forward sum 5 sum 20 5" would eventually return "forward 30" post-traversal. 

The returned Commandable and parameters combo are then used in a Visitor design pattern. Here Commandables represent Visitables, and they all have one Visitor, the Executor. By using the design pattern, we can call each Commandable (from our List< Commandable>) to accept the Executor, and the Executor will in turn visit each Commandable, invoking the appropriate logic of each. In the case of Translators and Rotators, for example, they will create new TurtleData objects that reflect the Turtle's updated position, heading, etc. These TurtleData objects are collected into a list, returned back to the interpreter, and eventually returned back to the view for the front-end to draw the turtle movements.

###API Example Code:
After the user types “fd 50” in the Feed and hits the enter button, an event listener in the feed triggers and sends the text to the View, which feeds it into the Model via an updateModel() method.

The model will send the feed string to parser via Parse(String), which will first add the string to the list of used commands; then it will create a list of Graphs (in this case just one Graph). It will pass this list to Interpreter via interpret(List< Graph>). The interpreter will then call visit(fd, [10]), which will return a TurtleData object  with information about what the turtle should do to accomplish the command. The object will then be added to a list and returned to the view.

Finally, a List of TurtleData objects is returned that contains the instructions necessary for each step of the turtle’s movement. This List will be fed into the ViewManager, which will use the methods in TurtleView to interpret each piece of TurtleData, adding Lines to the Scene and updating the coordinates of the Turtle that is in the scene, reflecting the changes on screen.


###Design Considerations:

The Parsing and Interpretation process had many alternatives. One idea was to use the interpreter pattern. This involved interpreting a String[] of tokens and defining many "startsWith" methods that would both recognize the commands and specify how many arguments it needed. The pro here was that it seemed simple enough on the parsing side, but having to define many "startsWith" methods seemed like a lot of if statements. Additionally, there would be a lot of recursion within the corresponding Expression class that seemed quite complex. There was definitely uncertainty if such a pattern would be robust enough to handle the SLogo syntax.

Another issue right now is how to deal with ResourceBundles. In the current design, only the Parser has access to the bundles since it will translate from the user-specified language back into the unique English keys corresponding to each token. However, the resource bundle will need to be accessed one more time when the GUI accesses the observable maps within the databases. The contents of the maps need to be retranslated into the appropriate language. It is undecided how and where this translation will occur.

###Team Responsibilities:
Front-end:
Jeremy: Implementing the front-end JavaFX features
Peter:  Implementing Turtle, helping with JavaFX, implementing errors

Back-end:
Nathan: Implementing the commands and visitor design pattern
Jason:  Implementing the parser and interpreter