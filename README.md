# Reversi
### Game setup
To install the game, simply open the .apk file and accept the permissions it requests you (used for multiplayer through Google Play Services API purposes). To run the game, open the app "Retro Reversi"

### Project setup
 ##### Android SDK Requirements
 - Google Play Services
 - Google Repository

To open the project, one must install Android Studio. The application was developed using gradle version 2.3.2 and SDK build tools version 25.0.3. If you wish to use different versions (maybe a future update), be sure the necessary build.gradle files are updated.

### User Manual


### UML Diagrams
##### Model Diagram
 [![Model](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/model.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/model.png?raw=true)
 
##### View & Presenter Diagram
 [![Model](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/view%20&%20presenter.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/view%20&%20presenter.png?raw=true)

##### State Diagrams
- App State Machine
[![AppStateMachine](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/app%20state.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/app%20state.png?raw=true)
- Game Logic State Machine
 [![GameStateMachine](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/game%20logic.png?raw=true)](https://github.com/cyrilico/LPOO1617_T1G7/blob/master/uml/game%20logic.png?raw=true)


### Design Patterns
- *Strategy* - An AI's moves are chosen through an algorithm. Different AI difficulties have different algorithms for the way it chooses a move
- *Model-View-Presenter* - To separate the components' representation from its logic and relations and facilitate unit testing
- *Memento* - Implement 'Undo last play' functionality (only on local gameplay)

#### Other notes
- "Other relevant design decisions"
- Relatively to the initial thought application structure, some changes were made throughout the development process but no big refactoring processes were thought to be necessary
- The Google Play Services API integration and online multiplayer, during the final part of development, were considered the hardest parts to implement due to the lack of previous knowledge in working with servers and application/data synchronization through multiple devices
- With this project we mainly learned the importance of a well thought architecture since the beginning of the development
- Overall, we spent roughly "cenas" hours developing the application


